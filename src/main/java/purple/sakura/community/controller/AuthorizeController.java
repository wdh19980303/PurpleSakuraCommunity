package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import purple.sakura.community.pojo.GitHubAccessToken;
import purple.sakura.community.pojo.GitHubUser;
import purple.sakura.community.provider.GithubProvider;

import java.io.IOException;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GitHubAccessToken gitHubAccessToken;



    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state
    ) throws IOException {

        gitHubAccessToken.setCode(code);
        gitHubAccessToken.setState(state);

        String accessToken = githubProvider.getAccessToken(gitHubAccessToken);
        GitHubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }

}
