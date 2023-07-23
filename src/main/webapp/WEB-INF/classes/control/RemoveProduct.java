package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CarrelloDao;
import model.CarrelloGuest;
import model.ProductBean;


@WebServlet("/RimuoviProdotto")
public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RemoveProduct() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer query = Integer.parseInt(request.getParameter("query")) ;
		CarrelloDao dao= new CarrelloDao();
		Integer user= (Integer) request.getSession().getAttribute("userCode");
		
		Boolean answer;
		try {
			if(user!=null) answer=dao.doDelete(query, user);
			else {
				CarrelloGuest cart=(CarrelloGuest) request.getSession().getAttribute("Carrello");
				Iterator<ProductBean> iterator =cart.getProdotti().iterator();
				while(iterator.hasNext()) {
					ProductBean product = iterator.next();
			        if (product.getCodice() == query) {
			            iterator.remove();
			        }
				}
				answer=true;
			}
			 Gson gson = new Gson();
		        String json = gson.toJson(answer);
		        
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");

		        PrintWriter out = response.getWriter();
		        out.print(json);
		        out.flush();
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}
	}


