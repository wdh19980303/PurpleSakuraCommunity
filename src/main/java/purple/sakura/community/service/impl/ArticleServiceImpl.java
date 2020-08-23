package purple.sakura.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import purple.sakura.community.mapper.ArticleMapper;
import purple.sakura.community.pojo.Article;
import purple.sakura.community.service.ArticleService;

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
           return false;
        }


        return  i == 1;

    }
}
