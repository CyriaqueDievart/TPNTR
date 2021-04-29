package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.Transaction;


public class GetTransactionCommand {
	public ArrayList<Transaction> execute(int id) {
		ArrayList<Transaction> t = new ArrayList<Transaction>();
		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM transaction WHERE id_buyer = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction tra = new Transaction();
				tra.setAmount(rs.getDouble("transaction_amount"));
				tra.setId(rs.getInt("id"));
				tra.setId_buyer(rs.getInt("id_buyer"));
				tra.setSite(rs.getString("site"));
				tra.setDescription(rs.getString("description"));
				t.add(tra);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
}
