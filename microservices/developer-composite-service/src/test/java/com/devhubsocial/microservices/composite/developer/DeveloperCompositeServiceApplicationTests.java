package com.devhubsocial.microservices.composite.developer;

import com.devhubsocial.api.core.contact.Contact;
import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.api.core.recruiter.Recruiter;
import com.devhubsocial.microservices.composite.developer.services.DeveloperCompositeIntegration;
import com.devhubsocial.util.exceptions.InvalidInputException;
import com.devhubsocial.util.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootTest
class DeveloperCompositeServiceApplicationTests {
	private static final int DEVELOPER_ID_OK = 1;
	private static final int  DEVELOPER_ID_NOT_FOUND = 2;
	private static final int  DEVELOPER_ID_INVALID = 3;

	@Autowired
	private WebTestClient client;

	@MockBean
	private DeveloperCompositeIntegration compositeIntegration;

	@Before
	public void setUp() {
		when(compositeIntegration.getDeveloper(DEVELOPER_ID_OK)).
				thenReturn(new Developer(DEVELOPER_ID_OK , "Mike", "Turner", "Python", true, "San Fran Inc", "mock-address"));
		when(compositeIntegration.getRecruiters(DEVELOPER_ID_OK)).
				thenReturn(singletonList(new Recruiter(DEVELOPER_ID_OK , 1, "Samantha Haines", "Job Spring Activities", 2, "mock address")));
		when(compositeIntegration.getContacts(DEVELOPER_ID_OK )).
				thenReturn(singletonList(new Contact(DEVELOPER_ID_OK, 1, 2, "778-928-4000", "shaines@gmail.com", "mock address")));

		when(compositeIntegration.getDeveloper(DEVELOPER_ID_NOT_FOUND)).thenThrow(new NotFoundException("NOT FOUND: " + DEVELOPER_ID_NOT_FOUND ));

		when(compositeIntegration.getDeveloper(DEVELOPER_ID_INVALID)).thenThrow(new InvalidInputException("INVALID: " + DEVELOPER_ID_INVALID));
	}

}
