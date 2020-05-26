package dev.internet.shop.service;

import dev.internet.shop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {

    Optional<User> findByLogin(String login);
}
