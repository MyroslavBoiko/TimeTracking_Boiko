package services.interfaces;

import entities.ActivityTranslate;

import java.util.List;

/**
 * @author Mirosha
 */

public interface ActivitiesService extends Service {

    List<ActivityTranslate> getActivitiesPerPage(int currentPage, int recordsPerPage, String locale);
    int getCountOfRows();
}
