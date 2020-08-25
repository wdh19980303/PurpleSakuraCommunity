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
@Table(name = "user")
public class CommunityUser {


    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String accountId;

    private String username;

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtModified;

    private String dio;

    private String avatarUrl;






}
