package TheShoeBox.TheShoeBox.model.validator;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueEmail;
import TheShoeBox.TheShoeBox.service.UserEntityService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserEntityService userService;

    public UniqueEmailValidator(UserEntityService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return false;

        return userService.checkEmail(email);
    }
}
