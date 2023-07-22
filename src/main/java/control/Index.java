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


@WebServlet("/ciao")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Index() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<ProductBean> product= new ArrayList<>();
		ProductDao dao = new ProductDao();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		try {
			product= (List<ProductBean>) dao.Newest();
			request.setAttribute("products", product);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
