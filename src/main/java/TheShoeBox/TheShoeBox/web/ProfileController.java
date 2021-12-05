package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.UserUpdateBindingModel;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import TheShoeBox.TheShoeBox.model.service.UserServiceModel;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class ProfileController {
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    public ProfileController(ModelMapper modelMapper, UserEntityService userEntityService) {
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model, Principal principal) {
        UserProfileView userEntity = userEntityService.findUserByEmail(principal.getName());
        model.addAttribute("isAuthorize", true);
        model.addAttribute("user", userEntity);
        return "profile";
    }


    @GetMapping("/profile/{id}/edit-profile")
    public String editUser(@PathVariable Long id, Model model, Principal principal) {
        UserProfileView view = modelMapper.map(userEntityService.findById(id), UserProfileView.class);
        UserUpdateBindingModel map = modelMapper.map(view, UserUpdateBindingModel.class);
        model.addAttribute("user", map);
        return "edit-profile";
    }

    @PatchMapping("/profile/{id}/edit-profile")
    public String editUser(@PathVariable Long id, @Valid UserUpdateBindingModel profileUpdateBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profileUpdateBindingModel", profileUpdateBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileUpdateBindingModel",
                            bindingResult);

            return "redirect:/profile/" + id + "/edit-profile";
        }
        UserServiceModel model = modelMapper.map(profileUpdateBindingModel, UserServiceModel.class);

        userEntityService.editProfile(model);

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
