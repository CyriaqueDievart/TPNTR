package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.User;

public class CreationLoginCommand {
	public String execute(User user) {

		try {
			Connection connection = BDDConnection.getConnection();

			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO customer (name,password,amount) VALUES (?,?,?)");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setDouble(3, user.getAmount());
			stmt.executeUpdate();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
}
