package rs.raf.repositories.shop;

import rs.raf.models.Shop;

import java.util.List;

public interface ShopRepository {

    List<Shop> paginate(int limit, int page);

    Shop find(int id);
}
