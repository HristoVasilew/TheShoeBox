package TheShoeBox.TheShoeBox.model.view;

import java.math.BigDecimal;

public class OrderViewModel {

    private Long buyerId;

    private String buyerFullName;

    private Long sellerId;

    private String sellerFullName;

    private Long productId;

    private String imageUrl;

    private String brandAndModelProduct;

    private BigDecimal price;


    public Long getBuyerId() {
        return buyerId;
    }

    public OrderViewModel setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public OrderViewModel setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public OrderViewModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OrderViewModel setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderViewModel setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrandAndModelProduct() {
        return brandAndModelProduct;
    }

    public OrderViewModel setBrandAndModelProduct(String brandAndModelProduct) {
        this.brandAndModelProduct = brandAndModelProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
