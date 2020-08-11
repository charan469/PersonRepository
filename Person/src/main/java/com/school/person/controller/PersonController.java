
package com.school.person.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.person.model.PersonModel;

import com.school.person.service.PersonService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/person")
public class PersonController 
{
	
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public String check()
	{
		return "Successfull";
	}
	
	@GetMapping(path="/get")
	public List<PersonModel> getAll()
    {
    	return personService.getAll();
    }
    
    @PostMapping(path="/find")
    public PersonModel find(@RequestBody final PersonModel personModel)
    {
    	return personService.findPerson(personModel);
    }
    
    @PostMapping(path="/login")
    public PersonModel login(@RequestBody final PersonModel personModel)
    {
    	return personService.loginPerson(personModel);
    }
	
	@PostMapping(path="/insert")
	public PersonModel insert(@RequestBody final PersonModel personModel)
    {
    	System.out.println("Insert" + personModel.getPersonId());
		return personService.insertPerson(personModel);
    }
	
    @PostMapping(path="/delete")
    public PersonModel delete(@RequestBody final PersonModel personModel)
    {
    	return personService.deletePerson(personModel);
    }
    
    @PostMapping(path="/update")
    public PersonModel update(@RequestBody final PersonModel personModel)
    {
    	return personService.updatePerson(personModel);
    }
    
    
    
}
