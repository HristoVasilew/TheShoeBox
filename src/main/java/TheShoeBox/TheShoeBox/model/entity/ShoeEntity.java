package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "shoes")
public class ShoeEntity extends BaseEntity{

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private BigDecimal size;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private ConditionEnum condition;
}
