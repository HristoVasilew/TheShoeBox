package TheShoeBox.TheShoeBox.init;

import TheShoeBox.TheShoeBox.service.CategoryEntityService;
import TheShoeBox.TheShoeBox.service.RoleEntityService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserEntityService userEntityService;
    private final RoleEntityService roleEntityService;
    private final CategoryEntityService categoryEntityService;
    public DBInit(UserEntityService userEntityService, RoleEntityService roleEntityService, CategoryEntityService categoryEntityService) {
        this.userEntityService = userEntityService;
        this.roleEntityService = roleEntityService;
        this.categoryEntityService = categoryEntityService;
    }


    @Override
    public void run(String... args) throws Exception {
       userEntityService.initUsers();
       roleEntityService.initRoles();
       categoryEntityService.initCategories();
    }
}
