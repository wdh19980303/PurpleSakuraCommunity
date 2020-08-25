package purple.sakura.community.service;

import purple.sakura.community.model.Article;

import java.util.List;

public interface ArticleService {
    public boolean saveNewArticleService(Article article);

    public boolean titleIsExist(String title);

    public List<Article>  findAll();
}
