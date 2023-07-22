package control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.CarrelloDao;
import model.OrdineBean;
import model.OrdineDao;
import model.ProductBean;
import model.SelledProductBean;
import model.SelledProductDao;


@WebServlet("/FinalizzaOrdine")
public class DoOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DoOrder() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer query = (int) request.getSession().getAttribute("userCode");
		CarrelloDao dao= new CarrelloDao();
		OrdineDao ordao= new OrdineDao();
		OrdineBean orbean= new OrdineBean();
		SelledProductDao venduto=new SelledProductDao();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ciao");
		
		orbean.setCodCliente(query);
		try{
			
			List<ProductBean> beans= (List<ProductBean>) dao.doRetrieveProducts(query);
			
			int codice= ordao.doSave(orbean, 0);
			
			
			for(ProductBean prodotto:beans) {
				SelledProductBean pippo= new SelledProductBean();
				pippo.setCategoria(prodotto.getCategoria());
				pippo.setNome(prodotto.getNome());
				pippo.setOrdine(codice);
				pippo.setPrezzo(prodotto.getPrezzo());
				pippo.setQuantita(prodotto.getQuantita());
				venduto.doSave(pippo);
				
			}
			
			dao.SvuotaCarrello(query);
			dispatcher.forward(request, response);
			
			
		} catch (SQLException e) {
			 			
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}
