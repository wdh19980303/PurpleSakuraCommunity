package purple.sakura.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import purple.sakura.community.config.ChinaTimeFormat;
import purple.sakura.community.mapper.CommunityUserMapper;
import purple.sakura.community.model.Article;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.model.GitHubAccessToken;
import purple.sakura.community.service.ArticleService;
import purple.sakura.community.service.CommunityUserService;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    GitHubAccessToken accessToken;

    @Autowired
    DataSource dataSource;

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private ArticleService articleService;



    @Autowired
    CommunityUserMapper communityUserMapper;

    CommunityUser communityUser = new CommunityUser();

    @Test
    void contextLoads() {


    }

    @Test
    public void test01() throws Exception {
        ChinaTimeFormat chinaTimeFormat = new ChinaTimeFormat();
        System.out.println(chinaTimeFormat.format(new Date()));

    }


    @Test
    public void test02() throws Exception {
        System.out.println(accessToken);

    }


    @Test
    public void test03() throws Exception {
        System.out.println(dataSource);
    }

    @Test
    public void test05() throws Exception {
        Integer id = 1;
        CommunityUser communityUser = communityUserMapper.selectByPrimaryKey(id);
        System.out.println(communityUser);
        System.out.println("++++++++++++++++++++++++++++++++");

        List<CommunityUser> communityUsers = communityUserMapper.selectAll();

        System.out.println(communityUsers);
    }


    @Test
    public void test06() throws Exception {
        String accountId = "88888";
        communityUser.setAccountId(accountId);
        CommunityUser user = communityUserMapper.selectOne(this.communityUser);
        if(user == null) {

        }
        System.out.println(user);
    }

    @Test
    public void test07() throws Exception {

        CommunityUser communityUser = communityUserService.selectByToken("07485fdd733cd4360a47faac98230e82cb03a045");
        System.out.println(communityUser);
    }


    @Test
    public void test08() throws Exception {
        Article article = new Article();
        article.setGmtModify(new Date());
        article.setGmtCreate(new Date());
        article.setTitle("总之就是非常可爱");
        article.setCreator(2);
        boolean b = articleService.saveNewArticleService(article);
        System.out.println(b);
        System.out.println("++++++++++++++++++++");

    }

}
