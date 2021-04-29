package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.User;

public class GetUserCommand {
	public User execute(int id) {
		User user = new User();
		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM customer WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user.setAmount(rs.getDouble("amount"));
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
