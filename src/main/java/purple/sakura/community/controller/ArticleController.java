package purple.sakura.community.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import purple.sakura.community.model.*;
import purple.sakura.community.service.ArticleService;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list/{currentPage}/{pageSize}/{creator}")
    @ResponseBody
    public PageDataSet<Article> indexList(
            @PathVariable("currentPage") int currentPage,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("creator") int creator,
            HttpServletRequest request
    ) {

        if (currentPage == 0) {
            currentPage = 1;
        }


        if (pageSize == 0) {
            pageSize = 2;
        }

        PageDataSet<Article> pageDataSet = new PageDataSet<>();
        Page<Article> page = new Page<>();
        if (creator == 0) {
            page = articleService.pagination(currentPage, pageSize, 0);
        }

        if (creator == -1) {
            CommunityUser user = (CommunityUser) request.getSession().getAttribute("user");
            if (user == null) {
                page = articleService.pagination(currentPage, pageSize, -1);
            } else {
                page = articleService.pagination(currentPage, pageSize, user.getId());
            }


        }


        pageDataSet.setPageList(page);
        pageDataSet.setTotal(page.getTotal());
        pageDataSet.setCurrentPage(page.getPageNum());
        pageDataSet.setPages((page.getPages()));
        pageDataSet.setPageSize(page.getPageSize());

        return pageDataSet;


    }

    @GetMapping(value = "/creatorImg/{creator}", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String articleImg(@PathVariable("creator") Integer creator) {
        CommunityUser communityUser = communityUserService.selectById(creator);
        return communityUser.getAvatarUrl();
    }


    /*文章*/
    @GetMapping("/complex/{articleId}")
    @ResponseBody
    public Message<Object> articleComplex(
            @PathVariable(name = "articleId") Integer articleId

    ) {
        /*查询结果*/
        Article article= articleService.findById(articleId);
        CommunityUser communityUser = communityUserService.selectById(article.getCreator());

        /*取消token的封装*/
        communityUser.setToken("#");

        /*关系建立*/
        Relationship<Article, CommunityUser> relationship = new Relationship<>();
        relationship.setPrimary(article);
        relationship.setSecondary(communityUser);


        /*消息封装*/
        Message<Object> message = new Message<>();
        message.setModel(relationship);
        return message;

    }



}
