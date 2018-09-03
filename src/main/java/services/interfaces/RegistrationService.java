package services.interfaces;

import entities.User;

/**
 * @author Mirosha
 */

public interface RegistrationService extends Service {
    boolean performRegistration(User user);
}
