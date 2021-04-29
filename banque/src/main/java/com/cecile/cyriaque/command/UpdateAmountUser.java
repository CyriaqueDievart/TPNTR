package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.User;

public class UpdateAmountUser {
	
	public String execute(User user) {

		try {
		
			Connection connection = BDDConnection.getConnection();
			PreparedStatement getAmount = connection
					.prepareStatement("SELECT * FROM customer WHERE id=?");
			getAmount.setInt(1, user.getId());
			ResultSet rs = getAmount.executeQuery();
			Double amount = 0.0;
			while (rs.next()) {
				amount = rs.getDouble("amount");
			}
			user.setAmount(amount - user.getAmount());
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE customer SET amount=? WHERE id=?");
			stmt.setDouble(1, user.getAmount());
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
