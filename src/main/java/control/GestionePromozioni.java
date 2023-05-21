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

import model.PromozioniBean;
import model.PromozioniDao;


/**
 * Servlet implementation class GestioneClienti
 */
@WebServlet("/admin/GestionePromozioni")
public class GestionePromozioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionePromozioni() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<PromozioniBean> product= new ArrayList<>();
		PromozioniDao dao = new PromozioniDao();
		try {
			product= (List<PromozioniBean>) dao.doRetrieveAll("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  request.getSession().setAttribute("prodotti", product);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
