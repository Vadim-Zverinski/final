package by.it_academy.util.api;

import by.it_academy.dto.PageOf;
import org.springframework.data.domain.Page;

public interface IPager {
    <T> PageOf<T> getAll(Page<T> page);
}
