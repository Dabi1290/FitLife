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
		
		CarrelloDao dao= new CarrelloDao();
		Integer user= (int) request.getSession().getAttribute("userCode");
		try {
			Boolean answer=false;
			if(user!=null) {
				try {
					answer=dao.SvuotaCarrello(user);
				} catch (SQLException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			else {
				request.getSession().removeAttribute("cart");
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

}
