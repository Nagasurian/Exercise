package com.exercise.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.exercise.dao.PersonDao;
import com.exercise.dto.PersonDto;
import com.exercise.entity.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	private Session getSession() {
		Session session = null;
		if (entityManager == null || (session = entityManager.unwrap(Session.class)) == null) {
			throw new NullPointerException();
		}
		return session;
	}

	@Override
	public List<PersonDto> listAll() {
		return getSession().createQuery("from Person", Person.class).stream().map((person) -> {
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(person, PersonDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public void saveOrUpdate(Person person) {
		getSession().saveOrUpdate(person);
	}

	@Override
	public Person listByFirstName(String firstName) {

		@SuppressWarnings("unchecked")
		List<Person> persons = getSession().createQuery("from Person where firstName = :firstName")
				.setParameter("firstName", firstName).list();
		if (persons != null && persons.size() > 0) {
			return persons.get(0);
		}
		return null;
	}

	@Override
	public void delete(Person person) {
		getSession().delete(person);
	}

}
