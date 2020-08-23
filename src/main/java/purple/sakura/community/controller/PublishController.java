package purple.sakura.community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import purple.sakura.community.mapper.ArticleMapper;
import purple.sakura.community.pojo.Article;
import purple.sakura.community.service.ArticleService;

import java.util.Date;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;


    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }


    @RequestMapping("/pushArticle")
    @ResponseBody
    public String save(Article article) {
        System.out.println(article);
        article.setGmtCreate(new Date());
        article.setGmtModify(new Date());
        article.setCreator(2);


        boolean b = articleService.saveNewArticleService(article);
        System.out.println(b);
        return "successful";
    }

    @RequestMapping(value = "/test" )
    @ResponseBody
    public String test() throws Exception {
        String s = null;
        List<Article> articles = articleMapper.selectAll();
        for (Article article : articles) {
            System.out.println(article);
            ObjectMapper objectMapper = new ObjectMapper();
            s = objectMapper.writeValueAsString(articles);

        }

        return s;
    }
}
