package purple.sakura.community.mapper;

import org.springframework.stereotype.Repository;
import purple.sakura.community.pojo.CommunityUser;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommunityUserMapper extends Mapper<CommunityUser> {

//    public CommunityUser selectByAccount_id(String account_id);

}
