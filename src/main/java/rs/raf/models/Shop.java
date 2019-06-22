package rs.raf.models;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private long id;
    private String name;
    private List<Coupon> coupons;

    public Shop() {
        this.coupons = new ArrayList<>();
    }

    public Shop(long id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public Shop(long id, String name, List<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.coupons = coupons;
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

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
