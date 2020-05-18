package mate.academy.internet.shop.security;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import mate.academy.internet.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

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
