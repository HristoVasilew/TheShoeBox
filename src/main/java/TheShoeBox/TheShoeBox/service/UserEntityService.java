package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.service.UserRegisterServiceModel;

import java.io.IOException;

public interface UserEntityService {
    void registerUser(UserRegisterServiceModel userRegisterServiceModel) throws IOException;
    void initUsers();

}
