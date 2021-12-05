package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;


    @Test
    public void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("abv@abv.abv");
        userEntity.setUsername("abvv");
        userEntity.setPassword("abvv");
        userEntity.setRoles(Set.of(new UserRoleEntity().setRole(UserRoleEnum.USER)))
                .setFirstName("shosho")
                .setLastName("shosho");

        UserEntity save = userRepository.save(userEntity);

        assertTrue(save.getId() > 0);
    }

    @Test
    public void findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(System.out::println);
    }

    @Test
    public void findUserByEmail() {
        Optional<UserEntity> userEntity = userRepository.findByEmail("asda@as.com");
        assertNotNull(userEntity);
    }

    @Test
    public void findUserByUsername() {
        Optional<UserEntity> userEntity = userRepository.findByUsername("asda@as.com");
        assertNotNull(userEntity);
    }

    @Test
    public void findUserById() {
        Optional<UserEntity> userEntity = userRepository.findById(1L);
        assertNotNull(userEntity);

    }

    @Test
    public void deleteUser(){
        Optional<UserEntity> userEntity = userRepository.findById(1L);
        assertNotNull(userEntity);
        //userRepository.deleteById(userEntity.get().getId());
    }

}