package br.com.lazaru.matrimonio;

import br.com.lazaru.matrimonio.bean.Dados;
import br.com.lazaru.matrimonio.pdf.RelCracha;
import br.com.lazaru.matrimonio.pdf.RelEquipeMatrimonio;
import br.com.lazaru.matrimonio.pdf.RelEtiquetaPasta;

public class Main {

	
	public static void main(String[] args) throws Exception {
/*
		casais = new ArrayList<String>();
		casais.add("Geraldo");
		casais.add("Alberto");
		casais.add("João");
		casais.add("Thiago");
		casais.add("Waiss");

		equipe = new ArrayList<String>();
		casais.add("Geraldo equipe");
		casais.add("Alberto equipe");
		casais.add("João equipe");
		casais.add("Thiago equipe");
		casais.add("Waiss equipe");
*/
		Dados dados = Dados.getFromFile();
//		Dados dados = new Dados();
//		
//		
//		List<Casal> equipe = new ArrayList<Casal>();
//		Casal eq = new Casal();
//		eq.setHomem("Pe. Flávio");
//		eq.setAtividade("Palestra - Sacramentos");
//		eq.setTelefone("3219-3316");
//		equipe.add(eq);
//		
//		eq = new Casal();
//		eq.setHomem("Mad. Tereza");
//		eq.setAtividade("Palestra - Sacramentos");
//		eq.setTelefone("3219-3316");
//		equipe.add(eq);
//		
//		for (int i = 0; i < 9; i++) {
//			eq = new Casal();
//			eq.setHomem("Geraldo");
//			eq.setMulher("Lúcia");
//			eq.setAtividade("Palestra - Amor conjugal - Diálogo - Conhecimento de si e do outro");
//			eq.setTelefone("99976-4376");
//			equipe.add(eq);
//		}
//
//		List<ICasal> eqp = new ArrayList<ICasal>();
//		for(Casal e:equipe) {
//			eqp.add(e);
//		}
//		
//		dados.setCasais(equipe);
//		
//		
//		List<Encontrista> casais= new ArrayList<Encontrista>();
//		for (int i = 0; i < 9; i++) {
//			Encontrista casal = new Encontrista();
//			casal.setHomem("homen nome comprido 123456456"+i);
//			casal.setMulher("mulher "+i);			
//			casal.setTelefoneHomem("99976-4376");
//			casal.setTelefoneMulher("99976-4377");
//			casais.add(casal);
//		}
//		dados.setDatasPreparacao("03 a 04/08/2019");
//		dados.setEncontristas(casais);
//		Dados.saveToFile(dados);

		//
		//
		
		RelEtiquetaPasta.gerarEtiqueta(dados.getEncontristas(),dados.getDatasPreparacao(), null);
		RelCracha.gerarCrachaEquipe(dados, null);
		RelEquipeMatrimonio.gerarRelatorio(dados.getCasais(), null);
	}

}
