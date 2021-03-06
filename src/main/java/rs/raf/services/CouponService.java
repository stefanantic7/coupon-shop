package rs.raf.services;

import rs.raf.dtos.CouponDto;
import rs.raf.enums.CouponStatus;
import rs.raf.mappers.CouponMapper;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.repositories.Coupon.CouponRepository;
import rs.raf.repositories.shop.ShopRepository;
import rs.raf.responses.PaginationResponse;

import javax.inject.Inject;
import java.util.*;

public class CouponService {

    private CouponRepository couponRepository;
    private ShopRepository shopRepository;

    @Inject
    public CouponService(CouponRepository couponRepository, ShopRepository shopRepository) {
        this.couponRepository = couponRepository;
        this.shopRepository = shopRepository;
    }

    public PaginationResponse<CouponDto> paginate(int limit, int page, CouponStatus couponStatus)
    {
        List<Coupon> coupons = this.couponRepository.paginate(limit, page, couponStatus);
        HashSet<Long> shopIds = new HashSet<>();
        HashMap<Long, Shop> shopHashMap = new HashMap<>();
        for (Coupon coupon: coupons) {
            shopIds.add(coupon.getShopId());
        }
        Long[] shopIdsArray = shopIds.toArray(new Long[0]);
        if (shopIdsArray.length>0) {
            List<Shop> shops = shopRepository.get(shopIdsArray);
            for (Shop shop: shops) {
                shopHashMap.put(shop.getId(), shop);
            }
            for (Coupon coupon: coupons) {
                coupon.setShop(shopHashMap.get(coupon.getShopId()));
            }
        }


        List<CouponDto> couponDtos = new ArrayList<>(coupons.size());
        for (Coupon coupon: coupons) {
            couponDtos.add(CouponMapper.instance.couponToCouponDto(coupon));
        }
        int totalSize = this.couponRepository.count(couponStatus);
        int pages = (int) Math.ceil(totalSize/(float)limit);
        return new PaginationResponse<>(couponDtos, totalSize, page, pages, limit);
    }

    public void delete(long couponId) {
        this.couponRepository.delete(couponId);
    }

    public CouponDto create(long shopId, String product, float discountedPrice,
                            float originalPrice, Date validFrom, Date validTo) {
        System.out.println(validFrom);
        System.out.println(validTo);
        Coupon coupon = this.couponRepository.create(shopId, product, discountedPrice, originalPrice, validFrom, validTo);

        return CouponMapper.instance.couponToCouponDto(coupon);
    }
}
