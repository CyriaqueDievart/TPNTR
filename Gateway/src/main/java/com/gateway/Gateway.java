package com.gateway;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


@WebService
public class Gateway {

	ObjectMapper mapperJson = new ObjectMapper();
	@WebMethod
	public String Transaction(@WebParam(name="name") String name,@WebParam(name="password") String password, @WebParam(name="site") String site,@WebParam(name="description") String description,@WebParam(name="amount") double amount)
	{
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/xml");
			RequestBody body = RequestBody.create(mediaType, "<payload>\r\n<name>"+name+"</name>\r\n<password>"+password+"</password>\r\n</payload>");
			Request request = new Request.Builder()
										 .url("http://localhost:8080/banque/rest/user/login")
										 .method("POST", body)
										 .addHeader("Content-Type", "application/xml")
										 .build();
			Response response = client.newCall(request).execute();
			User user = new User();
			user = mapperJson.readValue(response.body().string(), User.class);
			System.out.println(user.getName());
			if(user.getName() == null) {
			
				return "ok";
			}else {
				
				body = RequestBody.create(mediaType, "<payload>\r\n<id_buyer>"+user.getId()+"</id_buyer>\r\n<site>"+site+"</site>\r\n<description>"+description+"</description>\r\n<amount>"+amount+"</amount>\r\n</payload>");
				request = new Request.Builder()
											 .url("http://localhost:8080/banque/rest/user/addTransaction")
											 .method("POST", body)
											 .addHeader("Content-Type", "application/xml")
											 .build();
				response = client.newCall(request).execute();
				
				body = RequestBody.create(mediaType, "<payload>\r\n<id>"+user.getId()+"</id>\r\n<amount>"+amount+"</amount>\r\n</payload>");
				request = new Request.Builder()
											 .url("http://localhost:8080/banque/rest/user/changeAmount")
											 .method("POST", body)
											 .addHeader("Content-Type", "application/xml")
											 .build();
				response = client.newCall(request).execute();
				return "ok";
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "NOK";
		}
	}
}
