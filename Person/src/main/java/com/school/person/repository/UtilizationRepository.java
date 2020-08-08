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

import com.school.person.model.UtilizationModel;

@Repository
public class UtilizationRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public class UtilizationRowMapper implements RowMapper<UtilizationModel>
	{
		@Override
		public UtilizationModel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			UtilizationModel utilizationModel = new UtilizationModel();
			
			utilizationModel.setPersonId(rs.getString("PersonId"));
			utilizationModel.setPersonMonth(rs.getInt("PersonMonth"));
			utilizationModel.setPersonYear(rs.getInt("PersonYear"));
			utilizationModel.setWaterUtilized(rs.getInt("WaterUtilized"));
			utilizationModel.setElectricityUtilized(rs.getInt("ElectricityUtilized"));
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			utilizationModel.setUpdateDate(df.format(rs.getDate("UpdateDate")));
		    
			return utilizationModel;
			
		}
	}
	
	public int insertUtilization(UtilizationModel utilizationModel)
	{
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	
	
		
		int count=0;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date updateDate = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(updateDate));
			//Date updateDate = df.parse(utilizationModel.getUpdateDate());
			count = jdbcTemplate.update("INSERT INTO Utilization(PersonId, PersonMonth, PersonYear, WaterUtilized, ElectricityUtilized, UpdateDate)" + "VALUES(?, ?, ?, ?, ?, ?)",
					new Object[] {utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear(), utilizationModel.getWaterUtilized(), utilizationModel.getElectricityUtilized(), updateDate});
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
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int count=0;
		try
		{
			Date updateDate = df.parse(utilizationModel.getUpdateDate());
			count = jdbcTemplate.update("UPDATE Utilization SET WaterUtilized=?, ElectricityUtilized=?, UpdateDate=? WHERE PersonId=? AND PersonMonth=? AND PersonYear=?", 
					new Object[] {utilizationModel.getWaterUtilized(), utilizationModel.getElectricityUtilized(), updateDate, utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear()});
		}
		catch(Exception ex)
		{
			count=0;
			ex.printStackTrace();
		}
		return count;
	}

	public Optional<UtilizationModel> findUtilization(UtilizationModel utilizationModel) 
	{
		
		return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM Utilization WHERE PersonId=?", new Object[] {utilizationModel.getPersonId()}, new BeanPropertyRowMapper<UtilizationModel>(UtilizationModel.class)));
	}

	public List<UtilizationModel> getAll(UtilizationModel utilizationModel) 
	{
		return jdbcTemplate.query("SELECT * FROM Utilization WHERE PersonId=? ORDER BY PersonMonth ASC ", new Object[] {utilizationModel.getPersonId()}, new UtilizationRowMapper());
	}
	
	public Optional<UtilizationModel> findByMonthYearUtilization(UtilizationModel utilizationModel) 
	{
		
		return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM Utilization WHERE PersonId=? AND PersonMonth=? AND PersonYear=?", new Object[] {utilizationModel.getPersonId(), utilizationModel.getPersonMonth(), utilizationModel.getPersonYear() }, new BeanPropertyRowMapper<UtilizationModel>(UtilizationModel.class)));
	}

	

}
