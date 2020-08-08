package com.school.person.service;

import java.util.List;
import java.util.Optional;

import com.school.person.model.UtilizationModel;

public interface UtilizationService 
{

	List<UtilizationModel> getAll(UtilizationModel utilizationModel);

	Optional<UtilizationModel> findUtilization(UtilizationModel utilizationModel);

	UtilizationModel insertUtilization(UtilizationModel utilizationModel);

	UtilizationModel deleteUtilization(UtilizationModel utilizationModel);

	UtilizationModel updateUtilization(UtilizationModel utilizationModel);
	
	Optional<UtilizationModel> findByMonthYearUtilization(UtilizationModel utilizationModel);

}

