package br.com.lazaru.matrimonio.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "encontrista")
@XmlType()
public class Encontrista extends CasalBase implements Comparable<Encontrista> {

	private String homem;
	private String mulher;
	private String telefoneHomem;
	private String telefoneMulher;
	

	public String getHomem() {
		return homem;
	}

	public void setHomem(String homem) {
		this.homem = homem;
	}

	public String getMulher() {
		return mulher;
	}

	public void setMulher(String mulher) {
		this.mulher = mulher;
	}
	

	public int compareTo(Encontrista e) {
		if (getHomem() == null || e.getHomem() == null) {
			return 0;
		}
		return getHomem().compareTo(e.getHomem());
	}


	public String getTelefoneMulher() {
		return telefoneMulher;
	}

	public void setTelefoneMulher(String telefoneMulher) {
		this.telefoneMulher = telefoneMulher;
	}

	public String getTelefoneHomem() {
		return telefoneHomem;
	}

	public void setTelefoneHomem(String telefoneHomem) {
		this.telefoneHomem = telefoneHomem;
	}

}
