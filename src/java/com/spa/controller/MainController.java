package com.spa.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class MainController extends HttpServlet {

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String REGISTER_STAFF_PAGE = "RegisterStaff";

    private static final String REGISTER_STAFF = "RegisterStaffUser";
    private static final String REGISTER_STAFF_CONTROLLER = "RegisterStaffController";
    private static final String CREATE_SERVICE = "CreateService";
    private static final String CREATE_SERVICE_CONTROLLER = "CreateServiceController";
    private static final String VIEW_SERVICES = "ViewServices";
    private static final String VIEW_SERVICES_CONTROLLER = "ViewServicesController";
    private static final String BOOK_APPOINTMENT = "BookAppointment";
    private static final String BOOK_APPOINTMENT_CONTROLLER = "BookAppointmentController";
    private static final String BOOK_APPOINTMENT_1 = "BookAppointment1";
    private static final String BOOK_APPOINTMENT_CONTROLLER_1 = "BookAppointmentController1";
    private static final String REVIEW_SERVICE = "ReviewService";
    private static final String REVIEW_SERVICE_CONTROLLER = "ReviewServiceController";
    private static final String REVIEW_SERVICE_1 = "ReviewService1";
    private static final String REVIEW_SERVICE_CONTROLLER_1 = "ReviewServiceController1";
    private static final String VIEW_CONSULTATION = "ViewConsultation";
    private static final String VIEW_CONSULTATION_CONTROLLER = "ViewConsultationController";
    private static final String UPDATE_SERVICE = "UpdateService";
    private static final String UPDATE_SERVICE_CONTROLLER = "UpdateServiceController";
    private static final String ADD_CONSULTATION_NOTES = "AddConsultationNotes";
    private static final String ADD_CONSULTATION_NOTES_CONTROLLER = "AddConsultationNotesController";
    private static final String VIEW_APPOINTMENTS = "ViewAppointments";
    private static final String VIEW_WAITLIST = "ViewWaitlist";
    private static final String VIEW_WAITLIST_CONTROLLER = "ViewWaitlistController";
    private static final String APPROVE_WAITLIST = "ApproveWaitlist";
    private static final String APPROVE_WAITLIST_CONTROLLER = "ApproveWaitlistController";
    private static final String VIEW_APPOINTMENTS_CONTROLLER = "ViewAppointmentsController";
    private static final String CANCEL_APPOINTMENT = "CancelAppointment";
    private static final String CANCEL_APPOINTMENT_CONTROLLER = "CancelAppointmentController";
    private static final String REGISTER_PAGE = "Register";
    private static final String REGISTER_USER = "RegisterUser";
    private static final String REGISTER_USER_CONTROLLER = "RegisterController";
    private static final String ASSIGN_STAFF = "AssignStaff";
    private static final String ASSIGN_STAFF_CONTROLLER = "AssignStaffController";
    private static final String UPDATE_STAFF = "UpdateStaff";
    private static final String UPDATE_STAFF_CONTROLLER = "UpdateStaffController";
    private static final String DELETE_SERVICE = "DeleteService";
    private static final String DELETE_SERVICE_CONTROLLER = "DeleteServiceController";
    private static final String COMPLETE_APPOINTMENT = "CompleteAppointment";
    private static final String COMPLETE_APPOINTMENT_CONTROLLER = "CompleteAppointmentController";
    private static final String ACTIVE_VIP = "ActiveVIP";
    private static final String ACTIVE_VIP_CONTROLLER = "ActiveVIPUserController";
    private static final String ACTIVE = "Active";
    private static final String ACTIVE_CONTROLLER = "UpdateVIPUserController";
    private static final String VIEWFEEDBACK = "Feedback";
    private static final String VIEWFEEDBACK_CONTROLLER = "ViewFeedback";
    private static final String CANCEL_REVIEW = "Cancel";
    private static final String CANCEL_REVIEW_CONTROLLER = "CancelReview";
    private static final String FEEDBACK = "FeedBackUser";
    private static final String FEEDBACK_CONTROLLER = "FeedBackUserController";
    private static final String FEEDBACK_1 = "FeedBackUser1";
    private static final String FEEDBACK_CONTROLLER_1 = "FeedBackUserController1";
    private static final String VIEW_CONSUMABLE = "ViewConsumable";
    private static final String VIEW_CONSUMABLE_CONTROLLER = "InventoryUsageController";
    private static final String ADD_CONSUMABLE = "AddConsumable";
    private static final String ADD_CONSUMABLE_CONTROLLER = "NewConsumable";
    private static final String ADD_USAGE_LOG = "AddUsageLog";
    private static final String ADD_USAGE_LOG_CONTROLLER = "AddUsageLogController";
    private static final String UPDATE_CONSUMABLE = "UpdateConsumable";
    private static final String UPDATE_CONSUMABLE_CONTROLLER = "UpdateConsumableController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "jsp/login.jsp";  // Default page

        try {
            String action = request.getParameter("action");
            String typeaction = request.getParameter("typeaction");
            System.out.println(action);
            System.out.println(typeaction);
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (REGISTER_STAFF_PAGE.equals(action)) {
                url = "/jsp/registerStaff.jsp";
            } else if (REGISTER_STAFF.equals(action)) {
                url = REGISTER_STAFF_CONTROLLER;
            } else if (CREATE_SERVICE.equals(action)) {
                url = CREATE_SERVICE_CONTROLLER;
            } else if (BOOK_APPOINTMENT.equals(action)) {
                url = BOOK_APPOINTMENT_CONTROLLER;
            } else if (BOOK_APPOINTMENT_1.equals(action)) {
                url = BOOK_APPOINTMENT_CONTROLLER_1;
            } else if (REVIEW_SERVICE.equals(action)) {
                url = REVIEW_SERVICE_CONTROLLER;
            } else if (REVIEW_SERVICE_1.equals(action)) {
                url = REVIEW_SERVICE_CONTROLLER_1;
            } else if (VIEW_CONSULTATION.equals(action)) {
                url = VIEW_CONSULTATION_CONTROLLER;
            } else if (VIEW_SERVICES.equals(action)) {
                url = VIEW_SERVICES_CONTROLLER;
            } else if (UPDATE_SERVICE.equals(action)) {
                url = UPDATE_SERVICE_CONTROLLER;
            } else if (ADD_CONSULTATION_NOTES.equals(action)) {
                url = ADD_CONSULTATION_NOTES_CONTROLLER;
            } else if (VIEW_APPOINTMENTS.equals(action)) {
                url = VIEW_APPOINTMENTS_CONTROLLER;
            } else if (CANCEL_APPOINTMENT.equals(action)) {
                url = CANCEL_APPOINTMENT_CONTROLLER;
            } else if (REGISTER_PAGE.equals(action)) {
                url = "/jsp/register.jsp";
            } else if (REGISTER_USER.equals(action)) {
                url = REGISTER_USER_CONTROLLER;
            } else if (ASSIGN_STAFF.equals(action)) {
                url = ASSIGN_STAFF_CONTROLLER;
            } else if (UPDATE_STAFF.equals(action)) {
                url = UPDATE_STAFF_CONTROLLER;
            } else if (DELETE_SERVICE.equals(action)) {
                url = DELETE_SERVICE_CONTROLLER;
            } else if (COMPLETE_APPOINTMENT.equals(action)) {
                url = COMPLETE_APPOINTMENT_CONTROLLER;
            } else if (ACTIVE_VIP.equals(action)) {
                url = ACTIVE_VIP_CONTROLLER;
            } else if (ACTIVE.equals(action)) {
                url = ACTIVE_CONTROLLER;
            } else if (VIEWFEEDBACK.equals(action)) {
                url = VIEWFEEDBACK_CONTROLLER;
            } else if (CANCEL_REVIEW.equals(action)) {
                url = CANCEL_REVIEW_CONTROLLER;
            } else if (FEEDBACK.equals(action)) {
                url = FEEDBACK_CONTROLLER;
            } else if (FEEDBACK_1.equals(action)) {
                url = FEEDBACK_CONTROLLER_1;
            } else if (VIEW_WAITLIST.equals(action)) {
                url = VIEW_WAITLIST_CONTROLLER;
            } else if (APPROVE_WAITLIST.equals(action)) {
                url = APPROVE_WAITLIST_CONTROLLER;
            } else if (VIEW_CONSUMABLE.equals(action)) {
                url = VIEW_CONSUMABLE_CONTROLLER;
            } else if (ADD_CONSUMABLE.equals(action)) {
                url = "jsp/addConsumable.jsp";
            } else if (ADD_USAGE_LOG.equals(action)) {
                url = "jsp/addUsageLog.jsp";  // Để mở form
            } else if ("Add".equals(action)) {
                if (ADD_CONSUMABLE_CONTROLLER.equals(typeaction)) {
                    url = "AddConsumableController";
                } else if ("NewUsageLog".equals(typeaction)) {
                    url = ADD_USAGE_LOG_CONTROLLER;
                }
            } else if (UPDATE_CONSUMABLE.equals(action)) {
                url = "processUpdateConsumableController";  // Để mở form
            } else if ("Save".equals(action)) {
                if (UPDATE_CONSUMABLE_CONTROLLER.equals(typeaction)) {
                    url = UPDATE_CONSUMABLE_CONTROLLER;
                } 
            } else {
                request.setAttribute("ERROR", "Action not supported.");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Main Controller for Spa Portal";
    }
}
