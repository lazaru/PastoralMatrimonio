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
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import br.com.lazaru.matrimonio.bean.Casal;
import br.com.lazaru.matrimonio.bean.Dados;
import br.com.lazaru.matrimonio.bean.Encontrista;

public class ImportarDados {
	
	private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
	
	private static InputStream getInputStream(String stringUrl) throws NoSuchAlgorithmException, KeyManagementException, IOException {
		 // configure the SSLContext with a TrustManager
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        URL url = new URL(stringUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        InputStream input = conn.getURL().openStream();
        conn.disconnect();	
        return input;
	}
	
	public static void buscaEncontristas(Dados dados) {
		getDadosEncontristas("https://docs.google.com/spreadsheets/d/e/2PACX-1vSGJ45Z2CiC3kUXcBQ8J92OiAjXqRDb74FXXbjzLGYx035OrQVfv3N6LFhCGcUhi5H_YtIBD1JXkF-4/pub?output=csv", dados);
	}
	
	public static void buscaCasais(Dados dados) {
		getDadosCasasis("https://docs.google.com/spreadsheets/d/e/2PACX-1vTXfLumWx8IWIXhzWiMhUBO1ZuUPlb3jdWYr4obCbqpFLz0OVs5ZOllcJxgiqz3YhPg0YozgKBmpdLB/pub?gid=0&single=true&output=csv", dados);
	}
	
	private static void getDadosEncontristas(String url, Dados dados) {
		try {
			//InputStream in = new URL(url).openStream();
			InputStream in = getInputStream(url);
			Files.copy(in, Paths.get("./encontristas.csv"), StandardCopyOption.REPLACE_EXISTING);
			importarEncontristasCvs(dados);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException|KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	private static void getDadosCasasis(String url, Dados dados) {
		try {
			//InputStream in = new URL(url).openStream();
			InputStream in = getInputStream(url);
			Files.copy(in, Paths.get("./casais.csv"), StandardCopyOption.REPLACE_EXISTING);
			importarCasaisCvs(dados);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException|KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	private static void importarEncontristasCvs(Dados dados) {
		int idxEmail = 0,idxNomeNoivo = 0,idxNomeNoiva = 0,idxTelefoneNoivo = 0, idxTelefoneNoiva = 0,idxDataNascNoivo = 0,idxDataNascNoiva=0;
		String arquivoCSV = "./encontristas.csv";
		List<Encontrista> encontristas = new ArrayList<Encontrista>();
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ",";
	    try {
	    	boolean pulaLinha = true;
	        br = new BufferedReader(new FileReader(arquivoCSV));
	        while ((linha = br.readLine()) != null) {
	        	if(pulaLinha) {
		        	//testa se e a linha cabeçalho da equipe
		        	if(linha.contains("Nome completo do Noivo")&&linha.contains("Nome completo do Noiva")&&linha.contains("Email Address")) {
		        		//esperado dentro de linha = Timestamp,Email Address,Nome completo do Noivo,Data de nascimento,Telefone,Religião,É batizado?,Fez a primeira Eucaristia?,Fez a Crisma,Possui alguma restrição alimentar por indicação médica?,Qual a restrição alimentar do noivo?,Nome completo do Noiva,Data de nascimento,Telefone,Religião,É batizado?,Fez a primeira Eucaristia?,Fez a Crisma,Possui alguma restrição alimentar por indicação médica?,Qual a restrição alimentar da noiva?,Data do casamento,Paróquia / Cidade
		        		String[] colunasImportadas = linha.split(",");
		        		List<String> colunas = Arrays.asList(colunasImportadas);
		        		idxEmail = colunas.indexOf("Email Address");
		        		idxNomeNoivo = colunas.indexOf("Nome completo do Noivo");
		        		idxNomeNoiva = colunas.indexOf("Nome completo do Noiva");
		        		idxTelefoneNoivo = colunas.indexOf("Telefone");
		        		idxTelefoneNoiva = colunas.lastIndexOf("Telefone");
		        		idxDataNascNoivo = colunas.indexOf("Data de nascimento");
		        		idxDataNascNoiva = colunas.lastIndexOf("Data de nascimento");
		        	}
	        	}
				if (!pulaLinha) {
					String[] noivos = linha.split(csvDivisor);
					Encontrista e = new Encontrista();
					//se mudar a posicao da coluna na planilha tem que alterar estas linhas
					e.setHomem(noivos[idxNomeNoivo]);
					e.setMulher(noivos[idxNomeNoiva]);
					e.setTelefoneHomem(noivos[idxTelefoneNoivo]);
					e.setTelefoneMulher(noivos[idxTelefoneNoiva]);
					e.setEmail(noivos[idxEmail]);
					e.setDataNascHomem(noivos[idxDataNascNoivo]);
					e.setDataNascMulher(noivos[idxDataNascNoiva]);
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
		int idxHomem=0, idxNomeImpHomem=0, idxMulher=0, idxNomeImpMulher=0, idxAtivo=0, idxPalestrante=0, idxImpCracha=0, idxAtividade=0, idxTelefone=0, idxOrdemPalestra=0, idxEquipe=0;
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
	        	//testa se e a linha cabeçalho da equipe
	        	if(linha.contains("nomeImpHomem")&&linha.contains("nomeImpMulher")&&linha.contains("Imprime Crachá")) {
	        		//esperado dentro de linha = homem,nomeImpHomem,mulher,nomeImpMulher,Ativo,palestrante,equipe,Imprime Crachá,atividade,telefone,OrdemPalestra
	        		String[] colunasImportadas = linha.split(",");
	        		List<String> colunas = Arrays.asList(colunasImportadas);
	        		idxHomem = colunas.indexOf("homem");
	        		idxNomeImpHomem = colunas.indexOf("nomeImpHomem");
	        		idxMulher = colunas.indexOf("mulher");
	        		idxNomeImpMulher = colunas.indexOf("nomeImpMulher");
	        		idxAtivo = colunas.indexOf("Ativo");
	        		idxPalestrante = colunas.indexOf("palestrante");
	        		idxImpCracha = colunas.indexOf("Imprime Crachá");
	        		idxAtividade = colunas.indexOf("atividade");
	        		idxTelefone = colunas.indexOf("telefone");
	        		idxOrdemPalestra = colunas.indexOf("OrdemPalestra");
	        		idxEquipe = colunas.indexOf("equipe");
	        		
	        	}
	        	
	        	
				if (!pulaLinha) {
					String[] strLinha = linha.split(csvDivisor);
					if(contLinha == 2) {
						String data = strLinha[1]+" a "+strLinha[2]+"/"+strLinha[3]+"/"+strLinha[4];
						dados.setDatasPreparacao(data);
						System.out.println("Data"+data);
					}else if(contLinha >= 5) {
						Casal e = new Casal();
						if(!strLinha[idxAtivo].isEmpty() && strLinha[idxAtivo].startsWith("S")) {
							//	se mudar a posicao da coluna na planilha tem que alterar estas linhas
							e.setHomem(strLinha[idxHomem]);
							e.setNomeImpHomem(strLinha[idxNomeImpHomem]);
							e.setMulher(strLinha[idxMulher]);						
							e.setNomeImpMulher(strLinha[idxNomeImpMulher]);
							e.setAtivo(strLinha[idxAtivo]);
							e.setImprimeCracha((!strLinha[idxImpCracha].isEmpty() && strLinha[idxImpCracha].startsWith("S")));
							e.setAtividade(strLinha[idxAtividade]);
							e.setEquipe((!strLinha[idxEquipe].isEmpty() && strLinha[idxEquipe].startsWith("S")));
							if(e.getAtividade().isEmpty() && e.isEquipe()) {
								e.setAtividade("Apoio");	
							}
							e.setTelefone(strLinha[idxTelefone]);
							
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
	
	public static void removeElement(Object[] arr, int removedIdx) {
	    System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
	}
}
