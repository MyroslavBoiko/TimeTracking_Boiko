package services.interfaces;

import entities.Activity;

import java.util.List;

public interface ActivitiesService extends Service {

    List<Activity> getAllActivities();
}
