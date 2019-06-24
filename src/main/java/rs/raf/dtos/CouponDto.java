package rs.raf.dtos;

import rs.raf.models.Shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CouponDto {
    private long id;
    private long shopId;
    private String product;
    private ShopDto shop;
    private float discountedPrice;
    private float originalPrice;
    private float percent;
    private Date validFrom;
    private Date validTo;

    public float getPercent() {
        float percent = 100 - (discountedPrice*100/originalPrice);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        return Float.parseFloat(decimalFormat.format(percent));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getValidFrom() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(validFrom);
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        if (validTo==null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(validTo);
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getShopId() {
        return shopId;
    }
}
