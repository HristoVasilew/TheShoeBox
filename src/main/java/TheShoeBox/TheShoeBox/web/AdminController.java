package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.security.Principal;

@Controller
public class AdminController {

    private final UserEntityService userService;
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    public AdminController(UserEntityService userService, ModelMapper modelMapper, UserEntityService userEntityService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PageTitle("Home Page")
    @GetMapping("/admin")
    public String adminProceed(Model model, Principal principal) {
        UserProfileView user = userEntityService.findUserByEmail(principal.getName());

        model.addAttribute("users", userService.getAllUsersByFetch(user.getId()));
        return "admin";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/{id}/promote")
    public String makeAdmin(@PathVariable Long id) {
        userService.makeUserAdmin(id);

        return "redirect:/admin";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/admin/{id}/demote")
    public String makeUser(@PathVariable Long id) {
        userService.makeAdminUser(id);

        return "redirect:/admin";
    }
}
