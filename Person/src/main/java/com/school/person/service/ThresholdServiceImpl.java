package com.school.person.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.person.model.ThresholdModel;
import com.school.person.model.UtilizationModel;
import com.school.person.repository.ThresholdRepository;
import com.school.person.util.Constants;

@Service
public class ThresholdServiceImpl implements ThresholdService {
	@Autowired
	ThresholdRepository thresholdRepository;

	@Override
	public String findThreshold(UtilizationModel utilizationModel) {
		
		ThresholdModel thresholdModel = thresholdRepository.findThreshold();
		String elecZone = null;
		String waterZone = null;
		String finalZone = null;
		
		if(utilizationModel.getElectricityUtilized() < thresholdModel.getElecAmberMin() && utilizationModel.getElectricityUtilized() > thresholdModel.getElecGreenMin())
		{
			elecZone = Constants.GREEN_ZONE;
		}
		else if(utilizationModel.getElectricityUtilized() > thresholdModel.getElecAmberMin() && utilizationModel.getElectricityUtilized() < thresholdModel.getElecRedMin())
{
		elecZone = Constants.AMBER_ZONE;	
		}
		if(elecZone.equals(Constants.RED_ZONE) || waterZone.equals(Constants.RED_ZONE))
		{
			finalZone =Constants.RED_ZONE;
		}
		return finalZone ;
	}

}
