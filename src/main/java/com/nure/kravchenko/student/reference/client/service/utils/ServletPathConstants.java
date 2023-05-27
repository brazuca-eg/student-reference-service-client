package com.nure.kravchenko.student.reference.client.service.utils;

public class ServletPathConstants {

    private ServletPathConstants() {
        //private constructor for ServletPathConstants class.
    }

    //AUTHENTICATION paths for servlets
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_LOGOUT = "/logout";
    public static final String AUTH_REGISTER = "/register";

    //Download reports path
    public static final String DOWNLOAD_PATH = "/download";

    //Waiting approval path
    public static final String WAITING_PATH = "/waiting";


    //ADMIN paths for servlets
    public static final String ADMIN_MAIN = "/admin";
    public static final String ADMIN_APPROVE_WORKERS = "/adminApproveWorkers";
    public static final String ADMIN_CREATION = "/adminCreation";
    public static final String ADMIN_SEARCH_STUDENTS = "/adminSearchStudents";
    public static final String ADMIN_SHOW_WAITING_STUDENTS = "/adminShowWaitingStudents";

    //STUDENT paths for servlets
    public static final String STUDENT_MAIN = "/student";
    public static final String STUDENT_CREATE_REQUEST = "/createRequest";
    public static final String STUDENT_DENIED_REQUESTS = "/studentDeniedRequests";
    public static final String STUDENT_REQUESTS = "/studentRequests";
    public static final String STUDENT_REPORTS = "/studentReports";

    //WORKER paths for servlets
    public static final String WORKER_MAIN = "/worker";
    public static final String WORKER_REQUESTS = "/workerRequests";
    public static final String WORKER_DENIED_REQUESTS = "/workerDeniedReports";
    public static final String WORKER_SEARCH_REQUESTS = "/searchRequest";
    public static final String WORKER_REPORTS = "/workerReports";

}
