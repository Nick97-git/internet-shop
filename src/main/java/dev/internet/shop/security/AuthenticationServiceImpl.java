package dev.internet.shop.security;

import dev.internet.shop.exceptions.AuthenticationException;
import dev.internet.shop.lib.Inject;
import dev.internet.shop.lib.Service;
import dev.internet.shop.model.User;
import dev.internet.shop.service.UserService;
import dev.internet.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = userService.findByLogin(login).orElseThrow(
                () -> new AuthenticationException("Incorrect username or password!!!"));
        if (HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Incorrect username or password!!!");
    }
}
