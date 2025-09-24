package by.it_academy.service.userService.api;

import by.it_academy.repository.entity.UserEntity;

public interface IJwtService {
       String generateToken(UserEntity user);
}
