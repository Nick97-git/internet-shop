package mate.academy.internet.shop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Override
    public List<Order> getUserOrders(User user) {
        String query = "SELECT * from orders where user_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(getCopyOfOrder(resultSet));
            }
            LOGGER.info("Orders of user with id=" + user.getId() + " were received");
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of list of orders of user with id="
                    + user.getId() + " is failed", e);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) values(?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductsToOrder(order);
            LOGGER.info("Order with id=" + order.getId() + " was created");
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Creation of order is failed", e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * from orders where order_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCopyOfOrder(resultSet));
            }
            LOGGER.info("Order with id=" + id + " was received");
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of order with id="
                    + id + " is failed", e);
        }
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(getCopyOfOrder(resultSet));
            }
            LOGGER.info("List of orders was received");
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of list of orders is failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("Order with id=" + id + " was deleted");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Delete of order with id="
                    + id + " is failed", e);
        }
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order);
        addProductsToOrder(order);
        LOGGER.info("Order with id=" + order.getId() + " was updated");
        return order;
    }

    private void addProductsToOrder(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                String query = "INSERT INTO orders_products(order_id, product_id) "
                        + "values(?,?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to order", e);
        }
    }

    private Order getCopyOfOrder(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        return new Order(orderId, userId, getProductsOfOrder(orderId));
    }

    private List<Product> getProductsOfOrder(Long orderId) {
        String query = "SELECT products.product_id, products.name, products.price "
                + "FROM orders_products INNER JOIN products "
                + "ON  orders_products.product_id=products.product_id \n"
                + "WHERE orders_products.order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                products.add(new Product(productId, name, price));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get list of products of order", e);
        }
    }

    private void deleteProductsFromOrder(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete products from order", e);
        }
    }
}
