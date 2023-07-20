package control;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		List<OrdineBean> product= new ArrayList<>();
		OrdineDao dao = new OrdineDao();
		String ordtypeReq=(String) request.getParameter("OrdType");
		String ordtypeSes=(String) request.getSession().getAttribute(GestioneOrdine.OTYPE);
		String predicate=(String) request.getParameter("predicate");
		String text=(String) request.getParameter("testo");
		String text1=(String) request.getParameter("testo1");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		Date date2;
		
		Predicate<OrdineBean> filter;
		if(predicate==null || text==null || text.trim().isEmpty() || text1==null || text1.trim().isEmpty()) {
			
			filter = order -> true;
		}
		else {
			if(predicate.equals("data")) {
				 try {
					date1 = dateformat.parse(text);
					date2 = dateformat.parse(text1);
					filter = order -> {
						try {
							return dateformat.parse(order.getData()).compareTo(date1)>=0 && dateformat.parse(order.getData()).compareTo(date2)<=0;
						} catch (ParseException e) {
							return false;
						}
						
					};
				} catch (ParseException e) {
					filter = order -> true;
				}
				 
			}
			else if (predicate.equals("cliente")) {
				filter = order -> String.valueOf(order.getCodCliente()).equals(text);
			}
			else {
				filter = order -> true;
			}
		}
		
		try {
			product= (List<OrdineBean>) dao.doRetrieveAll();
			
		} catch (SQLException e) {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		if(ordtypeReq==null && ordtypeSes==null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);
		}
		if(ordtypeReq!=null) {
			if(ordtypeReq.equals("1")){
				
				request.getSession().setAttribute(GestioneOrdine.OTYPE, "1");
				product=product.stream().filter(a->!a.getIsProcessed()).filter(filter).toList();
			}
			else {product=product.stream().filter(a->a.getIsProcessed()).filter(filter).toList();
			request.getSession().setAttribute(GestioneOrdine.OTYPE, "0");
			
			}
		}
		else {
			if(ordtypeSes.equals("1")){
				product=product.stream().filter(a->!a.getIsProcessed()).filter(filter).toList();
				
			}
			else { product=product.stream().filter(a->a.getIsProcessed()).filter(filter).toList();
			
			}
		}
		
		
		if(product.isEmpty()) {
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("void", false);
			request.setAttribute("tipo",new OrdineBean());
		  request.setAttribute("prodotti", product);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione.jsp");
		dispatcher.forward(request, response);
	}
	
	}

}
