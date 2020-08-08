package com.school.person.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.person.model.UtilizationModel;
import com.school.person.service.UtilizationService;


@RestController
@RequestMapping(path="/utilization")
@CrossOrigin(origins="*")
public class UtilizationController 
{
	@Autowired
	UtilizationService utilizationService;
	
	@GetMapping(path="/check")
	public String check()
	{
		return "Hello";
	}
	
	@PostMapping(path="/get")
	public List<UtilizationModel> getAll(@RequestBody final UtilizationModel utilizationModel)
	{
		System.out.println("getController " + utilizationModel.getPersonId());
		return utilizationService.getAll(utilizationModel);
	} 
	
	@PostMapping(path="/find")
	public Optional<UtilizationModel> find(@RequestBody final UtilizationModel utilizationModel)
	{
	    return utilizationService.findUtilization(utilizationModel);
	}
		
    @PostMapping(path="/insert")
	public UtilizationModel insert(@RequestBody final UtilizationModel utilizationModel)
	{
		return utilizationService.insertUtilization(utilizationModel);
	}
		
	@PostMapping(path="/delete")
	public UtilizationModel delete(@RequestBody final UtilizationModel utilizationModel)
	{
	    return utilizationService.deleteUtilization(utilizationModel);
	}
	    
	@PostMapping(path="/update")
	public UtilizationModel update(@RequestBody final UtilizationModel utilizationModel)
	{
	    return utilizationService.updateUtilization(utilizationModel);
	}
	
	@PostMapping(path="/find")
	public Optional<UtilizationModel> findByMonthYear(@RequestBody final UtilizationModel utilizationModel)
	{
	    return utilizationService.findByMonthYearUtilization(utilizationModel);
	}

}
