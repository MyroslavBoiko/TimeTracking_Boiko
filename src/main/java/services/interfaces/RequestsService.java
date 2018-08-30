package services.interfaces;

import entities.RequestToAdd;
import entities.RequestToDelete;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

public interface RequestsService extends Service {
    boolean createRequest(String email, String activityDescription);
    List<RequestToAdd> getAllActiveAddRequests();
    List<Pair<String, String>> outputAddRequests();
    List<Pair<String, String>> outputDeleteRequests();
    List<RequestToDelete> getAllActiveDeleteRequests();
    boolean setInactiveToRequest(String userEmail, String activityDescription);
    boolean createRequestToDelete(String email, String description);
}
