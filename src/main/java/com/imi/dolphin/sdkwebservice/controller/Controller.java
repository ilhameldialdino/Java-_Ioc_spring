package com.imi.dolphin.sdkwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.imi.dolphin.sdkwebservice.service.IService;

/**
 * 
 * @author reja
 * 
 */
@RestController
public class Controller {

	@Autowired
	IService svcService;
	
	@RequestMapping("/hit/{country}")
	public String covid (@PathVariable String country) {
		return svcService.getData(country); 
	}


}
