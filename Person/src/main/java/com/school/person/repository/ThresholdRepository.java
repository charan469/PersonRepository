package com.school.person.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.school.person.model.ThresholdModel;
import com.school.person.model.UtilizationModel;

@Repository
public class ThresholdRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class ThresholdRowMapper implements RowMapper<ThresholdModel>
	{
		public ThresholdModel mapRow(ResultSet rs, int  rowNum) throws SQLException
		{
			ThresholdModel thresholdModel = new ThresholdModel();
			thresholdModel.setElecGreenMin(rs.getInt("ElecGreenMin"));
			thresholdModel.setElecAmberMin(rs.getInt("ElecAmberMin"));
			thresholdModel.setElecRedMin(rs.getInt("ElecRedMin"));
			thresholdModel.setWaterGreenMin(rs.getInt("WaterGreenMin"));
			thresholdModel.setWaterAmberMin(rs.getInt("WaterAmberMin"));
			thresholdModel.setWaterRedMin(rs.getInt("WaterRedMin"));
			return thresholdModel;
			
		}
	}
	
	
	
	
	public ThresholdModel findThreshold()
	{
		return jdbcTemplate.queryForObject("SELECT * FROM Threshold ", new Object[] {}, new BeanPropertyRowMapper<ThresholdModel>(ThresholdModel.class));
	}
	

}
