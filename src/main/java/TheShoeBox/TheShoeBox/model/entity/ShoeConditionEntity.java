package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conditions")
public class ShoeConditionEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private ConditionEnum condition;

    @OneToMany(mappedBy = "shoeConditionEntity")
    private List<ShoeEntity> shoeEntities;

    public ConditionEnum getCondition() {
        return condition;
    }

    public ShoeConditionEntity setCondition(ConditionEnum condition) {
        this.condition = condition;
        return this;
    }

    public List<ShoeEntity> getShoeEntities() {
        return shoeEntities;
    }

    public ShoeConditionEntity setShoeEntities(List<ShoeEntity> shoeEntities) {
        this.shoeEntities = shoeEntities;
        return this;
    }
}
