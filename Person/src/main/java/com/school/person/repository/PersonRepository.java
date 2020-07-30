package com.school.person.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.school.person.model.PersonModel;

@Repository
public class PersonRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<PersonModel>
	{
		@Override
		public PersonModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			PersonModel personModel = new PersonModel();
			
			personModel.setPersonId(rs.getString("PersonId"));
			personModel.setPersonName(rs.getString("PersonName"));
			personModel.setPersonEmail(rs.getString("PersonEmail"));
			personModel.setPersonPassword(rs.getString("PersonPassword"));
			personModel.setGrade(rs.getString("Grade"));
			personModel.setPersonSection(rs.getString("PersonSection"));
			personModel.setGender(rs.getString("Gender"));
			personModel.setPersonStatus(rs.getString("PersonStatus"));
			personModel.setJobType(rs.getString("JobType"));
			
			return personModel;
			
		}
	}

	public int insertPerson(PersonModel personModel) 
	{
	
		int count = 0;
		System.out.println("InsertPersonRepo" + personModel.getPersonId());
		try
		{
			count = jdbcTemplate.update("INSERT INTO Person(PersonId, PersonName, PersonEmail, PersonPassword, Grade, PersonSection, Gender, PersonStatus, JobType)"+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[] {personModel.getPersonId(), personModel.getPersonName(), personModel.getPersonEmail(), personModel.getPersonPassword(), personModel.getGrade(), personModel.getPersonSection(), personModel.getGender(), personModel.getPersonStatus(), personModel.getJobType()});
		}
		catch(DuplicateKeyException dke)
		{
			count=-1;
			dke.printStackTrace();
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
		
		
	
	}

	public int delete(PersonModel personModel) 
	{
		int count=0;
		try
		{
			count = jdbcTemplate.update("DELETE FROM Person WHERE PersonId=?", new Object[] {personModel.getPersonId()});
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
	}

	public int update(PersonModel personModel) 
	{
		int count=0;
		try
		{
			count = jdbcTemplate.update("UPDATE Person  SET PersonName=?, PersonEmail=?, PersonPassword=?, Grade=?, PersonSection=?, Gender=?, PersonStatus=?, JobType=? WHERE PersonId=? ", 
					new Object[] {personModel.getPersonName(), personModel.getPersonEmail(), personModel.getPersonPassword(), personModel.getGrade(), personModel.getPersonSection(), personModel.getGender(), personModel.getPersonStatus(), personModel.getJobType(), personModel.getPersonId()});
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
	}

	public Optional<PersonModel> find(PersonModel personModel) 
	{
		return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM Person WHERE PersonId=?", new Object[] {personModel.getPersonId()}, new BeanPropertyRowMapper<PersonModel>(PersonModel.class)));
	}
	
	public PersonModel login(PersonModel personModel) 
	{
//		return Optional.of(jdbcTemplate.queryForObject("SELECT PersonId,PersonPassword FROM Person WHERE PersonId=?", new Object[] {personModel.getPersonId()}, new BeanPropertyRowMapper<PersonModel>(PersonModel.class)));
	   
		PersonModel optional =null;
			
		try {
			optional = jdbcTemplate.queryForObject("SELECT PersonId,PersonPassword FROM Person WHERE PersonId=?", new Object[] {personModel.getPersonId()}, new BeanPropertyRowMapper<PersonModel>(PersonModel.class));
		} 
		catch (EmptyResultDataAccessException  ed) {
			
			ed.printStackTrace();
			return null;
		}
		
		
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return optional;

	
	
	}

	public List<PersonModel> getAll() 
	{
		return jdbcTemplate.query("SELECT * FROM Person", new PersonRowMapper());
	}
	
	

}
