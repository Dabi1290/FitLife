package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.UserDao;


@WebServlet("/PassReset")
public class PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasswordReset() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass= (String) request.getParameter("Password");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AreaUtente");
		UserDao dao= new UserDao();
		
		try {
			dao.doPass(pass, (int)request.getSession().getAttribute("userCode"));
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}
