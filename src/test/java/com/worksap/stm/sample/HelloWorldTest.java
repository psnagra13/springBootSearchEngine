package com.worksap.stm.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class HelloWorldTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testHelloWorld() {
		ResponseEntity<String> r = this.restTemplate.getForEntity("/hello-world", String.class);
		assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(r.getBody()).isEqualTo("Hello World!");
	}
}
