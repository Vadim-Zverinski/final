package by.it_academy.service.userService.api;

import by.it_academy.repository.userRepository.entity.UserEntity;

public interface IJwtService {
       String generateToken(UserEntity user);
}
