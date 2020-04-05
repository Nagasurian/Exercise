package com.exercise.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.constant.Constant;
import com.exercise.dao.PersonDao;
import com.exercise.dto.PersonDto;
import com.exercise.entity.Person;
import com.exercise.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public PersonDto listByFirstName(PersonDto personDto) throws Exception {
		Person person = personDao.listByFirstName(personDto.getFirstName());
		if(person == null) throw new Exception(Constant.PERSON_NOT_FOUND);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> listAll() {
		return personDao.listAll();
	}

	@Override
	public PersonDto save(PersonDto personDto) throws Exception {
		Person person = personDao.listByFirstName(personDto.getFirstName());
		if (person != null) {
			throw new Exception(Constant.PERSON_EXIST);
		}
		ModelMapper modelMapper = new ModelMapper();
		personDao.saveOrUpdate(modelMapper.map(personDto, Person.class));
		return listByFirstName(personDto);
	}

	@Override
	public void delete(PersonDto personDto) throws Exception {
		Person person = personDao.listByFirstName(personDto.getFirstName());
		if (person == null) {
			throw new Exception(Constant.PERSON_NOT_FOUND);
		}
		personDao.delete(person);
	}

	@Override
	public PersonDto update(PersonDto personDto, String firstName) throws Exception {
		Person person = personDao.listByFirstName(firstName);
		person.setEmail(personDto.getEmail());
		person.setLastName(personDto.getLastName());
		personDao.saveOrUpdate(person);
		return listByFirstName(personDto);
	}
}
