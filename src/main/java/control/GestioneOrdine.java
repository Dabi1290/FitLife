package control;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDao;


@WebServlet("/admin/GestioneOrdine")
public class GestioneOrdine extends HttpServlet {
	private static final String OTYPE="ordType";
	private static final long serialVersionUID = 1L;
   
	
    public GestioneOrdine() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ordtypeReq = request.getParameter("OrdType");
	    String ordtypeSes = (String) request.getSession().getAttribute(GestioneOrdine.OTYPE);
	    String predicate = request.getParameter("predicate");
	    String text = request.getParameter("testo");
	    String text1 = request.getParameter("testo1");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    Predicate<OrdineBean> filter = buildFilter(predicate, text, text1, dateFormat);

	    List<OrdineBean> product = retrieveFilteredOrders(filter, response);

	    handleOrderTypeAndForward(product, ordtypeReq, ordtypeSes, request, response);
	}

	private Predicate<OrdineBean> buildFilter(String predicate, String text, String text1, SimpleDateFormat dateFormat) {
	    if (predicate == null || predicate.isEmpty()) {
	        return order -> true;
	    } else if (predicate.equals("data") && text != null && text1 != null) {
	        try {
	            final Date date1 = dateFormat.parse(text);
	            final Date date2 = dateFormat.parse(text1);
	            if (date1.compareTo(date2) >= 0) {
	                return order -> true;
	            } else {
	                return order -> {
	                    try {
	                        Date orderDate = dateFormat.parse(order.getData());
	                        return orderDate.compareTo(date1) >= 0 && orderDate.compareTo(date2) <= 0;
	                    } catch (ParseException e) {
	                        return false;
	                    }
	                };
	            }
	        } catch (ParseException e) {
	            return order -> true;
	        }
	    } else if (predicate.equals("cliente") && text != null && !text.isEmpty()) {
	        return order -> String.valueOf(order.getCodCliente()).equals(text);
	    } else {
	        return order -> true;
	    }
	}

	private List<OrdineBean> retrieveFilteredOrders(Predicate<OrdineBean> filter, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        OrdineDao dao = new OrdineDao();
	        List<OrdineBean> product = (List<OrdineBean>) dao.doRetrieveAll();
	        return product.stream().filter(filter).toList();
	    } catch (SQLException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return Collections.emptyList();
	    }
	}

	private void handleOrderTypeAndForward(List<OrdineBean> product, String ordtypeReq, String ordtypeSes, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (ordtypeReq == null && ordtypeSes == null) {
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }

	    boolean isProcessed = (ordtypeReq != null) ? ordtypeReq.equals("1") : ordtypeSes.equals("1");
	    product = filterAndSetOrderType(product, isProcessed);
	    request.getSession().setAttribute(GestioneOrdine.OTYPE, isProcessed ? "1" : "0");

	    if (product.isEmpty()) {
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        request.setAttribute("void", false);
	        request.setAttribute("tipo", new OrdineBean());
	        request.setAttribute("prodotti", product);
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione.jsp");
	        dispatcher.forward(request, response);
	    }
	}

	private List<OrdineBean> filterAndSetOrderType(List<OrdineBean> product, boolean isProcessed) {
	    return product.stream()
	            .filter(order -> order.getIsProcessed() == isProcessed)
	            .toList();
	}


}
