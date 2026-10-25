package org.repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.model.StudentModel;

public class StudentRepository extends DBConnection {

	/* ADD STUDENT */

	public boolean addStudent(StudentModel model) {

		try {

			stmt = conn.prepareStatement(

			"insert into students(student_name,email,password,course,mobile) values(?,?,?,?,?)"

			);

			stmt.setString(1,
			model.getStudentName());

			stmt.setString(2,
			model.getEmail());

			stmt.setString(3,
			model.getPassword());

			stmt.setString(4,
			model.getCourse());

			stmt.setString(5,
			model.getMobile());

			return stmt.executeUpdate() > 0;

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	/* VIEW STUDENTS */

	public ArrayList<StudentModel> getAllStudents(){

		ArrayList<StudentModel> list =
		new ArrayList<StudentModel>();

		try {

			stmt = conn.prepareStatement(
			"select * from students");

			rs = stmt.executeQuery();

			while(rs.next()) {

				StudentModel model =
				new StudentModel();

				model.setStudentId(
				rs.getInt("student_id"));

				model.setStudentName(
				rs.getString("student_name"));

				model.setEmail(
				rs.getString("email"));

				model.setCourse(
				rs.getString("course"));

				model.setMobile(
				rs.getString("mobile"));

				list.add(model);
			}

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	/* DELETE */

	public boolean deleteStudent(int id) {

		try {
			try (PreparedStatement ps1 = conn.prepareStatement("delete from result where student_id=?")) {
				ps1.setInt(1, id);
				ps1.executeUpdate();
			}

			stmt = conn.prepareStatement(
			"delete from students where student_id=?");

			stmt.setInt(1, id);

			return stmt.executeUpdate() > 0;

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return false;
	}
	
	
	
	/*----------upadate---------------*/
	
	public StudentModel getStudentById(int id) {

		StudentModel s = new StudentModel();

		try {

		stmt = conn.prepareStatement(
		"select * from students where student_id=?"
		);

		stmt.setInt(1, id);

		rs = stmt.executeQuery();

		if(rs.next()) {

		s.setStudentId(rs.getInt("student_id"));
		s.setStudentName(rs.getString("student_name"));
		s.setEmail(rs.getString("email"));
		s.setCourse(rs.getString("course"));
		s.setMobile(rs.getString("mobile"));
		}

		} catch(Exception e) {
		e.printStackTrace();
		}

		return s;
	}
	
	public boolean updateStudent(int id, String name, String email, String course, String mobile) {

		try {

		stmt = conn.prepareStatement(
		"update students set student_name=?, email=?, course=?, mobile=? where student_id=?"
		);

		stmt.setString(1, name);
		stmt.setString(2, email);
		stmt.setString(3, course);
		stmt.setString(4, mobile);
		stmt.setInt(5, id);

		return stmt.executeUpdate() > 0;

		} catch(Exception e) {
		e.printStackTrace();
		}

		return false;
	}
	
	
	
	public StudentModel login(String email, String password) {

	    StudentModel student = null;

	    try {

	        stmt = conn.prepareStatement(
	            "SELECT * FROM students WHERE email=? AND password=?"
	        );

	        stmt.setString(1, email);
	        stmt.setString(2, password);

	        rs = stmt.executeQuery();

	        if (rs.next()) {

	            student = new StudentModel();

	            student.setStudentId(rs.getInt("student_id"));
	            student.setStudentName(rs.getString("student_name"));
	            student.setEmail(rs.getString("email"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return student;
	}
}
