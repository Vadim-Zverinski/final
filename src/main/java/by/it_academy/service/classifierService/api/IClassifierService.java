package by.it_academy.service.classifierService.api;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.classifierDto.Currency;
import by.it_academy.dto.classifierDto.OperationCategory;
import by.it_academy.dto.userDto.UserCreate;
import org.springframework.data.domain.Pageable;

public interface IClassifierService {

   void createCurrency(Currency currency);
   void createOperationCategory(OperationCategory operationCategory);
   PageOf<Currency> readAllCurrency(Pageable pageable);
   PageOf<OperationCategory> readAllOperationCategory(Pageable pageable);
}
