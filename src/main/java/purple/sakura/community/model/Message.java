package purple.sakura.community.model;

import lombok.Data;

@Data
public class Message<T> {

    private String message ;
    private String error;
    private boolean flag = false;
    private T model;
}
