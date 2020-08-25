package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.model.GitHubAccessToken;
import purple.sakura.community.model.GitHubUser;
import purple.sakura.community.provider.GithubProvider;
import purple.sakura.community.service.CommunityUserService;
import purple.sakura.community.utils.UserConvert;

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
        System.out.println(user.getAvatarUrl());

        if (user != null) {
            // 登录成功
            CommunityUser loginCommunityUser = UserConvert.gitHubUserToCommunityUser(user);
            CommunityUser oldCommunityUser = new CommunityUser();

            // 如果用户不存在, 存储用户到数据库
            if (!communityUserService.userIsExistByAccountID(user.getId())) {

                loginCommunityUser.setToken(accessToken);
                loginCommunityUser.setGmtCreate(new Date());
                loginCommunityUser.setGmtModified(new Date());
                communityUserService.save(loginCommunityUser);
            } else {
                // 更新用户信息
                oldCommunityUser = communityUserService.selectByAccountID(user.getId());

                // 登录用户信息不包含存储用户的Id, 需要对老用户信息进行更新的话需要把老用户id设置到新用户中,然后更新
                loginCommunityUser.setId(oldCommunityUser.getId());

                // 把token和用户时间设置到里面
                loginCommunityUser.setToken(accessToken);
                loginCommunityUser.setGmtModified(new Date());


                communityUserService.updateByIdSelect(loginCommunityUser);
            }


            model.addAttribute("user", loginCommunityUser);

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
