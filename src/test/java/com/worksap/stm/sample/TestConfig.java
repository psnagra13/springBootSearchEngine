package com.worksap.stm.sample;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


public class TestConfig {
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
	    return new RestTemplateBuilder().requestFactory(SimpleClientHttpRequestFactory.class);
	}
}
