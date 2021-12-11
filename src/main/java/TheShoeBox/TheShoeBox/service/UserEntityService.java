package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.service.UserServiceModel;
import TheShoeBox.TheShoeBox.model.view.AdminPanelUserViewModel;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;

import java.util.List;

public interface UserEntityService {
    void registerUser(UserServiceModel userServiceModel);

    void initUsers();

    UserServiceModel findById(Long id);

    UserProfileView findUserByEmail(String username);

    UserServiceModel findByEmail(String email);

    List<UserViewModel> findAllUsers();

    void deleteUserById(Long id);

    void makeUserAdmin(Long id);

    void makeAdminUser(Long id);

    boolean checkEmail(String email);

    boolean isUsernameFree(String username);

    List<AdminPanelUserViewModel> getAllUsersByFetch(Long id);

    void editProfile(UserServiceModel model, Long idUserEdited);
}
