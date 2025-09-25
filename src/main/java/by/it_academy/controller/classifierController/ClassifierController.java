package by.it_academy.controller.classifierController;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.classifierDto.Currency;
import by.it_academy.dto.classifierDto.OperationCategory;
import by.it_academy.service.classifierService.ClassifierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier")
@AllArgsConstructor
public class ClassifierController {

    public final ClassifierService classifierService;

    @PreAuthorize("hasRole('ADMIN','MANAGER')")
    @PostMapping("/currency")
    public ResponseEntity<String> addCurrency(@RequestBody Currency currency) {
        classifierService.createCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/currency")
    public ResponseEntity<PageOf<Currency>> getAllCurrency(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.readAllCurrency(pageable));
    }
    @PreAuthorize("hasRole('ADMIN','MANAGER')")
    @PostMapping("/operation/category")
    public ResponseEntity<String> addCategory(@RequestBody OperationCategory operationCategory) {
        classifierService.createOperationCategory(operationCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/operation/category")
    public ResponseEntity<PageOf<OperationCategory>> getAllOperationCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(classifierService
                .readAllOperationCategory(pageable));
    }


}
