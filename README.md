# Online Examination System (ExamPro)

A full-stack **Online Examination System** built with **Java 21, Jakarta EE (Servlets), JDBC, MySQL, HTML5, CSS3, Bootstrap 5, and Apache Tomcat 10.1**.

## ✨ Key Features
- **Single-Page Admin Workspace (`AdminDashboard.html`):**
  - Manage Students (Add, View, Update, Delete)
  - Manage Subjects (Add, View, Update, Delete)
  - Manage Examinations (Create, View, Update, Delete)
  - Manage Question Bank (Add, View, Update, Delete MCQ Questions)
  - View Real-Time Student Exam Results & Pass/Fail Status
- **Single-Page Student Portal (`studentDashboard`):**
  - Live Dashboard Overview (Total, Completed, and Pending Exams)
  - Interactive Timed Examination Runner with Auto-Submit Timer
  - Instant Score Evaluation & Detailed Performance Report
  - Student Profile View

## 🚀 Running Locally (Eclipse & Tomcat 10.1)
1. Import the project into Eclipse as an **Existing Maven Project**.
2. Create the MySQL database `online_exam_db` and configure credentials in `src/main/java/org/repository/DBConnection.java` (or via environment variables `DB_URL`, `DB_USER`, `DB_PASSWORD`).
3. Run on **Apache Tomcat v10.1 Server**.

## 🌐 Deploying Live (Render / Railway)
This repository includes a multi-stage `Dockerfile` (Maven + Tomcat 10.1 + JDK 21) for one-click cloud deployment on **Render** or **Railway**:
1. Connect your GitHub repository `SiddhiArote0420/Online-Examination-System`.
2. Set environment variables: `DB_URL`, `DB_USER`, and `DB_PASSWORD` pointing to your cloud MySQL database (e.g., Aiven / Railway MySQL / TiDB Cloud).
