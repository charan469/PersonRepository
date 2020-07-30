package com.school.person.service;

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
		
		int count = 0;
		count = utilizationRepository.insertUtilization(utilizationModel);
		
		if(count > 0)
		{
			utilizationModel.setStatus(Constants.SUCCESS);
		}
		else if(count==-1)
		{
			utilizationModel.setStatus(Constants.DUPLICATE);
		}
		else
		{
			utilizationModel.setStatus(Constants.FAILED);
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
	public List<UtilizationModel> getAll()
	{
		return utilizationRepository.getAll();
		
	}

}
