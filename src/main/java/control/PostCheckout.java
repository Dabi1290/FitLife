package control;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.annotation.*;




import model.*;



@WebServlet("/PostCheckout")
public class PostCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PostCheckout() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		BufferedReader reader = request.getReader();
		    StringBuilder sb = new StringBuilder();
		    String line;
		    UserBean user= new UserBean();
		    UserDao dao= new UserDao();
		    int code= (int)request.getSession().getAttribute("userCode");
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		    String json = sb.toString();

		    
		    ObjectMapper objectMapper = new ObjectMapper();
		    

		    try {
		        PostCheck data = objectMapper.readValue(json, PostCheck.class);

		       
		        
		        String nome = data.getNome();
		        String cognome = data.getCognome();
		        String indirizzo = data.getIndirizzo();
		        String telefono = data.getTelefono();
		        
		        
		        
		        user.setNome(nome);
		        user.setCognome(cognome);
		        user.setIndirizzo(indirizzo);
		        user.setTelefono(telefono);
		        user.setCodice(code);
		        
		        dao.doUpdateAfterOrder(user);
		        
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/FinalizzaOrdine");
		        dispatcher.forward(request, response);
		        
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}

}
