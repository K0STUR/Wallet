package pl.edu.wszib.wallet.dao;

import pl.edu.wszib.wallet.model.User;

public interface IUserDao {
    User getUserByLogin(String login);
    boolean addNewUser(User user);
}
