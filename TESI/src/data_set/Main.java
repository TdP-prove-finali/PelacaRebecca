package data_set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;


public class Main {
	
	public static void main(String[] args) {
		
		Connection conn = DBConnect.getConnection();
		
		String dropTableProducts = "DROP TABLE products;";
		String dropTableStorico = "DROP TABLE storico";
		
		try {
			
			PreparedStatement st = conn.prepareStatement(dropTableStorico);
			st.execute();	

		} 
		catch (SQLException e) {
//			e.printStackTrace();
		}
		
		
		try {
			PreparedStatement st = conn.prepareStatement(dropTableProducts);
			st.execute();
		} 
		catch (SQLException e) {
//			e.printStackTrace();
		}
		
		String createTableProducts = "CREATE TABLE products ("
				+ "productId varchar(20), "
				+ "PRIMARY KEY (productId)"
				+ ");"
				;
		
		String createTableStorico = "CREATE TABLE storico ("
				+ "contatore int, "
				+ "productId varchar(20), "
				+ "date date, "
				+ "x char(10), "
				+ "quantity int, "
				+ "PRIMARY KEY (contatore, productId), "
				+ "FOREIGN KEY (productId) REFERENCES products(productId)"
				+ ");"
				;
		
		try {
			PreparedStatement st = conn.prepareStatement(createTableProducts);
			st.execute();
			
			st = conn.prepareStatement(createTableStorico);
			st.execute();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		String p1 = "C:\\Users\\Rebecca\\Desktop\\POLITO\\TDP\\Workspace\\DATA-SET\\prod1.csv";
		String p2 = "C:\\Users\\Rebecca\\Desktop\\POLITO\\TDP\\Workspace\\DATA-SET\\prod2.csv";
		String p3 = "C:\\Users\\Rebecca\\Desktop\\POLITO\\TDP\\Workspace\\DATA-SET\\prod3.csv";
		String p4 = "C:\\Users\\Rebecca\\Desktop\\POLITO\\TDP\\Workspace\\DATA-SET\\prod4.csv";
		
		String prod1 = "ZPS0001";
		String prod2 = "ZPS0002";
		String prod3 = "ZPS0003";
		String prod4 = "ZPS0004";
		
		Map<String, String> file = new HashMap<String, String>();
		
		file.put(p1, prod1);
		file.put(p2, prod2);
		file.put(p3, prod3);
		file.put(p4, prod4);
		
		for(String key : file.keySet()) {
		
			List<String> lines = new ArrayList<String>();
			
			lines = Data_set.loadLines(key);
			
			String insertProd = "INSERT INTO products(productId) "
					+ "VALUES(?);";
			
			String insertX = "INSERT INTO storico(contatore, productId, date, x, quantity) "
					+ "VALUES(?, ?, ?, ?, ?);";
			
			
			try {
				PreparedStatement st = conn.prepareStatement(insertProd);
				st.setString(1, file.get(key));
				st.executeUpdate();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (lines != null){
				for (int i=0; i<lines.size(); i++){
					String[] attr = lines.get(i).split(";");
					LocalDate date = DateUtil.getDate(attr[0]);
					String vendite = attr[7];
					int quantita = 0;
					try{
						quantita = Integer.parseInt(attr[9]);
					} catch (NumberFormatException e){
					} catch (ArrayIndexOutOfBoundsException e){
					}
					
					try {
						if (date != null){
							PreparedStatement st = conn.prepareStatement(insertX);
							st.setInt(1, i+1);
							st.setString(2, file.get(key));
							st.setDate(3, Date.valueOf(date));
							st.setString(4, vendite);
							st.setInt(5, quantita);
							st.executeUpdate();
						}
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}				
				}
			}
		}	
	}
}
