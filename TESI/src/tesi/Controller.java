package tesi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import tesi.model.Forecast;
import tesi.model.Model;
import tesi.model.Prodotto;
import tesi.model.Row;


public class Controller {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Prodotto> boxProdotti;

    @FXML
    private TextField alfaES;

    @FXML
    private TextField betaEST;

    @FXML
    private TextField gammaW;

    @FXML
    private ComboBox<String> boxMetodi;

    @FXML
    private TextField tau;

    @FXML
    private Button ButtonPrevisione;
    
    @FXML
    private Button btnStorico;
    
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private TextField m;

    @FXML
    private TextField alfaEST;

    @FXML
    private TextField alfaW;

    @FXML
    private TextField betaW;

    @FXML
    private TextField Nperiod;
    
    @FXML
    private TableColumn<Forecast, Integer> tbf1;

    @FXML
    private TableColumn<Forecast, Integer> tbf2;

    @FXML
    private TableColumn<Forecast, Integer> tbf3;

    @FXML
    private TableColumn<Forecast, Integer> tbf4;

    @FXML
    private TableColumn<Forecast, Integer> tbf5;

    @FXML
    private TableColumn<Forecast, Integer> tbf6;

    @FXML
    private TableColumn<Forecast, Integer> tbf7;

    @FXML
    private TableColumn<Forecast, Integer> tbf8;

    @FXML
    private TableColumn<Forecast, Integer> tbf9;
    
    @FXML
    private Tab tabMPS;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;
    
    @FXML
    private TextField txt3;
    
    @FXML
    private TextField txt4;
    
    @FXML
    private TextField txt5;

    @FXML
    private TextField txt6;
    
    @FXML
    private TextField txt7;

    @FXML
    private TextField txt8;

    @FXML
    private TextField txt9;

    @FXML
    private TextField txtLotSize;

    @FXML
    private TextField txtMagIn;
    
    @FXML
    private Button btnProsegui;

    @FXML
    private Label labelInserisciOrdini;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button buttonMPS;
    
    @FXML
    private Label labelError2;
    
    @FXML
    private TableColumn<Row, String> tbm0;
    
    @FXML
    private TableColumn<Row, Integer> tbm1;

    @FXML
    private TableColumn<Row, Integer> tbm2;

    @FXML
    private TableColumn<Row, Integer> tbm3;

    @FXML
    private TableColumn<Row, Integer> tbm4;

    @FXML
    private TableColumn<Row, Integer> tbm5;

    @FXML
    private TableColumn<Row, Integer> tbm6;

    @FXML
    private TableColumn<Row, Integer> tbm7;

    @FXML
    private TableColumn<Row, Integer> tbm8;

    @FXML
    private TableColumn<Row, Integer> tbm9;
    
    @FXML
    private Tab tabSimulation;
  
    @FXML
    private Slider sliderProb;

    @FXML
    private TextField txtMin;

    @FXML
    private TextField txtMax;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private TableView<Row> tableView;
    
    @FXML
    private TableView<Forecast> tvForecast;
    
    @FXML
    private Label labelError;
    
	@SuppressWarnings("unchecked")
	@FXML
    void doGraficoStorico(ActionEvent event) {
    	
    	lineChart.getData().clear();
    	
    	XYChart.Series<String, Number> series = model.getSeries(boxProdotti.getValue());
    	series.setName(boxProdotti.getValue().getCodice());
    	lineChart.getData().addAll(series);
    }
	
