package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.view.AdminPanelUserViewModel;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminPanelUserViewModel> users
                = userService.findAllUsers()
                .stream()
                .map(s->modelMapper.map(s,AdminPanelUserViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "admin";
    }
    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}/promote")
    public String makeAdmin(@PathVariable Long id) {
        userService.makeUserAdmin(id);

        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}/demote")
    public String makeUser(@PathVariable Long id) {
        userService.makeAdminUser(id);

        return "redirect:/admin";
    }
}
