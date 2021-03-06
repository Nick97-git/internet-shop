package dev.internet.shop.dao.jdbc;

import dev.internet.shop.dao.ProductDao;
import dev.internet.shop.exceptions.DataProcessingException;
import dev.internet.shop.lib.Dao;
import dev.internet.shop.model.Product;
import dev.internet.shop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoJdbcImpl.class);

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products (name, price) values(?,?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            LOGGER.info("Product with id=" + product.getId() + " was created");
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Creation of product is failed", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * from products where product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCopyOfProduct(resultSet));
            }
            LOGGER.info("Product with id=" + id + " was received");
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of product with id="
                    + id + " is failed", e);

        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getCopyOfProduct(resultSet));
            }
            LOGGER.info("List of products was received");
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of list of products is failed", e);
        }
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products SET name=?, price=? WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            LOGGER.info("Product with id=" + product.getId() + " was updated");
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Update of product with id="
                    + product.getId() + " is failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM products WHERE product_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("Product with id=" + id + " was deleted");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Delete of product with id="
                    + id + " is failed", e);
        }
    }

    private Product getCopyOfProduct(ResultSet resultSet) throws SQLException {
        Long productId = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Product(productId, name, price);
    }
}
