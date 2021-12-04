package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    void deleteById(Long id);

    boolean existsByEmail(String email);

    @Query("select u from UserEntity u left join fetch u.roles")
    List<UserEntity> getAllUsersByFetch();
}
