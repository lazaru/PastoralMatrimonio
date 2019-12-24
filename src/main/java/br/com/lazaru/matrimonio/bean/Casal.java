package br.com.lazaru.matrimonio.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "casal")
@XmlType()
public class Casal extends CasalBase {

	private String homem;
	private String nomeImpHomem;
	private String nomeImpMulher;
	private String mulher;
	private String telefone;
	private String atividade;
	private String ativo;
	private Integer ordem;
	private boolean palestrante;
	private boolean equipe;
	private boolean imprimeCracha;

	public Casal() {
		ordem = Integer.MAX_VALUE;
		nomeImpHomem = "";
		nomeImpMulher = "";
		palestrante = false;
		equipe = false;
	}
	
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAtividade() {
		if (atividade == null || atividade.isEmpty()) {
			return "Apoio";
		}
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
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
		this.nomeImpHomem = nomeImpHomem;
	}

	public String getNomeImpMulher() {
		return nomeImpMulher;
	}

	public void setNomeImpMulher(String nomeImpMulher) {
		this.nomeImpMulher = nomeImpMulher;
	}

	public boolean isPalestrante() {
		return palestrante;
	}

	public void setPalestrante(boolean palestrante) {
		this.palestrante = palestrante;
	}

	public boolean isEquipe() {
		return equipe;
	}

	public void setEquipe(boolean equipe) {
		this.equipe = equipe;
	}

	public void setImprimeCracha(boolean b) {
		this.imprimeCracha = b;
	}
	
	public boolean isImprimeCracha() {
		return this.imprimeCracha;
	}



}
