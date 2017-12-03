package tesi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import tesi.DAO.DAO;

public class Model{
	
	private final static int MPSsize = 6;
	
	private DAO dao;
	private List<String> metodi;	
	private List<Prodotto> prodotti;
	private List<Double> forecast;
	
	private int[] ATP;
	
	
	public Model() {
		
		this.dao = new DAO();
		this.prodotti = dao.getProdottiDB();
		
		this.ATP = new int[MPSsize];
	}
	
	public List<Prodotto> getProdotti() {
		
		return prodotti;
	}
	
	public Series<String, Number> getSeries(Prodotto prodotto) {
		
		List<Integer> domanda = dao.getStoricoDB(prodotto);
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		for(int i=0; i<domanda.size(); i++) {
			series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i+1), domanda.get(i)));
		}
		
		return series;
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
			forecast.add(smoothed_estimate.get(last_index-i)+tau*smoothed_estimate.get(last_index-i));
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
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		List<Double> smoothed_seasonality = new ArrayList<Double>();
		this.forecast = new ArrayList<Double>();
		
		int sum = 0;
		double average;
		
		for(int i=0; i<=N; i++)
			sum += demand.get(i);
		
		average = (double)sum/N;
		
		for(int i=0; i<=N; i++) {
			smoothed_seasonality.add(demand.get(i)/average);
			smoothed_trend.add(0.0);
			if(i!=N)
				smoothed_estimate.add(0.0);
		}
		
		smoothed_estimate.add(average);
		
		System.out.println(average);
		
		double f;
		double t;
		double c;
		
		for(int i=N+1; i<demand.size(); i++) {
			f = alfa*(demand.get(i)/smoothed_seasonality.get(i-N)+(1-alfa)*(smoothed_estimate.get(i-1)+smoothed_trend.get(i-1)));
			smoothed_estimate.add(f);
			t = beta*(smoothed_estimate.get(i)-smoothed_estimate.get(i-1))+(1-beta)*smoothed_trend.get(i-1);
			smoothed_trend.add(t);
			c = gamma*(demand.get(i)/smoothed_estimate.get(i))+(1-gamma)*smoothed_seasonality.get(i-N);
			smoothed_seasonality.add(c);
		}
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add((smoothed_estimate.get(last_index-i)+tau*smoothed_estimate.get(last_index-i))*smoothed_seasonality.get(last_index-i-N+1));
		}
		
		result.append("Previsione della domanda con il metodo di Winter :\n");
		
		for(int i=1; i<=tau; i++)
			result.append(i + "\t\t");
		
		result.append("\n");
	
		for(Double d : forecast)
			result.append((int)Math.round(d) + "\t\t");

		
		return result.toString();
	}

	public ObservableList<Row> getMPSeATP(Prodotto prodotto, int lotSize, int magIn, int...tbs) {
		
		if(forecast.size()!=MPSsize) {
			//genera eccezione
		}
				
		int[] ordini_acquisiti = new int[MPSsize];
		int[] disponibilita_magazzino = new int[MPSsize];
		int[] MPSquantity = new int[MPSsize];
		
		int count=0;
		
		for (int tb : tbs) {
			ordini_acquisiti[count] = tb;
			MPSquantity[count] = 0;      
			ATP[count++] = 0;               // setta a 0 il valore all'indice count e poi fa l'incremento
		}
		
		int It;
		
		for(int i=0; i<MPSsize; i++) {
			
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
		
		for(int i=0; i<MPSsize; i++) {
			
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
		
		int[] forecast_array = new int[forecast.size()];
		
		for(int i=0; i<forecast.size(); i++)
			forecast_array[i] = (int)Math.floor(forecast.get(i));
			
		ObservableList<Row> result = FXCollections.observableArrayList();
		
		result.add(new Row("Previsione", forecast_array[0], forecast_array[1], forecast_array[2], forecast_array[3], forecast_array[4], forecast_array[5]));
		result.add(new Row("Ordini acquisiti", ordini_acquisiti[0],ordini_acquisiti[1],ordini_acquisiti[2],ordini_acquisiti[3],ordini_acquisiti[4],ordini_acquisiti[5]));
		result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4], disponibilita_magazzino[5]));
		result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4], MPSquantity[5]));
		result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4], ATP[5]));
		
		for(Row r : result)
			System.out.println(r + "\n");
		
		return result;
	}
	
	public String simulaModel(double probabilita, int min, int max) {
		
		Simulazione sim = new Simulazione(this.ATP);
		StringBuilder result = new StringBuilder();
		
		for(int i=0; i<this.ATP.length; i++) {
			if(Math.random()>=1-probabilita) {
				Random r = new Random();
				int qty = r.nextInt((max-min)) + min;
				Ordine ordine = new Ordine(i, qty);
				sim.addOrdine(ordine);
			}
		}
		
		SimulationResult sr = sim.simula();
		
		result.append("\n");
		
		for(Ordine o : sr.getOrdini()) 
			result.append(o.toString()+"\n");
		
		result.append("Numero ordini accettati : " + sr.getOrdini_accettati() + "\n");
		result.append("Numero ordini rifiutati : " + sr.getOrdini_rifiutati() + "\n");
		result.append("Percentuale ordini accettati : " + sr.getPercentuale_accettati() + " %\n");
		result.append("Percentuale ordini rifiutati : " + (1-sr.getPercentuale_accettati()) + " %\n");

		return result.toString();
	}
}
