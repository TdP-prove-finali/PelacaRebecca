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
	
	private DAO dao;
	
	private List<String> metodi;	
	private List<Prodotto> prodotti;
	private List<Integer> forecast;
	private int[] ATP;
	
	
	public Model() {
		
		this.dao = new DAO();
		this.prodotti = dao.getProdottiDB();
	
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
	
	public ObservableList<Forecast> getMovingAverage(Prodotto prodotto, int tau, int m) {
		
		this.forecast = new ArrayList<Integer>();
		
		ObservableList<Forecast> result = FXCollections.observableArrayList();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		
//		List<Integer> demand = new ArrayList<Integer>();
//		demand.add(10);
//		demand.add(12);
//		demand.add(12);
//		demand.add(11);
//		demand.add(15);
//		demand.add(14);
//		demand.add(18);
//		demand.add(22);
//		demand.add(18);
//		demand.add(28);
//		demand.add(33);
//		demand.add(31);
//		demand.add(31);
//		demand.add(37);
//		demand.add(40);
//		demand.add(33);
//		demand.add(50);
//		demand.add(45);
//		demand.add(55);
//		demand.add(60);
		
		List<Double> smoothed_estimate = new ArrayList<Double>();
		
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
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add((int)Math.round(smoothed_estimate.get(last_index-i)));
		}
		
		Forecast tableRow = null;
		
		switch (forecast.size()) {
		
		case 1 : 
			tableRow = new Forecast(forecast.get(0));
			break;
			
		case 2 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1));
			break;
			
		case 3 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2));
			break;
			
		case 4 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3));
			break;
			
		case 5 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4));
			break;
			
		case 6 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5));
			break;
			
		case 7 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6));
			break;
			
		case 8 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7));
			break;
			
		case 9 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7), forecast.get(8));
			break;
		}
		
		result.add(tableRow);
		
		return result; 
	
	}

	public ObservableList<Forecast> getExponentialSmoothing(Prodotto prodotto, int tau, double alfa) {
		
		ObservableList<Forecast> result = FXCollections.observableArrayList();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		
//		List<Integer> demand = new ArrayList<Integer>();
//		demand.add(10);
//		demand.add(12);
//		demand.add(12);
//		demand.add(11);
//		demand.add(15);
//		demand.add(14);
//		demand.add(18);
//		demand.add(22);
//		demand.add(18);
//		demand.add(28);
//		demand.add(33);
//		demand.add(31);
//		demand.add(31);
//		demand.add(37);
//		demand.add(40);
//		demand.add(33);
//		demand.add(50);
//		demand.add(45);
//		demand.add(55);
//		demand.add(60);
		
		List<Double> smoothed_estimate = new ArrayList<Double>();
		this.forecast = new ArrayList<Integer>();
		
		double f;
		
		smoothed_estimate.add((double)demand.get(0));
		
		for(int i=1; i<demand.size(); i++) {
			f = alfa*demand.get(i)+(1-alfa)*smoothed_estimate.get(i-1);
			smoothed_estimate.add(f);
		}
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			forecast.add((int)Math.round(smoothed_estimate.get(last_index-i)));
		}
	
		Forecast tableRow = null;
		
		switch (forecast.size()) {
		
		case 1 : 
			tableRow = new Forecast(forecast.get(0));
			break;
			
		case 2 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1));
			break;
			
		case 3 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2));
			break;
			
		case 4 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3));
			break;
			
		case 5 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4));
			break;
			
		case 6 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5));
			break;
			
		case 7 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6));
			break;
			
		case 8 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7));
			break;
			
		case 9 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7), forecast.get(8));
			break;
		}
		
		result.add(tableRow);
		
		return result;
	}

	public ObservableList<Forecast> getExponentialSmoothingWithTrend(Prodotto prodotto, int tau, double alfa, double beta) {
		
		ObservableList<Forecast> result = FXCollections.observableArrayList();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		
//		List<Integer> demand = new ArrayList<Integer>();
//		demand.add(10);
//		demand.add(12);
//		demand.add(12);
//		demand.add(11);
//		demand.add(15);
//		demand.add(14);
//		demand.add(18);
//		demand.add(22);
//		demand.add(18);
//		demand.add(28);
//		demand.add(33);
//		demand.add(31);
//		demand.add(31);
//		demand.add(37);
//		demand.add(40);
//		demand.add(33);
//		demand.add(50);
//		demand.add(45);
//		demand.add(55);
//		demand.add(60);
		
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		this.forecast = new ArrayList<Integer>();
		
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
			forecast.add((int)Math.round(smoothed_estimate.get(last_index-i)+tau*smoothed_trend.get(last_index-i)));
		}
		
		Forecast tableRow = null;
		
		switch (forecast.size()) {
		
		case 1 : 
			tableRow = new Forecast(forecast.get(0));
			break;
			
		case 2 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1));
			break;
			
		case 3 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2));
			break;
			
		case 4 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3));
			break;
			
		case 5 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4));
			break;
			
		case 6 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5));
			break;
			
		case 7 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6));
			break;
			
		case 8 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7));
			break;
			
		case 9 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7), forecast.get(8));
			break;
		}
		
		result.add(tableRow);

		return result;
	}

	public ObservableList<Forecast> getWinter(Prodotto prodotto, int tau, double alfa, double beta, double gamma, int N) {
		
		ObservableList<Forecast> result = FXCollections.observableArrayList();
		
		List<Integer> demand = dao.getStoricoDB(prodotto);
		
//		List<Integer> demand = new ArrayList<Integer>();
//		
//		demand.add(4);
//		demand.add(2);
//		demand.add(5);
//		demand.add(8);
//		demand.add(11);
//		demand.add(13);
//		demand.add(18);
//		demand.add(15);
//		demand.add(9);
//		demand.add(6);
//		demand.add(5);
//		demand.add(4);
//		demand.add(5);
//		demand.add(4);
//		demand.add(7);
//		demand.add(7);
//		demand.add(15);
//		demand.add(17);
//		demand.add(24);
//		demand.add(18);
//		demand.add(12);
//		demand.add(7);
//		demand.add(8);
//		demand.add(6);
		
		List<Double> smoothed_estimate = new ArrayList<Double>();
		List<Double> smoothed_trend = new ArrayList<Double>();
		List<Double> smoothed_seasonality = new ArrayList<Double>();
		this.forecast = new ArrayList<Integer>();
		
		int sum = 0;
		double average;
		
		for(int i=0; i<N; i++) 
			sum += demand.get(i);
		
		average = (double)sum/N;
		
		for(int i=0; i<N; i++) {
			smoothed_seasonality.add(demand.get(i)/average);
			smoothed_trend.add(0.0);
			if(i<N-1)
				smoothed_estimate.add(0.0);		
		}

		smoothed_estimate.add(average);
				
		double f;
		double t;
		double c;
		
		for(int i=N; i<demand.size(); i++) {
//			System.out.println("DEMAND " + demand.get(i));
//			System.out.println("SMOOTHED SEASONALITY " + smoothed_seasonality.get(i-N));
//			System.out.println("SMOOTHED ESTIMATE " + smoothed_estimate.get(i-N));
//			System.out.println("SMOOTHED TREND " + smoothed_trend.get(i-1));
			f = alfa*(demand.get(i)/smoothed_seasonality.get(i-N))+(1-alfa)*(smoothed_estimate.get(i-1)+smoothed_trend.get(i-1));
//			System.out.println("f = " + f);
			smoothed_estimate.add(f);
//			System.out.println("TREND");
//			System.out.println("SMOOTHED ESTIMATE i " + smoothed_estimate.get(i));
//			System.out.println("SMOOTHED ESTIMATE i-1 " + smoothed_estimate.get(i-1));
//			System.out.println("SMOOTHED TREND " + smoothed_trend.get(i-1));
			t = beta*(smoothed_estimate.get(i)-smoothed_estimate.get(i-1))+(1-beta)*smoothed_trend.get(i-1);
//			System.out.println("t = " + t);
			smoothed_trend.add(t);
//			System.out.println("SEASONALITY");
//			System.out.println("DEMAND " + demand.get(i));
//			System.out.println("SMOOTHED ESTIMATE " + smoothed_estimate.get(i));
//			System.out.println("SMOOTHED SEASONALITY " + smoothed_seasonality.get(i-N));
			c = gamma*(demand.get(i)/smoothed_estimate.get(i))+(1-gamma)*smoothed_seasonality.get(i-N);
//			System.out.println("c = " + c);
			smoothed_seasonality.add(c);
			
//			System.out.println("/////////////////////////////////");
		}
		
		for(int i=tau-1; i>=0; i--) {
			int last_index = smoothed_estimate.size()-1;
			int res = (int)Math.round((smoothed_estimate.get(last_index-i)+tau*smoothed_trend.get(last_index-i))*smoothed_seasonality.get(last_index-i-N+1));
//			System.out.println("Res " + res);
			forecast.add(res);
		}		
		
		Forecast tableRow = null;
		
		switch (forecast.size()) {
		
		case 1 : 
			tableRow = new Forecast(forecast.get(0));
			break;
			
		case 2 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1));
			break;
			
		case 3 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2));
			break;
			
		case 4 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3));
			break;
			
		case 5 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4));
			break;
			
		case 6 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5));
			break;
			
		case 7 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6));
			break;
			
		case 8 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7));
			break;
			
		case 9 : 
			tableRow = new Forecast(forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7), forecast.get(8));
			break;
		}
		
		result.add(tableRow);
		
		return result;
	}

	public ObservableList<Row> getMPSeATP(Prodotto prodotto, int lotSize, int magIn, int...tbs) {

		int[] ordini_acquisiti = new int[forecast.size()];
		int[] disponibilita_magazzino = new int[forecast.size()];
		int[] MPSquantity = new int[forecast.size()];
		this.ATP = new int[forecast.size()];
		
		int count=0;
		
		for (int tb : tbs) {
			ordini_acquisiti[count] = tb;
			MPSquantity[count] = 0;      
			ATP[count++] = 0;               // setta a 0 il valore all'indice count e poi fa l'incremento
		}
		
		int It;
		
		for(int i=0; i<forecast.size(); i++) {
			
			It = 0;
			
			if(i==0)
				It = magIn + MPSquantity[i];
			else 
				It = disponibilita_magazzino[i-1] + MPSquantity[i];
			
			if(forecast.get(i)>=ordini_acquisiti[i])
				It -= forecast.get(i);
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
					It -= forecast.get(i);
				else
					It -= ordini_acquisiti[i];
				
				disponibilita_magazzino[i] = It;
			}
		}
		
		int atp;
		boolean calcolaATP;
		
		for(int i=0; i<forecast.size(); i++) {
			
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
								
				if(atp<0 && i!=0) {
					for(int j=i; j>=0; j--) {
						if(ATP[j]>0) {
							ATP[j] += atp;
							ATP[i] = 0;
							break;
						}
					}
				}
				else
					ATP[i] = atp;		
			}
		}
		
		ObservableList<Row> result = FXCollections.observableArrayList();
		
		switch (forecast.size()) {
		
		case 1 : 
			result.add(new Row("Previsione", forecast.get(0)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0]));
			result.add(new Row("Quantità MPS", MPSquantity[0]));
			result.add(new Row("ATP", ATP[0]));
			break;
			
		case 2 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1]));
			result.add(new Row("ATP", ATP[0], ATP[1]));
			break;
			
		case 3 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2]));
			break;
			
		case 4 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3]));
			break;
			
		case 5 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3], ordini_acquisiti[4]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4]));
			break;
			
		case 6 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3], ordini_acquisiti[4], ordini_acquisiti[5]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4], disponibilita_magazzino[5]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4], MPSquantity[5]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4], ATP[5]));
			break;
			
		case 7 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3], ordini_acquisiti[4], ordini_acquisiti[5], ordini_acquisiti[6]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4], disponibilita_magazzino[5], disponibilita_magazzino[6]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4], MPSquantity[5], MPSquantity[6]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4], ATP[5], ATP[6]));
			break;
			
		case 8 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3], ordini_acquisiti[4], ordini_acquisiti[5], ordini_acquisiti[6], ordini_acquisiti[7]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4], disponibilita_magazzino[5], disponibilita_magazzino[6], disponibilita_magazzino[7]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4], MPSquantity[5], MPSquantity[6], MPSquantity[7]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4], ATP[5], ATP[6], ATP[7]));
			break;
			
		case 9 : 
			result.add(new Row("Previsione", forecast.get(0), forecast.get(1), forecast.get(2), forecast.get(3), forecast.get(4), forecast.get(5), forecast.get(6), forecast.get(7), forecast.get(8)));
			result.add(new Row("Ordini acquisiti", ordini_acquisiti[0], ordini_acquisiti[1], ordini_acquisiti[2], ordini_acquisiti[3], ordini_acquisiti[4], ordini_acquisiti[5], ordini_acquisiti[6], ordini_acquisiti[7], ordini_acquisiti[8]));
			result.add(new Row("Disponibilità magazzino", disponibilita_magazzino[0], disponibilita_magazzino[1], disponibilita_magazzino[2], disponibilita_magazzino[3], disponibilita_magazzino[4], disponibilita_magazzino[5], disponibilita_magazzino[6], disponibilita_magazzino[7], disponibilita_magazzino[8]));
			result.add(new Row("Quantità MPS", MPSquantity[0], MPSquantity[1], MPSquantity[2], MPSquantity[3], MPSquantity[4], MPSquantity[5], MPSquantity[6], MPSquantity[7], MPSquantity[8]));
			result.add(new Row("ATP", ATP[0], ATP[1], ATP[2], ATP[3], ATP[4], ATP[5], ATP[6], ATP[7], ATP[8]));
			break;
		}
		
		return result;
		
