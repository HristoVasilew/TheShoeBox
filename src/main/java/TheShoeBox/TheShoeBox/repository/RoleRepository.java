package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRoleEnum admin);
}
