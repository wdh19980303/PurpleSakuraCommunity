package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.service.ArticleService;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/")
    public String index(HttpServletRequest request) {

       /* Cookie[] cookies = request.getCookies();
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
        }*/

        return "index";
    }

    @GetMapping("/loginUser")
    @ResponseBody
    public CommunityUser isLogin(HttpServletRequest request) {
        CommunityUser user = (CommunityUser) request.getSession().getAttribute("user");
        return user;
    }



}
