package TheShoeBox.TheShoeBox.init;

import TheShoeBox.TheShoeBox.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserEntityService userEntityService;
    private final RoleEntityService roleEntityService;
    private final CategoryEntityService categoryEntityService;
    private final ShoeConditionService shoeConditionService;
    private final ShoeService shoeService;
    private final CommentService commentService;

    public DBInit(UserEntityService userEntityService, RoleEntityService roleEntityService, CategoryEntityService categoryEntityService, ShoeConditionService shoeConditionService, ShoeService shoeService, CommentService commentService) {
        this.userEntityService = userEntityService;
        this.roleEntityService = roleEntityService;
        this.categoryEntityService = categoryEntityService;
        this.shoeConditionService = shoeConditionService;
        this.shoeService = shoeService;
        this.commentService = commentService;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryEntityService.initCategories();
        shoeConditionService.initConditions();
        roleEntityService.initRoles();
//        userEntityService.initUsers();
//        shoeService.initShoes();
//        commentService.initComments();
    }
}
