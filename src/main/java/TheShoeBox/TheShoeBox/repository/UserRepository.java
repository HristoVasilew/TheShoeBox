package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity  u LEFT JOIN FETCH u.roles where u.username = :username")
    Optional<UserEntity> findUserEntityByUsername(String username);

    boolean existsByUsername(String username);
}
