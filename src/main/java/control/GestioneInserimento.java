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
 * Servlet implementation class GestioneInserimento
 */
@WebServlet("/admin/Insert")
public class GestioneInserimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneInserimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type=request.getParameter("tipo");
		switch(type) {
		case "ProductBean":
			ProductBean prodotto=new ProductBean();
			ProductDao dao=new ProductDao();
			
				prodotto.setNome(request.getParameter("nome"));
				prodotto.setCategoria(request.getParameter("categoria"));
				prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
				prodotto.setAdmin(Integer.parseInt(request.getParameter("Admin")));
				try {
					dao.doSave(prodotto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			break;
		case "PromozioniBean":
			break;
		case "OrdineBean":
			break;
		}
	}

}
