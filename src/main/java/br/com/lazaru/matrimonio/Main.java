package br.com.lazaru.matrimonio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lazaru.matrimonio.bean.Encontrista;
import br.com.lazaru.matrimonio.bean.Equipe;
import br.com.lazaru.matrimonio.bean.ICasal;
import br.com.lazaru.matrimonio.pdf.RelCracha;
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
		
		
		List<Equipe> equipe = new ArrayList<Equipe>();
		Equipe eq = new Equipe();
		eq.setHomem("Pe. Flávio");
		eq.setAtividade("Palestra - Sacramentos");
		eq.setTelefone("3219-3316");
		equipe.add(eq);
		
		eq = new Equipe();
		eq.setHomem("Mad. Tereza");
		eq.setAtividade("Palestra - Sacramentos");
		eq.setTelefone("3219-3316");
		equipe.add(eq);
		
		for (int i = 0; i < 9; i++) {
			eq = new Equipe();
			eq.setHomem("Geraldo");
			eq.setMulher("Lúcia");
			eq.setAtividade("Palestra - Amor conjugal - Diálogo - Conhecimento de si e do outro");
			eq.setTelefone("99976-4376");
			equipe.add(eq);
		}

		List<ICasal> eqp = new ArrayList<ICasal>();
		for(Equipe e:equipe) {
			eqp.add(e);
		}
		
		List<Encontrista> casais= new ArrayList<Encontrista>();
		for (int i = 0; i < 9; i++) {
			Encontrista casal = new Encontrista();
			casal.setHomem("homen nome comprido 123456456"+i);
			casal.setMulher("mulher "+i);
			casal.setDataEncontro(LocalDate.now());
			casal.setTelefoneHomem("99976-4376");
			casal.setTelefoneMulher("99976-4377");
			casais.add(casal);
		}
		
		//RelEquipeMatrimonio.gerarRelatorio(equipe, null);
		//RelCracha.gerarCrachaEquipe(eqp, null);
		RelEtiquetaPasta.gerarEtiqueta(casais, null);

	}

}
