package rs.raf.boot;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rs.raf.repositories.user.UserRepository;
import rs.raf.repositories.user.UserRepositoryMySql;
import rs.raf.services.UserService;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(UserRepositoryMySql.class).to(UserRepository.class);
        bindAsContract(UserService.class);
    }
}
