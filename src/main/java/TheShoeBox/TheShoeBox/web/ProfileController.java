package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProfileController {
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    public ProfileController(ModelMapper modelMapper, UserEntityService userEntityService) {
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
    }


    //@GetMapping("/users/profile")
//    public String profile() {
//        return "profile";
//    }

//    @GetMapping("/profile/{id}")
//    private String profile(@PathVariable Long id, Model model){
//
//        model
//                .addAttribute("user", modelMapper
//                        .map(userEntityService.findById(id), UserViewModel.class));
//
//        return "profile";
//    }

    @GetMapping("/profile/edit-profile")
    public String editProfile(Model model){
        Optional<UserEntity> userEntity = getCurrentUser();

        UserProfileView user = new UserProfileView();
        user.setFirstname(userEntity.get().getFirstName());
        user.setLastname(userEntity.get().getLastName());
        user.setEmail(userEntity.get().getEmail());
        user.setRoles(userEntity.get().getRoles());
        user.setUsername(userEntity.get().getUsername());
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model){

        Optional<UserEntity> userEntity = getCurrentUser();

        UserProfileView user = new UserProfileView();
        user.setFirstname(userEntity.get().getFirstName());
        user.setLastname(userEntity.get().getLastName());
        user.setEmail(userEntity.get().getEmail());
        user.setRoles(userEntity.get().getRoles());
        user.setUsername(userEntity.get().getUsername());
        model.addAttribute("user", user);

        return "profile";
    }

    private Optional<UserEntity> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UserEntity> userEntity = userEntityService.findUserByEmail(username);

        return userEntity;
    }

}
