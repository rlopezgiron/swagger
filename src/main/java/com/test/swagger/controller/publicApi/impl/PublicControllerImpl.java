package com.test.swagger.controller.publicApi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.swagger.controller.publicApi.IPublicController;
import com.test.swagger.model.TestItem;
import com.test.swagger.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public/test")
@Slf4j
public class PublicControllerImpl implements IPublicController {
	
	@Autowired
	private TestService testService;
	

	@Override
	@GetMapping("/")
	public List<TestItem> findAll() {
		log.info("starts findAll method");
		return testService.findAll();
	}

}
