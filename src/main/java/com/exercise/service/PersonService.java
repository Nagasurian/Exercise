package com.exercise.service;

import java.util.List;

import com.exercise.dto.PersonDto;

public interface PersonService {

	public PersonDto listByFirstName(PersonDto personDto) throws Exception;

	public List<PersonDto> listAll();

	public PersonDto save(PersonDto personDto) throws Exception;

	public void delete(PersonDto personDto) throws Exception;

	public PersonDto update(PersonDto personDto, String firstName) throws Exception;
}
