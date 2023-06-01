package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDao;
import model.ProductBean;
import model.ProductDao;
import model.PromozioniBean;
import model.PromozioniDao;

/**
 * Servlet implementation class GestioneUpdate
 */
@WebServlet("/admin/GestioneUpdate")
public class GestioneUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("tipo");
		String operazione;
		String riga;
		List<String> errors = new ArrayList<>();
		RequestDispatcher dispatcherToLoginPage ;
		switch(type) {
		case "ProductBean":
			
			operazione= request.getParameter("updateBtn");
			riga=String.valueOf(Integer.parseInt(operazione.substring(1, operazione.length())));
			ProductBean prodotto=new ProductBean();
			ProductDao dao=new ProductDao();
			dispatcherToLoginPage= request.getRequestDispatcher("/admin/GestioneProdotti");
			if(operazione.charAt(0)=='0') { // operazione di salvataggio
				String codice=request.getParameter("codice"+riga);
				String nome=request.getParameter("nome"+riga);
				String categoria=request.getParameter("categoria"+riga);
				String prezzo=request.getParameter("prezzo"+riga);
				String admin=request.getParameter("Admin"+riga);
				if(codice == null || codice.trim().isEmpty() || nome == null || nome.trim().isEmpty() || categoria == null || categoria.trim().isEmpty() || prezzo == null || prezzo.trim().isEmpty() || admin == null || admin.trim().isEmpty()) {
					errors.add("Non lasciare i campi vuoti!!!");
					request.setAttribute("errors", errors);
					dispatcherToLoginPage.forward(request, response);
	            	return;
				}
				
				
				
				
				prodotto.setCodice(Integer.parseInt(codice));
				prodotto.setNome(nome);
				prodotto.setCategoria(categoria);
				prodotto.setPrezzo(Double.parseDouble(prezzo));
				prodotto.setAdmin(Integer.parseInt(admin));
				
				try {
					dao.doUpdate(prodotto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else { //operazione di eliminazione
				try {
					
					dao.doDelete(Integer.parseInt(riga));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			break;
		case "PromozioniBean": //posso Solo eliminarle
			operazione= request.getParameter("updateBtn");
			riga=operazione.substring(1, operazione.length());
			
			PromozioniDao daopromoz=new PromozioniDao();
			
				try {
					
					daopromoz.doDelete(riga);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestionePromozioni");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
			break;
		case "OrdineBean":
			operazione= request.getParameter("updateBtn");
			riga=String.valueOf(Integer.parseInt(operazione.substring(1, operazione.length())));
			OrdineBean ordine=new OrdineBean();
			OrdineDao daoOrdine=new OrdineDao();
			if(operazione.charAt(0)=='0') { // operazione di salvataggio
				
				int codice=Integer.parseInt(request.getParameter("codice"+riga));
				
			
				try {
					daoOrdine.doUpdate(codice);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneOrdine");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else { //operazione di eliminazione
				try {
					
					daoOrdine.doDelete(Integer.parseInt(riga));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneOrdine");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			break;
		}
	}

}
