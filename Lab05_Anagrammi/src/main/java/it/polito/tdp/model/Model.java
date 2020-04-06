package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.db.DizionarioDAO;



public class Model {
	
	DizionarioDAO dao=new DizionarioDAO();
	
	String parolaPassata;
	private List<Character> caratteriPassati;
	//tiene in mente i caratteri che sono stati usati
	private List<Boolean> caratteriUsati;
	private int N;
	private String parziale;
	private int livello;
	//con caratteri che si ripetono nella parola, gli anagrammi potrebbero ripetersi e dunque andiamo a generare
	//un hashSet per evitare duplicati
	private Map<String, Boolean> soluzione;

	//questo metodo richiama il metodo ricorsivo che e' il metodo in basso
	public Map<String, Boolean> cerca(String parola) {
		// TODO Auto-generated method stub
		
		/*
			Il livello ci dice quante lettere abbiamo sistemato in totale e siccome tra i caratteri
			dobbiamo muoverci avanti e indietro non possiamo usarlo per estrarre da degli indici, ci
			indica solamente la parola che stiamo componendo quanto e' lunga.
			
			Il ragionamento che ho fatto per svolgere questo laboratorio e' stato quello di crearmi un vettore
			di caratteri passati che sarebbero quelli totali da utilizzare per la costruzione di anagrammi e poi
			crearmi un vettore di booleani, ogni posizione corrispondente ad un carattere dell'anagramma.
			
			In ogni livello quello che vado a fare e' prendere in ordine un carattere e scendere nel livello successivo
			mettendo nel booleano che abbiamo usato quel carattere e poi quando si risale disattiviamo questa cosa
			per l'iterazione successiva allo stesso livello.
		 */
		
		/*
		 	A differenza di come ho fatto io, nell'esercizio fatto in aula sulla ricerca di anagrammi, si poteva invece
		 	di crearsi il vettore di booleani, andare a passare in ogni chiamata ricorsiva i caratteri che erano ancora 
		 	disponibili e con cui andare a costruire la parziale arricchendola;
		 */
		
		//prepariamo l'ambiente in cui la funzione ricorsiva lavora
		//carichiamo i singoli caratteri da utilizzare
		//carichiamo i booleani corrispondenti
		this.parolaPassata=parola.toLowerCase();
		this.N=parola.length();
		caratteriPassati=new ArrayList<>();
		//non esiste un metodo per convertire una stringa in una lista perche' in java le stringhe
		//non sono editabili
		for(int i=0;i<parolaPassata.length();i++) {
			caratteriPassati.add(parola.charAt(i));
		}
		parziale="";
		livello=0;
		this.soluzione=new HashMap<>();
		caratteriUsati=new ArrayList<>();
		for(int i=0;i<parolaPassata.length();i++) {
			caratteriUsati.add(false);
		}
		
		
		ricorsiva(parziale,livello);
		
		
		return this.soluzione;
	}
	
	//questo e' il metodo ricorsivo con quella struttura che si richiama
	private void ricorsiva(String parziale,int livello) {
		//il livello parte da zero dove decido la lettera
		
		
		
		//sono in stato terminale?
		if(livello==N) {
			//la soluzione parziale e' ora una soluzione completa in realta'
			//System.out.println("FINALE: "+parziale);
			if(this.isPresente(parziale)==false) {
				//System.out.println("FALSA\n");
				this.soluzione.put(parziale,false);
				return;
			}
			else {
				//System.out.println("VERA\n");
				this.soluzione.put(parziale,true);
				return;
			}
			
			
		}
		//se non sono in uno stato terminale probabilmente ha senso controllare se esitono soluzioni
		//che incominciano con il mix di lettere che abbiamo per ora nella parziale e se no
		//interrompiamo direttamente la ricerca  anche se si spende tempo in entrambe quindi dato che
		//fino a 4-5 lettere il tutto e' abbastanza immediato, forse ha senso controllare l'inizio tipo
		//sulle 5/6 lettere
		//il laboratorio comunque chiede di stampare sia anagrammi corretti che no quindi non adotto questa
		//soluzione di cui parlavo, ma la lascio commentata e la provo con il test
		//in effetti allunga notevolmente il brodo e ci mette molto tempo gia' con una parola come
		//'casale' che per cercare tutti i 360 anagrammi e' immediato, mentre con questo ci mette molto tempo
		/*
		if(livello>=5) {
			//System.out.println("CIAO");
			if(this.isParzialePresente(parziale)==false) {
				//System.out.println("CIAO2");
				return;
			}
		}
		*/
		//ogni volta per livello analizzo le soluzioni possibili andando in profondita' finche' posso
		for(int i=0; i<N;i++) {
			if(caratteriUsati.get(i)==false) {
				caratteriUsati.set(i,true);
				Character c=caratteriPassati.get(i);
				//metto il carattere nella soluzione parziale in posizione livello
				parziale=parziale+""+c;
				//System.out.println("\nPARZIALE CICLO: "+parziale);
				ricorsiva(parziale,livello+1);
				
				//rimetto a posto togliendo l'ultimo che avevo messo per avere tutto
				//a posto per l'iterazione successiva per questo livello
				//metodo creato addHoc per rimuovere il carattere
				parziale=removeCh(parziale,parziale.length()-1);
				caratteriUsati.set(i, false);
				
			}	
		}	
	}
	
	public static String removeCh (String s , int index) {
		if ((index > s.length()-1) || (index < 0)) {
			return null;
		}
		String c = s.substring(0,index) + s.substring(index+1 , s.length());
		return c;
	}
	
	public boolean isPresente(String parola) {
		
		List<String> ritorno= dao.estraiFinale(parola);

		if(ritorno.size()>0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isParzialePresente(String parola) {
		
		List<String> ritorno=dao.estraiParziale(parola);
		
		//System.out.println(ritorno);
		
		if(ritorno.size()>0) {
			return true;
		}
		
		return false;
	}

}
