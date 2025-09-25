package by.it_academy.service.userService.mapper;

import by.it_academy.dto.userDto.UserCreate;
import by.it_academy.dto.userDto.UserRegistration;
import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import by.it_academy.repository.userRepository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Instant.class, UserRole.class, UserStatus.class})
public interface UserMapper {

    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "dtCreate", expression = "java(Instant.now().toEpochMilli())")
    @Mapping(target = "dtUpdate", expression = "java(Instant.now().toEpochMilli())")
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "status", constant = "WAITING_ACTIVATION")
    UserCreate registrationToCreate(UserRegistration registration);


    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "dtCreate", source = "time")
    @Mapping(target = "dtUpdate", source = "time")
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "status", constant = "WAITING_ACTIVATION")
    UserEntity userCreateToEntity(UserCreate dto, UUID uuid, long time);


    UserCreate toDto(UserEntity entity);

    by.it_academy.dto.userDto.User toUserDto(UserEntity entity);

    default UserDetails toUserDetails(UserEntity user) {
        if (user == null) {
            return null;
        }

        List<SimpleGrantedAuthority> authorities = getAuthorities(user);
        boolean disabled = user.getStatus() == null || user.getStatus() != UserStatus.ACTIVE;
        return User.builder()
                .username(user.getMail())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(disabled)
                .build();
    }

    default List<SimpleGrantedAuthority> getAuthorities(UserEntity user) {
        if (user == null || user.getRole() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
}
