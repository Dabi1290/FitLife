package security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminBean;
import model.AdminDao;
import model.UserBean;
import model.UserDao;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ERROR="errors";

	private String toHash(String password) throws SQLException {
		StringBuilder hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
             hashString = new StringBuilder();
            
            for (int i = 0; i < hash.length; i++) {
                hashString.append(Integer.toHexString( 
                        (hash[i] & 0xFF) | 0x100 
                    ).toLowerCase().substring(1,3))   ;
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new SQLException();
        }
        return hashString.toString();
    }protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");

        if (username == null || username.trim().isEmpty()) {
            errors.add("Il campo username non può essere vuoto!");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.add("Il campo password non può essere vuoto!");
        }
        if (!errors.isEmpty()) {
            request.setAttribute(ERROR, errors);
            dispatcherToLoginPage.forward(request, response);
            return;
        }

        if (username.contains("FitLife.com")) { // Admin
            processAdminLogin(username, password, request, response, dispatcherToLoginPage);
        } else { // User
            processUserLogin(username, password, request, response, dispatcherToLoginPage);
        }
    }

    private void processAdminLogin(String username, String password,
                                   HttpServletRequest request, HttpServletResponse response,
                                   RequestDispatcher dispatcherToLoginPage) throws IOException, ServletException {
        AdminBean admin = new AdminBean();
        AdminDao admindao = new AdminDao();

        try {
            admin = admindao.doRetrieveByEmail(username);
            if (admin.getPassword().equals(toHash(password))) {
                request.getSession().setAttribute("isAdmin", Boolean.TRUE);
                response.sendRedirect("admin/index.jsp");
            } else {
                handleLoginError(request, response, dispatcherToLoginPage);
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void processUserLogin(String username, String password,
                                  HttpServletRequest request, HttpServletResponse response,
                                  RequestDispatcher dispatcherToLoginPage) throws IOException, ServletException {
        UserBean user = new UserBean();
        UserDao userdao = new UserDao();

        try {
            user = userdao.doRetrieveByEmail(username);
            if (user.getPassword().equals(toHash(password))) {
                request.getSession().setAttribute("isUser", Boolean.TRUE);
                request.getSession().setAttribute("isAdmin", Boolean.FALSE);
                request.getSession().setAttribute("userCode", user.getCodice());
                response.sendRedirect("ciao");
            } else {
                handleLoginError(request, response, dispatcherToLoginPage);
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleLoginError(HttpServletRequest request, HttpServletResponse response,
                                  RequestDispatcher dispatcherToLoginPage) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        errors.add("Username o password non validi!");
        request.setAttribute(ERROR, errors);
        dispatcherToLoginPage.forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
