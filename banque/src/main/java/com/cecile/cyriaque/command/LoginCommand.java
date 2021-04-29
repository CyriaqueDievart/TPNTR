package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.User;

public class LoginCommand {
	public User execute(User getuser) {
		User user = new User();
		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM customer WHERE name = ? AND password = ?");
			stmt.setString(1, getuser.getName());
			stmt.setString(2, getuser.getPassword());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setAmount(rs.getDouble("amount"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
