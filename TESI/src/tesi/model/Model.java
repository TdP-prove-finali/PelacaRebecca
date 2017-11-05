package tesi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
	
	List<String> metodi;
	
	Map<Prodotto, List<Integer>> storici;
	
	public Model() {
		
		this.storici = new HashMap<Prodotto, List<Integer>>();
	}

	public List<String> getMetodi() {
		
		this.metodi = new ArrayList<String>();
		
		metodi.add("Moving average");
		metodi.add("Exponential smoothing");
		metodi.add("Exponential smoothing with trend");
		metodi.add("Winter");
			
		return metodi;
	}

	public void getMovingAverage(Prodotto prodotto, int tau, int m) {
		
		List<Integer> storico = storici.get(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
		int tot;
		double media;
		
		for(int i=m-1; i<=storico.size(); i++) {
			tot = 0;
			media = 0;
			for(int j=0; j<m; j++) {
				tot += storico.get(i-j);
			}
			media = tot/m;
			smoothed_estimate.add(media);	
		}
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add(smoothed_estimate.get(last_index-i));
		}
	}

	public void getExponentialSmoothing(Prodotto prodotto, int tau, double alfa) {
		// TODO Auto-generated method stub
		
	}

	public void getExponentialSmoothingWithTrend(Prodotto prodotto, int tau, double alfa, double beta) {
		// TODO Auto-generated method stub
		
	}

	public void getWinter(Prodotto prodotto, int tau, double alfa, double beta, double gamma) {
		// TODO Auto-generated method stub
		
	}
}
