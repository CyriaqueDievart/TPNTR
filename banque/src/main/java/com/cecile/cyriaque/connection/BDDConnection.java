package com.cecile.cyriaque.connection;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDDConnection {
	public static Connection getConnection() throws URISyntaxException, SQLException {
		String username = "postgres";
		String password = "Ysyxaytg59";
		try {
			Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		String dbUrl = "jdbc:postgresql://localhost:5432/banque";
		System.out.println(dbUrl);
		return DriverManager.getConnection(dbUrl, username, password);
		}
	
	public static void main(String[] args) {
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO customer (name,password,amount) VALUES ('moi','test',19)");
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
			while (rs.next()) {
				System.out.println("user : " + rs.getString("name"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
