package rs.raf.repositories.Coupon;

import rs.raf.boot.MySqlConnectionPool;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponRepositoryMySql implements CouponRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Coupon> getWhereShopId(int shopId) {
        List<Coupon> coupons = new ArrayList<>();

        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM coupons where shop_id = ?");
            preparedStatement.setInt(1, shopId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int couponShopId = resultSet.getInt("shop_id");
                String product = resultSet.getString("product");
                float discountedPrice = resultSet.getFloat("discounted_price");
                float originalPrice = resultSet.getFloat("original_price");
                Date validFrom = resultSet.getDate("valid_from");
                Date validTo = resultSet.getDate("valid_to");

                coupons.add(new Coupon(id, couponShopId, product, discountedPrice, originalPrice, validFrom, validTo));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }

    @Override
    public int deleteWhereShopId(int shopId) {
        int deleted = 0;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM coupons where shop_id = ?");
            preparedStatement.setInt(1, shopId);
            deleted = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

}
