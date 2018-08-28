package dao;

import dao.impl.*;
import dao.interfaces.*;

public class DaoFactory {

    public static UserTypeDao createUserTypeDao(){
        return UserTypeDaoImpl.getInstance();
    }

    public static UserDao createUserDao(){
        return UserDaoImpl.getInstance();
    }

    public static AssignmentDao createAssignmentDao(){
        return AssignmentDaoImpl.getInstance();
    }

    public static RequestToAddDao createRequestToAddDao(){
        return RequestToAddDaoImpl.getInstance();
    }

    public static RequestToDeleteDao createRequestToDeleteDao(){
        return RequestToDeleteDaoImpl.getInstance();
    }

    public static ActivityDao createActivityDao(){
        return ActivityDaoImpl.getInstance();
    }

    public static ActivityTranslateDao createActivityTranslateDao(){ return ActivityTranslateDaoImpl.getInstance(); }

    public static LanguageDao createLanguageDao(){
        return LanguageDaoImpl.getInstance();
    }
}
