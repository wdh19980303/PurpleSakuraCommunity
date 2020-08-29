package purple.sakura.community.model;


import lombok.Data;

@Data
public class Relationship<T,E> {
    private T primary;
    private E secondary;
}
