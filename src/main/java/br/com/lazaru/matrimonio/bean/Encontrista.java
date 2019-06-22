package br.com.lazaru.matrimonio.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Encontrista implements Comparable<Encontrista>, ICasal {

	private String homem;
	private String mulher;
	private String telefoneHomem;
	private String telefoneMulher;
	
	private LocalDate dataEncontro;

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

	public String getCasal() {
		if ((getMulher() != null && !getMulher().isEmpty()) || (getHomem() != null && !getHomem().isEmpty())) {
			if ((getMulher() == null || getMulher().isEmpty()) && (!getHomem().isEmpty())) {
				return getHomem();
			} else if ((getHomem() == null || getHomem().isEmpty()) && (!getMulher().isEmpty())) {
				return getMulher();
			} else {
				return getHomem() + " e " + getMulher();
			}
		}else {
			return "nada informado";
		}
	}

	public int compareTo(Encontrista e) {
		if (getHomem() == null || e.getHomem() == null) {
			return 0;
		}
		return getHomem().compareTo(e.getHomem());
	}

	public String getDataEncontro() {
		String brDatePattern = "dd/MM/yyyy";
		DateTimeFormatter brDateFormatter = DateTimeFormatter.ofPattern(brDatePattern);
		return brDateFormatter.format(dataEncontro);
	}

	public void setDataEncontro(LocalDate dataEncontro) {
		this.dataEncontro = dataEncontro;
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
