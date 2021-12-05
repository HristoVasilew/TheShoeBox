package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoeBoxUserDetailsTest {

    private UserEntity testUser;
    private UserRoleEntity adminRole, userRole;

    private ShoeBoxUserDetails serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {

        serviceToTest = new ShoeBoxUserDetails(mockUserRepository);

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        testUser = new UserEntity();
        testUser.setUsername("pesho");
        testUser.setEmail("pesho@peso.com");
        testUser.setPassword("11111");
        testUser.setRoles(Set.of(adminRole, userRole));

    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid_username")
        );
    }


    @Test
    void testUserFound() {

        Mockito.when(mockUserRepository.findByEmail(testUser.getEmail())).
                thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getEmail());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(), testUser.getEmail());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }


}