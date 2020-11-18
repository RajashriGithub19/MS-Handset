package com.mobile.microservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.microservice.domain.Handset;

@Service
public class MobileHandsetService {

	public static final ConcurrentHashMap<Integer, Handset> cache = new ConcurrentHashMap<Integer, Handset>();

	/*
	 * this method is used to initialize handset cache as the json file is static
	 * this loads the cache one time on load of application also i could not find
	 * json file on given link in assignement at following URL
	 * https://a511e938-a640-4868-939e-6eef06127ca1.mock.pstmn.io/handsets/list,
	 * 
	 */
	public MobileHandsetService() {

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("mobile-db.json");

		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! mobile-db.jso");
		}

		try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {
			String line, json = "";
			while ((line = reader.readLine()) != null) {
				json += line;

			}

			List<Handset> handset = new ObjectMapper().readValue(json, new TypeReference<List<Handset>>() {
			});

			handset.forEach(item -> {
				cache.put(item.getId(), item);
			});

		} catch (IOException e) {
			System.out.println("Exception while reading json file " + e.toString());
		}
		
	}  
		

	/*
	 * this method will filter the handset list based on each search criteria more
	 * cases(which the search param) can be included as per the requirement later,
	 * which leaves room to enhance/extend the search criteria
	 */
	public static List<Handset> search(Map<String, String> reqParam) {

		List<Handset> temp = new ArrayList<Handset>();
		temp.addAll(cache.values());

		for (String param : reqParam.keySet()) {
			String val = (String) reqParam.get(param);

			switch (param) {
			case "priceEur": {
				temp = temp.stream().filter(handset -> handset.getRelease().getPriceEur() == Integer.parseInt(val))
						.collect(Collectors.toList());
				break;
			}
			case "sim": {
				temp = temp.stream().filter(handset -> handset.getSim().toLowerCase().contains(val.toLowerCase()))
						.collect(Collectors.toList());
				break;
			}
			case "announceDate": {
				temp = temp.stream().filter(handset -> handset.getRelease().getAnnounceDate().equalsIgnoreCase(val))
						.collect(Collectors.toList());
				break;
			}
			default:
				temp = new ArrayList<Handset>();
			}
		}

		System.out.println("filtered handset size " + temp.size());
		return temp;

	}

	public int getFIlteredItemCount(Map<String, String> reqParam) {
		List<Handset> handsetList = search(reqParam);
		System.out.println("filtered handset size " + handsetList.size());
		return handsetList.size();
	}
}
