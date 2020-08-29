package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import purple.sakura.community.mapper.ArticleMapper;
import purple.sakura.community.model.Article;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.model.Message;
import purple.sakura.community.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;


    @RequestMapping("/publish")
    public String publish( ModelAndView modelAndView) {
        return "publish";
    }


    @RequestMapping("/pushArticle")
    @ResponseBody
    public Message<Article> save(Article article, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println(article);
        // 获取user数据
        CommunityUser user = (CommunityUser) req.getSession().getAttribute("user");

        // 返回数据
        Message<Article> message = new Message<>();
        message.setModel(article);

        if (user == null) {
            message.setError("用户登录超时");
            return message;
        }

        if (articleService.titleIsExist(article.getTitle())) {
            message.setError("文章名已经存在");
            return message;
        }


        article.setGmtCreate(new Date());
        article.setGmtModify(new Date());
        article.setCreator(user.getId());


        boolean b = articleService.saveNewArticleService(article);
        System.out.println(b);

        message.setFlag(true);
        message.setError("发布成功");

        req.getRequestDispatcher("/").forward(req,res);
        return message;
    }




}

