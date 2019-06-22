package rs.raf.boot;


import org.glassfish.jersey.internal.inject.AbstractBinder;
import rs.raf.repositories.shop.ShopRepository;
import rs.raf.repositories.shop.ShopRepositoryMySql;
import rs.raf.repositories.user.UserRepository;
import rs.raf.repositories.user.UserRepositoryMySql;
import rs.raf.services.ShopService;
import rs.raf.services.UserService;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(UserRepositoryMySql.class).to(UserRepository.class);
        bind(ShopRepositoryMySql.class).to(ShopRepository.class);

        bindAsContract(UserService.class);
        bindAsContract(ShopService.class);
    }
}
