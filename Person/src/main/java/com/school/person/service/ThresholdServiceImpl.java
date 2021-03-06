package com.school.person.service;

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

		if (utilizationModel.getElectricityUtilized() <= thresholdModel.getElecAmberMin()
				&& utilizationModel.getElectricityUtilized() >= thresholdModel.getElecGreenMin()) {
			elecZone = Constants.GREEN_ZONE;
		} else if (utilizationModel.getElectricityUtilized() >= thresholdModel.getElecAmberMin()
				&& utilizationModel.getElectricityUtilized() <= thresholdModel.getElecRedMin()) {
			elecZone = Constants.AMBER_ZONE;
		} else {
			elecZone = Constants.RED_ZONE;
		}

		if (utilizationModel.getWaterUtilized() <= thresholdModel.getWaterAmberMin()
				&& utilizationModel.getWaterUtilized() >= thresholdModel.getWaterGreenMin()) {
			waterZone = Constants.GREEN_ZONE;
		} else if (utilizationModel.getWaterUtilized() >= thresholdModel.getWaterAmberMin()
				&& utilizationModel.getWaterUtilized() <= thresholdModel.getWaterRedMin()) {
			waterZone = Constants.AMBER_ZONE;
		} else {
			waterZone = Constants.RED_ZONE;
		}

		if (elecZone.equals(Constants.RED_ZONE) || waterZone.equals(Constants.RED_ZONE)) {
			finalZone = Constants.RED_ZONE;
		} else if (elecZone.equals(Constants.GREEN_ZONE) && waterZone.equals(Constants.GREEN_ZONE)) {
			finalZone = Constants.GREEN_ZONE;
		} else {
			finalZone = Constants.AMBER_ZONE;

		}

		return finalZone;
	}

	@Override
	public int findZone(String personZone) {

		int zone = 0;

		switch (personZone) {
		
		case Constants.RED_ZONE:
			zone = Constants.RED_ZONE_INT;
			break;

		case Constants.AMBER_ZONE:
			zone = Constants.AMBER_ZONE_INT;
			break;

		case Constants.GREEN_ZONE:
			zone = Constants.GREEN_ZONE_INT;
			break;

		}
		return zone;

	}
	
	@Override
	public String findColour(String personZone) {

		String colour = null;

		switch (personZone) {
		
		case Constants.RED_ZONE:
			colour = Constants.RED_COLOUR;
			break;

		case Constants.AMBER_ZONE:
			colour = Constants.AMBER_COLOUR;
			break;

		case Constants.GREEN_ZONE:
			colour = Constants.GREEN_COLOUR;
			break;

		}
		return colour;

	}
}