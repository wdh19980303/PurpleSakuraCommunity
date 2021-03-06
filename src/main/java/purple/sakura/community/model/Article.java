package purple.sakura.community.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "article")
public class Article {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private String description;
    private String introduction;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtModify;

    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;

}
