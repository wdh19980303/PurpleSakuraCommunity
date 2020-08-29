package purple.sakura.community.service;

import com.github.pagehelper.Page;
import purple.sakura.community.model.Article;

import java.util.List;

public interface ArticleService {
    public boolean saveNewArticleService(Article article);

    public boolean titleIsExist(String title);

    public List<Article> findAll();

    public Page<Article> pagination(Integer currentPage, Integer pageSize, Integer creator);

    public Article findById(Integer articleId);
}
