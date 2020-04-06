package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/* COMMENTI LABORATORIO 5
 
 Data una parola in input, con l'algoritmo ricorsivo ne andiamo a calcolare tutti gli anagrammi, e poi quando siamo ad una
 soluzione finale andiamo a verificare se e' corretta oppure no, cioe' se e' contenuta nel database.
 
 Il laboratorio comunque chiede di stampare sia anagrammi corretti che no quindi mettiamo solo parole brevi altrimenti
 gia' sulle 10 lettere la soluzione si perde e ci mette troppo tempo. Lascio commentata nel model nella parte ricorsiva
 la parte in cui se sapevamo gia' all'inizio che non erano anagrammi corretti non proseguivamo con la ricerca.
 
 Il programma regge fino a parole con 7 lettere.
 */

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserisci;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private Button btnReset;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	txtErrati.clear();
    	txtCorretti.clear();
    	
    	Map<String,Boolean> ritorno=model.cerca(txtInserisci.getText());
    	int contaC=0;
    	int contaE=0;
    	for(String s:ritorno.keySet()) {
    		if(ritorno.get(s)==true) {
    			contaC++;
    			txtCorretti.appendText(""+contaC+" "+s+"\n");
    		}
    		else {
    			contaE++;
    			txtErrati.appendText(""+contaE+" "+s+"\n");
    		}
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtErrati.clear();
    	txtCorretti.clear();
    	txtInserisci.clear();

    }

    @FXML
    void initialize() {
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
	}
}
