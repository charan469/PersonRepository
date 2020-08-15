package com.school.person.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.person.model.UtilizationModel;
import com.school.person.repository.UtilizationRepository;
import com.school.person.util.Constants;

@Service
public class UtilizationServiceImpl implements UtilizationService
{
	@Autowired                                                                            
	UtilizationRepository utilizationRepository;                 
	@Autowired
	ThresholdService thresholdService;
	
	
	@Override
	public UtilizationModel insertUtilization(UtilizationModel utilizationModel)
	{
		
		int count = 0;
//		String finalZone = thresholdService.findThreshold(utilizationModel);
//		utilizationModel.setPersonZone(finalZone);
		utilizationModel.setPersonZone(thresholdService.findThreshold(utilizationModel));
		utilizationModel.setZone(thresholdService.findZone(utilizationModel.getPersonZone()));
		count = utilizationRepository.insertUtilization(utilizationModel);
		
		if(count > 0)
		{
			utilizationModel.setStatus(Constants.UPDATE_SUCCESS);
			utilizationModel.setMessage(Constants.UPDATE_SUCCESS_MSG);
		}
		else if(count==-1)
		{
			// In case of existing record do not give duplicate message instead override the existing record
			count = utilizationRepository.updateUtilization(utilizationModel);
			if(count > 0)
			{
				utilizationModel.setStatus(Constants.UPDATE_SUCCESS);
				utilizationModel.setMessage(Constants.UPDATE_SUCCESS_MSG);
			}
			else
			{
				utilizationModel.setStatus(Constants.UPDATE_FAILED);
				utilizationModel.setMessage(Constants.UPDATE_FAILED_MSG);
			}
			
//			utilizationModel.setStatus(Constants.DUPLICATE);
//			utilizationModel.setMessage(Constants.UTILIZATION_DUPLICATE_MSG);
			
			
		}
		else
		{
			utilizationModel.setStatus(Constants.UPDATE_FAILED);
			utilizationModel.setMessage(Constants.UPDATE_FAILED_MSG);
			
		}
		return utilizationModel;
	}
	
	@Override
	public UtilizationModel deleteUtilization(UtilizationModel utilizationModel)
	{
		
		int count=0;
		count = utilizationRepository.deleteUtilization(utilizationModel);
		
		if(count > 0)
		{
			utilizationModel.setStatus(Constants.SUCCESS);
		}
		else
		{
			utilizationModel.setStatus(Constants.NOT_EXISTS);
		}
		return utilizationModel;
	}
	
	@Override
	public UtilizationModel updateUtilization(UtilizationModel utilizationModel)
	{
		
		int count=0;
		
		try
		{
			utilizationModel.setPersonZone(thresholdService.findThreshold(utilizationModel));
			utilizationModel.setZone(thresholdService.findZone(utilizationModel.getPersonZone()));
			count = utilizationRepository.updateUtilization(utilizationModel);
			if(count > 0)
			{
				utilizationModel.setStatus(Constants.SUCCESS);
			}
			else
			{
				utilizationModel.setStatus(Constants.NOT_EXISTS);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return utilizationModel;
		
	}
	
	@Override
	public UtilizationModel findUtilization(UtilizationModel utilizationModel)
	{
		return utilizationRepository.findUtilization(utilizationModel);
	}
	
	@Override
	public List<UtilizationModel> getAll(UtilizationModel utilizationModel)
	{
		return utilizationRepository.getAll(utilizationModel);
	}
	
	@Override
	public UtilizationModel findByMonthYearUtilization(UtilizationModel utilizationModel)
	{ 
		return utilizationRepository.findByMonthYearUtilization(utilizationModel);
	}
	@Override
	public List<UtilizationModel> findByGradeUtilization(UtilizationModel utilizationModel) 
	{
		List<UtilizationModel> optional = utilizationRepository.findByGradeUtilization(utilizationModel);
		for(int i = 0; i <=optional.size()-1; i++) {
			optional.get(i).setPersonZone(thresholdService.findThreshold(optional.get(i)));
					
			optional.get(i).setZone(thresholdService.findZone(optional.get(i).getPersonZone()));
		}
		
		
		return optional;
	}
	
}
