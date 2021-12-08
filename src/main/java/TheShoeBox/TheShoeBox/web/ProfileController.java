package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.UserUpdateBindingModel;
import TheShoeBox.TheShoeBox.model.service.UserServiceModel;
import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class ProfileController {
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    public ProfileController(ModelMapper modelMapper, UserEntityService userEntityService) {
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/profile")
    @PageTitle("Profile Page")
    public String getUserProfile(Model model, Principal principal) {
        UserProfileView userEntity = userEntityService.findUserByEmail(principal.getName());
        model.addAttribute("isAuthorize", true);
        model.addAttribute("user", userEntity);
        return "profile";
    }


    @GetMapping("/profile/edit-profile")
    @PageTitle("Edit Profile Page")
    public String editUser(Model model, Principal principal) {

        Long id = userEntityService.findUserByEmail(principal.getName()).getId();
        UserServiceModel userServiceModel = userEntityService.findById(id);

        model.addAttribute("userUpdateBindingModel", modelMapper.map(userServiceModel,UserUpdateBindingModel.class));
        return "edit-profile";
    }

    @PatchMapping("/profile/edit-profile")
    public String editUser(@Valid UserUpdateBindingModel userUpdateBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) throws IOException {

        Long id = userEntityService.findUserByEmail(principal.getName()).getId();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userUpdateBindingModel", userUpdateBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userUpdateBindingModel", bindingResult);

            return "redirect:/profile/" + id + "/edit-profile";
        }


        UserServiceModel model = modelMapper.map(userUpdateBindingModel, UserServiceModel.class);

        userEntityService.editProfile(model,id);

        return "redirect:/profile";
    }

//    private Optional<UserEntity> getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        //Optional<UserEntity> userEntity = userEntityService.findUserByEmail(username);
//
//        return userEntity;
//    }

}
