package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDao;


@WebServlet("/OrdiniCliente")
public class OrdiniCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public OrdiniCliente() {
        super();
            }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user = (int) request.getSession().getAttribute("userCode");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ordiniUtente.jsp");
		OrdineDao dao = new OrdineDao();
		try {
			List<OrdineBean> ordini= (List<OrdineBean>) dao.doRetrievebyUser(user);
			request.setAttribute("ordini", ordini);
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
