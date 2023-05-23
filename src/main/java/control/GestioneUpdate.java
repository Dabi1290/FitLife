package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDao;

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
		String type=request.getParameter("tipo");
		switch(type) {
		case "ProductBean":
			String operazione= request.getParameter("updateBtn");
			String riga=String.valueOf(Integer.parseInt(operazione.substring(1, operazione.length())));
			ProductBean prodotto=new ProductBean();
			ProductDao dao=new ProductDao();
			if(operazione.charAt(0)=='0') { // operazione di salvataggio
				
				prodotto.setCodice(Integer.parseInt(request.getParameter("codice"+riga)));
				
				prodotto.setNome(request.getParameter("nome"+riga));
				prodotto.setCategoria(request.getParameter("categoria"+riga));
				prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo"+riga)));
				prodotto.setAdmin(Integer.parseInt(request.getParameter("Admin"+riga)));
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
					System.out.println(riga);
					dao.doDelete(Integer.parseInt(riga));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			break;
		case "PromozioniBean":
			break;
		case "OrdineBean":
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
