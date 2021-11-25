package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "collection")
public class CollectionEntity extends BaseEntity {

    @OneToMany(mappedBy = "collection")
    private Set<UserEntity> users;
    @OneToMany(mappedBy = "collection")
    private Set<ShoeEntity> shoes;

    public Set<UserEntity> getUsers() {
        return users;
    }

    public CollectionEntity setUsers(Set<UserEntity> users) {
        this.users = users;
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
