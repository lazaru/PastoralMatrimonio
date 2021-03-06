package br.com.lazaru.matrimonio.bean;

public abstract class CasalBase implements Comparable<CasalBase> {
	public abstract Integer getOrdem();

	public abstract String getMulher();

	public abstract String getNomeImpMulher();

	public abstract String getHomem();

	public abstract String getNomeImpHomem();

	public String getCasal() {
		if ((getPrimeiroNomeMulher() != null && !getPrimeiroNomeMulher().isEmpty())
				|| (getPrimeiroNomeHomem() != null && !getPrimeiroNomeHomem().isEmpty())) {
			if ((getPrimeiroNomeMulher() == null || getPrimeiroNomeMulher().isEmpty())
					&& (!getPrimeiroNomeHomem().isEmpty())) {
				return getPrimeiroNomeHomem();
			} else if ((getPrimeiroNomeHomem() == null || getPrimeiroNomeHomem().isEmpty())
					&& (!getPrimeiroNomeMulher().isEmpty())) {
				return getPrimeiroNomeMulher();
			} else {
				return getPrimeiroNomeHomem() + " e " + getPrimeiroNomeMulher();
			}
		} else {
			return "nada informado";
		}
	}

	public String getPrimeiroNomeHomem() {
		if (getNomeImpHomem().isEmpty()) {
			return getFirstName(getHomem());
		} else {
			return getNomeImpHomem();
		}
	}

	public String getPrimeiroNomeMulher() {
		if (getNomeImpMulher().isEmpty()) {
			return getFirstName(getMulher());
		} else {
			return getNomeImpMulher();
		}
	}
	
	private String getNomePadrao(String name) {
		if (name != null && !name.isEmpty()) {
			String[] parts = name.split("\\s+");
			String primeiro = parts[0];//nao vou alterar ficara como foi digitado
			//aqui e para tirar nomes com 2 digitos ex: de, e, da, p.
			if(primeiro.length() > 2 ) {
				primeiro = parts[0].toUpperCase().substring(0,1) + parts[0].toLowerCase().substring(1);
			}
			return primeiro;
		}
		return "";
		
	}
	private String getFirstName(String name) {
		if (name != null && !name.isEmpty()) {
			String[] parts = name.split("\\s+");
			String primeiro = parts[0].toUpperCase().substring(0,1) + parts[0].toLowerCase().substring(1);
			if(primeiro.length() < 3) {
				//caso seja P. Marcio por exemplo entra aqui.
				primeiro += " "+parts[1];
			}
			return primeiro;
		}
		return "";
	}
	
	public boolean isImprimeCracha() {
		return true;
	}
	
	
	protected String padronizaNome(String nome) {
		StringBuilder sb = new StringBuilder();
		if (nome != null && !nome.isEmpty()) {
			String[] parts = nome.split("\\s+");
			for(String s:parts) {
				if(!sb.toString().isEmpty()) {
					sb.append(" ");
				}
				sb.append( getNomePadrao(s) );
			}
		}
		return sb.toString();
	}

	public int compareTo(CasalBase e) {
		
		if (getHomem() == null || e.getHomem() == null) {
			return 0;
		}
		
		// 	se nao foi informado nenhuma ordem, vai pelo nome
		if ((getOrdem() == 2147483647 ) && ( e.getOrdem() == 2147483647) ) {
			return getHomem().compareTo(e.getHomem());
		} else {
			return getOrdem().compareTo(e.getOrdem());
		}
	}
}
