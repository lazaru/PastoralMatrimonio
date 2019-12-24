package br.com.lazaru.matrimonio.pdf;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Table;

public class TableUtil {
	
	private static final float PADDING = 20f;
	
	public static void createAndSaveDocumentWithTable(String outputFileName, List<Table> tables) throws IOException {
        createAndSaveDocumentWithTables(outputFileName, tables);
    }

    public static void createAndSaveDocumentWithTables(String outputFileName, List<Table> tables) throws IOException {
        createAndSaveDocumentWithTables(new PDDocument(), outputFileName, tables);
    }

    public static void createAndSaveDocumentWithTables(PDDocument document2, String outputFileName, List<Table> tables) throws IOException {

//        PDPage page = new PDPage(PDRectangle.A4);
//        document.addPage(page);

       // float HeightDoc = page.getMediaBox().getHeight() - PADDING;

        try (final PDDocument document = new PDDocument()) {
        //try (final PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            for (final Table table : tables) {            	
                TableDrawer.builder()
                        //.contentStream(contentStream)
                        .table(table)
                        .startX( PADDING )
                        .startY( PDRectangle.A4.getHeight() - PADDING )
                        .endY( PADDING )
                        .build()
                        .draw(() -> document, () -> new PDPage(PDRectangle.A4), PADDING);
//                        .draw();
                
//                System.out.println("escrevi em " + HeightDoc);
////                widthTable = table.getWidth()+ PADDING;
//                
//                float need = ((table.getHeight()+40) - (2 * PADDING)); 
//                
//                if(need > HeightDoc){
//                	page = new PDPage(PDRectangle.A4);
//                	HeightDoc += page.getMediaBox().getHeight() - PADDING;
//                	document.addPage(page);
//                	System.out.println("reiniciou");
//                }else {
//                	System.out.println(" ainda tem espaço para "+ need );
//                	HeightDoc -= need;
//                }
               
//                System.out.println("altura "+HeightDoc);
//                if(((2 * widthTable) + PADDING) > WidthDoc) {
//                	widthTable = 0;
//                	startY -= (table.getHeight() + PADDING);
//                	WidthDoc = page.getMediaBox().getWidth();
//                }else {
//                	WidthDoc -= widthTable;
//                }
            }
            document.save(outputFileName);

        }

//        document.save(outputFileName);
//        document.close();

    }
}
