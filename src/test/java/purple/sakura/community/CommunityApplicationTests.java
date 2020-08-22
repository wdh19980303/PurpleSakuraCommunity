package purple.sakura.community;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import purple.sakura.community.config.ChinaTimeFormat;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {


    }

    @Test
    public void test01() throws Exception {
        ChinaTimeFormat chinaTimeFormat = new ChinaTimeFormat();
        System.out.println(chinaTimeFormat.format(new Date()));

    }

}
