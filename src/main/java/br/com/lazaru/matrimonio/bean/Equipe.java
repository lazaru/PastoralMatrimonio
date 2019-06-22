package br.com.lazaru.matrimonio.bean;

public class Equipe implements ICasal, Comparable<Equipe> {

	private String homem;
	private String mulher;
	private String telefone;
	private String atividade;
	private String ativo;

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

	public int compareTo(Equipe e) {
		if (getHomem() == null || e.getHomem() == null) {
			return 0;
		}
		return getHomem().compareTo(e.getHomem());
	}

}
