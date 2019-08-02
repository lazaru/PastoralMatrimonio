package br.com.lazaru.matrimonio.pdf;

import java.awt.Color;
import java.io.File;
import java.util.List;

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
import be.quodlibet.boxable.line.LineStyle;
import br.com.lazaru.matrimonio.bean.Casal;

public class RelEquipeMatrimonio {

	public static void gerarRelatorio(List<Casal> equipe, File diretorio) throws Exception {
		String outputFileName;
	
		if ((diretorio==null)||(!diretorio.exists())) {
			outputFileName = "Pastoral do Matrimônio.pdf";
		}else {
			outputFileName = diretorio.getPath().toString() +"\\Pastoral do Matrimônio.pdf";
		}
		// Create a new font object selecting one of the PDF base fonts
		PDFont fontPlain = PDType1Font.HELVETICA;
		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
		PDFont fontMono = PDType1Font.COURIER;

		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
		// PDRectangle.LETTER and others are also possible
		PDRectangle rect = page.getMediaBox();
		// rect can be used to get the page width and height
		document.addPage(page);

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream cos = new PDPageContentStream(document, page);

		// Dummy Table
		float margin = 15;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - margin;
		// we want table across whole page width (subtracted by left and right margin
		// ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float bottomMargin = 10;
		// y position is your coordinate of top left corner of the table
		float yPosition = page.getMediaBox().getHeight() - margin;//550;

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page,
				true, drawContent);

		// the parameter is the row height
		Row<PDPage> headerRow1 = table.createRow(50);
		// the first parameter is the cell width

		Cell<PDPage> cell = headerRow1.createCell(100, "<p>PASTORAL DO MATRIMÔNIO N.SRA DAS DORES</p><p>EQUIPE SAGRADA FAMÍLIA</p>");

		cell.setFont(fontBold);
		cell.setFontSize(20);
		
		cell.setAlign(HorizontalAlignment.CENTER);
		cell.setValign(VerticalAlignment.MIDDLE);
		// border style
		cell.setTopBorderStyle(new LineStyle(Color.BLACK, 1));		
		table.addHeaderRow(headerRow1);

		// the parameter is the row height
		Row<PDPage> headerRow2 = table.createRow(20);
		// the first parameter is the cell width

		cell = headerRow2.createCell(30, "Nome");
		cell.setFontSize(15);		
		cell.setFont(fontBold);
		cell.setAlign(HorizontalAlignment.CENTER);				
		cell.setValign(VerticalAlignment.MIDDLE);
		cell = headerRow2.createCell(55, "Atividade");
		cell.setFontSize(15);		
		cell.setFont(fontBold);
		cell.setAlign(HorizontalAlignment.CENTER);				
		cell.setValign(VerticalAlignment.MIDDLE);
		cell = headerRow2.createCell(15, "Telefone");
		cell.setFontSize(15);		
		cell.setFont(fontBold);
		cell.setAlign(HorizontalAlignment.CENTER);				
		cell.setValign(VerticalAlignment.MIDDLE);
		// border style
		cell.setTopBorderStyle(new LineStyle(Color.BLACK, 1));
		table.addHeaderRow(headerRow2);
				
		for (Casal e : equipe) {			
			Row<PDPage> row = table.createRow(20);
			cell = row.createCell(30, e.getCasal());
			cell.setFontSize(15);

			cell = row.createCell(55, e.getAtividade());
			cell.setFontSize(15);

			cell = row.createCell(15, e.getTelefone());
			cell.setFontSize(15);

		}

		table.draw();

		

		// close the content stream
		cos.close();

		// Save the results and ensure that the document is properly closed:
		document.save(outputFileName);
		document.close();
	}
}
