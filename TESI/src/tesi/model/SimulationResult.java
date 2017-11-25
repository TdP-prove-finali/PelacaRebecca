package tesi.model;

public class SimulationResult {
	
	private int ordini_accettati;
	private int ordini_rifiutati;
	private double percentuale_accettati;
	
	public SimulationResult(int ordini_accettati, int ordini_rifiutati, double percentuale_accettati) {
		super();
		this.ordini_accettati = ordini_accettati;
		this.ordini_rifiutati = ordini_rifiutati;
		this.percentuale_accettati = percentuale_accettati;
	}

	public int getOrdini_accettati() {
		return ordini_accettati;
	}

	public void setOrdini_accettati(int ordini_accettati) {
		this.ordini_accettati = ordini_accettati;
	}

	public int getOrdini_rifiutati() {
		return ordini_rifiutati;
	}

	public void setOrdini_rifiutati(int ordini_totali) {
		this.ordini_rifiutati = ordini_totali;
	}

	public double getPercentuale_accettati() {
		return percentuale_accettati;
	}

	public void setPercentuale_accettati(double percentuale_accettati) {
		this.percentuale_accettati = percentuale_accettati;
	}
}
