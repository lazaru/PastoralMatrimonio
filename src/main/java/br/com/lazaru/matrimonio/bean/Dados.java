package br.com.lazaru.matrimonio.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.lazaru.matrimonio.ImportarDados;

@XmlRootElement(name = "dados")
public class Dados {

	private List<Casal> casais;
	
	
	private List<Encontrista> encontristas;
	
	private String datasPreparacao;
	
	public Dados() {
		casais = new ArrayList<Casal>();
		encontristas = new ArrayList<Encontrista>();
	}
	@XmlElementWrapper(name="casais")
    @XmlElement(name="casal")
	public List<Casal> getCasais() {
		return casais;
	}
	public void setCasais(List<Casal> casais) {
		this.casais = casais;
	}
	@XmlElementWrapper(name="encontristas")
	@XmlElement(name = "encontrista")  
	public List<Encontrista> getEncontristas() {
		return encontristas;
	}

	public void setEncontristas(List<Encontrista> encontristas) {
		this.encontristas = encontristas;
	}
	@XmlElement(name="datasDePreparacao")
	public String getDatasPreparacao() {
		return datasPreparacao;
	}
	public void setDatasPreparacao(String datasPreparacao) {
		this.datasPreparacao = datasPreparacao;
	}
	
	
	
	public static void saveToFile(Dados dados) {
		File file = null;
		try {
			file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "dados.xml");
			System.out.println(file.getAbsolutePath());
			JAXBContext context = JAXBContext.newInstance(Dados.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(dados, file);
		} catch (Exception e) { // catches ANY exception
			System.out.println(e);
		}
	}

	public static Dados getFromFile() {
		File file = null;
		try {
			file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "dados.xml");
			if (!file.exists()) {
				Dados config = new Dados();
				//IMPORTA DADOS DA PLANILHA ON-LINE
				ImportarDados.buscaEncontristas(config);
				ImportarDados.buscaCasais(config);
				Dados.saveToFile(config);
			}
			    
			JAXBContext context = JAXBContext.newInstance(Dados.class);
			Unmarshaller um = context.createUnmarshaller();
			Dados wrapper = (Dados) um.unmarshal(file);
			return wrapper;

		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			return null;
		}

	}
	



}
