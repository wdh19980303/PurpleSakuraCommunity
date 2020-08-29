package purple.sakura.community.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purple.sakura.community.mapper.ArticleMapper;
import purple.sakura.community.model.Article;
import purple.sakura.community.service.ArticleService;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public boolean saveNewArticleService(Article article) {
        int i = 0;
        try {
             i = articleMapper.insertSelective(article);
        } catch (Exception e) {
            e.printStackTrace();
           return false;
        }


        return  i == 1;

    }

    @Override
    public boolean titleIsExist(String title) {
       Article article = new Article();
       article.setTitle(title);
        try {
            if( articleMapper.selectOne(article) != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public Page<Article> pagination(Integer currentPage, Integer pageSize, Integer creator) {
        Page<Article> articlePageSet = PageHelper.startPage(currentPage, pageSize);
        if(creator == 0) {
            articleMapper.selectAll();
        }
        else {
            Article article = new Article();
            article.setCreator(creator);
            articleMapper.select(article);
        }
        return articlePageSet;
    }

    @Override
    public Article findById(Integer articleId) {

        return articleMapper.selectByPrimaryKey(articleId);
    }
}
