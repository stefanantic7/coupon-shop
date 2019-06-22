package rs.raf.repositories.users;

import rs.raf.models.User;

public interface UserRepository {
    User find(int id);
    User find(String username);
}
