package com.school.person.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.person.model.ThresholdModel;

import com.school.person.service.ThresholdService;

@RestController
public class ThresholdController {
	@Autowired
	ThresholdService thresholdService;
	
	@PostMapping(path="/find")
	public Optional<ThresholdModel> find(@RequestBody final ThresholdModel thresholdModel)
	{
	    return thresholdService.findThreshold(thresholdModel);
	}

}
