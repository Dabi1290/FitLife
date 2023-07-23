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
    private static final String CODE="codice";
    public GestioneUpdate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String type = request.getParameter("tipo");

	    switch (type) {
	        case "ProductBean":
	            handleProductBean(request, response);
	            break;
	        case "PromozioniBean":
	            handlePromozioniBean(request, response);
	            break;
	        case "OrdineBean":
	            handleOrdineBean(request, response);
	            break;
	        default:
	            // Handle unknown type or invalid request
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            break;
	    }
	}

	private void handleProductBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String operazione = request.getParameter(GestioneUpdate.UPDT_BTN);
	    String riga = operazione.substring(1);
	    ProductDao dao = new ProductDao();
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneProdotti");

	    if (operazione.charAt(0) == '0') { // operazione di salvataggio
	        List<String> errors = validateProductFields(request, riga);
	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", errors);
	            dispatcher.forward(request, response);
	            return;
	        }

	        ProductBean prodotto = createProductBean(request, riga);
	        try {
	            dao.doUpdate(prodotto);
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    } else { //operazione di eliminazione
	        try {
	            dao.doDelete(Integer.parseInt(riga));
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
	}

	private List<String> validateProductFields(HttpServletRequest request, String riga) {
	    List<String> errors = new ArrayList<>();
	    String codice = request.getParameter(CODE + riga);
	    String nome = request.getParameter("nome" + riga);
	    String categoria = request.getParameter("categoria" + riga);
	    String prezzo = request.getParameter("prezzo" + riga);
	    String descrizione = request.getParameter("descrizione" + riga);
	    String quantita = request.getParameter("quantita" + riga);

	    if (codice == null || codice.trim().isEmpty()
	            || nome == null || nome.trim().isEmpty()
	            || categoria == null || categoria.trim().isEmpty()
	            || prezzo == null || prezzo.trim().isEmpty()
	            || descrizione==null || descrizione.trim().isEmpty()
	            || quantita==null || quantita.trim().isEmpty()) {
	        errors.add("Non lasciare i campi vuoti!!!");
	    }
	    return errors;
	}

	private ProductBean createProductBean(HttpServletRequest request, String riga) throws IOException, ServletException {
	    String codice = request.getParameter(CODE + riga);
	    String nome = request.getParameter("nome" + riga);
	    String categoria = request.getParameter("categoria" + riga);
	    String prezzo = request.getParameter("prezzo" + riga);
	    String descrizione = request.getParameter("descrizione" + riga);
	    String quantita = request.getParameter("quantita" + riga);
	    Part filePart = request.getPart("immagine" + riga);
	    int newc = Integer.parseInt(categoria);

	    InputStream inputStream = filePart.getInputStream();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	        outputStream.write(buffer, 0, bytesRead);
	    }
	    byte[] fileData = outputStream.toByteArray();
	    Blob blob = null;
	    try {
	        blob = new javax.sql.rowset.serial.SerialBlob(fileData);
	    } catch (Exception e) {
	        // Handle Blob creation error
	    }

	    ProductBean prodotto = new ProductBean();
	    prodotto.setCodice(Integer.parseInt(codice));
	    prodotto.setNome(nome);
	    prodotto.setCategoria(newc);
	    prodotto.setPrezzo(Double.parseDouble(prezzo));
	    prodotto.setImmagine(blob);
	    prodotto.setDescrizione(descrizione);
	    prodotto.setQuantita(Integer.parseInt(quantita));

	    return prodotto;
	}

	private void handlePromozioniBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String operazione = request.getParameter(GestioneUpdate.UPDT_BTN);
	    String riga = operazione.substring(1);
	    PromozioniDao daopromoz = new PromozioniDao();
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestionePromozioni");

	    try {
	        daopromoz.doDelete(riga);
	        dispatcher.forward(request, response);
	    } catch (SQLException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}

	private void handleOrdineBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String operazione = request.getParameter(GestioneUpdate.UPDT_BTN);
	    String riga = operazione.substring(1);
	    OrdineDao daoOrdine = new OrdineDao();
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/GestioneOrdine");

	    if (operazione.charAt(0) == '0') { // operazione di salvataggio
	        int codice = Integer.parseInt(request.getParameter(CODE + riga));

	        try {
	            daoOrdine.doUpdate(codice);
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    } else { //operazione di eliminazione
	        try {
	            daoOrdine.doDelete(Integer.parseInt(riga));
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
	}

	

}
