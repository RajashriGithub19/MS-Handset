package com.mobile.microservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.mobile.microservice.endpoint.SearchController;
import com.mobile.microservice.service.MobileHandsetService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MobileMicroServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	MobileHandsetService service;

	@Autowired
	private SearchController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	// unit test case for search method
	@Test
	public void unitTestSearchpriceEur() throws Exception {
		Map<String, String> reqParam = new HashMap<String, String>();
		reqParam.put("priceEur", "200");
		Assert.assertEquals(service.search(reqParam).size(), 10);
	}

	@Test
	public void unitTestSearchSim() throws Exception {
		Map<String, String> reqParam = new HashMap<String, String>();
		reqParam.put("sim", "eSim");
		Assert.assertEquals(service.search(reqParam).size(), 18);
	}

	@Test
	public void unitTestSearchAnnounceDateAndSim() throws Exception {
		Map<String, String> reqParam = new HashMap<String, String>();
		reqParam.put("announceDate", "1999");
		reqParam.put("priceEur", "200");
		Assert.assertEquals(service.search(reqParam).size(), 2);
	}
	
	@Test
	public void integrationTestSearchAnnounceDateAndSim() throws Exception{
		mockMvc.perform(get("/mobile/search?announceDate=1999&priceEur=200&sim=Mini-SIM")).andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void integrationTestSearchpriceEur() throws Exception{
		mockMvc.perform(get("/mobile/search?priceEur=200")).andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void integrationTestSearchSim() throws Exception{
		mockMvc.perform(get("/mobile/search?im=eSim")).andExpect(status().isOk())
		.andDo(print());
	}
}
