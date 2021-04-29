package com.cecile.cyriaque.services;

 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cecile.cyriaque.command.AddTransactionCommand;
import com.cecile.cyriaque.command.CreationLoginCommand;
import com.cecile.cyriaque.command.GetTransactionCommand;
import com.cecile.cyriaque.command.GetUserCommand;
import com.cecile.cyriaque.command.LoginCommand;
import com.cecile.cyriaque.command.UpdateAmountUser;
import com.cecile.cyriaque.model.Transaction;
import com.cecile.cyriaque.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
 
@Path("/user")
public class Services {
	ObjectMapper mapperXML = new XmlMapper();
	ObjectMapper mapperJson = new ObjectMapper();
	// get user by Id
		@GET
		@Path("/getID/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response getUser(@PathParam("id") int id) {
			GetUserCommand command = new GetUserCommand();
			String userString = null;
			try {
				userString = mapperJson.writeValueAsString(command.execute(id));
			} catch (Exception e) {
				try {
					userString = mapperXML.writeValueAsString(command.execute(id));
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return Response.status(200).entity(userString).build();
		}
		
		@POST
		@Path("/changeAmount")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response updateAmount(String payload) {
			UpdateAmountUser update = new UpdateAmountUser();
			User user = null;
			try {
				user = mapperJson.readValue(payload, User.class);
			} catch (Exception ex) {
				try {
					user = mapperXML.readValue(payload, User.class);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
				update.execute(user);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
		
		@GET
		@Path("/getTransactionUser/{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response getTransaction(@PathParam("id") int id) {
			GetTransactionCommand command = new GetTransactionCommand();
			String transactionString = null;
			try {
				transactionString = mapperJson.writeValueAsString(command.execute(id));
			} catch (Exception e) {
				try {
					transactionString = mapperXML.writeValueAsString(command.execute(id));
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return Response.status(200).entity(transactionString).build();
		}
		
		@POST
		@Path("/addTransaction")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response addTransaction(String payload) {
		
			AddTransactionCommand addTransaction = new AddTransactionCommand();
			Transaction transaction = null;
			try {
				transaction = mapperJson.readValue(payload, Transaction.class);
		
		
			} catch (Exception ex) {
				try {
					transaction = mapperXML.readValue(payload, Transaction.class);
					
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
				addTransaction.execute(transaction);
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
			return Response.status(200).build();
		}
		
		@POST
		@Path("/creationLogin")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response creationLogin(String payload) {
			CreationLoginCommand creationLogin = new CreationLoginCommand();
			User user = null;
			try {
				user = mapperJson.readValue(payload, User.class);
		
		
			} catch (Exception ex) {
				ex.printStackTrace();
				return Response.status(400).entity("could not read string").build();
			}
			try {
				creationLogin.execute(user);
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
			return Response.status(200).build();
		}
		
		@POST
		@Path("/login")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response login(String payload) {
			LoginCommand login = new LoginCommand();
			User user = null;
			String userString = null;
			try {
				
				user = mapperJson.readValue(payload, User.class);
			} catch (Exception ex) {
				try {
					user = mapperXML.readValue(payload, User.class);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				System.out.println(user.getName());
				userString = mapperJson.writeValueAsString(login.execute(user));
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
			return Response.status(200).entity(userString).build();
		}
		
		
}