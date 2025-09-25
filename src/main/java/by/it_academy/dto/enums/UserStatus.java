package by.it_academy.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    WAITING_ACTIVATION,
    ACTIVE,
    DEACTIVATED
}
