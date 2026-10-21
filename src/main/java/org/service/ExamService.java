package org.service;

import org.model.ExamModel;
import org.model.QuestionModel;
import org.model.ResultModel;
import org.repository.ExamRepository;
import org.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExamService {


    ExamRepository repo = new ExamRepository();

    QuestionRepository questionRepo = new QuestionRepository();

    // ADD
    public boolean addExam(ExamModel e) {
        return repo.addExam(e);
    }

    // VIEW ALL
    public ArrayList<ExamModel> getAllExams() {
        return repo.getAllExams();
    }

    // GET BY ID
    public ExamModel getExamById(int id) {
        return repo.getExamById(id);
    }

    // UPDATE
    public boolean updateExam(ExamModel model) {
        return repo.updateExam(model);
    }

    // DELETE
    public boolean deleteExam(int id) {
        return repo.deleteExam(id);
    }

    public List<ExamModel> getExamWithStatus(int studentId) {
        return repo.getExamWithStatus(studentId);
    }


    // STUDENT EXAMS
    public List<ExamModel> getStudentExams(int studentId) {
        return repo.getStudentExams(studentId);
    }

    public int getTotalExams(int studentId) {
        return repo.getTotalExams(studentId);
    }

    public int getCompletedExams(int studentId) {
        return repo.getCompletedExams(studentId);
    }
 
    public int getPendingExams(int studentId) {
        return repo.getPendingExams(studentId);
    }


    // STUDENT SUBJECT EXAM SHOW
    public List<ExamModel> getExamWithStatus(int studentId, int subjectId) {
        return repo.getExamWithStatus(studentId, subjectId);
    }



    
    // RESULT CALCULATION 
    public ResultModel calculateResult(int examId,
            Map<String,String> answers) {

        List<QuestionModel> questions =
                questionRepo.getQuestionsByExamId(examId);

        ResultModel result = new ResultModel();

        if(questions == null || questions.isEmpty()) {

            result.setTotalQuestions(0);
            result.setCorrect(0);
            result.setWrong(0);
            result.setObtainedMarks(0);
            result.setTotalMarks(0);
            result.setPercentage(0);

            return result;
        }

        if(answers == null) {
            answers = new java.util.HashMap<>();
        }

        int totalQuestions = questions.size();

        int correct = 0;

        int wrong = 0;

        for(QuestionModel q : questions) {

            String selectedAnswer =
                    answers.get(
                    String.valueOf(q.getQuestionId())
                    );

            if(selectedAnswer != null) {

                if(selectedAnswer.equals(q.getCorrectAnswer())) {

                    correct++;

                } else {

                    wrong++;

                }

            }
        }

        result.setTotalQuestions(totalQuestions);

        result.setCorrect(correct);

        result.setWrong(wrong);

        result.setObtainedMarks(correct);

        result.setTotalMarks(totalQuestions);

        result.setPercentage(
                (correct * 100.0) / totalQuestions
        );

        return result;
    }
}