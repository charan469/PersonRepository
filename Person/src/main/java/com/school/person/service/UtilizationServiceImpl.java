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
	
	@Override
	public UtilizationModel insertUtilization(UtilizationModel utilizationModel)
	{
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date(System.currentTimeMillis());
//		System.out.println(formatter.format(date));
//		utilizationModel.setUpdateDate(formatter.format(date));
		
		int count = 0;
		count = utilizationRepository.insertUtilization(utilizationModel);
		
		if(count > 0)
		{
			utilizationModel.setStatus(Constants.UPDATE_SUCCESS);
			utilizationModel.setMessage(Constants.UPDATE_SUCCESS_MSG);
		}
		else if(count==-1)
		{
			utilizationModel.setStatus(Constants.DUPLICATE);
			utilizationModel.setMessage(Constants.UTILIZATION_DUPLICATE_MSG);
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
	public Optional<UtilizationModel> findUtilization(UtilizationModel utilizationModel)
	{
		return utilizationRepository.findUtilization(utilizationModel);
		
	}
	
	@Override
	public List<UtilizationModel> getAll(UtilizationModel utilizationModel)
	{
		return utilizationRepository.getAll(utilizationModel);
		
	}

}