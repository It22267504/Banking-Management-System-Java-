package mngLoanApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ApplicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationDAO applicationDAO;

    public ApplicationServlet() {
        this.applicationDAO = new ApplicationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to GET method
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertApplication(request, response);
                    break;
                case "/delete":
                    deleteApplication(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateApplication(request, response);
                    break;
                default:
                    listApplications(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listApplications(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Application> listApplications = applicationDAO.selectAllApplications();
        request.setAttribute("listApplications", listApplications);
        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestLoan-list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateApplication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String accountNumber = request.getParameter("accountNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String photo = request.getParameter("photo");
        String nic = request.getParameter("nic");
        String type = request.getParameter("type");
        String amount = request.getParameter("amount");
        String years = request.getParameter("years");
        

        Application application = new Application(id, name, accountNumber, phoneNumber, email, photo, nic, type, amount, years);
        applicationDAO.updateApplication(application);
        response.sendRedirect("list");
    }

    private void deleteApplication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        applicationDAO.deleteApplication(id);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Application existingApplication = applicationDAO.selectApplication(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestLoan-form.jsp");
        request.setAttribute("application", existingApplication);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestLoan-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertApplication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
        String name = request.getParameter("name");
        String accountNumber = request.getParameter("accountNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String photo = request.getParameter("photo");
        String nic = request.getParameter("nic");
        String type = request.getParameter("type");
        String amount = request.getParameter("amount");
        String years = request.getParameter("years");

        Application newApplication = new Application(name, accountNumber, phoneNumber, email, photo, nic, type, amount, years);
        applicationDAO.insertApplication(newApplication);
        response.sendRedirect("list");
    }
}
