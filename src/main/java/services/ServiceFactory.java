package services;


import annotation.Transaction;
import services.impl.*;
import services.interfaces.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Mirosha
 */
public class ServiceFactory {

    /**
     * Return Service instance in order to result of isTransaction method.
     *
     * @param type Instance of service class.
     * @param <T>  Generic type which extends from Service interface
     * @return Service instance.
     */
    private static <T extends Service> T getService(T type) {
        Class<? extends Service> serviceClass = type.getClass();
        if (isTransaction(serviceClass)) {
            ProxyService<T> proxyService = new ProxyService<>(type, serviceClass);
            return proxyService.getProxy();
        }
        return type;
    }

    public static ActivitiesService getActivitiesService() {
        return getService(ActivitiesServiceImpl.getInstance());
    }

    public static AssignmentsService getAssignmentsService() {
        return getService(AssignmentsServiceImpl.getInstance());
    }

    public static LoginService getLoginService() {
        return getService(LoginServiceImpl.getInstance());
    }

    public static RegistrationService getRegistrationService() {
        return getService(RegistrationServiceImpl.getInstance());
    }

    public static RequestsService getRequestsService() {
        return getService(RequestsServiceImpl.getInstance());
    }

    public static UsersService getUsersService() {
        return getService(UsersServiceImpl.getInstance());
    }

    /**
     * Define the availability of Transaction class in clazz parameter.
     *
     * @param clazz Class of type to be checked in the method.
     * @return Value describe the availability of Transaction class.
     */
    private static boolean isTransaction(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Transaction.class)) {
                return true;
            }
        }
        return false;
    }
}
