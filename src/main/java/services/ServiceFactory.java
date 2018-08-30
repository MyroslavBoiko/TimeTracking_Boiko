package services;


import annotation.Transaction;
import services.interfaces.*;

import java.util.Arrays;

public class ServiceFactory {

    private static <T extends Service> T getService(T type) {
        Class<? extends Service> serviceClass = type.getClass();
        if (isTransactional(serviceClass)) {
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

    public static RegistrationService getRegistrationService(){return getService(RegistrationServiceImpl.getInstance());}

    public static RequestsService getRequestsService(){return getService(RequestsServiceImpl.getInstance());}

    public static UsersService getUsersService(){return getService(UsersServiceImpl.getInstance());}

    private static boolean isTransactional(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Transaction.class));
    }
}
