package pl.edu.wszib.wallet.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.wallet.configuration.TestConfiguration;
import pl.edu.wszib.wallet.dao.IUserDao;
import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.model.view.RegistrationModel;
import pl.edu.wszib.wallet.services.IUserService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @Autowired
    IUserDao userDao;

    @Resource
    SessionObject sessionObject;


    @Test
    public void addNewUserTest() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("rafal");
        registrationModel.setPass("rafal");
        registrationModel.setPass2("rafal");
        Mockito.when(this.userDao.getUserByLogin("rafal")).thenReturn(null);
        Mockito.when(this.userDao.addNewUser(ArgumentMatchers.any())).thenReturn(true);

        boolean result = userService.addNewUser(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void addNewUserIncorrectTest() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("piotrek");
        registrationModel.setPass("piotrek");
        registrationModel.setPass2("piotrek");
        Mockito.when(this.userDao.getUserByLogin("piotrek")).thenReturn(new User());

        boolean result = userService.addNewUser(registrationModel);

        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest() {
        User user = new User();
        user.setLogin("rafal");
        user.setPass("rafal");
        Mockito.when(this.userDao.getUserByLogin("rafal")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectLoginAuthenticationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPass("janusz");
        Mockito.when(this.userDao.getUserByLogin("janusz")).thenReturn(null);

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectPassAuthenticationTest() {
        User user = new User();
        user.setLogin("rafal");
        user.setPass("rafal123");
        Mockito.when(this.userDao.getUserByLogin("rafal")).thenReturn(generateUser());

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    private User generateUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("rafal");
        user.setPass("rafal");

        return user;
    }
}
