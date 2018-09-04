package services.impl;

import dao.DaoFactory;
import dao.interfaces.AssignmentDao;
import entities.Assignment;
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
public class AssignmentsServiceImplTest {

    final AssignmentDao assignmentDao = mock(AssignmentDao.class);

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DaoFactory.class);
    }

    @Test
    public void getCountForUser() throws Exception {
        when(DaoFactory.createAssignmentDao()).thenReturn(assignmentDao);
        when(assignmentDao.getNumberForUser("email", true)).thenReturn(6);
        int result = AssignmentsServiceImpl.getInstance().getCountForUser("email",true);
        assertEquals(result, 6);
    }

    @Test
    public void getCountForUserException() throws Exception {
        when(DaoFactory.createAssignmentDao()).thenReturn(assignmentDao);
        when(assignmentDao.getNumberForUser(null, true)).thenThrow(new Exception());
        int result = AssignmentsServiceImpl.getInstance().getCountForUser(null,true);
        assertEquals(result, 0);
    }

    @Test
    public void getCountOfRows() throws Exception {
        when(DaoFactory.createAssignmentDao()).thenReturn(assignmentDao);
        when(assignmentDao.getNumberByActive(true)).thenReturn(6);
        int result = AssignmentsServiceImpl.getInstance().getCountOfRows(true);
        assertEquals(result, 6);
    }
    @Test
    public void getCountOfRowsException() throws Exception {
        when(DaoFactory.createAssignmentDao()).thenReturn(assignmentDao);
        when(assignmentDao.getNumberByActive(true)).thenThrow(new Exception());
        int result = AssignmentsServiceImpl.getInstance().getCountOfRows(true);
        assertEquals(result, 0);
    }
}