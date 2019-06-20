package rs.raf.models;

import java.util.Date;

public class Coupon {
    private long id;
    private Shop shop;
    private String product;
    private float discountedPrice;
    private float originalPrice;
    private Date validFrom;
    private Date validTo;

    public Coupon() {}

    public Coupon(long id, Shop shop, String product, float originalPrice, float discountedPrice, Date validFrom, Date validTo) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.discountedPrice = discountedPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public Coupon(long id, Shop shop, String product, float originalPrice, float discountedPrice, Date validFrom) {
        this(id, shop, product, originalPrice, discountedPrice, validFrom, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
