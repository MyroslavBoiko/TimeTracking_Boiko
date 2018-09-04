package services.impl;

import dao.DaoFactory;
import dao.interfaces.ActivityTranslateDao;
import dao.interfaces.RequestToAddDao;
import entities.ActivityTranslate;
import entities.RequestToAdd;
import entities.RequestToDelete;
import entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import services.interfaces.RequestsService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoFactory.class)
public class RequestsServiceImplTest {

    final RequestToAddDao requestToAddDao = mock(RequestToAddDao.class);
    final ActivityTranslateDao activityTranslateDao = mock(ActivityTranslateDao.class);



    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(DaoFactory.class);

    }

    @Test
    public void createRequest() throws Exception{
        when(DaoFactory.createRequestToAddDao()).thenReturn(requestToAddDao);
        when(DaoFactory.createActivityTranslateDao()).thenReturn(activityTranslateDao);
        when(activityTranslateDao.findWhereDescriptionEquals("description")).thenReturn(new ActivityTranslate());
        doNothing().when(requestToAddDao).insertNewRequestToAdd(new RequestToAdd());
        assertTrue(RequestsServiceImpl.getInstance().createRequest(new User(), "description"));
    }

    @Test
    public void getCountOfRowsRequestToAdd() {
    }

    @Test
    public void getCountOfRowsRequestToDelete() {
    }

    @Test
    public void getRequestsToAddPerPage() {
    }

    @Test
    public void getRequestsToDeletePerPage() {
    }

    @Test
    public void checkUsedActivity() {
    }

    @Test
    public void setInactiveToRequest() {
    }

    @Test
    public void createRequestToDelete() {
    }
}