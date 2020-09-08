package com.school.person.service;

import java.util.List;

import com.school.person.model.GradeModel;
import com.school.person.model.JobTypeModel;
import com.school.person.model.PersonModel;
import com.school.person.model.SchoolModel;
import com.school.person.model.SectionModel;


public interface ModelService {
	
	List<SchoolModel> getSchool();
	

	List<GradeModel> getGrade();
	
	List<SectionModel> getSection();
	
	List<JobTypeModel> getJob();

}
