package tesi.model;

import java.util.ArrayList;
import java.util.List;

import tesi.DAO.DAO;

public class Model {
	
	private DAO dao;
	private List<String> metodi;	
	private List<Prodotto> prodotti;
	
	public Model() {
		
		this.dao = new DAO();
		this.prodotti = dao.getProdottiDB();
	}
	
	public List<Prodotto> getProdotti() {
		
		return prodotti;
	}

	public List<String> getMetodi() {
		
		this.metodi = new ArrayList<String>();
		
		metodi.add("Moving average");
		metodi.add("Exponential smoothing");
		metodi.add("Exponential smoothing with trend");
		metodi.add("Winter");
			
		return metodi;
	}
	
	public String getMovingAverage(Prodotto prodotto, int tau, int m) {
		
		String result = "";
		
		List<Integer> storico = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
//		for(Integer i : storico)
//			System.out.println(i + "\n");
		
		int tot;
		double media;
		
		for(int i=m-1; i<storico.size(); i++) {
			tot = 0;
			media = 0;
			for(int j=0; j<m; j++) {
				tot += storico.get(i-j);
			}
			media = (double)tot/m;
			smoothed_estimate.add(media);	
		}
		
//		for(Double d : smoothed_estimate)
//			System.out.println(d + "\n");
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add(smoothed_estimate.get(last_index-i));
		}
		
		for(Double d : forecast)
			result += d + "\n";
		
		return result.trim();
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
