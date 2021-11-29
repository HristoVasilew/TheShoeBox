package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.service.UserServiceModel;

import java.util.Optional;

public interface UserEntityService {
    void registerUser(UserServiceModel userServiceModel);

    void initUsers();

    UserServiceModel findById(Long id);

    Optional<UserEntity> findUserByEmail(String username);

    UserServiceModel findByEmail(String email);

}
