package rs.raf.repositories.Coupon;

import rs.raf.models.Coupon;

import java.util.List;

public interface CouponRepository {
    List<Coupon> paginate(int limit, int page);
    List<Coupon> getWhereShopId(int shopId);
    int deleteWhereShopId(int shopId);
    int count();
}
