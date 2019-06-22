package rs.raf.dtos;

import rs.raf.models.Coupon;

import java.util.ArrayList;
import java.util.List;

public class ShopDto {
    private long id;
    private String name;
    private List<CouponDto> coupons;

    public ShopDto() {
        this.coupons = new ArrayList<>();
    }

    public ShopDto(long id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoupons(List<CouponDto> coupons) {
        this.coupons = coupons;
    }

    public List<CouponDto> getCoupons() {
        return coupons;
    }
}
