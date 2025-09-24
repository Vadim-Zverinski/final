package by.it_academy.util;

import by.it_academy.dto.PageOf;
import by.it_academy.util.api.IPager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class Pager implements IPager {

    public <T> PageOf<T> getAll(Page<T> page) {
        return PageOf.<T>builder()
                .number(page.getNumber())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .numberOfElements(page.getNumberOfElements())
                .last(page.isLast())
                .content(page.getContent())
                .build();
    }

}
