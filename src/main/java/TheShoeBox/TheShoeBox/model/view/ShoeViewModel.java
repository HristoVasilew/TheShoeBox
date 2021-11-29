package TheShoeBox.TheShoeBox.model.view;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoeViewModel {

    private String brand;
    private String model;
    private BigDecimal price;
    private String description;
    private LocalDateTime createdOn;


    public String getBrand() {
        return brand;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ShoeViewModel setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ShoeViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
