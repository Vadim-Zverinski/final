package by.it_academy.service.userService.api;

import by.it_academy.repository.userRepository.entity.UserEntity;

import java.util.UUID;

public interface IJwtService {
       String generateToken(UserEntity user);
       UUID extractUuid(String token);
}
