package com.school.person.service;

import java.util.Optional;

import com.school.person.model.ThresholdModel;

public interface ThresholdService {

	Optional<ThresholdModel> findThreshold(ThresholdModel thresholdModel);

}
