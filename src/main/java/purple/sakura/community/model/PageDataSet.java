package purple.sakura.community.model;

import lombok.Data;

import java.util.List;

@Data
public class PageDataSet <T> {
    private long total;
    private int currentPage;
    private int pages;
    private int pageSize;
    private List<T> pageList;




}
