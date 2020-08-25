package purple.sakura.community.service;

import purple.sakura.community.model.CommunityUser;

public interface CommunityUserService {
    public boolean userIsExistByAccountID(String accountId);

    public boolean save(CommunityUser communityUser);

    public CommunityUser selectByToken(String token);

    public CommunityUser selectByAccountID(String accountId);

    public void updateByIdSelect(CommunityUser communityUser);

    public boolean userIsExist(CommunityUser communityUser);

    public CommunityUser selectById(Integer Id);
}
