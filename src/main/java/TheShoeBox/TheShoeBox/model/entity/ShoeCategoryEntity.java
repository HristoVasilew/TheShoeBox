package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

}