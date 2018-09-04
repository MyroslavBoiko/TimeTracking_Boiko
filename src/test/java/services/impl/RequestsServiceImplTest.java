package services.impl;

import dao.DaoFactory;
import dao.interfaces.*;
import entities.ActivityTranslate;
import entities.RequestToAdd;
import entities.RequestToDelete;
import entities.User;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import services.interfaces.RequestsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class RequestsServiceImplTest {

    final UserDao userDao = mock(UserDao.class);
    final RequestToAddDao requestToAddDao = mock(RequestToAddDao.class);
    final RequestToDeleteDao requestToDelete = mock(RequestToDeleteDao.class);
    final ActivityTranslateDao activityTranslateDao = mock(ActivityTranslateDao.class);
    final AssignmentDao assignmentDao = mock(AssignmentDao.class);
    final LanguageDao languageDao = mock(LanguageDao.class);


    final List<Pair<String, String>> result = new ArrayList<>();
    final List<RequestToAdd> requestsToAdd = new ArrayList<>();
    final ActivityTranslate activityTranslate = new ActivityTranslate();

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DaoFactory.class);
        requestsToAdd.add(new RequestToAdd());
        result.add(new Pair<String, String>("", ""));
        activityTranslate.setLanguageId(1L);
        activityTranslate.setActivityId(1L);

    }

    @Test
    public void createRequest() throws Exception{
        when(DaoFactory.createRequestToAddDao()).thenReturn(requestToAddDao);
        when(DaoFactory.createActivityTranslateDao()).thenReturn(activityTranslateDao);
        when(DaoFactory.createUserDao()).thenReturn(userDao);
        when(DaoFactory.createLanguageDao()).thenReturn(languageDao);
        when(activityTranslateDao.findWhereDescriptionEquals("description")).thenReturn(new ActivityTranslate());
        doNothing().when(requestToAddDao).insertNewRequestToAdd(new RequestToAdd());
        assertTrue(RequestsServiceImpl.getInstance().createRequest(new User(), "description"));
    }

    @Test
    public void getCountOfRowsRequestToAdd() throws Exception{
        when(DaoFactory.createRequestToAddDao()).thenReturn(requestToAddDao);
        when(requestToAddDao.getNumberByActive(true)).thenReturn(2);
        int result = RequestsServiceImpl.getInstance().getCountOfRowsRequestToAdd(true);
        assertEquals(2,result);
    }

    @Test
    public void getCountOfRowsRequestToDelete() throws Exception {
        when(DaoFactory.createRequestToDeleteDao()).thenReturn(requestToDelete);
        when(requestToDelete.getNumberByActive(true)).thenReturn(2);
        int result = RequestsServiceImpl.getInstance().getCountOfRowsRequestToDelete(true);
        assertEquals(2,result);
    }

    @Test
    public void getRequestsToAddPerPageException() throws Exception {
        when(requestToAddDao.findRequestsToAddIsActiveByLimit(true, -1, -1)).thenThrow(new Exception());
        assertNull(RequestsServiceImpl.getInstance().getRequestsToAddPerPage(-1,-1,"en"));
    }

    @Test
    public void getRequestsToAddPerPageExceptionResult() throws Exception {
        when(requestToAddDao.findRequestsToAddIsActiveByLimit(true, -1, -1)).thenReturn(requestsToAdd);
        when(activityTranslateDao.findWhereActivityIdAndLanguageIdEquals(-1L, -1L)).thenThrow(new Exception());
        assertNull(RequestsServiceImpl.getInstance().getRequestsToAddPerPage(-1,-1,"en"));
    }

    @Test
    public void getRequestsToDeletePerPage() throws Exception{
        when(requestToDelete.findRequestsToDeleteIsActiveByLimit(true,0, -1)).thenThrow(new Exception());
        assertNull(RequestsServiceImpl.getInstance().getRequestsToDeletePerPage(-1,-1, "en"));
    }

    @Test
    public void checkUsedActivity() throws Exception {
        when(assignmentDao.findWhereEmailDescriptionActiveEquals("email", "description", true)).thenThrow(new Exception());
        assertFalse(RequestsServiceImpl.getInstance().checkUsedActivity(null, null));
    }
    @Test
    public void checkUsedActivityException() throws Exception {
        when(assignmentDao.findWhereEmailDescriptionActiveEquals(null, null, true)).thenThrow(new Exception());
        assertFalse(RequestsServiceImpl.getInstance().checkUsedActivity(null, null));
    }

    @Test
    public void setInactiveToRequest() throws Exception {
        doThrow(Exception.class).when(requestToAddDao).setInactiveRequestToAdd(-1L, -1L);
        assertFalse(RequestsServiceImpl.getInstance().setInactiveToRequest(null, null));
    }

    @Test
    public void createRequestToDelete()  throws Exception{
        when(userDao.findWhereEmailEquals(null)).thenThrow(new Exception());
        assertFalse(RequestsServiceImpl.getInstance().createRequestToDelete(null, ""));
    }
}