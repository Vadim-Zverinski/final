package by.it_academy.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class PageOf<T>{
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private List<T> content;
}

