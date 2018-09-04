package services.impl;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import entities.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class LoginServiceImplTest {

    final UserTypeDao userTypeDao = mock(UserTypeDao.class);
    final UserDao userDao = mock(UserDao.class);
    final UserType userT = new UserType();
    final User user = new User();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DaoFactory.class);
        user.setEmail("email");
        user.setUserTypeId(1L);
    }

    @Test
    public void getUserType() throws Exception {
        when(DaoFactory.createUserTypeDao()).thenReturn(userTypeDao);
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findWhereEmailEquals("email")).thenReturn(user);
        when(userTypeDao.findWhereUserTypeIdEquals(user.getUserTypeId())).thenReturn(userT);
        assertNotNull(LoginServiceImpl.getInstance().getUserType("email"));
    }

    @Test
    public void getUser() throws Exception{
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findWhereEmailEquals("email")).thenReturn(user);
        assertNotNull(LoginServiceImpl.getInstance().getUser("email"));
    }


    @Test
    public void getUserWhenExceptionOccur() throws Exception{
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findWhereEmailEquals("12345")).thenThrow(new Exception());
        assertNull(LoginServiceImpl.getInstance().getUser("12345"));
    }
}