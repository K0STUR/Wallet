package pl.edu.wszib.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.wallet.dao.IUserDao;
import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.model.view.RegistrationModel;
import pl.edu.wszib.wallet.services.IUserService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IUserDao UserDao;

    @Override
    public void authenticate(User user) {
        User userFromDataBase = this.UserDao.getUserByLogin(user.getLogin());
        if(userFromDataBase == null){
            return;
        }
        if(user.getPass().equals(userFromDataBase.getPass())){
            this.sessionObject.setLoggedUser(userFromDataBase);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean addNewUser(RegistrationModel registrationModel) {
        if(this.UserDao.getUserByLogin(registrationModel.getLogin())!=null){
            return false;
        }
        User newUser = new User(0, registrationModel.getLogin(), registrationModel.getPass());
        return this.UserDao.AddNewUser(newUser);
    }
}
