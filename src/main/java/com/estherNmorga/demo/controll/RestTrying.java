package com.estherNmorga.demo.controll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estherNmorga.demo.model.RestModel;
import com.estherNmorga.demo.service.MemberService;
import com.estherNmorga.demo.service.RestService;

@CrossOrigin
@RestController
public class RestTrying {
	
	@Autowired
	private RestService restService;

	
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public Map<String, String> findByCompany (
			@RequestParam(value = "company") String company){
		
		List<RestModel> restModelList = restService.findMemberByCompany(company);
		Map<String, String> map = new HashMap<String, String>();
		if(restModelList==null) {
			map.put("status", "Error");
			map.put("data", new ArrayList<RestModel>().toString());
			map.put("message", "Company Searching is Empty");
		}
		else {
			map.put("status", "Complete");
			map.put("data", restModelList.toString());
			map.put("message", "Company Searching Done");
		}
		
		return map;
	}
	
}
