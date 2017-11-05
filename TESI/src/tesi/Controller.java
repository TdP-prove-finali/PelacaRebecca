package tesi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private TextField alfa;

    @FXML
    private TextField beta;

    @FXML
    private TextField gamma;

    @FXML
    private ComboBox<String> boxMetodi;

    @FXML
    private TextField tau;

    @FXML
    private Button ButtonPrevisione;
    
    @FXML
    private TextField m;

    @FXML
    private TextArea txtResult;

    @FXML
    void doPrevisione(ActionEvent event) {
    	
    	if(boxMetodi.getValue()!=null && boxProdotti.getValue()!=null) {
    		
    		if(tau.getText()!="") {
    			
	    		if(boxMetodi.getValue().toLowerCase().equals("moving average")) {
	    			if(m.getText()!="")
	    				model.getMovingAverage(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Integer.parseInt(m.getText()));
	    			else
	    				txtResult.appendText("Inserisci il parametro m");
	    		}	
	    		else if (boxMetodi.getValue().toLowerCase().equals("exponential smoothing")) {
	    			if(alfa.getText()!="")
	    				model.getExponentialSmoothing(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfa.getText()));
	    			else
	    				txtResult.appendText("Inserisci il parametro alfa");
	    		}	
	    		else if(boxMetodi.getValue().toLowerCase().equals("exponential smoothing with trend")) {
	    			if(alfa.getText()!="" && beta.getText()!="")
	    				model.getExponentialSmoothingWithTrend(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfa.getText()), Double.parseDouble(beta.getText()));
	    			else
	    				txtResult.appendText("Inserisci parametri alfa e beta");
	    		}	
	    		else if(boxMetodi.getValue().toLowerCase().equals("winter")) {
	    			if(alfa.getText()!="" && beta.getText()!="" && gamma.getText()!="")
	    				model.getWinter(boxProdotti.getValue(), Integer.parseInt(tau.getText()), Double.parseDouble(alfa.getText()), Double.parseDouble(beta.getText()), Double.parseDouble(gamma.getText()));
	    			else 
	    				txtResult.appendText("Inserisci i parametri alfa, beta e gamma");
	    		}
	    			
    		}
    		else
    			txtResult.appendText("Inserisci il parametro tau");
    	}
    	else
    		txtResult.appendText("Selezionare un prodotto e un metodo");
    }

    @FXML
    void initialize() {
        assert boxProdotti != null : "fx:id=\"boxProdotti\" was not injected: check your FXML file 'tesi.fxml'.";
        assert alfa != null : "fx:id=\"alfa\" was not injected: check your FXML file 'tesi.fxml'.";
        assert beta != null : "fx:id=\"beta\" was not injected: check your FXML file 'tesi.fxml'.";
        assert gamma != null : "fx:id=\"gamma\" was not injected: check your FXML file 'tesi.fxml'.";
        assert boxMetodi != null : "fx:id=\"boxMetodi\" was not injected: check your FXML file 'tesi.fxml'.";
        assert tau != null : "fx:id=\"tau\" was not injected: check your FXML file 'tesi.fxml'.";
        assert ButtonPrevisione != null : "fx:id=\"ButtonPrevisione\" was not injected: check your FXML file 'tesi.fxml'.";
        assert m != null : "fx:id=\"m\" was not injected: check your FXML file 'tesi.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'tesi.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
		boxMetodi.getItems().addAll(model.getMetodi());
	}
}