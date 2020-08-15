package com.school.person.service;

import java.util.Optional;

import com.school.person.model.ThresholdModel;
import com.school.person.model.UtilizationModel;

public interface ThresholdService {

	String findThreshold(UtilizationModel utilizationModel);

	int findZone(String personZone);

}
