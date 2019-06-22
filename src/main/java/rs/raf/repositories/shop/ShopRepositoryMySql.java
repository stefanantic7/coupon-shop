package rs.raf.repositories.shop;

import rs.raf.boot.MySqlConnectionPool;
import rs.raf.models.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopRepositoryMySql implements ShopRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Shop> paginate(int limit, int page) {
        List<Shop> shops = new ArrayList<>();

        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM shops LIMIT ?, ?;");
            preparedStatement.setInt(1, (page-1) * limit);
            preparedStatement.setInt(2, limit);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int shopId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                shops.add(new Shop(shopId, name));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shops;
    }

    @Override
    public Shop find(int id) {
        Shop shop = null;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM shops where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int shopId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                shop = new Shop(shopId, name);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shop;
    }

    @Override
    public int delete(int id) {
        int deleted = 0;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM shops where id = ?");
            preparedStatement.setInt(1, id);
            deleted = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
