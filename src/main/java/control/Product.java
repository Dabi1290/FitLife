package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDao;

@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Product() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		ProductBean bean = new ProductBean();
		try {
			bean= dao.doRetrieveByKey(/*(int)request.getAttribute("codice")*/18);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("prodotto", bean);
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/product.jsp");
		dispatcherToLoginPage.forward(request, response);
	}

}
