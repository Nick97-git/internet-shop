package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * from users where login=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOGGER.info("User with login=" + login + " was received");
                return Optional.of(getCopyOfUser(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of user with login="
                    + login + " is failed", e);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password, salt) values(?,?,?,?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            addRolesForUser(user);
            LOGGER.info("User with id=" + user.getId() + " was created");
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Creation of user is failed", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * from users where user_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCopyOfUser(resultSet));
            }
            LOGGER.info("User with id=" + id + " was received");
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of user with id="
                    + id + " is failed", e);
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getCopyOfUser(resultSet));
            }
            LOGGER.info("List of users was received");
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting of list of users is failed", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET name=?, login=?, password=? WHERE user_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            deleteRolesOfUser(user);
            addRolesForUser(user);
            LOGGER.info("User with id=" + user.getId() + " was updated");
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Update of user with id="
                    + user.getId() + " is failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE user_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("User with id=" + id + " was deleted");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Delete of user with id="
                    + id + " is failed", e);
        }
    }

    private void addRolesForUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role: user.getRoles()) {
                String query = "INSERT INTO users_roles (user_id, role_id) "
                        + "values(?,?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, user.getId());
                statement.setLong(2, getRoleId(role.getRoleName().name(), connection));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add role for user", e);
        }
    }

    private Long getRoleId(String roleName, Connection connection) throws SQLException {
        String query = "SELECT role_id from roles where role_name=?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roleName);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong("role_id");
    }

    private void deleteRolesOfUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users_roles WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete roles of user", e);
        }
    }

    private User getCopyOfUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        return new User(userId, name, login, password, getRolesOfUser(userId), salt);
    }

    private Set<Role> getRolesOfUser(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT roles.role_name FROM users_roles INNER JOIN roles "
                + "ON  users_roles.role_id=roles.role_id \n"
                + "WHERE users_roles.user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get roles of user", e);
        }
        return roles;
    }
}
