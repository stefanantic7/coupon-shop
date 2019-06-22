package rs.raf.services;

import rs.raf.dtos.ShopDto;
import rs.raf.mappers.ShopMapper;
import rs.raf.models.Shop;
import rs.raf.repositories.Coupon.CouponRepository;
import rs.raf.repositories.shop.ShopRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ShopService {

    private ShopRepository shopRepository;
    private CouponRepository couponRepository;

    @Inject
    public ShopService(ShopRepository shopRepository, CouponRepository couponRepository) {
        this.shopRepository = shopRepository;
        this.couponRepository = couponRepository;
    }

    public List<ShopDto> paginate(int limit, int page) {
        List<ShopDto> shopDtos = new ArrayList<>();
        List<Shop> shops = this.shopRepository.paginate(limit, page);
        for (Shop shop: shops) {
            shopDtos.add(ShopMapper.instance.shopToShopDto(shop));
        }
        return shopDtos;
    }

    public ShopDto find(int id) {
        Shop shop = this.shopRepository.find(id);
        shop.setCoupons(this.couponRepository.getWhereShopId(id));
        return ShopMapper.instance.shopToShopDto(shop);
    }

    public void delete(int id) {
        this.couponRepository.deleteWhereShopId(id);
        this.shopRepository.delete(id);
    }
}
