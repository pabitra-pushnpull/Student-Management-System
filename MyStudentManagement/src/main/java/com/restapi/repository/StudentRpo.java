package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.StudentModel;

public interface StudentRpo extends JpaRepository<StudentModel, Integer> {

	
	
	
}
