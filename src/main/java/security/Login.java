package security;

import java.io.IOException;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			List<String> errors = new ArrayList<>();
        	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");

			
			if(username == null || username.trim().isEmpty()) {
				errors.add("Il campo username non può essere vuoto!");
			}
            if(password == null || password.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
			}
            if (!errors.isEmpty()) {
            	request.setAttribute("errors", errors);
            	dispatcherToLoginPage.forward(request, response);
            	return; // note the return statement here!!!
            }
			
			if(username.contains("FitLife.com")){//admin
				AdminBean admin= new AdminBean();
				AdminDao admindao= new AdminDao();
				try {
					admin=admindao.doRetrieveByEmail(username);
					if(admin.getPassword().equals(password)==true) {
					
						request.getSession().setAttribute("isAdmin", Boolean.TRUE);
						response.sendRedirect("admin/index.jsp");
					}
					else {
						errors.add("Username o password non validi!");
						request.setAttribute("errors", errors);
		            	dispatcherToLoginPage.forward(request, response);
		            	return;
					}
				} catch (SQLException e) {
					// Da implementare error pages
				}
				
				
			} else{//user
				UserBean user = new UserBean();
				UserDao userdao = new UserDao();
				try {
					user=userdao.doRetrieveByEmail(username);
					if(user.getPassword().equals(password)==true) {
						
						request.getSession().setAttribute("isAdmin", Boolean.FALSE);
						response.sendRedirect("common/index.jsp");
					}
					else {
						errors.add("Username o password non validi!");
						request.setAttribute("errors", errors);
		            	dispatcherToLoginPage.forward(request, response);
		            	return;
					}
				} catch (SQLException e) {
					//error pages
				}
				
			} 
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
