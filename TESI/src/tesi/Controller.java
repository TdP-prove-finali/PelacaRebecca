package tesi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tesi.model.Model;
import tesi.model.Prodotto;

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
    private TextField txtLotSize;

    @FXML
    private TextField txtMagIn;

    @FXML
    private Button buttonMPS;
    
    @FXML
    private Button btnAggiornaStorico;
    
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
    
    @SuppressWarnings("unchecked")
	@FXML
    void doGraficoStorico(ActionEvent event) {
    	
    	lineChart.getData().clear();
    	
    	XYChart.Series<String, Number> series = model.getSeries(boxProdotti.getValue());
    	series.setName(boxProdotti.getValue().getCodice());
    	lineChart.getData().addAll(series);
    }
    
    @FXML
    void doPrevisione(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(boxMetodi.getValue()!=null && boxProdotti.getValue()!=null) {
    		
    		if(boxMetodi.getValue().toLowerCase().equals("moving average")) {
    			if(!tau.getText().equals("") && !m.getText().equals("")) 
    				if(Integer.parseInt(tau.getText())>0 && Integer.parseInt(m.getText())>0)
    					txtResult.appendText(model.getMovingAverage(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Integer.parseInt(m.getText())));
    				else
    					txtResult.appendText("Inserisci i parametri strettamente positivi m e tau");
    			else
    				txtResult.appendText("Inserisci i parametri strettamente positivi m e tau");
    		}	
    		else if (boxMetodi.getValue().toLowerCase().equals("exponential smoothing")) {
    			if(!tau.getText().equals("") && !alfaES.getText().equals(""))
    				if(Integer.parseInt(tau.getText())>0 && Double.parseDouble(alfaES.getText())>0.0)
    					txtResult.appendText(model.getExponentialSmoothing(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaES.getText())));
    				else
    					txtResult.appendText("Inserisci i parametri strettamente positivi alfa e tau");
    			else
    				txtResult.appendText("Inserisci i parametri strettamente positivi alfa e tau");				
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("exponential smoothing with trend")) {
    			if(!tau.getText().equals("") && !alfaEST.getText().equals("") && !betaEST.getText().equals(""))
    				if(Integer.parseInt(tau.getText())>0 && Double.parseDouble(alfaEST.getText())>0.0 && Double.parseDouble(betaEST.getText())>0.0)
    					txtResult.appendText(model.getExponentialSmoothingWithTrend(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaEST.getText()), Double.parseDouble(betaEST.getText())));
    				else
    					txtResult.appendText("Inserisci i parametri strettamente positivi alfa, beta e tau");
    			else
    				txtResult.appendText("Inserisci i parametri strettamente positivi alfa, beta e tau");	
    		}	
    		else if(boxMetodi.getValue().toLowerCase().equals("winter")) {
    			if(!tau.getText().equals("") && !alfaW.getText().equals("") && !betaW.getText().equals("") && !gammaW.getText().equals("") && !Nperiod.getText().equals(""))
    				if(Integer.parseInt(tau.getText())>0 && Double.parseDouble(alfaW.getText())>0.0 && Double.parseDouble(betaW.getText())>0.0 && Double.parseDouble(gammaW.getText())>0.0 && Integer.parseInt(Nperiod.getText())>0)
    					txtResult.appendText(model.getWinter(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfaW.getText()), Double.parseDouble(betaW.getText()), Double.parseDouble(gammaW.getText()), Integer.parseInt(Nperiod.getText())));
    				else
    					txtResult.appendText("Inserisci i parametri strettamente positivi alfa, beta, gamma, tau e N");
    			else
    				txtResult.appendText("Inserisci i parametri strettamente positivi alfa, beta, gamma, tau e N");		
    		}
    	}
    	else
    		txtResult.appendText("Selezionare un prodotto e un metodo");
    }

    @FXML
    void doMPS(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(!txt1.getText().equals("") && !txt2.getText().equals("") && !txt3.getText().equals("") &&
    	   !txt4.getText().equals("") && !txt5.getText().equals("") && !txt6.getText().equals(""))
    		if(Integer.parseInt(txt1.getText())>=0 && Integer.parseInt(txt2.getText())>=0 && Integer.parseInt(txt3.getText())>=0 &&
    		   Integer.parseInt(txt4.getText())>=0 && Integer.parseInt(txt5.getText())>=0 && Integer.parseInt(txt6.getText())>=0)
    			if(!txtLotSize.getText().equals(""))
    				if(Integer.parseInt(txtLotSize.getText())>0)
    					if(!txtMagIn.getText().equals(""))
    						if(Integer.parseInt(txtMagIn.getText())>=0)
    							txtResult.appendText(model.getMPSeATP(boxProdotti.getValue(), Integer.parseInt(txtLotSize.getText()), Integer.parseInt(txtMagIn.getText()),
    									             Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()),Integer.parseInt(txt3.getText()),
    									             Integer.parseInt(txt4.getText()), Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText())));
    						else
    							txtResult.appendText("Il valore del magazzino iniziale deve essere maggiore o uguale a zero");
    					else
    						txtResult.appendText("Inserisci un valore iniziale di disponibilità del magazzino maggiore o uguale a zero");
    				else
    					txtResult.appendText("Il valore di MPS Lot-size deve essere maggiore di zero");
    			else
    				txtResult.appendText("Inserisci un valore di MPS lot-size maggiore di zero");
    		else
    			txtResult.appendText("Tutti i valori degli ordini acquisiti devono essere interi positivi");
    	else
    		txtResult.appendText("Inserisci i valori degli ordini acquisiti per i 10 periodi, dopo aver avviato una previsione con tau = 10");
    }
    
    @FXML
    void doAggiornaStorico(ActionEvent event) {
    	
    	model.aggiornaStoricoModel(boxProdotti.getValue(), Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()),Integer.parseInt(txt3.getText()), Integer.parseInt(txt4.getText()),
				Integer.parseInt(txt5.getText()), Integer.parseInt(txt6.getText()));
    }
    
    @FXML
    void doSimula(ActionEvent event) {

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
        assert txt1 != null : "fx:id=\"txt1\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt2 != null : "fx:id=\"txt2\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt3 != null : "fx:id=\"txt3\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt4 != null : "fx:id=\"txt4\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt5 != null : "fx:id=\"txt5\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txt6 != null : "fx:id=\"txt6\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtLotSize != null : "fx:id=\"txtLotSize\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMagIn != null : "fx:id=\"txtMagIn\" was not injected: check your FXML file 'tesi.fxml'.";
        assert buttonMPS != null : "fx:id=\"buttonMPS\" was not injected: check your FXML file 'tesi.fxml'.";
        assert btnAggiornaStorico != null : "fx:id=\"btnAggiornaStorico\" was not injected: check your FXML file 'tesi.fxml'.";
        assert sliderProb != null : "fx:id=\"sliderProb\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMin != null : "fx:id=\"txtMin\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'tesi.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'tesi.fxml'.";
    }
    
	public void setModel(Model model) {
		
		this.model = model;
		
		boxProdotti.getItems().addAll(model.getProdotti());
		boxMetodi.getItems().addAll(model.getMetodi());
	}
}
