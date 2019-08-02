package br.com.lazaru.matrimonio.bean;

public abstract class CasalBase {
	public abstract String getMulher();
	public abstract String getHomem();
	
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
}
