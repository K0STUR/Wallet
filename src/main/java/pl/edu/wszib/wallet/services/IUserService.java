package pl.edu.wszib.wallet.services;

import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.model.view.RegistrationModel;

public interface IUserService {
    void authenticate (User user);
    void logout ();
    boolean addNewUser (RegistrationModel registrationModel);

}
