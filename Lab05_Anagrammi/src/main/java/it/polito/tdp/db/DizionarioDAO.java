package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DizionarioDAO {

	public List<String> estraiFinale(String parola) {
		// TODO Auto-generated method stub
		String sql="select * " + 
				"from parola " + 
				"where nome = ? ";
		
		List<String> ritorno= new ArrayList<>();
		
		try {
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//settiamo il parametro che arriva da parametro del metodo
			st.setString(1, parola);
			//il risultato lo saviamo qui che e' il cursore ricordiamo che
			//punta alle righe
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ritorno.add(rs.getString("nome"));
			}
			st.close();
			
			conn.close();
			
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return ritorno;
	}

	public List<String> estraiParziale(String parola) {
		// TODO Auto-generated method stub
		//un'istruzione come quella qui sotto mi restituisce tutte le parole che iniziano (indice 1) con
		//le prime 4 lettere (indice 4) che sono 'abac'
		/*
		 	select *
			from parola
			where SUBSTRING(nome,1,4)='abac'
		 */
		String sql="select * " + 
				"from parola " + 
				"where SUBSTRING(nome,1,?)= ? ";
		
		List<String> ritorno= new ArrayList<>();
		
		try {
			Connection conn = ConnectionDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//settiamo i parametri che arrivano da parametro del metodo
			st.setInt(1, parola.length());
			st.setString(2, parola);
			//il risultato lo saviamo qui che e' il cursore ricordiamo che
			//punta alle righe
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ritorno.add(rs.getString("nome"));
			}
			st.close();
			
			conn.close();
			
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return ritorno;
	}
	
	

}
