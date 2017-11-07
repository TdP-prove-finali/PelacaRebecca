package tesi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tesi.model.Prodotto;

public class DAO {
	
	public List<Prodotto> getProdottiDB() {
		
		String sql = "SELECT productId FROM products";

		List<Prodotto> prodotti = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Prodotto p = new Prodotto(res.getString("productId"));
				prodotti.add(p);
			}

			conn.close();
			return prodotti;
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getStoricoDB(Prodotto prodotto) {
		
		String sql = "SELECT quantity " +
		             "FROM storico " +
                     "WHERE productId = ? ORDER BY date;";

		List<Integer> storico = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, prodotto.getCodice());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				storico.add(res.getInt("quantity"));
			}

			conn.close();
			return storico;
		} 
		catch (SQLException e) {	
			e.printStackTrace();
			return null;
		}
	}
}