    @FXML
    void abilitaMetodo(ActionEvent event) {
    	
    	if(boxMetodi.getValue()!=null && boxProdotti.getValue()!=null) {
    		
    		tau.setDisable(false);
    		
    		if(boxMetodi.getValue().toLowerCase().equals("moving average")) {
    			
    	    	alfaES.setDisable(true);
    	    	alfaEST.setDisable(true);
    			betaEST.setDisable(true);
    			alfaW.setDisable(true);
    			betaW.setDisable(true);
    			gammaW.setDisable(true);
    			Nperiod.setDisable(true);
    			m.setDisable(false);
    		}	
    		else if (boxMetodi.getValue().toLowerCase().equals("exponential smoothing")) {
    			
    			m.setDisable(true);
    	    	alfaEST.setDisable(true);
    			betaEST.setDisable(true);
    			alfaW.setDisable(true);
    			betaW.setDisable(true);
    			gammaW.setDisable(true);
    			Nperiod.setDisable(true);
    			alfaES.setDisable(false);	
    			
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("exponential smoothing with trend")) {
    			
    			m.setDisable(true);
    			alfaW.setDisable(true);
    			betaW.setDisable(true);
    			gammaW.setDisable(true);
    			Nperiod.setDisable(true);
    			alfaES.setDisable(true);
    			alfaEST.setDisable(false);
    			betaEST.setDisable(false);
    			
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("winter")) {
    			
    			m.setDisable(true);
    	    	alfaEST.setDisable(true);
    			betaEST.setDisable(true);
    			alfaES.setDisable(true);
    			alfaW.setDisable(false);
    			betaW.setDisable(false);
    			gammaW.setDisable(false);
    			Nperiod.setDisable(false);
    			
    		}
    	}
    	else
    		labelError.setText("Selezionare un prodotto e un metodo");
    }

    
    @FXML
    void doPrevisione(ActionEvent event) {
    	
    	labelError.setText("");
    	tvForecast.setVisible(false);
    	tabMPS.setDisable(true);
    	tabSimulation.setDisable(true);
		
    	ObservableList<Forecast> result = FXCollections.observableArrayList();
    	
    	if(boxMetodi.getValue()!=null && boxProdotti.getValue()!=null) {
    		
    		if(boxMetodi.getValue().toLowerCase().equals("moving average")) {
    			
    			try { 
    				if(Integer.parseInt(tau.getText())>0 && Integer.parseInt(tau.getText())<10 && Integer.parseInt(m.getText())>0)
    					result.addAll(model.getMovingAverage(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Integer.parseInt(m.getText())));
    				else
    					labelError.setText("Il parametro m deve essere strettamente positivo, il parametro tau deve essere compreso tra 1 e 9 inclusi");
    			}
    			catch(Exception exc) {
    				labelError.setText("Inserisci i valori numerici dei parametri m e tau");
    			}
    		}	
    		else if (boxMetodi.getValue().toLowerCase().equals("exponential smoothing")) {	
    			
    			try {
    				if(Integer.parseInt(tau.getText())>0 && Integer.parseInt(tau.getText())<10 && Double.parseDouble(alfaES.getText())>0.0)
    					result.addAll(model.getExponentialSmoothing(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaES.getText())));
    				else
    					labelError.setText("Il parametro alfa deve essere strettamente positivo, il parametro tau deve essere compreso tra 1 e 9 inclusi");
    			}
    			catch(Exception exc) {
    				labelError.setText("Inserisci i valori numerici dei parametri alfa e tau");
    			}			
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("exponential smoothing with trend")) {
    			
    			try {
    				if(Integer.parseInt(tau.getText())>0 && Integer.parseInt(tau.getText())<10 && Double.parseDouble(alfaEST.getText())>0.0 && Double.parseDouble(betaEST.getText())>0.0)
    					result.addAll(model.getExponentialSmoothingWithTrend(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaEST.getText()), Double.parseDouble(betaEST.getText())));
    				else
    					labelError.setText("Il parametri alfa e beta devono essere strettamente positivi, il parametro tau deve essere compreso tra 1 e 9 inclusi");
    			}
    			catch(Exception exc) {
    				labelError.setText("Inserisci i valori numerici dei parametri alfa, beta e tau");
    			}
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("winter")) {
    			
    			try {
    				if(Integer.parseInt(tau.getText())>0 && Integer.parseInt(tau.getText())<10 && Double.parseDouble(alfaW.getText())>0.0 && Double.parseDouble(betaW.getText())>0.0 && Double.parseDouble(gammaW.getText())>0.0 && Integer.parseInt(Nperiod.getText())>0)
    					result.addAll(model.getWinter(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaW.getText()), Double.parseDouble(betaW.getText()), Double.parseDouble(gammaW.getText()), Integer.parseInt(Nperiod.getText())));
    				else
    					labelError.setText("Il parametri alfa, beta e gamma devono essere strettamente positivi, il parametro tau deve essere compreso tra 1 e 9 inclusi");
    			}
    			catch(Exception exc) {
    				labelError.setText("Inserisci i valori numerici dei parametri alfa, beta, gamma, N e tau");
    			}
    		}
    	}
    	else
    		labelError.setText("Selezionare un prodotto e un metodo");
    	
    	if(!result.isEmpty()) {
    		tabMPS.setDisable(false);
    		switch (Integer.parseInt(tau.getText())) {
    		
    		case 1 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
    			break;
    			
    		case 2 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		break; 
        		
    		case 3 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		break;
        		
    		case 4 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		break;
        		
    		case 5 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		tbf5.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("five"));
        		break;
        		
    		case 6 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		tbf5.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("five"));
        		tbf6.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("six"));
        		break;
        		
    		case 7 :
    	 		tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		tbf5.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("five"));
        		tbf6.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("six"));
        		tbf7.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("seven"));
        		break;
        		
    		case 8 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		tbf5.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("five"));
        		tbf6.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("six"));
        		tbf7.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("seven"));
        		tbf8.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("eight"));
        		break;
        		
    		case 9 :
    			tbf1.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("one"));
        		tbf2.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("two"));
        		tbf3.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("three"));
        		tbf4.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("four"));
        		tbf5.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("five"));
        		tbf6.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("six"));
        		tbf7.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("seven"));
        		tbf8.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("eight"));
        		tbf9.setCellValueFactory(new PropertyValueFactory<Forecast, Integer>("nine"));
        		break;  			
    		}
    			
    		tvForecast.setItems(result);
    		tvForecast.setVisible(true);
    	}
    }
    
    @FXML
    void rendiVisibileMPS(ActionEvent event) {
    	
    	labelInserisciOrdini.setVisible(false);
    	gridPane.setVisible(false);
    	buttonMPS.setVisible(false);
    	labelError2.setText("");
    	
    	try {
			if(Integer.parseInt(txtLotSize.getText())>0) {
				if(Integer.parseInt(txtMagIn.getText())>=0) {
					labelInserisciOrdini.setVisible(true);
					gridPane.setVisible(true);
					buttonMPS.setVisible(true);
					
				switch(Integer.parseInt(tau.getText())) {
					
					case 1 :
						txt1.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
						
						break;
						
					case 2 :
						txt1.setDisable(false);
						txt2.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		
						break;
						
					case 3 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));

			    		break;
						
					case 4 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
						
						break;
						
					case 5 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
						
						break;
						
					case 6 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
						
						break;
						
					case 7 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));
						
						break;
						
					case 8 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
						txt8.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));
			    		tbm8.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb8"));
						
						break;
						
					case 9 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
						txt8.setDisable(false);
						txt9.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));
			    		tbm8.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb8"));
			    		tbm9.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb9"));
						
						break;
					}
				}
				else
					labelError2.setText("Il valore del magazzino iniziale deve essere maggiore o uguale a zero");
			}
			else
				labelError2.setText("Il valore di MPS Lot-size deve essere maggiore di zero");
    	}
    	catch(Exception exc) {
    		labelError2.setText("Inserisci un valore di MPS Lot-size e un valore iniziale di disponibilità del magazzino");
    	}
    }

    @FXML
    void doMPS(ActionEvent event) {
    	
    	tabSimulation.setDisable(true);
    	
    	ObservableList<Row> result = FXCollections.observableArrayList();
    	
    	try {
			if(Integer.parseInt(txtLotSize.getText())>0) {
				if(Integer.parseInt(txtMagIn.getText())>=0) {
					
					switch(Integer.parseInt(tau.getText())) {
					
					case 1 :
						txt1.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		
						try {
							if(Integer.parseInt(txt1.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText())));
							else
								labelError2.setText("Il valore non può essere negativo");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci il valore numerico");
						}
						
						break;
						
					case 2 :
						txt1.setDisable(false);
						txt2.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori numerici");
						}
							
						break;
						
					case 3 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
						
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori numerici");
						}
							
						break;
						
					case 4 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
						
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()),  Integer.parseInt(txt4.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
						
					case 5 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
						
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
						
					case 6 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));

						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0 && Integer.parseInt(txt6.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
						
					case 7 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));

						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0 && Integer.parseInt(txt6.getText())>=0 && Integer.parseInt(txt7.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText()), Integer.parseInt(txt7.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
						
					case 8 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
						txt8.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));
			    		tbm8.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb8"));
			    		
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0 && Integer.parseInt(txt6.getText())>=0 && Integer.parseInt(txt7.getText())>=0 && Integer.parseInt(txt8.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText()), Integer.parseInt(txt7.getText()), Integer.parseInt(txt8.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
						
					case 9 :
						txt1.setDisable(false);
						txt2.setDisable(false);
						txt3.setDisable(false);
						txt4.setDisable(false);
						txt5.setDisable(false);
						txt6.setDisable(false);
						txt7.setDisable(false);
						txt8.setDisable(false);
						txt9.setDisable(false);
			        	tbm0.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
			    		tbm1.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb1"));
			    		tbm2.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb2"));
			    		tbm3.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb3"));
			    		tbm4.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb4"));
			    		tbm5.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb5"));
			    		tbm6.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb6"));
			    		tbm7.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb7"));
			    		tbm8.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb8"));
			    		tbm9.setCellValueFactory(new PropertyValueFactory<Row, Integer>("tb9"));
			    		
						try {
							if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 && Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0 && Integer.parseInt(txt6.getText())>=0 && Integer.parseInt(txt7.getText())>=0 && Integer.parseInt(txt8.getText())>=0 && Integer.parseInt(txt9.getText())>=0)
								result.addAll(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText()), Integer.parseInt(txt7.getText()), Integer.parseInt(txt8.getText()), Integer.parseInt(txt9.getText())));
							else
								labelError2.setText("I valori non possono essere negativi");
						}
						catch(Exception exc) {
							labelError2.setText("Inserisci tutti i valori");
						}
						
						break;
					}
				}
				else
					labelError2.setText("Il valore del magazzino iniziale deve essere maggiore o uguale a zero");
			}
			else
				labelError2.setText("Il valore di MPS Lot-size deve essere maggiore di zero");
    	}
    	catch(Exception exc) {
    		labelError2.setText("Inserisci un valore di MPS Lot-size e un valore iniziale di disponibilità del magazzino");
    	}
						
    	if(!result.isEmpty()) {
    		tabSimulation.setDisable(false);
    		tableView.setItems(result);
    		tableView.setVisible(true);	
    	}
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	
    	if(sliderProb.getValue()>=0) {
    		try {
    			if(Integer.parseInt(txtMin.getText())>=0 && Integer.parseInt(txtMax.getText())>=0) 
    				if(Integer.parseInt(txtMin.getText())<Integer.parseInt(txtMax.getText()))
    					txtResult.appendText(model.simulaModel(sliderProb.getValue(), Integer.parseInt(txtMin.getText()), Integer.parseInt(txtMax.getText())));
    				else
    					txtResult.appendText("Inserisci un valore di min minore di quello di max");
    			else
    				txtResult.appendText("Inserisci valori interi positivi di min e max");
    		}
    		catch(Exception exp) {
    			txtResult.appendText("Inserisci valori interi positivi di min e max");
    		}
    	}
    	else
    		txtResult.appendText("Scegli un valore di probabilità");
    }

    @FXML
    void initialize() {
        assert boxProdotti != null : "fx:id=\"boxProdotti\" was not injected: check your FXML file 'tesi.fxml'.";
        assert alfaES != null : "fx:id=\"alfaES\" was not injected: check your FXML file 'tesi.fxml'.";
        assert betaEST != null : "fx:id=\"betaEST\" was not injected: check your FXML file 'tesi.fxml'.";
        assert gammaW != null : "fx:id=\"gammaW\" was not injected: check your FXML file 'tesi.fxml'.";
        assert boxMetodi != null : "fx:id=\"boxMetodi\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tau != null : "fx:id=\"tau\" was not injected: check your FXML file 'tesi.fxml'.";
        assert ButtonPrevisione != null : "fx:id=\"ButtonPrevisione\" was not injected: check your FXML file 'tesi.fxml'.";
        assert btnStorico != null : "fx:id=\"btnStorico\" was not injected: check your FXML file 'tesi.fxml'.";
        assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'tesi.fxml'.";
        assert y != null : "fx:id=\"y\" was not injected: check your FXML file 'tesi.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'tesi.fxml'.";
        assert m != null : "fx:id=\"m\" was not injected: check your FXML file 'tesi.fxml'.";
        assert alfaEST != null : "fx:id=\"alfaEST\" was not injected: check your FXML file 'tesi.fxml'.";
        assert alfaW != null : "fx:id=\"alfaW\" was not injected: check your FXML file 'tesi.fxml'.";
        assert betaW != null : "fx:id=\"betaW\" was not injected: check your FXML file 'tesi.fxml'.";
        assert Nperiod != null : "fx:id=\"Nperiod\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf1 != null : "fx:id=\"tbf1\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf2 != null : "fx:id=\"tbf2\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf3 != null : "fx:id=\"tbf3\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf4 != null : "fx:id=\"tbf4\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf5 != null : "fx:id=\"tbf5\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf6 != null : "fx:id=\"tbf6\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf7 != null : "fx:id=\"tbf7\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf8 != null : "fx:id=\"tbf8\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbf9 != null : "fx:id=\"tbf9\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt1 != null : "fx:id=\"txt1\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt2 != null : "fx:id=\"txt2\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt3 != null : "fx:id=\"txt3\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt4 != null : "fx:id=\"txt4\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt5 != null : "fx:id=\"txt5\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt6 != null : "fx:id=\"txt6\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt7 != null : "fx:id=\"txt7\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt8 != null : "fx:id=\"txt8\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt9 != null : "fx:id=\"txt9\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tabMPS != null : "fx:id=\"tabMPS\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtLotSize != null : "fx:id=\"txtLotSize\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMagIn != null : "fx:id=\"txtMagIn\" was not injected: check your FXML file 'tesi.fxml'.";
        assert btnProsegui != null : "fx:id=\"btnProsegui\" was not injected: check your FXML file 'tesi.fxml'.";
        assert labelInserisciOrdini != null : "fx:id=\"labelInserisciOrdini\" was not injected: check your FXML file 'tesi.fxml'.";
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'tesi.fxml'.";
        assert buttonMPS != null : "fx:id=\"buttonMPS\" was not injected: check your FXML file 'tesi.fxml'.";
        assert labelError2 != null : "fx:id=\"labelError2\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm0 != null : "fx:id=\"tbm0\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm1 != null : "fx:id=\"tbm1\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm2 != null : "fx:id=\"tbm2\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm3 != null : "fx:id=\"tbm3\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm4 != null : "fx:id=\"tbm4\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm5 != null : "fx:id=\"tbm5\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm6 != null : "fx:id=\"tbm6\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm7 != null : "fx:id=\"tbm7\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm8 != null : "fx:id=\"tbm8\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tbm9 != null : "fx:id=\"tbm9\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tabSimulation != null : "fx:id=\"tabSimulation\" was not injected: check your FXML file 'tesi.fxml'.";
        assert sliderProb != null : "fx:id=\"sliderProb\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMin != null : "fx:id=\"txtMin\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'tesi.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tvForecast != null : "fx:id=\"tvForecast\" was not injected: check your FXML file 'tesi.fxml'.";
        assert labelError != null : "fx:id=\"labelError\" was not injected: check your FXML file 'tesi.fxml'.";
    }
    
	public void setModel(Model model) {
		
		this.model = model;
		
		boxProdotti.getItems().addAll(model.getProdotti());
		boxMetodi.getItems().addAll(model.getMetodi());
	}
}
