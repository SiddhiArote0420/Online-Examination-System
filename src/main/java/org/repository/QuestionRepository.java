package org.repository;

import org.model.QuestionModel;
import java.util.*;
public class QuestionRepository extends DBConnection{

	
	//add que
	public boolean addQuestion(QuestionModel model) {

		try {

			stmt = conn.prepareStatement(
				"INSERT INTO questions(exam_id, question_text, option1, option2, option3, option4, correct_answer) VALUES (?,?,?,?,?,?,?)"
			);

			stmt.setInt(1, model.getExamId());
			stmt.setString(2, model.getQuestionText());
			stmt.setString(3, model.getOption1());
			stmt.setString(4, model.getOption2());
			stmt.setString(5, model.getOption3());
			stmt.setString(6, model.getOption4());
			stmt.setString(7, model.getCorrectAnswer());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	//view que
	public List<QuestionModel> getAllQuestions() {

	    List<QuestionModel> list = new ArrayList<>();

	    try {

	        stmt = conn.prepareStatement(
	            "SELECT q.*, e.exam_name " +
	            "FROM questions q " +
	            "JOIN exam e ON q.exam_id = e.exam_id"
	        );

	        rs = stmt.executeQuery();

	        while (rs.next()) {

	            QuestionModel model = new QuestionModel();

	            model.setQuestionId(rs.getInt("question_id"));
	            model.setExamId(rs.getInt("exam_id"));
	            model.setExamName(rs.getString("exam_name"));

	            model.setQuestionText(rs.getString("question_text"));

	            model.setOption1(rs.getString("option1"));
	            model.setOption2(rs.getString("option2"));
	            model.setOption3(rs.getString("option3"));
	            model.setOption4(rs.getString("option4"));

	            model.setCorrectAnswer(rs.getString("correct_answer"));

	            list.add(model);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	// GET BY ID
	public QuestionModel getQuestionById(int id) {

	    QuestionModel q = null;

	    try {

	        stmt = conn.prepareStatement(
	            "SELECT * FROM questions WHERE question_id=?"
	        );

	        stmt.setInt(1, id);

	        rs = stmt.executeQuery();


	        if(rs.next()) {

	            q = new QuestionModel();

	            q.setQuestionId(rs.getInt("question_id"));
	            q.setExamId(rs.getInt("exam_id"));

	            q.setQuestionText(
	                rs.getString("question_text")
	            );

	            q.setOption1(rs.getString("option1"));
	            q.setOption2(rs.getString("option2"));
	            q.setOption3(rs.getString("option3"));
	            q.setOption4(rs.getString("option4"));

	            q.setCorrectAnswer(
	                rs.getString("correct_answer")
	            );
	        }


	    } catch(Exception e) {

	        e.printStackTrace();

	    }


	    return q;
	}
	
	// DELETE
    public boolean deleteQuestion(int id) {

        try {

            stmt = conn.prepareStatement(
                "DELETE FROM questions WHERE question_id=?"
            );

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE
    public boolean updateQuestion(QuestionModel model) {

        try {

            stmt = conn.prepareStatement(
                "UPDATE questions SET exam_id=?, question_text=?, option1=?, option2=?, option3=?, option4=?, correct_answer=? WHERE question_id=?"
            );

            stmt.setInt(1, model.getExamId());
            stmt.setString(2, model.getQuestionText());
            stmt.setString(3, model.getOption1());
            stmt.setString(4, model.getOption2());
            stmt.setString(5, model.getOption3());
            stmt.setString(6, model.getOption4());
            stmt.setString(7, model.getCorrectAnswer());
            stmt.setInt(8, model.getQuestionId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
        /*student dashboard*/
    
    
    public List<QuestionModel> getQuestionsByExamId(int examId) {

        List<QuestionModel> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                "SELECT * FROM questions WHERE exam_id=?"
            );

            stmt.setInt(1, examId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                QuestionModel q = new QuestionModel();

                q.setQuestionId(rs.getInt("question_id"));
                q.setExamId(rs.getInt("exam_id"));
                q.setQuestionText(rs.getString("question_text"));

                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));

                q.setCorrectAnswer(rs.getString("correct_answer"));

                list.add(q);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}