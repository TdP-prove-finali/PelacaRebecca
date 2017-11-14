package tesi.model;

import java.util.ArrayList;
import java.util.List;
import tesi.DAO.DAO;

public class Model {
	
	private DAO dao;
	private List<String> metodi;	
	private List<Prodotto> prodotti;
	private List<Double> forecast;
	
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
		this.forecast = new ArrayList<Double>();
		
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
		
		result.append("Previsione della domanda con il metodo Moving Average :\n");
		
		for(int i=1; i<=tau; i++)
			result.append(i + "\t\t");
		
		result.append("\n");
	
		for(Double d : forecast)
			result.append((int)Math.round(d) + "\t\t");
		
		return result.toString().trim();
	
	}

	public String getExponentialSmoothing(Prodotto prodotto, int tau, double alfa) {
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		this.forecast = new ArrayList<Double>();
		
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
		
		result.append("Previsione della domanda con il metodo Exponential Smoothing :\n");
		
		for(int i=1; i<=tau; i++)
			result.append(i + "\t\t");
		
		result.append("\n");
	
		for(Double d : forecast)
			result.append((int)Math.round(d) + "\t\t");
		
		return result.toString().trim();
	}

	public String getExponentialSmoothingWithTrend(Prodotto prodotto, int tau, double alfa, double beta) {
		
		StringBuilder result = new StringBuilder();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		this.forecast = new ArrayList<Double>();
		
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
		
		result.append("Previsione della domanda con il metodo Exponential Smoothing with Trend :\n");
		
		for(int i=1; i<=tau; i++)
			result.append(i + "\t\t");
		
		result.append("\n");
	
		for(Double d : forecast)
			result.append((int)Math.round(d) + "\t\t");

		return result.toString().trim();
	}

	public String getWinter(Prodotto prodotto, int tau, double alfa, double beta, double gamma, int N) {
		
		StringBuilder result = new StringBuilder();
		
//		List<Integer> demand = dao.getStoricoDB(prodotto);
//		List<Double> smoothed_estimate = new ArrayList<Double>();
//		List<Double> smoothed_trend = new ArrayList<Double>();
//		List<Double> smoothed_seasonality = new ArrayList<Double>();
//		this.forecast = new ArrayList<Double>();
//		
//		int sum = 0;
//		double average;
//		
//		for(Integer d : demand) 
//			sum += d;
//			
//		average = (double)sum/demand.size();
//		smoothed_estimate.add(average);
//		smoothed_trend.add(0.0);
		
		return result.toString();
	}

	public String getMPSeATP(Prodotto prodotto, int lotSize, int magIn, int...tbs) {
		
		if(forecast.size()!=10)
			return "Prima di calcolare l'MPS, avvia una previsione con tau = 10!";
		
//		StringBuilder result = new StringBuilder();
				
		int[] ordini_acquisiti = new int[10];
		int[] disponibilita_magazzino = new int[10];
		int[] MPSquantity = new int[10];
		int[] ATP = new int[10];
		
		int count=0;
		
		for (int tb : tbs) {
			ordini_acquisiti[count] = tb;
			MPSquantity[count] = 0;      
			ATP[count++] = 0;               // setta a 0 il valore all'indice count e poi fa l'incremento
		}
		
		int It;
		
		for(int i=0; i<10; i++) {
			
			It = 0;
			
			if(i==0)
				It = magIn + MPSquantity[i];
			else 
				It = disponibilita_magazzino[i-1] + MPSquantity[i];
			
			if(forecast.get(i)>=ordini_acquisiti[i])
				It = (int) (It - forecast.get(i));
			else
				It -= ordini_acquisiti[i];
			
			if(It>=0)
				disponibilita_magazzino[i] = It;
			else {
				MPSquantity[i] = lotSize;
				
				if(i==0)
					It = magIn + MPSquantity[i];
				else 
					It = disponibilita_magazzino[i-1] + MPSquantity[i];
				
				if(forecast.get(i)>=ordini_acquisiti[i])
					It = (int) (It - forecast.get(i));
				else
					It -= ordini_acquisiti[i];
				
				disponibilita_magazzino[i] = It;
			}
		}
		
		int atp;
		boolean calcolaATP;
		
		for(int i=0; i<10; i++) {
			
			atp = 0;
			calcolaATP = false;
			
			if(i==0) {
				atp = magIn + MPSquantity[i];
				calcolaATP = true;
			}
			else if(MPSquantity[i]>0) {
				atp = MPSquantity[i];
				calcolaATP = true;
			}
			
			if(calcolaATP == true) {
				for(int j=i; j<MPSquantity.length; j++) {
					if(j==i || MPSquantity[j]==0)
						atp -= ordini_acquisiti[j];
					else if(MPSquantity[j]>0)
						break;
				}
				ATP[i] = atp;
			}
		}
		
		String[] temp = {"Previsione", "Ordini acquisiti", "Disponibilità magazzino", "Quantità MPS", "ATP"};
		return stampaFormattata(temp, ordini_acquisiti, disponibilita_magazzino, MPSquantity, ATP);
		
//		result.append("Previsione ");
//		
//		for(Double d : forecast)
//			result.append((int)Math.floor(d) + " ");
//		
//		result.append("\nOrdini acquisiti ");
//		
//		for(int i = 0; i<10; i++)
//			result.append(ordini_acquisiti[i] + " ");
//		
//		result.append("\nDisponibilità magazzino ");
//		
//		for(int i = 0; i<10; i++)
//			result.append(disponibilita_magazzino[i] + " ");
//		
//		result.append("\nQuantità MPS ");
//		
//		for(int i = 0; i<10; i++)
//			result.append(MPSquantity[i] + " ");
//		
//		return result.toString().trim();
	}
	
	private String stampaFormattata(String[] titles, int[] ordiniAcquisiti, int[] disponibilitaMagazzino, int[] mpsQuantity, int[] ATP){
		
		StringBuilder result = new StringBuilder();
		int maxLength=0;
		
		for (int i=0; i<titles.length; i++){
			if (titles[i].length()>maxLength)
				maxLength = titles[i].length();
		}
		
		for (int i=0; i<titles.length; i++){
			for (int j=0; j<maxLength-titles[i].length(); j++){
				titles[i]+=' ';
			}
			titles[i]+='\t';
		}
		
		result.append(titles[0]);
		
		for(Double d : forecast)
			result.append((int)Math.floor(d) + "\t");
		result.append('\n');
		
		result.append(titles[1]);
		
		for(int i = 0; i<10; i++)
			result.append(ordiniAcquisiti[i] + "\t");
		result.append('\n');
		
		result.append(titles[2]);
		
		for(int i = 0; i<10; i++)
			result.append(disponibilitaMagazzino[i] + "\t");
		result.append('\n');
		
		result.append(titles[3]);
		
		for(int i = 0; i<10; i++)
			result.append(mpsQuantity[i] + "\t");
		result.append('\n');
		
		result.append(titles[4]);
		
		for(int i = 0; i<10; i++)
			result.append(ATP[i] + "\t");
		
		return result.toString().trim();
		
	}

	public void aggiornaStoricoModel(Prodotto prodotto, int...tbs) {
		
		dao.aggiornaStorico(prodotto, tbs);	
	}
}
