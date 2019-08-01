package br.com.lazaru.matrimonio.pdf;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import br.com.lazaru.matrimonio.bean.Encontrista;
import br.com.lazaru.matrimonio.bean.Casal;
import br.com.lazaru.matrimonio.bean.ICasal;

public class RelEtiquetaPasta {

	public static void gerarEtiqueta(List<Encontrista> casais, String periodo, File diretorio) throws Exception {
		String outputFileName;

		if ((diretorio == null) || (!diretorio.exists())) {
			outputFileName = "Etiquetas.pdf";
		} else {
			outputFileName = diretorio.getPath().toString() + "\\Etiquetas.pdf";
		}
		// Create a new font object selecting one of the PDF base fonts
		PDFont fontPlain = PDType1Font.HELVETICA;
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
		PDFont fontMono = PDType1Font.COURIER;

		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		// PDRectangle.LETTER and others are also possible
		PDRectangle rect = page.getMediaBox();
		// rect can be used to get the page width and height
		document.addPage(page);

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream cos = new PDPageContentStream(document, page);

		// Dummy Table
		float margin = 50;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - margin;
		// we want table across whole page width (subtracted by left and right margin
		// ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float bottomMargin = 70;
		// y position is your coordinate of top left corner of the table
		float yPosition = page.getMediaBox().getHeight() - margin;// 550;

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page,
				true, drawContent);		
		
		

		for(Encontrista e : casais) {
			Row<PDPage> row = table.createRow(50);
			StringBuilder sb = new StringBuilder();
			sb.append("<p>").append(e.getHomem()).append("</p>")
			  .append("<p>").append(e.getMulher()).append("</p>")
			  .append("<p>").append("    Data de ").append(periodo).append("</p>");			
			    
			Cell<PDPage> cell = row.createCell(100, sb.toString());
			cell.setFontSize(30);
			cell.setAlign(HorizontalAlignment.LEFT);
			cell.setValign(VerticalAlignment.MIDDLE);
			// border style
			cell.setTopBorderStyle(new LineStyle(Color.BLACK, 1));			
		}

		table.draw();

		// close the content stream
		cos.close();

		// Save the results and ensure that the document is properly closed:
		document.save(outputFileName);
		document.close();
	}	
}
