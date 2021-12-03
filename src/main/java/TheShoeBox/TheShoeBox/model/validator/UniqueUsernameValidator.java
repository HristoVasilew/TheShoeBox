package TheShoeBox.TheShoeBox.model.validator;

import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueUsername;
import TheShoeBox.TheShoeBox.service.UserEntityService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserEntityService userService;

    public UniqueUsernameValidator(UserEntityService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) return false;

        return userService.isUsernameFree(username);
    }
}
