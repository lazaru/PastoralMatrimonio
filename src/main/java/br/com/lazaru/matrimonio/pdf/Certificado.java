package br.com.lazaru.matrimonio.pdf;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
 
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import br.com.lazaru.matrimonio.bean.Encontrista;

public class Certificado {

	/** user space units per inch */
    private static final float POINTS_PER_INCH = 72;
    
    /** user space units per millimeter */
    private static final float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;
    
    public static final PDRectangle CERTIFICADO = new PDRectangle(207 * POINTS_PER_MM, 260 * POINTS_PER_MM);
    
    private static float points(float point) {
    	return POINTS_PER_MM * point;
    }
    
    private static String getMes(String mes) {
    	switch (mes) {
		case "1":
			return "Janeiro";
		case "2":
			return "Fevereiro";
		case "3":
			return "Março";
		case "4":
			return "Abril";
		case "5":
			return "Maio";
		case "6":
			return "Junho";
		case "7":
			return "Julho";	
		case "8":
			return "Agosto";
		case "9":
			return "Setembro";
		case "10":
			return "Outubro";
		case "11":
			return "Novembro";
		case "12":
			return "Dezembro";	
		default:
			return"";
		}
    }
    
	public static void gerarRelatorio(List<Encontrista> encontristas, String data) {
		String filename = "Certificados.pdf";
		String dia1="", dia2="", mes="", ano="";
		try {
			//4 a 5/8/2019
			String[] dt = data.split(" a ");
			dia1 = dt[0];
			dt = dt[1].split("/");
			dia2 = dt[0];
			mes =  getMes(dt[1]);
			ano =  dt[2];
			if(ano.length()==4) {
				ano = ano.substring(2);
			}
		}catch (Exception e) {
			System.out.println("Atenção a data da preparação deve estar no formato:\"4 a 5/8/2019\"");
		}
		
        PDDocument doc = new PDDocument();
        try {
            for(Encontrista e:encontristas) {
            	PDPage page = new PDPage(CERTIFICADO);
            	doc.addPage(page);
            	gerarCertificado(doc, page, e.getHomem(),e.getMulher(), dia1, dia2, mes, ano);
            }
            doc.save(filename);
        }catch (Exception e) {
			e.printStackTrace();
		}
        finally {
            try {
				doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	private static void gerarCertificado(PDDocument doc, PDPage page, String homem, String mulher, String dia1, String dia2, String mes, String ano) throws IOException {
		PDPageContentStream contents = new PDPageContentStream(doc, page);
        
        final float startY = page.getMediaBox().getHeight();
        final float startX = points(4.5f);
        PDFont font = PDType1Font.TIMES_ROMAN;
        
        contents.setLeading(12 * 1.2f);
        
        contents.beginText();
        contents.setFont(font, 18);
        contents.newLineAtOffset(startX+ points(51.2f), startY - points(153) );//170
        contents.showText(homem);
        contents.endText();
        contents.beginText();
        contents.newLineAtOffset(startX+points(8), startY - points(165));
        contents.showText(mulher);
        contents.endText();
        
        contents.beginText();
        contents.newLineAtOffset(startX+points(3), startY - points(192));
        contents.showText(dia1+" e "+dia2+" de "+mes);
        contents.endText();
        
        contents.beginText();
        contents.newLineAtOffset(startX+points(80), startY - points(192));
        contents.showText(ano);
        contents.endText();
        
        contents.beginText();
        contents.newLineAtOffset(startX+points(105), startY - points(213));
        contents.showText(dia2);
        contents.endText();
        
        contents.beginText();
        contents.newLineAtOffset(startX+points(125), startY - points(213));
        contents.showText(mes);
        contents.endText();
        
        contents.beginText();
        contents.newLineAtOffset(startX+points(178), startY - points(213));
        contents.showText(ano);
        contents.endText();
        contents.close();
		
	}
}
