package com.cecile.cyriaque.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cecile.cyriaque.command.AddArticleCommand;
import com.cecile.cyriaque.command.GetArticleCommand;
import com.cecile.cyriaque.command.GetArticleUserCommand;
import com.cecile.cyriaque.model.Article;
import com.cecile.cyriaque.model.BuyArticle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

@Path("/article")
public class Services {
	ObjectMapper mapper = new ObjectMapper();
	@GET
	@Path("/getArticle")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getArticle() {
		GetArticleCommand command = new GetArticleCommand();
		String articleString = null;
		try {
			articleString = mapper.writeValueAsString(command.execute());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(articleString).build();
	}
	@GET
	@Path("/getUserArticle/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserArticle(@PathParam("id") int id) {
		GetArticleUserCommand command = new GetArticleUserCommand();
		String articleString = null;
		try {
			articleString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(articleString).build();
	}
	@POST
	@Path("/addArticle")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addArticle(String payload) {
		AddArticleCommand addArticle = new AddArticleCommand();
		Article article = null;
		try {
			article = mapper.readValue(payload, Article.class);
	
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(400).entity("could not read string").build();
		}
		try {
			addArticle.execute(article);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
	@POST
	@Path("/buyArticle")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response buyArticle(String payload) {
		
		BuyArticle article = null;
		try {
			article = mapper.readValue(payload, BuyArticle.class);
			OkHttpClient client = new OkHttpClient();
			com.squareup.okhttp.MediaType mediaType = com.squareup.okhttp.MediaType.parse("application/xml");
			RequestBody body = RequestBody.create(mediaType, "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
					+ "  <soap:Body>\r\n"
					+ "    <Transaction xmlns=\"http://gateway.com/\">\r\n"
					+ "        <name>"+article.getName()+"</name>\r\n"
					+ "        <password>"+article.getPassword()+"</password>\r\n"
					+ "        <site>"+article.getSite()+"</site>\r\n"
					+ "        <description>"+article.getDescription()+"</description>\r\n"
					+ "        <amount>"+article.getAmount()+"</amount>\r\n"
					+ "    </Transaction>\r\n"
					+ "  </soap:Body>\r\n"
					+ "</soap:Envelope>");
			Request request = new Request.Builder()
										 .url("http://localhost:8282/Gateway-0.0.1-SNAPSHOT/Gateway?wsdl")
										 .method("POST", body)
										 .addHeader("Content-Type", "application/xml")
										 .build();
			com.squareup.okhttp.Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
			return Response.status(200).entity(response.message()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(400).entity("could not read string").build();
		}		
	}
}
