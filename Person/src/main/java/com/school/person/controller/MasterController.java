package com.school.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.person.model.GradeModel;
import com.school.person.model.JobTypeModel;
import com.school.person.model.SchoolModel;
import com.school.person.model.SectionModel;
import com.school.person.service.ModelService;
@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/master")
public class MasterController {
	
	@Autowired
	ModelService modelService;
	
	
	
	@GetMapping(path="/getSchool")
	public List<SchoolModel> getSchool()
    {
    	return modelService.getSchool();
    }
	
	@GetMapping(path="/getSection")
	public List<SectionModel> getSection()
    {
    	return modelService.getSection();
    }

	@GetMapping(path="/getGrade")
	public List<GradeModel> getGrade()
    {
    	return modelService.getGrade();
    }

	@GetMapping(path="/getJob")
	public List<JobTypeModel> getJob()
    {
    	return modelService.getJob();
    }
	
}
