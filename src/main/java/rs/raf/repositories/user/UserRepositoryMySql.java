package rs.raf.repositories.user;

import rs.raf.boot.MySqlConnectionPool;
import rs.raf.enums.Privilege;
import rs.raf.exceptions.CustomException;
import rs.raf.exceptions.ModelNotFoundException;
import rs.raf.models.User;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMySql implements UserRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public User find(int id) throws ModelNotFoundException {

        User user = null;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Privilege privilegeLevel = Privilege.valueOf(resultSet.getString("privilege_level"));
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(userId, firstName, lastName, privilegeLevel, username, password);
            }
            else {
                throw new ModelNotFoundException();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User find(String userUsername) throws ModelNotFoundException {
        User user = null;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, userUsername);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Privilege privilegeLevel = Privilege.valueOf(resultSet.getString("privilege_level"));
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(userId, firstName, lastName, privilegeLevel, username, password);
            }
            else {
                throw new ModelNotFoundException();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User create(String firstName, String lastName, String privilegeLevel, String username, String password) throws CustomException, ModelNotFoundException {
        User user = new User();

        try {
            connection = MySqlConnectionPool.getConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection
                    .prepareStatement("INSERT INTO users (FIRST_NAME, LAST_NAME, PRIVILEGE_LEVEL, USERNAME, PASSWORD) VALUES(?, ?, ?, ?, ?)", generatedColumns);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, privilegeLevel);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPrivilegeLevel(Privilege.valueOf(privilegeLevel));
                user.setUsername(username);
                user.setPassword(password);
            }


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO: Create own exception
            throw new CustomException("User with that username already exists.", Response.Status.BAD_REQUEST);
//            throw new ModelNotFoundException();
        }

        return user;
    }
}
