package org.service;

import java.util.ArrayList;

import org.model.ResultModel;
import org.repository.ResultRepository;

public class ResultService {

    ResultRepository repo = new ResultRepository();

    // GET ALL RESULTS to admin
    public ArrayList<ResultModel> getAllResults() {
        return repo.getAllResults();
    }

   

    // SAVE FINAL RESULT (percentage + status)
    
    public void saveFinalResult(int studentId, int examId, int totalMarks, int obtainedMarks) {
        repo.saveFinalResult(studentId, examId, totalMarks, obtainedMarks);
    }
    
    
    /* show result to student*/
    public ArrayList<ResultModel> getStudentResults(int studentId) {
        return repo.getStudentResults(studentId);
    }
}