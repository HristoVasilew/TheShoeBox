package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private ShoeEntity shoe;


    public UserEntity getUser() {
        return user;
    }

    public ShopEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ShoeEntity getShoe() {
    return shoe;
    }

    public ShopEntity setShoe(ShoeEntity shoe) {
        this.shoe = shoe;
        return this;
    }
}
