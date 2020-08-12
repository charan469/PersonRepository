package com.school.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.person.model.UtilizationModel;
import com.school.person.service.ThresholdService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/threshold")
public class ThresholdController {
	@Autowired
	ThresholdService thresholdService;
	
//	@PostMapping(path="/find")
//	public String find(@RequestBody final UtilizationModel utilizationModel)
//	{
//	    return thresholdService.findThreshold(utilizationModel);
//	}

}
