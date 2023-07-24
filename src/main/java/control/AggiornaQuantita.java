package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarrelloBean;
import model.CarrelloDao;
import model.CarrelloGuest;
import model.ProductBean;


@WebServlet("/AggiornaQuantita")
public class AggiornaQuantita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AggiornaQuantita() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer query = Integer.parseInt(request.getParameter("query")) ;
		Integer qty = Integer.parseInt(request.getParameter("qty")) ;
		Integer code= (Integer) request.getSession().getAttribute("userCode");
		CarrelloGuest cart = (CarrelloGuest) request.getSession().getAttribute("Carrello"); 
		
		if(code==null) {
			for(ProductBean prod:cart.getProdotti()) {
				if(prod.getCodice()==query) {
					prod.setQuantita(qty);
					break;
				}
			}
		}
		else {
			CarrelloDao dao = new CarrelloDao();
			CarrelloBean bean= new CarrelloBean();
			bean.setQuantita(qty);
			bean.setCodiceProdotto(query);
			bean.setCodiceCliente(code);
			try {
				dao.doStrongUpdate(bean);
			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}

}
