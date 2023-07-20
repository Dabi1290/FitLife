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

import model.ProductBean;
import model.ProductDao;
import com.google.gson.Gson;


@WebServlet("/Ricerca")
public class SearchBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchBox() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		ProductDao dao= new ProductDao();
		try {
			List<ProductBean> beans= (List<ProductBean>) dao.doRetrieveByQuery(query);
			 Gson gson = new Gson();
		        String json = gson.toJson(beans);
		        
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
