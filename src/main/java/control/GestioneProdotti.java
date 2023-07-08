package control;

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

import model.ProductBean;
import model.ProductDao;


@WebServlet("/admin/GestioneProdotti")
public class GestioneProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public GestioneProdotti() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductBean> product= new ArrayList<>();
		ProductDao dao = new ProductDao();
		try {
			product= (List<ProductBean>) dao.doRetrieveAll();
			
		} catch (SQLException e) {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		if(product.isEmpty()) {
			
			request.setAttribute("void", true);
			request.setAttribute("tipo",new ProductBean());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("void", false);
			request.setAttribute("tipo",new ProductBean());
		  request.setAttribute("prodotti", product);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione.jsp");
		dispatcher.forward(request, response);
	}
	}

}
