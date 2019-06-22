package rs.raf.repositories.users;

import rs.raf.boot.MySqlConnectionPool;
import rs.raf.enums.Privilege;
import rs.raf.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMySql implements UserRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public User find(int id) {

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

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User find(String userUsername) {
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

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
