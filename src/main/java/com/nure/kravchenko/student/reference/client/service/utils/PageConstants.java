package com.nure.kravchenko.student.reference.client.service.utils;

public class PageConstants {

    private PageConstants() {
        //private constructor for PageConstants class.
    }

    //AUTH JSP Pages
    public static final String AUTH_LOGIN_PAGE = "/login.jsp";
    public static final String AUTH_REGISTER_PAGE = "/register.jsp";

    //WORKER JSP Pages
    public static final String WORKER_MAIN_PAGE = "/worker_main.jsp";
    public static final String WORKER_SEARCH_REQUEST_PAGE = "/worker_search_request.jsp";
    public static final String WORKER_REQUESTS_PAGE = "/worker_requests.jsp";
    public static final String WORKER_DENIED_REPORTS_PAGE = "/worker_denied_reports.jsp";
    public static final String WORKER_REPORTS_PAGE =  "/worker_reports.jsp";


    //STUDENT JSP Pages
    public static final String STUDENT_MAIN_PAGE = "/student_main.jsp";
    public static final String STUDENT_REQUESTS_PAGE = "/student_requests.jsp";
    public static final String STUDENT_CREATE_REQUEST_PAGE = "/student_create_request.jsp";
    public static final String STUDENT_DENIED_REQUESTS_PAGE = "/student_denied_requests.jsp";
    public static final String STUDENT_REPORTS_PAGE = "/student_reports.jsp";

    //ADMIN JSP Pages
    public static final String ADMIN_MAIN_PAGE = "/admin_main.jsp";
    public static final String ADMIN_APPROVAL_STUDENTS_PAGE = "/admin_approval_students.jsp";
    public static final String ADMIN_APPROVAL_WORKERS_PAGE = "/admin_approval_workers.jsp";
    public static final String ADMIN_CREATE_PAGE = "/admin_create.jsp";
    public static final String ADMIN_FIND_STUDENTS_PAGE = "/admin_find_students.jsp";
    public static final String ADMIN_SHOW_STUDENT_PAGE =  "/admin_show_student.jsp";

}
