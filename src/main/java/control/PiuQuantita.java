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

import model.ProductBean;
import model.ProductDao;


@WebServlet("/NumberOfProductPlus")
public class PiuQuantita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PiuQuantita() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String req= request.getParameter("qty");
		String key= request.getParameter("query");
		
		if(req.trim().isEmpty() || key.trim().isEmpty()
								|| req==null
								|| key==null) response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		
		int qtyReq= Integer.parseInt(req);
		int realkey= Integer.parseInt(key);
		ProductDao dao = new ProductDao();
		try {
			ProductBean bean= dao.doRetrieveByKey(realkey);
			if(bean.getQuantita()<=qtyReq)response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (SQLException e) {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
		Gson gson = new Gson();
        String json = gson.toJson("True");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
	}

}
