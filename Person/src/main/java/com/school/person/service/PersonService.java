package com.school.person.service;

import java.util.List;
import java.util.Optional;

import com.school.person.model.PersonModel;

public interface PersonService 
{

	List<PersonModel> getAll();

	Optional<PersonModel> findPerson(PersonModel personModel);
	
	PersonModel loginPerson(PersonModel personModel);

	PersonModel insertPerson(PersonModel personModel);

	PersonModel deletePerson(PersonModel personModel);

	PersonModel updatePerson(PersonModel personModel);
	
}
