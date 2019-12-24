package br.com.lazaru.matrimonio.pdf;

import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.GRAY;
import static java.awt.Color.LIGHT_GRAY;
import static java.awt.Color.WHITE;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD_OBLIQUE;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_OBLIQUE;
import static org.vandeseer.easytable.settings.HorizontalAlignment.CENTER;
import static org.vandeseer.easytable.settings.HorizontalAlignment.JUSTIFY;
import static org.vandeseer.easytable.settings.HorizontalAlignment.LEFT;
import static org.vandeseer.easytable.settings.HorizontalAlignment.RIGHT;
import static org.vandeseer.easytable.settings.VerticalAlignment.MIDDLE;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.settings.VerticalAlignment;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Row.RowBuilder;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.Table.TableBuilder;
import org.vandeseer.easytable.structure.cell.ImageCell;
import org.vandeseer.easytable.structure.cell.TextCell;

import br.com.lazaru.matrimonio.bean.CasalBase;
import br.com.lazaru.matrimonio.bean.Dados;

public class RelCrachaNovo {

    private final static Color BLUE_DARK = new Color(76, 129, 190);
    private final static Color BLUE_LIGHT_1 = new Color(186, 206, 230);
    private final static Color BLUE_LIGHT_2 = new Color(218, 230, 242);

    private final static Color GRAY_LIGHT_1 = new Color(245, 245, 245);
    private final static Color GRAY_LIGHT_2 = new Color(240, 240, 240);
    private final static Color GRAY_LIGHT_3 = new Color(216, 216, 216);
    private final static PDDocument pdDocument = new PDDocument();
    
    private static final float PADDING = 50f;
    
    public static void createDocumentWithExcelLikeTables(Dados dados) throws IOException {
    	List<CasalBase> casais = new ArrayList<CasalBase>();
		casais.addAll(dados.getCasais());
		casais.addAll(dados.getEncontristas());
		
		 TableDrawer.builder()
         .table(_createCracha(casais))
         .startX( PADDING-40 )
         .startY( PDRectangle.A4.getHeight() - PADDING )
         .endY( PADDING )
         .build()
         .draw(() -> pdDocument, () -> new PDPage(PDRectangle.A4), PADDING);
		 
		 pdDocument.save("Cracha.pdf");
		 pdDocument.close();
    }
  

	private static PDImageXObject createImage(PDDocument pdDocument, String path) throws IOException {
		final byte[] imgBytes = IOUtils.toByteArray(Objects.requireNonNull(RelCracha2.class.getResourceAsStream(path)));
        return PDImageXObject.createFromByteArray(pdDocument, imgBytes, "glider");
	}
	
	private static Table _createCracha(List<CasalBase> casais) throws IOException {

        final TableBuilder tableBuilder = Table.builder()
                .addColumnsOfWidth(60, 228, 60, 228)
                .borderColor(Color.BLACK)
                .textColor(Color.BLACK)
                .fontSize(15)
                .font(HELVETICA);
        for(CasalBase c:casais) {
    		if(c.isImprimeCracha()) {
    	
        String homem =c.getPrimeiroNomeHomem();
        String mulher=c.getPrimeiroNomeMulher();
        
        tableBuilder.addRow(Row.builder()
                .add(ImageCell.builder()
                        .verticalAlignment(MIDDLE)
                        .horizontalAlignment(CENTER)
                        .borderWidth(0).borderWidthTop(1).borderWidthLeft(1).borderWidthBottom(1)
                        .image(createImage(pdDocument, "sagradafam2.png"))
                        .scale(1.4f)
                        .rowSpan(6)
                        .build())
                .add(TextCell.builder().borderWidth(1).paddingLeft(28).paddingTop(28).text("Paróquia N. Srª Das Dores").horizontalAlignment(CENTER).borderWidth(0).borderWidthTop(1).borderWidthRight(1).build())
                
                
                
                .add(ImageCell.builder()
                        .verticalAlignment(MIDDLE)
                        .horizontalAlignment(CENTER)
                        .borderWidth(0).borderWidthTop(1).borderWidthLeft(1).borderWidthBottom(1)
                        .image(createImage(pdDocument, "sagradafam2.png"))
                        .scale(1.4f)                        
                        .rowSpan(6)
                        .build())
                .add(TextCell.builder().borderWidth(1).paddingLeft(28).paddingTop(28).text("Paróquia N. Srª Das Dores").horizontalAlignment(CENTER).borderWidth(0).borderWidthTop(1).borderWidthRight(1).build())
                .borderColor(Color.BLACK)                
                .build()
        );

        tableBuilder.addRow(Row.builder()
                .add(TextCell.builder().borderWidth(1).text("Preparação dos Noivos").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())

                .add(TextCell.builder().borderWidth(1).text("Preparação dos Noivos").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
                .build() );
        
        if((homem!=null&&!homem.isEmpty()) && (mulher!=null&&!mulher.isEmpty())) {
        	tableBuilder.addRow(Row.builder()
        				.add(TextCell.builder().borderWidth(1).text(homem).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
        				
        				.add(TextCell.builder().borderWidth(1).text(mulher).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
        				.build());
        	tableBuilder.addRow(Row.builder()
    				.add(TextCell.builder().borderWidth(1).text("da").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				
    				.add(TextCell.builder().borderWidth(1).text("do").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				.build() );
        	tableBuilder.addRow(Row.builder()
    				.add(TextCell.builder().borderWidth(1).text(mulher).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				
    				.add(TextCell.builder().borderWidth(1).text(homem).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				.build() );
        	}else {
        		String umApenas = ((homem!=null&&!homem.isEmpty())?homem:mulher);
        		String[] test = umApenas.split("\\s+");
        		tableBuilder.addRow(Row.builder()
        				.add(TextCell.builder().borderWidth(1).text(test[0]).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
        				
        				.add(TextCell.builder().borderWidth(1).text("").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
        				.build());
        		
        	tableBuilder.addRow(Row.builder()
    				.add(TextCell.builder().borderWidth(1).text(" ").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				
    				.add(TextCell.builder().borderWidth(1).text(" ").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				.build() );
        	
        	tableBuilder.addRow(Row.builder()
    				.add(TextCell.builder().borderWidth(1).text(test[1]).fontSize(30).horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				
    				.add(TextCell.builder().borderWidth(1).text("").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).build())
    				.build() );
        		

        	}

        tableBuilder.addRow(Row.builder()
                .add(TextCell.builder().borderWidth(1).paddingBottom(28).text("Equipe Sagrada Família").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).borderWidthBottom(1).build())                
                
                .add(TextCell.builder().borderWidth(1).paddingBottom(28).text("Equipe Sagrada Família").horizontalAlignment(CENTER).borderWidth(0).borderWidthRight(1).borderWidthBottom(1).build())
                .build() );
        
        }}
        return tableBuilder.build();
    
	}
	
	
}
