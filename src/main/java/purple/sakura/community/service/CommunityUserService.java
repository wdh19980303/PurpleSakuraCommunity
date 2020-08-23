package purple.sakura.community.service;

import purple.sakura.community.pojo.CommunityUser;

public interface CommunityUserService {
    public boolean userIsExist(CommunityUser communityUser);

    public boolean save(CommunityUser communityUser);

    public CommunityUser selectByToken(String token);
}
