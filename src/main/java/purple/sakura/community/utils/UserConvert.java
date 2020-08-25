package purple.sakura.community.utils;

import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.model.GitHubUser;

public class UserConvert {


    public static CommunityUser gitHubUserToCommunityUser(GitHubUser gitHubUser) {
        CommunityUser communityUser = new CommunityUser();
        communityUser.setAccountId(gitHubUser.getId());
        communityUser.setUsername(gitHubUser.getName());
        communityUser.setDio(gitHubUser.getDio());
        communityUser.setAvatarUrl(gitHubUser.getAvatarUrl());
        return communityUser;

    }

    public static GitHubUser communityToGitHubUser(CommunityUser communityUser) {
        GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setId(communityUser.getAccountId());
        gitHubUser.setName(communityUser.getUsername());
        gitHubUser.setDio(communityUser.getDio());
        gitHubUser.setAvatarUrl(communityUser.getAvatarUrl());
        return gitHubUser;
    }
}
