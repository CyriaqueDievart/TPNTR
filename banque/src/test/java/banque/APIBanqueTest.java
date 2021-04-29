package banque;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.cecile.cyriaque.services.Services;

public class APIBanqueTest {

	@Test
	public void testGetUserById() {
		Services services = new Services(); 
		Response r =  services.getUser(1);
		assertEquals(r.getStatus(), 200);
	}
	
	@Test
	public void testChangeAmount() {
		Services services = new Services(); 
		Response r =  services.updateAmount("{\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"amount\": 10\r\n"
				+ "}");
		assertEquals(r.getStatus(), 200);
	}
	
	@Test
	public void testAddTransaction() {
		Services services = new Services(); 
		Response r =  services.addTransaction("{\r\n"
				+ "    \"id_buyer\": 1,\r\n"
				+ "    \"site\": \"test\",\r\n"
				+ "    \"description\": \"test\",\r\n"
				+ "    \"amount\": 10.0\r\n"
				+ "}");
		assertEquals(r.getStatus(), 200);
	}
	
	@Test
	public void testLogin() {
		Services services = new Services(); 
		Response r =  services.login("{\r\n"
				+ "    \"name\": \"test1\",\r\n"
				+ "    \"password\": \"test\"\r\n"
				+ "}");
		assertEquals(r.getStatus(), 200);
	}
	

}
