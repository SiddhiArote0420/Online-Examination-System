package org.service;

import java.util.ArrayList;

import org.model.StudentModel;
import org.repository.StudentRepository;

public class StudentService {

	StudentRepository repo =
	new StudentRepository();
	
	/* GET BY ID */
	public StudentModel getStudentById(int id) {

	return repo.getStudentById(id);
	}

	/*-----add student-*/
	public boolean addStudent(
	StudentModel model) {

		return repo.addStudent(model);
	}

	
	/*-------View------*/
	public ArrayList<StudentModel>
	getAllStudents(){

		return repo.getAllStudents();
	}

	/*-----------delete----------*/
	public boolean deleteStudent(int id) {

		return repo.deleteStudent(id);
	}
	
	
	/*Update student*/
	public boolean updateStudent(int id, String name, String email, String course, String mobile) {

		StudentRepository repo =
		new StudentRepository();

		return repo.updateStudent(id, name, email, course, mobile);
		}
	
	
	
	 public StudentModel login(String email, String password) {
	        return repo.login(email, password);
	    }
}