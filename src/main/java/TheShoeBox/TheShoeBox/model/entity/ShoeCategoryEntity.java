package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class ShoeCategoryEntity extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private ShoeCategoryEnum name;

    @OneToMany(mappedBy = "shoeCategoryEntity")
    private List<ShoeEntity> shoe;

    public ShoeCategoryEnum getName() {
        return name;
    }

    public ShoeCategoryEntity setName(ShoeCategoryEnum name) {
        this.name = name;
        return this;
    }

    public List<ShoeEntity> getShoe() {
        return shoe;
    }

    public ShoeCategoryEntity setShoe(List<ShoeEntity> shoe) {
        this.shoe = shoe;
        return this;
    }
}