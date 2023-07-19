package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDao;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateUser() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userArea");
		UserBean bean= new UserBean();
		UserDao dao= new UserDao();
		bean.setCodice((int) request.getSession().getAttribute("userCode"));
		bean.setCognome((String)request.getParameter("Cognome"));
		bean.setNome((String)request.getParameter("Nome"));
		bean.setTelefono((String)request.getParameter("Telefono"));
		bean.setIndirizzo((String)request.getParameter("indirizzo"));
		bean.setEmail((String)request.getParameter("Email"));
		
		try {
			dao.doUpdate(bean);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
