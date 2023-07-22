package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CarrelloDao;
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
		int user= (int) request.getSession().getAttribute("userCode");
		try {
			
			Boolean answer=dao.doDelete(query, user);
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


