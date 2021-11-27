package TheShoeBox.TheShoeBox.service.impl;


import java.util.Set;
import java.util.stream.Collectors;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ShoeBoxUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public ShoeBoxUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var userEntity = userRepository.
                findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found."));

        return map(userEntity);
    }

    private UserDetails map(UserEntity user) {
        Set<GrantedAuthority> grantedAuthorities =
                user.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toUnmodifiableSet());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}