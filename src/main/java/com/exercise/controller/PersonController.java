package com.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.bean.ResponseBaseBean;
import com.exercise.constant.Constant;
import com.exercise.constant.URLConstant;
import com.exercise.dto.PersonDto;
import com.exercise.service.PersonService;

@RestController
@RequestMapping(value = URLConstant.PERSON)
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listPerson() {
		return new ResponseEntity<>(personService.listAll(), HttpStatus.OK);
	}

	@RequestMapping(value = URLConstant.PERSON_GET_BY_FIRST_NAME, method = RequestMethod.GET)
	public ResponseEntity<?> listPersonByFirstName(@PathVariable("firstName") String firstName) {
		ResponseEntity<?> responseEntity = null;
		try {
			PersonDto personDto = new PersonDto();
			personDto.setFirstName(firstName);
			responseEntity = new ResponseEntity<>(personService.listByFirstName(personDto), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(new ResponseBaseBean(e.getMessage()), HttpStatus.OK);
		}
		return responseEntity;
	}

	@RequestMapping(value = URLConstant.PERSON_DELETE, method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePerson(@PathVariable("firstName") String firstName) {
		ResponseEntity<?> responseEntity = null;
		try {
			PersonDto personDto = new PersonDto();
			personDto.setFirstName(firstName);
			personService.delete(personDto);
			responseEntity = new ResponseEntity<>(new ResponseBaseBean(Constant.GENERAL_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(new ResponseBaseBean(e.getMessage()), HttpStatus.OK);
		}
		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(personService.save(personDto), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(new ResponseBaseBean(e.getMessage()), HttpStatus.OK);
		}
		return responseEntity;
	}

	@RequestMapping(value = URLConstant.PERSON_UPDATE, method = RequestMethod.PUT)
	public ResponseEntity<?> updatePerson(@PathVariable("firstName") String firstName,
			@RequestBody PersonDto personDto) {
		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(personService.update(personDto, firstName), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(new ResponseBaseBean(e.getMessage()), HttpStatus.OK);
		}
		return responseEntity;
	}

}
