package br.com.lazaru.matrimonio.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "encontrista")
@XmlType()
public class Encontrista extends CasalBase {

	private String homem;
	private String mulher;
	private String nomeImpHomem;
	private String nomeImpMulher;
	private String telefoneHomem;
	private String telefoneMulher;
	private Integer ordem;

	public Encontrista() {
		ordem = Integer.MAX_VALUE;
		nomeImpHomem = "";
		nomeImpMulher = "";
	}
	public String getHomem() {
		return homem;
	}

	public void setHomem(String homem) {
		this.homem = padronizaNome(homem);
	}

	public String getMulher() {
		return mulher;
	}

	public void setMulher(String mulher) {
		this.mulher = padronizaNome(mulher);
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
	
	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public String getNomeImpHomem() {
		return nomeImpHomem;
	}
	public void setNomeImpHomem(String nomeImpHomem) {
		this.nomeImpHomem = padronizaNome(nomeImpHomem);
	}
	public String getNomeImpMulher() {
		return nomeImpMulher;
	}
	public void setNomeImpMulher(String nomeImpMulher) {
		this.nomeImpMulher = padronizaNome(nomeImpMulher);
	}

}
