package tesi.model;

public class Ordine {
	
	private int mese;
	private int quantita;
	private boolean stato;
	
	public Ordine(int mese, int quantita) {
		super();
		this.mese = mese;
		this.quantita = quantita;
		this.stato = false;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		
		if(stato==true) 
			result.append("Mese " + this.mese + ", Quantita " + this.quantita + ", Accettato");
		else
			result.append("Mese " + this.mese + ", Quantita " + this.quantita + ", Rifiutato");
		
		return result.toString();		
	}
}
