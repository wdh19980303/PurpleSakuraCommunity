package purple.sakura.community.pojo;


import lombok.Data;

@Data
public class GitHubAccessToken {
    private String client_id;
    private String code;
    private String client_secret;
    private String redirect_url;
    private String state;
}
