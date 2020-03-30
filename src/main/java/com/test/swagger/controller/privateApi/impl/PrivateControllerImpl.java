package com.test.swagger.controller.privateApi.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.swagger.controller.privateApi.IPrivateController;
import com.test.swagger.model.TestItem;
import com.test.swagger.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/private/test")
@Slf4j
public class PrivateControllerImpl implements IPrivateController{

	@Autowired
	private TestService testService;


	@Override
	@GetMapping("/{name}")
	public List<TestItem> findAllByName(@PathVariable(value="name") String name) {
		
		log.info("starts findAllByName method");

		return testService.findAll().stream()
									.filter(item -> name.equals(item.getName()))
									.collect(Collectors.toList());
	}

}
