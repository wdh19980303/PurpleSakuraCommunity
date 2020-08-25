package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import purple.sakura.community.mapper.ArticleMapper;
import purple.sakura.community.model.Article;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;


    @RequestMapping("/publish")
    public String publish( ModelAndView modelAndView) {
        return "publish";
    }


    @RequestMapping("/pushArticle")
    public ModelAndView save(Article article, HttpServletRequest request, ModelAndView modelAndView) throws ServletException, IOException {

        // 先把文章存入request
        modelAndView.addObject("article", article);


        // 获取user数据
        CommunityUser user = (CommunityUser) request.getSession().getAttribute("user");


        if (user == null) {
            modelAndView.setViewName("publish");
            modelAndView.addObject("error", "用户超时,轻重新登录");
            return modelAndView;
        }

        if (articleService.titleIsExist(article.getTitle())) {
            modelAndView.setViewName("publish");
            modelAndView.addObject("error", "文章标题重复");
            return modelAndView;
        }


        article.setGmtCreate(new Date());
        article.setGmtModify(new Date());
        article.setCreator(user.getId());


        boolean b = articleService.saveNewArticleService(article);
        System.out.println(b);
        modelAndView.setViewName("index");
        return modelAndView;


    }

}

