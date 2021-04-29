package com.cecile.cyriaque.command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cecile.cyriaque.connection.BDDConnection;
import com.cecile.cyriaque.model.Article;

public class GetArticleUserCommand {
	public ArrayList<Article> execute(int id) {
		ArrayList<Article> article = new ArrayList<Article>();
		try {
			Connection connection = BDDConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM article WHERE id_buyer = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Article art = new Article();
				art.setId(rs.getInt("id"));
				art.setName(rs.getString("name"));
				art.setDescription(rs.getString("description"));
				art.setPrice(rs.getDouble("price"));
				art.setId_buyer(rs.getInt("id_buyer"));
				article.add(art);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
}
