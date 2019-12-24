package br.com.lazaru.matrimonio;

import java.util.Collections;

import br.com.lazaru.matrimonio.bean.Dados;
import br.com.lazaru.matrimonio.pdf.Certificado;
import br.com.lazaru.matrimonio.pdf.RelCrachaNovo;
import br.com.lazaru.matrimonio.pdf.RelEncontristas;
import br.com.lazaru.matrimonio.pdf.RelEquipeMatrimonio;
import br.com.lazaru.matrimonio.pdf.RelEtiquetaPasta;

public class Main {

	
	public static void main(String[] args) throws Exception {

//		String tel = "34 9253-7191";
//		String tel2 = "9253-7191";
//		String tel3 = "34 99253-7191";
//		String tel4 = "034 99253-7191";
//		String tel5 = "034 9253-7191";
		
		Dados dados = Dados.getFromFile();

		//ordena
		Collections.sort(dados.getEncontristas());
		Collections.sort(dados.getCasais());
		
		RelEtiquetaPasta.gerarEtiqueta(dados.getEncontristas(),dados.getDatasPreparacao(), null);
		//RelCracha.gerarCrachaEquipe(dados, null);
		RelCrachaNovo.createDocumentWithExcelLikeTables(dados);
		RelEquipeMatrimonio.gerarRelatorio(dados.getCasais(), null);
		RelEncontristas.gerarRelatorio(dados.getEncontristas(), null);
		Certificado.gerarRelatorio(dados.getEncontristas(), dados.getDatasPreparacao());
		Dados.saveToFile(dados);
	}

}
