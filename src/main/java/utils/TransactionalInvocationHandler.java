package utils;

import annotation.Transaction;
import datasource.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Mirosha
 */
public class TransactionalInvocationHandler implements InvocationHandler {

    private TransactionManager transactionManager = TransactionManager.getInstance();

    private Object target;

    public TransactionalInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isTransactional(method)) {
            transactionManager.startTransaction();
            Object result = method.invoke(target, args);
            if (Objects.isNull(result) || (boolean) result) {
                transactionManager.commit(true);
            } else {
                transactionManager.commit(false);
            }
            return result;
        }
        return method.invoke(target, args);
    }

    private boolean isTransactional(Method method) throws NoSuchMethodException {
        Method realMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        return realMethod.isAnnotationPresent(Transaction.class);
    }
}
