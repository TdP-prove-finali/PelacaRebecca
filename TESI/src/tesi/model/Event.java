package tesi.model;

public class Event implements Comparable<Event> {
	
	public enum EventType { ARRIVA_ORDINE };
	
	private EventType type;
	private Ordine ordine;
	
	public Event(EventType type, Ordine ordine) {
		super();
		this.type = type;
		this.ordine = ordine;
	}

	public EventType getType() {
		return type;
	}
	
	public void setType(EventType type) {
		this.type = type;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	@Override
	public int compareTo(Event altro) {
		
		return this.ordine.getMese()-altro.getOrdine().getMese();
	}
}
