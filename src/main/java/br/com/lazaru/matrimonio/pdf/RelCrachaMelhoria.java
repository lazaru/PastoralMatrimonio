package br.com.lazaru.matrimonio.pdf;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import br.com.lazaru.matrimonio.bean.CasalBase;
import br.com.lazaru.matrimonio.bean.Dados;

public class RelCrachaMelhoria {
	//https://github.com/vandeseer/easytable
	//https://github.com/vandeseer/easytable/blob/master/src/test/java/org/vandeseer/integrationtest/ExcelLikeExampleTest.java
		
	public static void gerarCrachaEquipe(Dados dados, File diretorio) throws Exception {
		InputStream in = RelCrachaMelhoria.class.getResourceAsStream("sagradafam2.png");
		
		BufferedImage bi = ImageIO.read(in);
		Image img = new Image(bi);
		String outputFileName;

		List<CasalBase> casais = new ArrayList<CasalBase>();
//		for(CasalBase cb:dados.getCasais()) {
//			casais.addAll(dados.getCasais());
//		}
		casais.addAll(dados.getCasais());
		casais.addAll(dados.getEncontristas());
		if ((diretorio == null) || (!diretorio.exists())) {
			outputFileName = "Cracha Equipe.pdf";
		} else {
			outputFileName = diretorio.getPath().toString() + "\\Cracha.pdf";
		}
		// Create a new font object selecting one of the PDF base fonts
//		PDFont fontPlain = PDType1Font.HELVETICA;
//		PDFont fontBold = PDType1Font.HELVETICA_BOLD;
//		PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
//		PDFont fontMono = PDType1Font.COURIER;

		// Create a document and add a page to it
		PDDocument document = new PDDocument();		
		PDPage page = addNewPage(document);
		document.addPage(page);

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream cos = new PDPageContentStream(document, page);
		int margin = 15;
		float startNewPageY = page.getMediaBox().getHeight() - margin;
		//int bottomMargin = 100;
		int leftMargin = 18;

		int spaceBetweenTables = leftMargin;

		// we want 2 tables so our table width is 50% of page width without left and
		// right margin AND provided space between tables
		float tableWidth = 0.5f * (page.getMediaBox().getWidth() - (2 * leftMargin) - spaceBetweenTables);
		System.out.println("------------------ nava pagina--------------");
		for (CasalBase e : casais) {
			float alturaTable= (float) 157.23282; //valor retornado pelo metodo table1.getHeaderAndDataHeight();			
			
			System.out.println("cracha:"+startNewPageY);
			
			if ((e.getHomem() != null) && (!e.getHomem().isEmpty())) {
				BaseTable table1 = new BaseTable(startNewPageY, startNewPageY, margin, tableWidth, leftMargin,	document, page, true, true);
				// the parameter is the row height
				Row<PDPage> row = table1.createRow(100);

				Cell<PDPage> cell = row.createImageCell(25, img, HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
				cell.setRightBorderStyle(new LineStyle(Color.white, 0));
				// row = table1.createRow(20);

				StringBuilder cellHtml = new StringBuilder("Paróquia N. Srª Das Dores Preparação dos Noivos<br/><br/>");
				if ((e.getMulher() != null) && (!e.getMulher().isEmpty())) {
					cellHtml.append("<b>").append(e.getHomem()).append("</b><br/><br/>");
					cellHtml.append("Da<br/><br/>").append("<b>").append(e.getMulher()).append("</b><br/><br/>");
				} else {
					cellHtml.append("<br/><br/><b>").append(e.getHomem()).append("</b><br/><br/><br/><br/>");
					//cellHtml.append("<br/><br/><br/>");
				}

				cellHtml.append("Equipe Sagrada Família<br/>");
				cell = row.createCell(75, cellHtml.toString());
				cell.setAlign(HorizontalAlignment.CENTER);
				cell.setValign(VerticalAlignment.MIDDLE);
				cell.setFontSize(15);
				table1.draw();
			}

			if ((e.getMulher() != null) && (!e.getMulher().isEmpty())) {
				// pay attention where start x position for this table -> left margin + first
				// table width + space between our tables
				BaseTable table2 = new BaseTable(startNewPageY, startNewPageY, margin, tableWidth,	leftMargin + tableWidth + spaceBetweenTables, document, page, true, true);

				// the parameter is the row height
				Row<PDPage> row = table2.createRow(100);

				Cell<PDPage> cell = row.createImageCell(25, img, HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
				cell.setRightBorderStyle(new LineStyle(Color.white, 0));
				// row = table2.createRow(20);
				StringBuilder cellHtml = new StringBuilder("Paróquia N. Srª Das Dores Preparação dos Noivos<br/><br/>");
				if ((e.getHomem() != null) && (!e.getHomem().isEmpty())) {
					cellHtml.append("<b>").append(e.getMulher()).append("</b><br/><br/>");
					cellHtml.append("Do<br/><br/>").append("<b>").append(e.getHomem()).append("</b><br/><br/>");
				} else {
					cellHtml.append("<br/><br/><b>").append(e.getMulher()).append("</b><br/><br/><br/><br/>");
					//cellHtml.append("<br/><br/><br/>");
				}

				cellHtml.append("Equipe Sagrada Família<br/>");
				cell = row.createTableCell(75, cellHtml.toString(), document, page, startNewPageY, margin, margin);
				cell.setFontSize(30);
				cell.setAlign(HorizontalAlignment.CENTER);
				cell.setValign(VerticalAlignment.MIDDLE);
				table2.draw();	
			}
			startNewPageY -= alturaTable+leftMargin;
			if(startNewPageY < (alturaTable+leftMargin)) {	
				startNewPageY = page.getMediaBox().getHeight() - margin;
				page = addNewPage(document);
				System.out.println("------------------ nava pagina--------------");
			}
			

			
		}

		// close the content stream
		cos.close();

		// Save the results and ensure that the document is properly closed:
		document.save(outputFileName);
		document.close();
	}
	private static PDPage addNewPage(PDDocument doc) {
		PDPage page = new PDPage(PDRectangle.A4);
		doc.addPage(page);
		return page;
	}
}
