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

}
