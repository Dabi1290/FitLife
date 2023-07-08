package control;

import java.io.ByteArrayOutputStream;
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

import model.ProductBean;
import model.ProductDao;
import model.PromozioniBean;
import model.PromozioniDao;

@WebServlet("/admin/Insert")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class GestioneInserimento extends HttpServlet {
	private static final String IMAGE_ERROR = "Manca L'immagine!!";
	private static final String ERROR="errors";
	private static final long serialVersionUID = 1L;
    
    public GestioneInserimento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type=request.getParameter("tipo");
		List<String> errors = new ArrayList<>();
		RequestDispatcher dispatcherToLoginPage ;
		Part filePart;
		Blob blob = null;
		switch(type) {
		case "ProductBean":
			dispatcherToLoginPage= request.getRequestDispatcher("/admin/GestioneProdotti");
			ProductBean prodotto=new ProductBean();
			ProductDao dao=new ProductDao();
			String nome=request.getParameter("nome");
			String categoria=request.getParameter("categoria");
			String prezzo=request.getParameter("prezzo");
			
			try {
				filePart = request.getPart("Immagine");
			} catch (IOException e3) {
				errors.add(GestioneInserimento.IMAGE_ERROR);
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			} catch (ServletException e3) {
				errors.add(GestioneInserimento.IMAGE_ERROR);
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			}

			String descrizione=request.getParameter("Descrizione");
			String quantità=request.getParameter("quantità");
			
			Double newp;
			int newq;
			if(nome == null || nome.trim().isEmpty() || categoria == null || categoria.trim().isEmpty() || prezzo == null || prezzo.trim().isEmpty()) {
				errors.add("Non puoi inserire campi vuoti!!!");
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			}
			
			try {
				newp=Double.parseDouble(prezzo);
			} catch (NumberFormatException e1) {
				errors.add("Prezzo Errato!!!");
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			}
			
			
			try {
				newq=Integer.parseInt(quantità);
				if(newq<0)throw new NumberFormatException();
			} catch (NumberFormatException e2) {
				errors.add("Problema con la quantità");
				request.setAttribute(GestioneInserimento.ERROR, errors);
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
	            e.printStackTrace();
	        }
			
			
				prodotto.setNome(nome);
				prodotto.setCategoria(categoria);
				prodotto.setPrezzo(newp);
				prodotto.setImmagine(blob);
				prodotto.setDescrizione(descrizione);
				prodotto.setQuantità(newq);
				
				try {
					dao.doSave(prodotto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
			
			break;
		case "PromozioniBean":
			dispatcherToLoginPage= request.getRequestDispatcher("/admin/GestionePromozioni");
			PromozioniBean promoz=new PromozioniBean();
			PromozioniDao daopromoz=new PromozioniDao();
			String codice=request.getParameter("codice");
			String cat=request.getParameter("categoria");
			Boolean IsCategoria=Boolean.parseBoolean(request.getParameter("IsCategoria"));
			
			try {
				filePart = request.getPart("Immagine");
			} catch (IOException e3) {
				errors.add(GestioneInserimento.IMAGE_ERROR);
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			} catch (ServletException e3) {
				errors.add(GestioneInserimento.IMAGE_ERROR);
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			}
			
			if(codice == null || codice.trim().isEmpty() || cat == null || cat.trim().isEmpty()) {
				errors.add("Non puoi inserire campi vuoti!!!");
				request.setAttribute(GestioneInserimento.ERROR, errors);
				dispatcherToLoginPage.forward(request, response);
            	return;
			}
			
			try {
				promoz=daopromoz.doRetrieveByKey(codice);
				if(promoz.getCodice().isEmpty()==false) {
					errors.add("Promozione già esistente!!!");
					request.setAttribute(GestioneInserimento.ERROR, errors);
					dispatcherToLoginPage.forward(request, response);
	            	return;
				}
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			inputStream = filePart.getInputStream();
			outputStream = new ByteArrayOutputStream();
	        buffer = new byte[4096];
	        
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        fileData = outputStream.toByteArray();

	        
	        try {
	            blob = new javax.sql.rowset.serial.SerialBlob(fileData);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
				promoz.setCodice(codice);
				promoz.setCategoria(cat);
				promoz.setIsCategoria(IsCategoria);
				promoz.setImmagine(blob);
				try {
					daopromoz.doSave(promoz);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestionePromozioni");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			break;
		
			
			default:
				break;
		}
	}

}
