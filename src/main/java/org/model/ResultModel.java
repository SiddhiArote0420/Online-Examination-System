package org.model;

public class ResultModel {
 
	private String studentName;
	private String examName;
	private String subjectName;
	private int obtainedMarks;
	private String status;
	private int resultId;
	private int totalMarks;
	private double percentage;
	
	private int totalQuestions;
	private int correct;
	private int wrong;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	
	public int getResultId() {
	    return resultId;
	}

	public void setResultId(int resultId) {
	    this.resultId = resultId;
	}

	public int getTotalMarks() {
	    return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
	    this.totalMarks = totalMarks;
	}

	public double getPercentage() {
	    return percentage;
	}

	public void setPercentage(double percentage) {
	    this.percentage = percentage;
	}
	
	public int getTotalQuestions() {
	    return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
	    this.totalQuestions = totalQuestions;
	}


	public int getCorrect() {
	    return correct;
	}

	public void setCorrect(int correct) {
	    this.correct = correct;
	}


	public int getWrong() {
	    return wrong;
	}

	public void setWrong(int wrong) {
	    this.wrong = wrong;
	}

}
