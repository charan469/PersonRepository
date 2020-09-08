package com.school.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.person.model.GradeModel;
import com.school.person.model.JobTypeModel;
import com.school.person.model.PersonModel;
import com.school.person.model.SchoolModel;
import com.school.person.model.SectionModel;
import com.school.person.repository.ModelRepository;
@Service
public class ModelServiceImpl implements ModelService {
	@Autowired
	ModelRepository modelRepository;


	
	@Override
	public List<SchoolModel> getSchool() {
		return modelRepository.getSchool();

	}

	@Override
	public List<GradeModel> getGrade() {
		return modelRepository.getGrade();

	}
	
	@Override
	public List<SectionModel> getSection() {
		return modelRepository.getSection();

	}
	
	
	@Override
	public List<JobTypeModel> getJob() {
		return modelRepository.getJob();

	}
}
