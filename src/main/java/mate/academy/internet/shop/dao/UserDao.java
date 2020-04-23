package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.model.User;

public interface UserDao {

    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}