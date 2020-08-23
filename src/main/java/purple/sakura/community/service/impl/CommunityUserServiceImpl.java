package purple.sakura.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purple.sakura.community.mapper.CommunityUserMapper;
import purple.sakura.community.pojo.CommunityUser;
import purple.sakura.community.service.CommunityUserService;

@Service
@Transactional
public class CommunityUserServiceImpl implements CommunityUserService {

    @Autowired
    private CommunityUserMapper communityUserMapper;


    @Override
    public boolean userIsExist(CommunityUser communityUser) {
        return communityUserMapper.selectOne(communityUser) != null;
    }

    @Override
    public boolean save(CommunityUser communityUser) {

        return communityUserMapper.insertSelective(communityUser) == 1;

    }

    @Override
    public CommunityUser selectByToken(String token) {
        CommunityUser communityUser = new CommunityUser();
        communityUser.setToken(token);
        return communityUserMapper.selectOne(communityUser);
    }
}
