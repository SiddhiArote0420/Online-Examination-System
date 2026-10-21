package org.repository;

import java.util.ArrayList;

import org.model.SubjectModel;

public class SubjectRepository extends DBConnection {
	
	/*--ad subject---*/
	public boolean addSubject(SubjectModel model) {

		try {

			stmt = conn.prepareStatement("insert into subject(subject_name) values(?)");

			stmt.setString(1, model.getSubjectName());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	/* ----------view--------*/
	public ArrayList<SubjectModel> getAllSubjects() {
		ArrayList<SubjectModel> list = new ArrayList<>();

		try {
		stmt = conn.prepareStatement("select * from subject");
		rs = stmt.executeQuery();

		while(rs.next()) {
		SubjectModel s = new SubjectModel();
		s.setSubjectId(rs.getInt("subject_id"));
		s.setSubjectName(rs.getString("subject_name"));
		list.add(s);
		}

		} catch(Exception e) {
		e.printStackTrace();
		}

		return list;
		}
	
	/* ---------upadate------------*/
	public SubjectModel getSubjectById(int id) {

		SubjectModel s = new SubjectModel();

		try {
		stmt = conn.prepareStatement("select * from subject where subject_id=?");
		stmt.setInt(1, id);

		rs = stmt.executeQuery();

		if(rs.next()) {
		s.setSubjectId(rs.getInt("subject_id"));
		s.setSubjectName(rs.getString("subject_name"));
		}

		} catch(Exception e) {
		e.printStackTrace();
		}

		return s;
		}
	
	
	
	public boolean updateSubject(int id, String name) {

		try {
		stmt = conn.prepareStatement(
		"update subject set subject_name=? where subject_id=?"
		);

		stmt.setString(1, name);
		stmt.setInt(2, id);

		return stmt.executeUpdate() > 0;

		} catch(Exception e) {
		e.printStackTrace();
		}

		return false;
		}
	
	/*-----delete--------*/
	public boolean deleteSubject(int id) {

		try {
		stmt = conn.prepareStatement(
		"delete from subject where subject_id=?"
		);

		stmt.setInt(1, id);

		return stmt.executeUpdate() > 0;

		} catch(Exception e) {
		e.printStackTrace();
		}

		return false;
		}
}