package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import purple.sakura.community.pojo.CommunityUser;
import purple.sakura.community.pojo.GitHubUser;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private CommunityUserService communityUserService;


    @RequestMapping("/")
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

                GitHubUser user = new GitHubUser();
                user.setName(communityUser.getUsername());
                user.setId(communityUser.getAccountId());

                request.getSession().setAttribute("user", user);
            }
        }

        return "index";
    }


}
