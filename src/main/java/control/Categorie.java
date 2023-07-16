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

import model.CategoriaBean;
import model.CategorieDao;
import model.ProductBean;

@WebServlet("/Categories")
public class Categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Categorie() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CategoriaBean> categories= new ArrayList<>();
		CategorieDao catdao = new CategorieDao();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/categorie.jsp");
		try {
			categories=(List<CategoriaBean>) catdao.doRetrieveAll();
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		if(categories.isEmpty()) {
			request.setAttribute("categorie", null);
		}
		else {
			request.setAttribute("categorie", categories);
		}
		
		
		dispatcher.forward(request, response);
		
	}

}
