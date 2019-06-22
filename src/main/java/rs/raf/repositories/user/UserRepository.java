package rs.raf.repositories.user;

import rs.raf.models.User;

public interface UserRepository {
    User find(int id);
    User find(String username);
}
