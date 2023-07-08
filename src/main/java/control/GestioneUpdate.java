package control;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.OrdineBean;
import model.OrdineDao;
import model.ProductBean;
import model.ProductDao;
import model.PromozioniBean;
import model.PromozioniDao;

@WebServlet("/admin/GestioneUpdate")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class GestioneUpdate extends HttpServlet {
	private static final String UPDT_BTN="updateBtn";
	private static final long serialVersionUID = 1L;
     
    public GestioneUpdate() {
        super();
        
    }

	
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
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
			operazione= request.getParameter(GestioneUpdate.UPDT_BTN);
			riga=String.valueOf(Integer.parseInt(operazione.substring(1, operazione.length())));
			ProductBean prodotto=new ProductBean();
			ProductDao dao=new ProductDao();
			dispatcherToLoginPage= request.getRequestDispatcher("/admin/GestioneProdotti");
			if(operazione.charAt(0)=='0') { // operazione di salvataggio
				
				String codice=request.getParameter("codice"+riga);
				String nome=request.getParameter("nome"+riga);
				String categoria=request.getParameter("categoria"+riga);
				String prezzo=request.getParameter("prezzo"+riga);
				Part filePart = request.getPart("immagine"+riga);
				
				String descrizione=request.getParameter("descrizione"+riga);
				String quantita=request.getParameter("quantita"+riga);
				Blob blob = null;
				if(codice == null || codice.trim().isEmpty() || nome == null || nome.trim().isEmpty() || categoria == null || categoria.trim().isEmpty() || prezzo == null || prezzo.trim().isEmpty() ) {
					errors.add("Non lasciare i campi vuoti!!!");
					request.setAttribute("errors", errors);
					dispatcherToLoginPage.forward(request, response);
	            	return;
				}
				
				InputStream inputStream = filePart.getInputStream();
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        byte[] buffer = new byte[4096];
		        int bytesRead;
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outputStream.write(buffer, 0, bytesRead);
		        }
		        byte[] fileData = outputStream.toByteArray();

		        
		        try {
		            blob = new javax.sql.rowset.serial.SerialBlob(fileData);
		        } catch (Exception e) {
		        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		        }

				
				
				
				prodotto.setCodice(Integer.parseInt(codice));
				prodotto.setNome(nome);
				prodotto.setCategoria(categoria);
				prodotto.setPrezzo(Double.parseDouble(prezzo));
				prodotto.setImmagine(blob);
				prodotto.setDescrizione(descrizione);
				prodotto.setQuantita(Integer.parseInt(quantita));
				
				
				try {
					dao.doUpdate(prodotto);
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			else { //operazione di eliminazione
				try {
					
					dao.doDelete(Integer.parseInt(riga));
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			break;
		case "PromozioniBean": //posso Solo eliminarle
			dispatcher = getServletContext().getRequestDispatcher("/admin/GestionePromozioni");
			operazione= request.getParameter(GestioneUpdate.UPDT_BTN);
			riga=operazione.substring(1, operazione.length());
			
			PromozioniDao daopromoz=new PromozioniDao();
			
				try {
					
					daopromoz.doDelete(riga);
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			
			break;
		case "OrdineBean":
			dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneOrdine");
			operazione= request.getParameter(GestioneUpdate.UPDT_BTN);
			riga=String.valueOf(Integer.parseInt(operazione.substring(1, operazione.length())));
			
			OrdineDao daoOrdine=new OrdineDao();
			if(operazione.charAt(0)=='0') { // operazione di salvataggio
				
				int codice=Integer.parseInt(request.getParameter("codice"+riga));
				
			
				try {
					daoOrdine.doUpdate(codice);
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			else { //operazione di eliminazione
				try {
					
					daoOrdine.doDelete(Integer.parseInt(riga));
					
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			break;
			
			
			default:
				break;
		}
	}
	

}