//		int[] ordini_acquisiti = new int[8];
//		int[] disponibilita_magazzino = new int[8];
//		int[] MPSquantity = new int[8];
//		this.ATP = new int[8];
//		this.forecast = new ArrayList<Integer>();
//		
//		forecast.add(20);
//		forecast.add(20);
//		forecast.add(20);
//		forecast.add(20);
//		forecast.add(40);
//		forecast.add(40);
//		forecast.add(40);
//		forecast.add(40);
//		
//		ordini_acquisiti[0]=23;
//		ordini_acquisiti[1]=15;
//		ordini_acquisiti[2]=8;
//		ordini_acquisiti[3]=4;
//		ordini_acquisiti[4]=0;
//		ordini_acquisiti[5]=0;
//		ordini_acquisiti[6]=0;
//		ordini_acquisiti[7]=0;
//		
//		int count=0;
//		
//		for (int i=0; i<8; i++) {
//			MPSquantity[count] = 0;      
//			ATP[count++] = 0;               // setta a 0 il valore all'indice count e poi fa l'incremento
//		}
//		
//		int It;
//		
//		for(int i=0; i<8; i++) {
//			
//			It = 0;
//			
//			if(i==0)
//				It = magIn + MPSquantity[i];
//			else 
//				It = disponibilita_magazzino[i-1] + MPSquantity[i];
//			
//			if(forecast.get(i)>=ordini_acquisiti[i])
//				It -= forecast.get(i);
//			else
//				It -= ordini_acquisiti[i];
//			
//			if(It>=0)
//				disponibilita_magazzino[i] = It;
//			else {
//				MPSquantity[i] = lotSize;
//				
//				if(i==0)
//					It = magIn + MPSquantity[i];
//				else 
//					It = disponibilita_magazzino[i-1] + MPSquantity[i];
//				
//				if(forecast.get(i)>=ordini_acquisiti[i])
//					It -= forecast.get(i);
//				else
//					It -= ordini_acquisiti[i];
//				
//				disponibilita_magazzino[i] = It;
//			}
//		}
//		
//		int atp;
//		boolean calcolaATP;
//		
//		for(int i=0; i<forecast.size(); i++) {
//			
//			atp = 0;
//			calcolaATP = false;
//			
//			if(i==0) {
//				atp = magIn + MPSquantity[i];
//				calcolaATP = true;
//			}
//			else if(MPSquantity[i]>0) {
//				atp = MPSquantity[i];
//				calcolaATP = true;
//			}
//			
//			if(calcolaATP == true) {
//				for(int j=i; j<MPSquantity.length; j++) {
//					if(j==i || MPSquantity[j]==0)
//						atp -= ordini_acquisiti[j];
//					else if(MPSquantity[j]>0)
//						break;
//				}
//				ATP[i] = atp;
//			}
//		}
//		
//		for(int i=0; i<8; i++)
//			System.out.println(forecast.get(i) + " ");
//		
//		System.out.println("\n");
//		
//		for(int i=0; i<8; i++)
//			System.out.println(ordini_acquisiti[i] + " ");
//		
//		System.out.println("\n");
//		
//		for(int i=0; i<8; i++)
//			System.out.println(MPSquantity[i] + " ");
//		
//		System.out.println("\n");
//		
//		for(int i=0; i<8; i++)
//			System.out.println(ATP[i] + " ");
		
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
		
		for(Ordine o : sr.getOrdini()) 
			result.append(o.toString()+"\n");
		
		result.append("Numero ordini accettati : " + sr.getOrdini_accettati() + "\n");
		result.append("Numero ordini rifiutati : " + sr.getOrdini_rifiutati() + "\n");
		result.append("Percentuale ordini accettati : " + Math.floor(sr.getPercentuale_accettati()) + " %\n");
		result.append("Percentuale ordini rifiutati : " + Math.floor(sr.getPercentuale_rifiutati()) + " %\n\n");

		return result.toString();
	}
}
