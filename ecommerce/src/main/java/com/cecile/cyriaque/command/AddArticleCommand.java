package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.Article;

public class AddArticleCommand {
	public int execute(Article article) {

		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO article(name,description,price) VALUES(?, ?, ?) Returning id");
			stmt.setString(1, article.getName());
			stmt.setString(2, article.getDescription());
			stmt.setDouble(3, article.getPrice());
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
