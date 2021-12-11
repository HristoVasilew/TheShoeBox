package TheShoeBox.TheShoeBox.model.service;

import java.math.BigDecimal;

public class OrderServiceModel {


    private Long buyerId;

    private String buyerFullName;

    private Long sellerId;

    private String sellerFullName;

    private Long productId;

    private String imageUrl;

    private String brandAndModelProduct;

    private BigDecimal price;

    private Boolean shipped;

    public Boolean getShipped() {
        return shipped;
    }

    public OrderServiceModel setShipped(Boolean shipped) {
        this.shipped = shipped;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public OrderServiceModel setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public OrderServiceModel setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public OrderServiceModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OrderServiceModel setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderServiceModel setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrandAndModelProduct() {
        return brandAndModelProduct;
    }

    public OrderServiceModel setBrandAndModelProduct(String brandAndModelProduct) {
        this.brandAndModelProduct = brandAndModelProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
