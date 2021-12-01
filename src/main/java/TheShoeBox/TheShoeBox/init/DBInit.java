package TheShoeBox.TheShoeBox.init;

import TheShoeBox.TheShoeBox.service.CategoryEntityService;
import TheShoeBox.TheShoeBox.service.RoleEntityService;
import TheShoeBox.TheShoeBox.service.ShoeConditionService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserEntityService userEntityService;
    private final RoleEntityService roleEntityService;
    private final CategoryEntityService categoryEntityService;
    private final ShoeConditionService shoeConditionService;

    public DBInit(UserEntityService userEntityService, RoleEntityService roleEntityService, CategoryEntityService categoryEntityService, ShoeConditionService shoeConditionService) {
        this.userEntityService = userEntityService;
        this.roleEntityService = roleEntityService;
        this.categoryEntityService = categoryEntityService;
        this.shoeConditionService = shoeConditionService;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryEntityService.initCategories();
        shoeConditionService.initConditions();
        roleEntityService.initRoles();
        userEntityService.initUsers();
    }
}
