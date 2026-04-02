package com.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restapi.model.StudentModel;
import com.restapi.repository.StudentRpo;

@RestController
public class StudentController {

	@Autowired
	StudentRpo repo;
	
	@GetMapping("/getall")
	public List<StudentModel> getall() 
	{
		List<StudentModel> mylist = repo.findAll();
		return mylist;
	}
	
	@GetMapping("/getStudent/{id}")
	public StudentModel getbyid(@PathVariable int id) {
		StudentModel studentModel = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not Found for rollNo. : " + id));
		return studentModel;
	}
	
	@PostMapping("/addStudent")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addstudent(@RequestBody StudentModel ref) {
		repo.save( ref);
		return "Added successfully Data !!";
	}
	
	@PutMapping("/Student/update/{id}")
	public StudentModel updateStudent(@PathVariable int id, @RequestBody StudentModel ref) {
		
		
		StudentModel stuMod = repo.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found of rollNo. : " + id));
		stuMod.setBranch(ref.getBranch());
		stuMod.setName(ref.getName());
		stuMod.setPercentage(ref.getPercentage());
		
		repo.save(stuMod);
		return stuMod;
	}
	
	@DeleteMapping("/Student/delete/{roll}")
	public String deleteStudent(@PathVariable int roll) {
		if(repo.findById(roll).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with rollno " + roll + " not found!");
		}
		repo.deleteById(roll);
		return "Student Deleted Successfully of rollno -> " + roll;
	}
	
	
}
