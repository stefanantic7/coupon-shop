package rs.raf.boot;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rs.raf.repositories.users.UserRepository;
import rs.raf.repositories.users.UserRepositoryMySql;
import rs.raf.services.UserService;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(UserRepositoryMySql.class).to(UserRepository.class);
        bindAsContract(UserService.class);
    }
}
