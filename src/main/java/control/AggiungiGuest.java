package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.CarrelloGuest;
import model.ProductBean;
import model.ProductDao;

@WebServlet("/AggiungiProdottoGuest")
public class AggiungiGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AggiungiGuest() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cartPlaceHolder="Carrello";
		Integer query = Integer.parseInt(request.getParameter("query")) ;
		Integer qty = Integer.parseInt(request.getParameter("qty")) ;
		HttpSession session=request.getSession();
		boolean exist=false;
		CarrelloGuest cart= (CarrelloGuest)session.getAttribute(cartPlaceHolder);
		if(cart==null) {
			session.setAttribute(cartPlaceHolder, new CarrelloGuest());
			cart=(CarrelloGuest) session.getAttribute(cartPlaceHolder);
			
		}
		
		ProductDao dao= new ProductDao();
		ProductBean prodb= new ProductBean();
		
		
		try {
			prodb=dao.doRetrieveByKey(query);
			prodb.setQuantita(qty);
			
			
			for(ProductBean prod:cart.getProdotti()) {
				if(prod.getCodice()==prodb.getCodice()) {
					exist=true;
					prod.setQuantita(prod.getQuantita()+prod.getQuantita());
				}
			}
			if(!exist)cart.getProdotti().add(prodb);
			
			
			
			Gson gson = new Gson();
	        String json = gson.toJson("True");
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
		}
		catch(SQLException e){
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		

	}
		
	}


