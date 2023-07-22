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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Register() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(nome.trim().length()<3 || cognome.trim().length()<3 || password.trim().length()<6) {
			RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/registrazione.jsp");
			dispatcherToLoginPage.forward(request, response);
		}
		else {
			UserBean bean= new UserBean();
			bean.setNome(nome);
			bean.setCognome(cognome);
			bean.setEmail(email);
			bean.setPassword(password);
			UserDao dao= new UserDao();
			try {
				dao.doSave(bean);
				RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/login.jsp");
				dispatcherToLoginPage.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

}
