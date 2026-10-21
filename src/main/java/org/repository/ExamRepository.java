package org.repository;

import java.sql.*;
import java.util.*;

import org.model.ExamModel;


public class ExamRepository extends DBConnection {

    public boolean addExam(ExamModel e) {

        try {

            stmt = conn.prepareStatement(
                "INSERT INTO exam(exam_name,subject_id,total_questions,total_marks,exam_duration) VALUES(?,?,?,?,?)"
            );

            stmt.setString(1, e.getExamName());
            stmt.setInt(2, e.getSubjectId());
            stmt.setInt(3, e.getTotalQuestions());
            stmt.setInt(4, e.getTotalMarks());
            stmt.setInt(5, e.getExamDuration());

            return stmt.executeUpdate() > 0;

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
    
    
    public List<ExamModel> getExamWithStatus(int studentId) {

        List<ExamModel> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                "SELECT e.*, " +
                "CASE WHEN r.exam_id IS NULL THEN 'PENDING' ELSE 'COMPLETED' END AS status " +
                "FROM exam e " +
                "LEFT JOIN result r ON e.exam_id = r.exam_id AND r.student_id = ?"
            );

            stmt.setInt(1, studentId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                ExamModel e = new ExamModel();

                e.setExamId(rs.getInt("exam_id"));
                e.setExamName(rs.getString("exam_name"));
                e.setStatus(rs.getString("status"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
 // GET ALL
    public ArrayList<ExamModel> getAllExams() {

        ArrayList<ExamModel> list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM exam");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExamModel m = new ExamModel();

                m.setExamId(rs.getInt("exam_id"));
                m.setExamName(rs.getString("exam_name"));
                m.setSubjectId(rs.getInt("subject_id"));
                m.setTotalQuestions(rs.getInt("total_questions"));
                m.setTotalMarks(rs.getInt("total_marks"));
                m.setExamDuration(rs.getInt("exam_duration"));

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET BY ID
    public ExamModel getExamById(int id) {

        ExamModel m = new ExamModel();

        try {
            stmt = conn.prepareStatement("SELECT * FROM exam WHERE exam_id=?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                m.setExamId(rs.getInt("exam_id"));
                m.setExamName(rs.getString("exam_name"));
                m.setSubjectId(rs.getInt("subject_id"));
                m.setTotalQuestions(rs.getInt("total_questions"));
                m.setTotalMarks(rs.getInt("total_marks"));
                m.setExamDuration(rs.getInt("exam_duration"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    // UPDATE
    public boolean updateExam(ExamModel model) {

        try {
            stmt = conn.prepareStatement(
                "UPDATE exam SET exam_name=?, subject_id=?, total_questions=?, total_marks=?, exam_duration=? WHERE exam_id=?"
            );

            stmt.setString(1, model.getExamName());
            stmt.setInt(2, model.getSubjectId());
            stmt.setInt(3, model.getTotalQuestions());
            stmt.setInt(4, model.getTotalMarks());
            stmt.setInt(5, model.getExamDuration());
            stmt.setInt(6, model.getExamId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public boolean deleteExam(int id) {

        try {
            stmt = conn.prepareStatement("DELETE FROM exam WHERE exam_id=?");
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    /*student dashboard*/
   
        // STUDENT EXAM LIST (WORKING)
    public List<ExamModel> getStudentExams(int studentId) {

        List<ExamModel> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(

                "SELECT e.*, " +
                "CASE " +
                "WHEN r.exam_id IS NULL THEN 'PENDING' " +
                "ELSE 'COMPLETED' END AS status " +

                "FROM exam e " +

                "JOIN subject sub ON e.subject_id = sub.subject_id " +

                "JOIN students s ON s.course = sub.subject_name " +

                "LEFT JOIN result r " +
                "ON r.exam_id = e.exam_id " +
                "AND r.student_id = s.student_id " +

                "WHERE s.student_id = ?"

            );

            stmt.setInt(1, studentId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                ExamModel exam = new ExamModel();

                exam.setExamId(rs.getInt("exam_id"));
                exam.setExamName(rs.getString("exam_name"));
                exam.setSubjectId(rs.getInt("subject_id"));
                exam.setTotalQuestions(rs.getInt("total_questions"));
                exam.setTotalMarks(rs.getInt("total_marks"));
                exam.setExamDuration(rs.getInt("exam_duration"));
                exam.setStatus(rs.getString("status"));

                list.add(exam);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    // TOTAL
    public int getTotalExams(int studentId) {

        int count = 0;

        try {

            stmt = conn.prepareStatement(

                "SELECT COUNT(*) " +
                "FROM exam e " +

                "JOIN subject sub ON e.subject_id = sub.subject_id " +

                "JOIN students s ON s.course = sub.subject_name " +

                "WHERE s.student_id = ?"

            );

            stmt.setInt(1, studentId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

        // COMPLETED
        public int getCompletedExams(int studentId) {

            int count = 0;

            try {
                stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM result WHERE student_id=?"
                );

                stmt.setInt(1, studentId);
                rs = stmt.executeQuery();

                if (rs.next()) count = rs.getInt(1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return count;
        }

        // PENDING
        public int getPendingExams(int studentId) {

            int count = 0;

            try {

                stmt = conn.prepareStatement(

                    "SELECT COUNT(*) " +

                    "FROM exam e " +

                    "JOIN subject sub ON e.subject_id = sub.subject_id " +

                    "JOIN students s ON s.course = sub.subject_name " +

                    "LEFT JOIN result r " +
                    "ON r.exam_id = e.exam_id " +
                    "AND r.student_id = s.student_id " +

                    "WHERE s.student_id = ? " +
                    "AND r.result_id IS NULL"

                );

                stmt.setInt(1, studentId);

                rs = stmt.executeQuery();

                if (rs.next()) {
                    count = rs.getInt(1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return count;
        }
        
    /* student show only there subject exam only*/
    public List<ExamModel> getExamWithStatus(int studentId, int subjectId) {

        List<ExamModel> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                "SELECT e.*, " +
                "CASE WHEN r.exam_id IS NULL THEN 'PENDING' ELSE 'COMPLETED' END AS status " +
                "FROM exam e " +
                "LEFT JOIN result r ON e.exam_id = r.exam_id AND r.student_id = ? " +
                "WHERE e.subject_id = ?"
            );

            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                ExamModel e = new ExamModel();

                e.setExamId(rs.getInt("exam_id"));
                e.setExamName(rs.getString("exam_name"));
                e.setSubjectId(rs.getInt("subject_id"));
                e.setStatus(rs.getString("status"));

                list.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    
       
    }