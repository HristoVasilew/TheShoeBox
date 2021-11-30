package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conditions")
public class ShoeConditionEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ConditionEnum name;

    @OneToMany(mappedBy = "shoeConditionEntity")
    private List<ShoeEntity> shoe;

    public ShoeConditionEntity() {
    }

    public ConditionEnum getName() {
        return name;
    }

    public ShoeConditionEntity setName(ConditionEnum condition) {
        this.name = condition;
        return this;
    }

    public List<ShoeEntity> getShoe() {
        return shoe;
    }

    public ShoeConditionEntity setShoe(List<ShoeEntity> shoe) {
        this.shoe = shoe;
        return this;
    }
}
