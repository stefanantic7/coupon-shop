package rs.raf.repositories.Coupon;

import rs.raf.boot.MySqlConnectionPool;
import rs.raf.enums.CouponStatus;
import rs.raf.enums.Privilege;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class CouponRepositoryMySql implements CouponRepository {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Coupon> paginate(int limit, int page, CouponStatus couponStatus) {
        List<Coupon> coupons = new ArrayList<>();
        Set<Integer> shopIds = new HashSet<>();
        try {
            String query = "SELECT * FROM coupons ";
            if (couponStatus.equals(CouponStatus.ACTIVE)) {
                query += " WHERE (valid_from <= ? AND valid_to >= ?) OR (valid_from <= ? AND valid_to is null)";
            }
            else if (couponStatus.equals(CouponStatus.INACTIVE)) {
                query += " WHERE valid_from > ? OR valid_to < ?";
            }
            query += " LIMIT ?, ?;";

            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(query);

            java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            int valueCounter = 0;
            if (couponStatus.equals(CouponStatus.ACTIVE)) {
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
            }
            else if (couponStatus.equals(CouponStatus.INACTIVE)) {
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
            }

            preparedStatement.setInt(++valueCounter, (page-1) * limit);
            preparedStatement.setInt(++valueCounter, limit);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int shopId = resultSet.getInt("shop_id");
                String product = resultSet.getString("product");
                float discountedPrice = resultSet.getFloat("discounted_price");
                float originalPrice = resultSet.getFloat("original_price");
                Date validFrom = resultSet.getDate("valid_from");
                Date validTo = resultSet.getDate("valid_to");

                Coupon coupon = new Coupon(id, shopId, product, originalPrice, discountedPrice, validFrom, validTo);
                coupons.add(coupon);
                shopIds.add(shopId);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }

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

                coupons.add(new Coupon(id, couponShopId, product, originalPrice, discountedPrice, validFrom, validTo));
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

    @Override
    public int delete(long id) {
        int deleted = 0;
        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM coupons where id = ?");
            preparedStatement.setLong(1, id);
            deleted = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public int count() {
        int count = 0;

        try {
            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM coupons");
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                count = resultSet.getInt("count");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int count(CouponStatus couponStatus) {
        int count = 0;

        try {
            String query = "SELECT count(id) as count FROM coupons ";
            if (couponStatus.equals(CouponStatus.ACTIVE)) {
                query += " WHERE (valid_from <= ? AND valid_to >= ?) OR (valid_from <= ? AND valid_to is null)";
            }
            else if (couponStatus.equals(CouponStatus.INACTIVE)) {
                query += " WHERE valid_from > ? OR valid_to < ?";
            }

            connection = MySqlConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(query);

            java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            int valueCounter = 0;
            if (couponStatus.equals(CouponStatus.ACTIVE)) {
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
            }
            else if (couponStatus.equals(CouponStatus.INACTIVE)) {
                preparedStatement.setDate(++valueCounter, currentDate);
                preparedStatement.setDate(++valueCounter, currentDate);
            }

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                count = resultSet.getInt("count");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public Coupon create(long shopId, String product, float discountedPrice, float originalPrice, Date validFrom, Date validTo) {
        Coupon coupon = new Coupon();
        try {
            connection = MySqlConnectionPool.getConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection
                    .prepareStatement("INSERT INTO coupons (shop_id, product, discounted_price, original_price, valid_from, valid_to) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);

            preparedStatement.setLong(1, shopId);
            preparedStatement.setString(2, product);
            preparedStatement.setFloat(3, discountedPrice);
            preparedStatement.setFloat(4, originalPrice);
            preparedStatement.setDate(5, new java.sql.Date(validFrom.getTime()));
            preparedStatement.setDate(6, new java.sql.Date(validTo.getTime()));

            //TODO: check execution.
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                coupon.setId(resultSet.getInt(1));
                coupon.setProduct(product);
                coupon.setDiscountedPrice(discountedPrice);
                coupon.setOriginalPrice(originalPrice);
                coupon.setValidFrom(validFrom);
                coupon.setValidTo(validTo);
            }


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupon;
    }
}
