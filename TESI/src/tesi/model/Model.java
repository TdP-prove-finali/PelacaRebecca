package tesi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
//		for(Integer i : demand)
//			System.out.println(i + "\n");
		
		int tot;
		double media;
		
		for(int i=m-1; i<demand.size(); i++) {
			tot = 0;
			media = 0;
			for(int j=0; j<m; j++) {
				tot += demand.get(i-j);
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
			result.append(String.format(Locale.US, "%.2f\n", d));
		
		return result.toString().trim();
	
	}

	public String getExponentialSmoothing(Prodotto prodotto, int tau, double alfa) {
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
//		for(Integer i : demand)
//		System.out.println(i + "\n");
		
		double f;
		
		smoothed_estimate.add((double)demand.get(0));
		
		for(int i=1; i<demand.size(); i++) {
			f = alfa*demand.get(i)+(1-alfa)*smoothed_estimate.get(i-1);
			smoothed_estimate.add(f);
		}
		
//		for(Double d : smoothed_estimate)
//		System.out.println(d + "\n");
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add(smoothed_estimate.get(last_index-i));
		}
		
		for(Double d : forecast)
			result.append(String.format(Locale.US, "%.2f\n", d));
		
		return result.toString().trim();
	}

	public String getExponentialSmoothingWithTrend(Prodotto prodotto, int tau, double alfa, double beta) {
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
		double f;
		double t;
		
		smoothed_estimate.add((double)demand.get(0));
		smoothed_trend.add(0.0);
		
		for(int i=1; i<demand.size(); i++) {
			f = alfa*demand.get(i) + (1-alfa)*(smoothed_estimate.get(i-1)+smoothed_trend.get(i-1));
			smoothed_estimate.add(f);
			t = beta*(smoothed_estimate.get(i)-smoothed_estimate.get(i-1))+(1-beta)*smoothed_trend.get(i-1);	
			smoothed_trend.add(t);
		}
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add(smoothed_estimate.get(last_index-i)+tau*smoothed_estimate.get(last_index-1));
		}
		
		for(Double d : forecast)
			result.append(String.format(Locale.US, "%.2f\n", d));

		return result.toString().trim();
	}

	public String getWinter(Prodotto prodotto, int tau, double alfa, double beta, double gamma) {
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		List<Double> smoothed_seasonality = new ArrayList<Double>();
		List<Double> forecast = new ArrayList<Double>();
		
		int sum = 0;
		double average;
		
		for(Integer d : demand) 
			sum += d;
			
		average = (double)sum/demand.size();
		smoothed_estimate.add(average);
		smoothed_trend.add(0.0);
		
		return null;
		
		
	}
}
