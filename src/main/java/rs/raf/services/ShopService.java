package rs.raf.services;

import rs.raf.dtos.ShopDto;
import rs.raf.mappers.ShopMapper;
import rs.raf.models.Shop;
import rs.raf.repositories.Coupon.CouponRepository;
import rs.raf.repositories.shop.ShopRepository;
import rs.raf.responses.PaginationResponse;

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

    public PaginationResponse<ShopDto> paginate(int limit, int page) {
        List<ShopDto> shopDtos = new ArrayList<>();
        List<Shop> shops = this.shopRepository.paginate(limit, page);
        for (Shop shop: shops) {
            shopDtos.add(ShopMapper.instance.shopToShopDto(shop));
        }
        int totalSize = this.shopRepository.count();
        int pages = (int) Math.ceil(totalSize/(float)limit);

        return new PaginationResponse<>(shopDtos, totalSize, page, pages, limit);
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

    public ShopDto create(String name) {
        Shop shop = this.shopRepository.create(name);
        return ShopMapper.instance.shopToShopDto(shop);
    }
}
