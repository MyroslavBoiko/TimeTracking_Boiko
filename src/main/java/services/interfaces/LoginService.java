package services.interfaces;

import entities.User;
import entities.UserType;

public interface LoginService extends Service {
    UserType getUserType(String email);
    User getUser(String email);
}
