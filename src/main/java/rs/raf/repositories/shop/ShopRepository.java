package rs.raf.repositories.shop;

import rs.raf.models.Shop;

import java.util.List;
import java.util.Set;

public interface ShopRepository {

    List<Shop> all();

    List<Shop> paginate(int limit, int page);

    List<Shop> get(Long[] ids);

    Shop find(int id);

    int delete(int id);

    int count();

    Shop create(String name);


}
