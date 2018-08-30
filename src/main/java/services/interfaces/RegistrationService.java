package services.interfaces;

import entities.User;

public interface RegistrationService extends Service {
    boolean performRegistration(User user);
}
