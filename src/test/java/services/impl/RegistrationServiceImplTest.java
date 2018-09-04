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
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class RegistrationServiceImplTest {

    final UserDao userDao = mock(UserDao.class);
    final UserTypeDao userTypeDao = mock(UserTypeDao.class);
    final UserType userType = new UserType();
    final User user = new User();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DaoFactory.class);
        userType.setTypeName("Client");
        userType.setUserTypeId(2L);
        user.setUserTypeId(userType.getUserTypeId());
    }

    @Test
    public void performRegistration() throws Exception{
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(DaoFactory.createUserTypeDao()).thenReturn(userTypeDao);
        when(userTypeDao.findWhereTypeNameEquals("Client")).thenReturn(userType);
        doNothing().when(userDao).insertNewUser(user);
        assertTrue(RegistrationServiceImpl.getInstance().performRegistration(user));

    }

    @Test
    public void performRegistrationWithExceptionThrown() throws Exception{
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(DaoFactory.createUserTypeDao()).thenReturn(userTypeDao);
        when(userTypeDao.findWhereTypeNameEquals("Client")).thenReturn(userType);
        doThrow(Exception.class).when(userDao).insertNewUser(user);
        assertFalse(RegistrationServiceImpl.getInstance().performRegistration(user));

    }
}