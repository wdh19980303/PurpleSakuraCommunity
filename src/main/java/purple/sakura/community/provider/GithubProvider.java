package purple.sakura.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import purple.sakura.community.pojo.GitHubAccessToken;
import purple.sakura.community.pojo.GitHubUser;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(GitHubAccessToken gitHubAccessToken) throws IOException {
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(gitHubAccessToken));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            /* 拆分获取token */
            /**
             * string 原型 : access_token=d157e117412e5c448835f630ed90b55051b23404&expires_in=28800&refresh_token=r1.0d1b4d7079c05860f95c73839fdae50eb6ddf1eb97493799c0ab5c0524a6dddbf9cf195cba44964a&refresh_token_expires_in=15897600&scope=&token_type=bearer
             */

            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {

        }

        return null;
    }

    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            // json解析
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    @ConfigurationProperties(prefix = "github")
    public GitHubAccessToken getGitHubAccessToken() {
        return new GitHubAccessToken();
    }


}
