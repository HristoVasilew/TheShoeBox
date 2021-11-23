package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "categories")
public class ShoeCategoryEntity extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private ShoeCategoryEnum name;

    @OneToMany(mappedBy = "category")
    private Set<ShoeEntity> shoe;

    public ShoeCategoryEntity() {
    }

    public ShoeCategoryEnum getName() {
        return name;
    }

    public void setName(ShoeCategoryEnum name) {
        this.name = name;
    }
}