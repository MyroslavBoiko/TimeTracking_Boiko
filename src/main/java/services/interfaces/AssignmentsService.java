package services.interfaces;

import entities.Assignment;

import java.util.List;

public interface AssignmentsService extends Service {

    List<Assignment> getActiveAssignments();
    boolean createAssignment(String email, String activityDescription);
    List<Assignment> getUserAssignments(String email);
    boolean saveTime(String email, String description, String time);
    boolean setAssignInactive(String email, String description);
}
