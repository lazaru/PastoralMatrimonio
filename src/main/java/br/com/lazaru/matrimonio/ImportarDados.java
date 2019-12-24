package br.com.lazaru.matrimonio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import br.com.lazaru.matrimonio.bean.Casal;
import br.com.lazaru.matrimonio.bean.Dados;
import br.com.lazaru.matrimonio.bean.Encontrista;

public class ImportarDados {

	public static void buscaEncontristas(Dados dados) {
		getDadosEncontristas("https://docs.google.com/spreadsheets/d/e/2PACX-1vSGJ45Z2CiC3kUXcBQ8J92OiAjXqRDb74FXXbjzLGYx035OrQVfv3N6LFhCGcUhi5H_YtIBD1JXkF-4/pub?output=csv", dados);
	}
	
	public static void buscaCasais(Dados dados) {
		getDadosCasasis("https://docs.google.com/spreadsheets/d/e/2PACX-1vTXfLumWx8IWIXhzWiMhUBO1ZuUPlb3jdWYr4obCbqpFLz0OVs5ZOllcJxgiqz3YhPg0YozgKBmpdLB/pub?gid=0&single=true&output=csv", dados);
	}
	
	private static void getDadosEncontristas(String url, Dados dados) {
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get("./encontristas.csv"), StandardCopyOption.REPLACE_EXISTING);
			importarEncontristasCvs(dados);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getDadosCasasis(String url, Dados dados) {
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get("./casais.csv"), StandardCopyOption.REPLACE_EXISTING);
			importarCasaisCvs(dados);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void importarEncontristasCvs(Dados dados) {
		String arquivoCSV = "./encontristas.csv";
		List<Encontrista> encontristas = new ArrayList<Encontrista>();
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ",";
	    try {
	    	boolean pulaLinha = true;
	        br = new BufferedReader(new FileReader(arquivoCSV));
	        while ((linha = br.readLine()) != null) {
				if (!pulaLinha) {
					String[] noivos = linha.split(csvDivisor);
					Encontrista e = new Encontrista();
					//se mudar a posicao da coluna na planilha tem que alterar estas linhas
					e.setHomem(noivos[1]);
					e.setMulher(noivos[13]);
					e.setTelefoneHomem(noivos[3]);
					e.setTelefoneMulher(noivos[15]);
					encontristas.add(e);
					System.out.println("Noivos [" + e.getHomem() + " e " + e.getMulher() + "]");
				}
	        	pulaLinha = false;
	        }
	        dados.setEncontristas(encontristas);

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	                File f = new File(arquivoCSV);
	    	        if(f.exists()) {
	    	        	f.delete();
	    	        }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	private static void importarCasaisCvs(Dados dados) {
		String arquivoCSV = "./casais.csv";
		List<Casal> casais = new ArrayList<Casal>();
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ",";
	    try {
	    	boolean pulaLinha = true;
	    	int contLinha =0;
	        br = new BufferedReader(new FileReader(arquivoCSV));
	        while ((linha = br.readLine()) != null) {
	        	contLinha++;
	        	if (contLinha == 1 || (contLinha >2 && contLinha<=4)) {
	        		pulaLinha = true;
	        	}
				if (!pulaLinha) {
					String[] strLinha = linha.split(csvDivisor);
					if(contLinha == 2) {
						String data = strLinha[1]+" a "+strLinha[2]+"/"+strLinha[3]+"/"+strLinha[4];
						dados.setDatasPreparacao(data);
						System.out.println("Data"+data);
					}else if(contLinha >= 5) {
						Casal e = new Casal();
						if(!strLinha[4].isEmpty() && strLinha[4].startsWith("S")) {
							//	se mudar a posicao da coluna na planilha tem que alterar estas linhas
							e.setHomem(strLinha[0]);
							e.setNomeImpHomem(strLinha[1]);
							e.setMulher(strLinha[2]);						
							e.setNomeImpMulher(strLinha[3]);
							e.setAtivo(strLinha[4]);
							e.setImprimeCracha((!strLinha[7].isEmpty() && strLinha[7].startsWith("S")));
							e.setAtividade(strLinha[8]);
							e.setEquipe((!strLinha[6].isEmpty() && strLinha[6].startsWith("S")));
							if(e.getAtividade().isEmpty() && e.isEquipe()) {
								e.setAtividade("Apoio");	
							}
							e.setTelefone(strLinha[9]);
							
							if((strLinha.length==11)&&!strLinha[10].isEmpty()) {
								try {
									Integer i = new Integer(strLinha[10]);
									e.setOrdem(i);
								}catch (Exception ignored) {}
							}	
							casais.add(e);
							System.out.println("casal [" + e.getHomem() + " e " + e.getMulher() + "]");
							
						}
					}
				}
	        	pulaLinha = false;
	        }
	        dados.setCasais(casais);

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	                File f = new File(arquivoCSV);
	    	        if(f.exists()) {
	    	        	f.delete();
	    	        }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	    }
	}
}
