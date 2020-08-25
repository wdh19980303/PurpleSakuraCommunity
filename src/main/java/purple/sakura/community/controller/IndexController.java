package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import purple.sakura.community.model.Article;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.service.ArticleService;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String token = null;
        // 寻找cookie
        if(cookies == null) {
            return "index";
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        }

        if( token != null) {
            CommunityUser communityUser = new CommunityUser();
            communityUser.setToken(token);

            if (communityUserService.userIsExist(communityUser)) {
                communityUser = communityUserService.selectByToken(token);


                request.getSession().setAttribute("user", communityUser);
            }
        }

        return "index";
    }


    @GetMapping("/indexList")
    @ResponseBody
    public List<Article> indexList() {

        return articleService.findAll();
    }

    @GetMapping(value = "/articleImg/{creator}",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String articleImg(@PathVariable("creator") Integer creator) {
        CommunityUser communityUser = communityUserService.selectById( creator);
        System.out.println("++++++++++++++++++++++++");
        System.out.println(communityUser.getAvatarUrl());
        return communityUser.getAvatarUrl();
    }


}
