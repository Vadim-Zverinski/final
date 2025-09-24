package by.it_academy.dto;

import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserCreate {
  private   UUID uuid;
  private  long dtCreate;
  private  long dtUpdate;
  private  String mail;
  private  String fio;
  private  UserRole role;
  private  UserStatus status;
  private  String password;
}
