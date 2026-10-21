package org.service;

import org.model.QuestionModel;
import org.repository.QuestionRepository;
import java.util.*;
public class QuestionService {
	
	QuestionRepository repo = new QuestionRepository();

	public boolean addQuestion(QuestionModel model) {
		return repo.addQuestion(model);
	}

	
	// GET ALL QUESTIONS (WITH EXAM NAME)
    public List<QuestionModel> getAllQuestions() {
        return repo.getAllQuestions();
    }

    // GET BY ID
    public QuestionModel getQuestionById(int id) {
        return repo.getQuestionById(id);

    }
    // DELETE
    public boolean deleteQuestion(int id) {
        return repo.deleteQuestion(id);
    }

    // UPDATE
    public boolean updateQuestion(QuestionModel model) {
        return repo.updateQuestion(model);
    }
    
    public List<QuestionModel> getQuestionsByExamId(int examId) {

        return repo.getQuestionsByExamId(examId);
    }
    
    
    
    /*student*/
    
}
