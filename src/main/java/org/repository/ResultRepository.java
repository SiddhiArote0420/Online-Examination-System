package org.repository;
import java.util.*;

import org.model.ResultModel;
public class ResultRepository extends DBConnection {
	
	public ArrayList<ResultModel> getAllResults() {

	    ArrayList<ResultModel> list = new ArrayList<>();

	    try {

	        stmt = conn.prepareStatement(
	            "SELECT r.result_id, s.student_name, e.exam_name, sub.subject_name, " +
	            "r.total_marks, r.obtained_marks, r.percentage, r.status " +
	            "FROM result r " +
	            "LEFT JOIN students s ON r.student_id = s.student_id " +
	            "LEFT JOIN exam e ON r.exam_id = e.exam_id " +
	            "LEFT JOIN subject sub ON e.subject_id = sub.subject_id"
	        );

	        rs = stmt.executeQuery();

	        while (rs.next()) {

	            ResultModel r = new ResultModel();

	            r.setResultId(rs.getInt("result_id"));
	            r.setStudentName(rs.getString("student_name"));
	            r.setExamName(rs.getString("exam_name"));
	            r.setSubjectName(rs.getString("subject_name"));

	            r.setTotalMarks(rs.getInt("total_marks"));
	            r.setObtainedMarks(rs.getInt("obtained_marks"));
	            r.setPercentage(rs.getDouble("percentage"));
	            r.setStatus(rs.getString("status"));

	            list.add(r);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	
	
	public void saveFinalResult(int studentId, int examId, int totalMarks, int obtainedMarks) {

	    try {

	        double percentage = (obtainedMarks * 100.0) / totalMarks;
	        String status = (percentage >= 40) ? "PASS" : "FAIL";

	        stmt = conn.prepareStatement(
	            "INSERT INTO result(student_id, exam_id, total_marks, obtained_marks, percentage, status) VALUES(?,?,?,?,?,?)"
	        );

	        stmt.setInt(1, studentId);
	        stmt.setInt(2, examId);
	        stmt.setInt(3, totalMarks);
	        stmt.setInt(4, obtainedMarks);
	        stmt.setDouble(5, percentage);
	        stmt.setString(6, status);

	        stmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	/*student own result*/
	public ArrayList<ResultModel> getStudentResults(int studentId) {

	    ArrayList<ResultModel> list = new ArrayList<>();

	    try {

	        stmt = conn.prepareStatement(
	            "SELECT r.result_id, s.student_name, e.exam_name, sub.subject_name, " +
	            "r.total_marks, r.obtained_marks, r.percentage, r.status " +
	            "FROM result r " +
	            "LEFT JOIN students s ON r.student_id = s.student_id " +
	            "LEFT JOIN exam e ON r.exam_id = e.exam_id " +
	            "LEFT JOIN subject sub ON e.subject_id = sub.subject_id " +
	            "WHERE r.student_id = ?"
	        );

	        stmt.setInt(1, studentId);

	        rs = stmt.executeQuery();

	        while (rs.next()) {

	            ResultModel r = new ResultModel();

	            r.setResultId(rs.getInt("result_id"));
	            r.setStudentName(rs.getString("student_name"));
	            r.setExamName(rs.getString("exam_name"));
	            r.setSubjectName(rs.getString("subject_name"));
	            r.setTotalMarks(rs.getInt("total_marks"));
	            r.setObtainedMarks(rs.getInt("obtained_marks"));
	            r.setPercentage(rs.getDouble("percentage"));
	            r.setStatus(rs.getString("status"));

	            list.add(r);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}	
}
