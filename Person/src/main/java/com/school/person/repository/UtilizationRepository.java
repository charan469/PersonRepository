package com.school.person.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.school.person.model.PersonModel;
import com.school.person.model.UtilizationModel;
import com.school.person.util.Utility;

@Repository
public class UtilizationRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	PersonRepository personRepository;
	

	
	public class UtilizationRowMapper implements RowMapper<UtilizationModel>
	{
		@Override
		public UtilizationModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			//DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			UtilizationModel utilizationModel = new UtilizationModel();
			
			utilizationModel.setPersonId(rs.getString("PersonId"));
			utilizationModel.setPersonMonth(rs.getInt("PersonMonth"));
			utilizationModel.setPersonYear(rs.getInt("PersonYear"));
//			utilizationModel.setWaterUtilized(rs.getString("WaterUtilized"));
//			utilizationModel.setElectricityUtilized(rs.getString("ElectricityUtilized"));
			utilizationModel.setWaterUtilized(rs.getInt("WaterUtilized"));
			utilizationModel.setElectricityUtilized(rs.getInt("ElectricityUtilized"));
			utilizationModel.setZone(rs.getInt("Zone"));
			utilizationModel.setPersonZone(rs.getString("PersonZone"));
			utilizationModel.setCreateDateTime(Utility.setDateFormat(rs.getDate("CreateDateTime")));
			utilizationModel.setLastModifiedDateTime(Utility.setDateFormat(rs.getDate("LastModifiedDateTime")));
		    
			return utilizationModel;
			
		}
	}
	
	public class GradeUtilizationRowMapper implements RowMapper<UtilizationModel>
	{
		@Override
		public UtilizationModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			//DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			UtilizationModel utilizationModel = new UtilizationModel();
			
			
			utilizationModel.setPersonMonth(rs.getInt("PersonMonth"));
			utilizationModel.setPersonYear(rs.getInt("PersonYear"));
			utilizationModel.setWaterUtilized(rs.getInt("WaterUtilized"));
			utilizationModel.setElectricityUtilized(rs.getInt("ElectricityUtilized"));
			//utilizationModel.setZone(rs.getInt("Zone"));
			//utilizationModel.setPersonZone(rs.getString("PersonZone"));
		
		    
			return utilizationModel;
			
		}
	}
	
	public class TotalUtilizationRowMapper implements RowMapper<UtilizationModel>
	{
		@Override
		public UtilizationModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			//DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			UtilizationModel utilizationModel = new UtilizationModel();
			
			
			utilizationModel.setPersonMonth(rs.getInt("PersonMonth"));
			utilizationModel.setPersonYear(rs.getInt("PersonYear"));
			utilizationModel.setWaterUtilized(rs.getInt("WaterUtilized"));
			utilizationModel.setElectricityUtilized(rs.getInt("ElectricityUtilized"));
			//utilizationModel.setZone(rs.getInt("Zone"));
			//utilizationModel.setPersonZone(rs.getString("PersonZone"));
		
		    
			return utilizationModel;
			
		}
	}
	
	public int insertUtilization(UtilizationModel utilizationModel)
	{
		int count=0;
		try
		{
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			
//			Date createDateTime = new Date(System.currentTimeMillis());
//			System.out.println(formatter.format(createDateTime));
//			
//			Date lastModifiedDateTime = new Date(System.currentTimeMillis());
//			System.out.println(formatter.format(lastModifiedDateTime));
			

//			count = jdbcTemplate.update("INSERT INTO Utilization(PersonId, PersonMonth, PersonYear, WaterUtilized, ElectricityUtilized,PersonZone, CreateDateTime, LastModifiedDateTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",

			count = jdbcTemplate.update("INSERT INTO Utilization(PersonId, PersonMonth, PersonYear, WaterUtilized, ElectricityUtilized, PersonZone, Zone, CreateDateTime, LastModifiedDateTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",

					new Object[] {utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear(), utilizationModel.getWaterUtilized(), utilizationModel.getElectricityUtilized(),utilizationModel.getPersonZone(), utilizationModel.getZone(), Utility.getCurrentDateTime(), Utility.getCurrentDateTime()});
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
	
	public int deleteUtilization(UtilizationModel utilizationModel) 
	{
		int count=0;
		try
		{
			count = jdbcTemplate.update("DELETE FROM Utilization WHERE PersonId=?", new Object[] {utilizationModel.getPersonId()});
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
	}
	
	public int updateUtilization(UtilizationModel utilizationModel) 
	{
		int count=0;
		try
		{

			count = jdbcTemplate.update("UPDATE Utilization SET WaterUtilized=?, ElectricityUtilized=?, PersonZone = ?, Zone = ?, LastModifiedDateTime=? WHERE PersonId=? AND PersonMonth=? AND PersonYear=?", 
					new Object[] {utilizationModel.getWaterUtilized(), utilizationModel.getElectricityUtilized(),utilizationModel.getPersonZone(), utilizationModel.getZone(), Utility.getCurrentDateTime(), utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear()});
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
	}

	public UtilizationModel findUtilization(UtilizationModel utilizationModel) 
	{
		return jdbcTemplate.queryForObject("SELECT * FROM Utilization WHERE PersonId=?", new Object[] {utilizationModel.getPersonId()}, new BeanPropertyRowMapper<UtilizationModel>(UtilizationModel.class));
	}

	public List<UtilizationModel> getAll(UtilizationModel utilizationModel) 
	{
		return jdbcTemplate.query("SELECT * FROM Utilization WHERE PersonId=? and PersonYear = ? ORDER BY PersonMonth ASC ", new Object[] {utilizationModel.getPersonId(), Utility.getCurrentYear()}, new UtilizationRowMapper());
	}
	
	public UtilizationModel findByMonthYearUtilization(UtilizationModel utilizationModel) 
	{
	
		
		try
		{
		utilizationModel = jdbcTemplate.queryForObject("SELECT * FROM Utilization WHERE PersonId=? AND PersonMonth=? AND PersonYear=?", new Object[] {utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear() }, new BeanPropertyRowMapper<UtilizationModel>(UtilizationModel.class));	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return utilizationModel;
	}

	public List<UtilizationModel> findByGradeUtilization(UtilizationModel utilizationModel) 
	{
	List<UtilizationModel> optional = null;
		
		try
		{
			PersonModel personModel = new PersonModel();
			personModel.setPersonId(utilizationModel.getPersonId()); 
			personModel = personRepository.find(personModel);
		optional = jdbcTemplate.query("SELECT floor(avg(a.WaterUtilized)) as WaterUtilized, floor(avg(a.ElectricityUtilized)) as ElectricityUtilized, "
				+ " a.PersonMonth, a.PersonYear, b.Grade" + 
				" FROM Utilization a LEFT JOIN Person b ON a.PersonId = b.PersonId WHERE b.Grade = ? and a.PersonYear= ? " + 
				" GROUP BY b.Grade, a.PersonMonth, a.PersonYear" + 
				" ORDER BY a.PersonMonth", new Object[] {personModel.getGrade(),Utility.getCurrentYear() }, new GradeUtilizationRowMapper());	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return optional;
	}
	
	public List<UtilizationModel> findTotalUtilization(UtilizationModel utilizationModel) 
	{
	List<UtilizationModel> optional = null;
		
		try
		{
			PersonModel personModel = new PersonModel();
			personModel.setPersonId(utilizationModel.getPersonId()); 
			personModel = personRepository.find(personModel);
		optional = jdbcTemplate.query("SELECT floor(avg(WaterUtilized)) as WaterUtilized, floor(avg(ElectricityUtilized)) as ElectricityUtilized, "
				+ " PersonMonth, PersonYear " + 
				" FROM Utilization WHERE PersonYear = ? "+ 
				" GROUP BY PersonMonth, PersonYear" + 
				" ORDER BY PersonMonth", new Object[] {Utility.getCurrentYear() }, new TotalUtilizationRowMapper());	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return optional;
	}
}
