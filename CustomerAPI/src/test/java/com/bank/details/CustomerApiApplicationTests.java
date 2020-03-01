package com.bank.details;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.bank.details.dao.CustomerDetailsEntity;
import com.bank.details.service.CustomerDetailsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = CustomerApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	
	@Mock
	CustomerDetailsService dataServiceMock;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCustomerDetails() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/details", HttpMethod.GET, entity,
				String.class);
		Assert.notNull(response.getBody());
	}

	@Test
	public void testAddCustomerDetails() {
		CustomerDetailsEntity user = new CustomerDetailsEntity();
		user.setFirstName("admin");
		user.setLastName("admin");
		restTemplate.postForEntity(getRootUrl() + "/details", user, CustomerDetailsEntity.class);
		dataServiceMock.addCustomerDetails(user);
	}
	
	@Test
	public void testAddAccountDetails() {
		dataServiceMock.addAccountDetails(1,100);
	}

}
