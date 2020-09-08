package com.school.person.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.school.person.model.GradeModel;
import com.school.person.model.JobTypeModel;
import com.school.person.model.PersonModel;
import com.school.person.model.SchoolModel;
import com.school.person.model.SectionModel;
import com.school.person.repository.PersonRepository.PersonRowMapper;

@Repository
public class ModelRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class SchoolRowMapper implements RowMapper<SchoolModel>
	{
		@Override
		public SchoolModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SchoolModel schoolModel = new SchoolModel();
			
			schoolModel.setSchoolId(rs.getString("SchoolId"));
			schoolModel.setSchoolName(rs.getString("SchoolName"));
			
			
			return schoolModel;
			
		}
	}

	class SectionRowMapper implements RowMapper<SectionModel>
	{
		@Override
		public SectionModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SectionModel sectionModel = new SectionModel();
			
			sectionModel.setSectionKey(rs.getString("SectionKey"));
			sectionModel.setSectionName(rs.getString("SectionName"));
			
			
			return sectionModel;
			
		}
	}
	
	class GradeRowMapper implements RowMapper<GradeModel>
	{
		@Override
		public GradeModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			GradeModel gradeModel = new GradeModel();
			
			gradeModel.setGradeKey(rs.getString("GradeKey"));
			gradeModel.setGradeName(rs.getString("GradeName"));
			
			
			return gradeModel;
			
		}
	}
	
	class JobTypeRowMapper implements RowMapper<JobTypeModel>
	{
		@Override
		public JobTypeModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			JobTypeModel jobTypeModel = new JobTypeModel();
			
			jobTypeModel.setJobKey(rs.getString("JobKey"));
			jobTypeModel.setJobName(rs.getString("JobName"));
			
			
			return jobTypeModel;
			
		}
	}
	
	

	public List<SchoolModel> getSchool() 
	{
		return jdbcTemplate.query("SELECT SchoolId, SchoolName FROM School ORDER BY SchoolId ASC", new SchoolRowMapper());
	}
	
	public List<SectionModel> getSection() 
	{
		return jdbcTemplate.query("SELECT SectionKey, SectionName FROM PersonSection", new SectionRowMapper());
	}
	
	public List<GradeModel> getGrade() 
	{
		return jdbcTemplate.query("SELECT GradeKey, GradeName FROM Grade", new GradeRowMapper());
	}

	public List<JobTypeModel> getJob() {
		
		return jdbcTemplate.query("Select JobKey, JobName FROM JobType", new  JobTypeRowMapper());
	}
	
	
}
