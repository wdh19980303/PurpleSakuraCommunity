package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import purple.sakura.community.pojo.CommunityUser;
import purple.sakura.community.pojo.GitHubAccessToken;
import purple.sakura.community.pojo.GitHubUser;
import purple.sakura.community.provider.GithubProvider;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@SessionAttributes("user")
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GitHubAccessToken gitHubAccessToken;

    @Autowired
    private CommunityUserService communityUserService;


    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           Model model,
                           HttpServletResponse response
    ) throws IOException {

        gitHubAccessToken.setCode(code);

        gitHubAccessToken.setState(state);

        String accessToken = githubProvider.getAccessToken(gitHubAccessToken);

        GitHubUser user = githubProvider.getUser(accessToken);


        if (user != null) {
            // 登录成功
            model.addAttribute("user", user);

            CommunityUser communityUser = new CommunityUser();
            communityUser.setAccountId(user.getId());


            // 如果用户不存在, 存储用户到数据库
            if ( !communityUserService.userIsExist(communityUser)) {

                communityUser.setUsername(user.getName());
                communityUser.setToken(accessToken);
                communityUser.setGmtCreate(new Date());
                communityUser.setGmtModified(new Date());
                communityUserService.save(communityUser);
            }

            // 写入cookie 实现登录
            response.addCookie(new Cookie("token", accessToken));



            // 重定向回主页
            return "redirect:/";
        } else {
            // 登录失败
            return "redirect:/";

        }

    }

}
