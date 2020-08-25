package purple.sakura.community.mapper;

import org.springframework.stereotype.Repository;
import purple.sakura.community.model.Article;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleMapper extends Mapper<Article> {
}
