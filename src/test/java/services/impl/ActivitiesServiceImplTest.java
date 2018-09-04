package services.impl;

import dao.DaoFactory;
import dao.interfaces.ActivityDao;
import dao.interfaces.ActivityTranslateDao;
import dao.interfaces.LanguageDao;
import entities.Activity;
import entities.ActivityTranslate;
import entities.Language;
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
public class ActivitiesServiceImplTest {

    final ActivityDao activityDao = mock(ActivityDao.class);
    final LanguageDao languageDao = mock(LanguageDao.class);
    final ActivityTranslateDao activityTranslateDao = mock(ActivityTranslateDao.class);
    final Language language = new Language();
    final List<Activity> activities = new ArrayList<>();
    final List<ActivityTranslate> activityTranslates = new ArrayList<>();

    @Before
    public void setUp() {
        PowerMockito.mockStatic(DaoFactory.class);
        language.setLanguageId(1L);
        language.setLanguageName("English");
        language.setLanguageCode("en");
        activities.add(new Activity());
        activities.add(new Activity());
        activityTranslates.add(new ActivityTranslate());
        activityTranslates.add(new ActivityTranslate());
    }

    @Test
    public void checkIfLanguageNullActivitiesPerPage() throws Exception {
        when(DaoFactory.createActivityDao()).thenReturn(activityDao);
        when(DaoFactory.createActivityTranslateDao()).thenReturn(activityTranslateDao);
        when(DaoFactory.createLanguageDao()).thenReturn(languageDao);
        when(languageDao.findWhereLanguageCodeEquals(null)).thenReturn(null);
        ActivitiesServiceImpl.getInstance().getActivitiesPerPage(0,2,null);
        assertNull(languageDao.findWhereLanguageCodeEquals(null));
    }

    @Test
    public void checkIfExceptionOccurActivitiesPerPage() throws Exception {
        when(DaoFactory.createActivityDao()).thenReturn(activityDao);
        when(DaoFactory.createActivityTranslateDao()).thenReturn(activityTranslateDao);
        when(DaoFactory.createLanguageDao()).thenReturn(languageDao);
        when(activityDao.findActivitiesByLimit(-1, -1)).thenThrow(new Exception());
        assertNull(ActivitiesServiceImpl.getInstance().getActivitiesPerPage(-1,-1,"en"));
    }

    @Test
    public void checkCountOfRows() throws Exception {
        when(DaoFactory.createActivityDao()).thenReturn(activityDao);
        when(activityDao.getNumberOfRows()).thenReturn(6);
        int result = ActivitiesServiceImpl.getInstance().getCountOfRows();
        assertEquals(result, 6);
    }
}