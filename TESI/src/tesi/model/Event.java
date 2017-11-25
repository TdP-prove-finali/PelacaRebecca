package tesi.model;

public class Event implements Comparable<Event> {
	
	public enum EventType { ARRIVA_ORDINE };
	
	private EventType type;
	private int mese;
	private double quantita;
	
	public Event(EventType type, int mese, double quantita) {
		super();
		this.type = type;
		this.mese = mese;
		this.quantita = quantita;
	}

	public EventType getType() {
		return type;
	}
	
	public void setType(EventType type) {
		this.type = type;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	@Override
	public int compareTo(Event altro) {
		
		return this.mese-altro.mese;
	}
}
