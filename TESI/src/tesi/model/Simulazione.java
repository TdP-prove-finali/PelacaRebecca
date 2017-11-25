package tesi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import tesi.model.Event.EventType;

public class Simulazione {
	
	// input
	private int[] ATP;

	// output
	private List<Ordine> ordini;
	private int ordini_accettati;
	private int ordini_rifiutati;
	private double percentuale_accettati;
	
	// lista degli eventi
	PriorityQueue<Event> queue ;
	
	public Simulazione(int[] atp) {
		
		this.ATP = atp;
		
		this.ordini = new ArrayList<Ordine>();
		this.ordini_accettati = 0;
		this.ordini_rifiutati = 0;
		this.percentuale_accettati = 0;
		
		this.queue = new PriorityQueue<>();
	}
	
	public void addOrdine(Ordine ordine) {
		
		queue.add(new Event(EventType.ARRIVA_ORDINE, ordine));
	}
	
	public SimulationResult simula() {
		
		while(!queue.isEmpty()) {
			
			Event e = queue.poll();
			
			switch(e.getType()) {
			
			case ARRIVA_ORDINE :
				
				for(int i=e.getOrdine().getMese(); i>=0; i--) {
					if(ATP[i]>=0) {
						if(ATP[i]>=e.getOrdine().getQuantita()) {
							ATP[i] -= e.getOrdine().getQuantita();
							e.getOrdine().setStato(true);
							ordini_accettati++;
							break;
						}
						else {
							ordini_rifiutati++;
							break;
						}
					}
				}
				
				ordini.add(e.getOrdine());
				break;
			}
		}
		
		percentuale_accettati = ordini_accettati/(ordini_accettati+ordini_rifiutati)*100;
		
		SimulationResult simulationResult = new SimulationResult(ordini, ordini_accettati, ordini_rifiutati, percentuale_accettati);
		
		return simulationResult;
	}
}
