package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDao;

@WebServlet("/userArea")
public class userArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public userArea() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao dao=new UserDao();
		UserBean bean;
		RequestDispatcher dispatcher = request.getRequestDispatcher("areaUtente.jsp");
		int codice=(int) request.getSession().getAttribute("userCode");
		
		try {
			bean=dao.doRetrieveByKey(codice);
			request.setAttribute("utente", bean);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
