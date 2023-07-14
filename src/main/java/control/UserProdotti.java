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
import model.ProductDao;

@WebServlet("/Prodotti")
public class UserProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserProdotti() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductBean> product= new ArrayList<>();
		ProductDao dao = new ProductDao();
		List<CategoriaBean> categories= new ArrayList<>();
		CategorieDao catdao = new CategorieDao();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/products.jsp");
		String strcategoria="";
		String strmin="";
		String strmax="";
		
		
		
		try {
			product= (List<ProductBean>) dao.doRetrieveAll();
			categories=(List<CategoriaBean>) catdao.doRetrieveAll();
			
		} catch (SQLException e) {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		try {
		strcategoria=(String) request.getParameter("categoria");
		strmin= (String) request.getParameter("min");
		strmax= (String) request.getParameter("max");
		}
		catch(Exception e) {
			
			strcategoria="-1";
			strmin="0";
			strmax="500";
		}
		
		if(strcategoria!=null && !strcategoria.trim().isEmpty()) {
			int categoria=Integer.parseInt(strcategoria);
			product=product.stream().filter(prodotti -> prodotti.getCategoria()==categoria).toList();	
		}
		
		if (strmin!=null && !strmin.trim().isEmpty() && strmax!=null && !strmax.trim().isEmpty()) {
			
			
			
			int min=Integer.parseInt(strmin);
			int max=Integer.parseInt(strmax);
			if(min<=max) {
				product=product.stream().filter(prodotti -> (prodotti.getPrezzo()>=min && prodotti.getPrezzo()<=max)).toList();
			}
			
		}
		
		
		
		if(product.isEmpty()) {
			request.setAttribute("prodotti", null);
		}else {
			request.setAttribute("prodotti", product);
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
	


