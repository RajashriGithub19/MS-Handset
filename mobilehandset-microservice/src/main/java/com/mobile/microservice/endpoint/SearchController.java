package com.mobile.microservice.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.microservice.domain.Handset;
import com.mobile.microservice.service.MobileHandsetService;

@RestController
@RequestMapping("/mobile")
public class SearchController {

	@GetMapping("/search")
	@ResponseBody
	public List<Handset> searchHandset(@RequestParam(value = "sim", required = false) String sim,
			@RequestParam(value = "priceEur", required = false) String priceEur,
			@RequestParam(value = "announceDate", required = false) String announceDate) {

		Map<String, String> reqMap = new HashMap<String, String>();

		if (sim != null)
			reqMap.put("sim", sim);

		if (priceEur != null)
			reqMap.put("priceEur", priceEur);

		if (announceDate != null)
			reqMap.put("announceDate", announceDate);

		return MobileHandsetService.search(reqMap);
	}
}
