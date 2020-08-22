package purple.sakura.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import purple.sakura.community.config.ChinaTimeFormat;
import purple.sakura.community.pojo.GitHubAccessToken;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    GitHubAccessToken accessToken;


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

}
