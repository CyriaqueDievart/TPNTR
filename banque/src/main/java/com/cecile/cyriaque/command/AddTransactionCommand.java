package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.Transaction;

public class AddTransactionCommand {
	public int execute(Transaction transaction) {

		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO transaction(id_buyer, site,description,transaction_amount) VALUES(?, ?, ?, ?) Returning id");
			stmt.setInt(1, transaction.getId_buyer());
			stmt.setString(2, transaction.getSite());
			stmt.setString(3, transaction.getDescription());
			stmt.setDouble(4, transaction.getAmount());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
