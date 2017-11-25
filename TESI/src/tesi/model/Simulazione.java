package tesi.model;

import java.util.PriorityQueue;

import tesi.model.Event.EventType;

public class Simulazione {
	
	// input
	private int[] ATP;

	// output
	private int ordini_accettati;
	private int ordini_rifiutati;
	
	// lista degli eventi
	PriorityQueue<Event> queue ;
	
	public Simulazione(int[] atp) {
		
		this.ATP = atp;
		
		this.ordini_accettati = 0;
		this.ordini_rifiutati = 0;
		
		this.queue = new PriorityQueue<>();
	}
	
	public void addOrdine(int mese, int quantita) {
		
		queue.add(new Event(EventType.ARRIVA_ORDINE, mese, quantita));
	}
	
	public SimulationResult simula() {
		
		while(!queue.isEmpty()) {
			
			Event e = queue.poll();
			
			switch(e.getType()) {
			
			case ARRIVA_ORDINE :
				
				for(int i=e.getMese(); i>=0; i--) {
					if(ATP[i]>=0) {
						if(ATP[i]>=e.getQuantita()) {
							ATP[i] -= e.getQuantita();
							ordini_accettati++;
							break;
						}
						else {
							ordini_rifiutati++;
							break;
						}
					}
				}
				
				break;
			}
		}
		double percentuale_accettati = ordini_accettati/(ordini_accettati+ordini_rifiutati)*100;
		SimulationResult sr = new SimulationResult(ordini_accettati, ordini_rifiutati, percentuale_accettati);
		return sr;
	}
}
