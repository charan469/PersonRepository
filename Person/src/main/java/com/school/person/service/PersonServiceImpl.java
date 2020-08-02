package com.school.person.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.school.person.model.PersonModel;
import com.school.person.repository.PersonRepository;
import com.school.person.util.Constants;

@org.springframework.stereotype.Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public PersonModel insertPerson(PersonModel personModel) {

		System.out.println("InsertPerson" + personModel.getPersonId());
		int count = 0;
		count = personRepository.insertPerson(personModel);

		if (count > 0) {
			if (!personModel.getJobType().equals("Student")) {
				personModel.setGrade(null);
				personModel.setPersonSection(null);
			}

			personModel.setStatus(Constants.REGISTER_SUCCESS);
			personModel.setMessage(Constants.REGISTER_SUCCESS_MSG);
		} else if (count == -1) {
			personModel.setStatus(Constants.DUPLICATE_REGISTER);
			personModel.setMessage(Constants.DUPLICATE_REGISTER_MSG);
		}

		else {
			personModel.setStatus(Constants.REGISTER_FAILED);
			personModel.setMessage(Constants.REGISTER_FAILED_MSG);
		}
		return personModel;
	}

	@Override
	public PersonModel deletePerson(PersonModel personModel) {

		int count = 0;
		count = personRepository.delete(personModel);

		if (count > 0) {
			personModel.setStatus(Constants.SUCCESS);
		} else {
			personModel.setStatus(Constants.NOT_EXISTS);
		}
		return personModel;
	}

	@Override
	public PersonModel updatePerson(PersonModel personModel) {

		int count = 0;

		try {
			count = personRepository.update(personModel);
			if (count > 0) {
				personModel.setStatus(Constants.UPDATE_SUCCESS);
				personModel.setMessage(Constants.UPDATE_SUCCESS_MSG);
			} else {
				personModel.setStatus(Constants.UPDATE_FAILED);
				personModel.setMessage(Constants.UPDATE_FAILED_MSG);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return personModel;

	}

	@Override
	public Optional<PersonModel> findPerson(PersonModel personModel) {
		return personRepository.find(personModel);

	}

	@Override
	public PersonModel loginPerson(PersonModel personModel) {
		// return personRepository.login(personModel);
		PersonModel optional = null;

		optional = personRepository.login(personModel);

		if (optional == null) {
			personModel.setStatus(Constants.RECORD_NOT_FOUND);
			personModel.setMessage(Constants.NOT_EXISTS);

			return personModel;

		} else {

		//	System.out.println("pw1" + personModel.getPersonPassword());
			//System.out.println("pw1" + optional.getPersonPassword());
		//	if (personModel.getPersonPassword().trim().equals(optional.getPersonPassword().trim())) {
			if (personModel.getPersonPassword().equals(optional.getPersonPassword())) {

			//	System.out.println("pw2" + personModel.getPersonPassword());
			//	System.out.println("pw2" + optional.getPersonPassword());

				personModel.setStatus(Constants.LOGIN_SUCCESS);
				personModel.setMessage(Constants.SUCCESS);

			}

			else {

			//	System.out.println("pw3" + personModel.getPersonPassword());
			//	System.out.println("pw3" + optional.getPersonPassword());
				personModel.setStatus(Constants.INVALID_PASSWORD);
			//	System.out.println("pw4" + personModel.getPersonPassword());
			//	System.out.println("pw4" + optional.getPersonPassword());
				personModel.setMessage(Constants.INVALID_PASSWORD_MSG);
			//	System.out.println("pw5" + personModel.getPersonPassword());
			//	System.out.println("pw5" + optional.getPersonPassword());

			}
			return personModel;
		}

	}

	@Override
	public List<PersonModel> getAll() {
		return personRepository.getAll();

	}

}
