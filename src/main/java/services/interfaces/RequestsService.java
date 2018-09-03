package services.interfaces;

import entities.RequestToAdd;
import entities.RequestToDelete;
import entities.User;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mirosha
 */

public interface RequestsService extends Service {

    boolean createRequest(User user, String description);

    List<RequestToAdd> getAllActiveAddRequests();

    List<Pair<String, String>> outputAddRequests();

    List<Pair<String, String>> outputDeleteRequests();

    List<RequestToDelete> getAllActiveDeleteRequests();

    List<Pair<String, String>> getRequestsToAddPerPage(int currentPage, int recordsPerPage, String language);

    List<Pair<String, String>> getRequestsToDeletePerPage(int currentPage, int recordsPerPage, String language);

    boolean checkUsedActivity(User user, String description);

    boolean setInactiveToRequest(String userEmail, String description);

    boolean createRequestToDelete(String email, String description);

    int getCountOfRowsRequestToAdd(boolean isActive);

    int getCountOfRowsRequestToDelete(boolean isActive);
}
