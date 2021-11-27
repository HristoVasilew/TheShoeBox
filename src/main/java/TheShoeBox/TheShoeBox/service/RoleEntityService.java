package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface RoleEntityService {
    void initRoles();
    UserRoleEntity findByName(UserRoleEnum role);
}
