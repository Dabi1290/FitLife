package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CarrelloDao;

@WebServlet("/SvuotaCarrello")
public class EmptyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EmptyCart() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Integer user= (Integer) request.getSession().getAttribute("userCode");
		try {
			boolean answer=false;
			if(user!=null) {
				
				answer=Svuotami(user,response);
			}
			else {
				request.getSession().removeAttribute("Carrello");
				answer=true;
			}
			 Gson gson = new Gson();
		        String json = gson.toJson(answer);
		        
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");

		        PrintWriter out = response.getWriter();
		        out.print(json);
		        out.flush();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	public boolean Svuotami(Integer user,HttpServletResponse response ) {
		CarrelloDao dao= new CarrelloDao();
		try {
			return dao.svuotaCarrello(user);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return false;
	}

}
