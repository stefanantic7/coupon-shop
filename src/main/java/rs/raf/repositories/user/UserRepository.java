package rs.raf.repositories.user;

import rs.raf.exceptions.CustomException;
import rs.raf.exceptions.ModelNotFoundException;
import rs.raf.models.User;

public interface UserRepository {
    User find(int id) throws ModelNotFoundException;
    User find(String username) throws ModelNotFoundException;
    User create(String firstName, String lastName, String privilegeLevel, String username, String password) throws CustomException, ModelNotFoundException;
}
