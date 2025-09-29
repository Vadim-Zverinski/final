package by.it_academy.filter.config;


import by.it_academy.repository.userRepository.api.UserRepository;
import by.it_academy.repository.userRepository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + mail));
        return new UserDetailsImpl(user);
    }
}
