package services.impl;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class UsersServiceImplTest {

    final UserDao userDao = mock(UserDao.class);
    final List<User> users = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        users.add(new User());
        users.add(new User());
        PowerMockito.mockStatic(DaoFactory.class);
    }

    @Test
    public void getUsersPerPage() throws Exception {
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findUsersByLimit(0, 2)).thenReturn(users);
        int result = UsersServiceImpl.getInstance().getUsersPerPage(0,2).size();
        assertEquals(result, 2);
    }

    @Test
    public void getUsersPerPageThenExceptionOccurs() throws Exception {
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.findUsersByLimit(-1, 2)).thenThrow(new Exception());
        List<User> users = UsersServiceImpl.getInstance().getUsersPerPage(-1,2);
        assertNull(users);
    }

    @Test
    public void getCountOfRows() throws Exception {
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.getNumberOfRows()).thenReturn(6);
        int result = UsersServiceImpl.getInstance().getCountOfRows();
        assertEquals(result, 6);
    }

    @Test
    public void checkExceptionCountOfRows() throws Exception {
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(userDao.getNumberOfRows()).thenThrow(new Exception());
        int result = UsersServiceImpl.getInstance().getCountOfRows();
        assertEquals(result, 0);

    }
}