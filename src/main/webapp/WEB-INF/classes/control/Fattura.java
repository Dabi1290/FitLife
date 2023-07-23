package control;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import model.SelledProductBean;
import model.SelledProductDao;



@WebServlet("/Fattura")
public class Fattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Fattura() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int code = Integer.parseInt(request.getParameter("ordcode"))  ;
		SelledProductDao dao = new SelledProductDao();
		List<SelledProductBean> products=null;
		try {
			products = (List<SelledProductBean>) dao.doRetrieveByOrder(code);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

        // Set response content type and attachment header
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=invoice.pdf");

        // Generate the PDF invoice dynamically and write it to the response output stream
        try (OutputStream out = response.getOutputStream()) {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Add the content to the PDF
            addContentToPdf(document, products);

            document.save(out);
            document.close();
	}}
        private void addContentToPdf(PDDocument document, List<SelledProductBean> products) throws IOException {
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Invoice");
            contentStream.endText();
            drawTable(contentStream, 100, 680, 4, "Name", "Price", "Quantity", "Total");
            
            
            

            int y = 660; // Y-coordinate for the first row
            for (SelledProductBean product : products) {
            	
                drawTableRow(contentStream, 100, y, product.getNome(), String.valueOf(product.getPrezzo()),String.valueOf(product.getQuantita()), String.valueOf(product.getPrezzo() * product.getQuantita()));
                y -= 20; // Move to the next row (reduce Y-coordinate for the next row)
            }

            
            contentStream.close();
        }
        private void drawTable(PDPageContentStream contentStream, float startX, float startY, int columns, String... headers) throws IOException {
            final int rows = 1;
            final float rowHeight = 20;
            final float tableWidth = 400;
            final float tableHeight = rowHeight * rows;
            final float columnWidth = tableWidth /  columns;
            final float tableMargin = 5;

            // Draw the table borders
            contentStream.drawLine(startX, startY, startX + tableWidth, startY); // Top border
            contentStream.drawLine(startX, startY - tableHeight, startX + tableWidth, startY - tableHeight); // Bottom border
            contentStream.drawLine(startX, startY, startX, startY - tableHeight); // Left border
            contentStream.drawLine(startX + tableWidth, startY, startX + tableWidth, startY - tableHeight); // Right border

            // Draw the table headers
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float textX = startX + tableMargin;
            float textY = startY - 15;
            for (String header : headers) {
                contentStream.beginText();
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText(header);
                contentStream.endText();
                textX += columnWidth;
            }
        }

        private void drawTableRow(PDPageContentStream contentStream, float startX, float startY, String... values) throws IOException {
            final int columns = values.length;
            
            final float tableWidth = 400;
            final float columnWidth = tableWidth /  columns;
            final float tableMargin = 5;

            float textX = startX + tableMargin;
            float textY = startY - 15;
            for (String value : values) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText(value);
                contentStream.endText();
                textX += columnWidth;
            }
        }

}
