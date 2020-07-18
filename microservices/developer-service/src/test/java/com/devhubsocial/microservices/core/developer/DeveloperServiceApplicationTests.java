package com.devhubsocial.microservices.core.developer;

import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.microservices.core.developer.entity.DeveloperRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static reactor.core.publisher.Mono.just;

class DeveloperServiceApplicationTests {

}
