package com.exercise.dao;

import java.util.List;

import com.exercise.dto.PersonDto;
import com.exercise.entity.Person;

public interface PersonDao {

	public List<PersonDto> listAll();

	public void saveOrUpdate(Person person);

	public Person listByFirstName(String firstName);

	public void delete(Person person);
}
