package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "collection")
public class CollectionEntity extends BaseEntity {

    @OneToOne
    private UserEntity user;
    @OneToMany(mappedBy = "collection")
    private Set<ShoeEntity> shoes;

    public UserEntity getUser() {
        return user;
    }

    public CollectionEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Set<ShoeEntity> getShoes() {
        return shoes;
    }

    public CollectionEntity setShoes(Set<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }
}
