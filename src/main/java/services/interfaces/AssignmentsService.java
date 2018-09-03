package services.interfaces;

import entities.Assignment;
import entities.User;

import java.util.List;

/**
 * @author Mirosha
 */

public interface AssignmentsService extends Service {

    List<Assignment> getActiveAssignments(String language);

    boolean createAssignment(String email, String description);

    List<Assignment> getUserAssignments(String email);

    boolean saveTime(String email, String description, String time);

    boolean setAssignInactive(String email, String description);

    List<Assignment> getAssignmentsPerPage(boolean isActive, int currentPage, int recordsPerPage);

    List<Assignment> getUserAssignmentsPerPage(String email, String language, boolean isActive, int currentPage, int recordsPerPage);

    int getCountForUser(String email, boolean isActive);

    int getCountOfRows(boolean isActive);
}
