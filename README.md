<div align="center">

# 📝 Online Examination System (ExamPro)

**A Full-Stack Online Assessment & Automated Grading Portal built with Java 21, Jakarta EE (Servlets), JDBC, MySQL, Bootstrap 5, and Apache Tomcat 10.1**

### 🚀 **[🌐 View Live Application (Render)](https://online-examination-system-3cvc.onrender.com/)** &nbsp;|&nbsp; 👩‍💻 **[Developer Portfolio](https://siddhiarote0420.github.io/Portfolio/)**

[![Live Demo](https://img.shields.io/badge/🚀_LIVE_DEMO-online--examination--system-10B981?style=for-the-badge)](https://online-examination-system-3cvc.onrender.com/)
[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta_EE-Servlets_%26_JSP-007396?style=for-the-badge&logo=java&logoColor=white)](https://jakarta.ee/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Render_Cloud-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://render.com/)

</div>

---

## ✨ Key Features

- **👑 Single-Page Admin Workspace (`AdminDashboard.html`):**
  - **Student Management:** Add, View, Update, Delete, and Paginate registered students
  - **Subject & Course Management:** Organize subjects and map exams dynamically
  - **Examination Builder:** Configure exam duration, total marks, and passing criteria
  - **Question Bank Management:** Full CRUD for Multiple-Choice Questions (MCQs) with instant filtering
  - **Live Result Analytics:** Real-time student scorecards with automated Pass/Fail evaluation
- **🎓 Student Examination Portal (`studentDashboard`):**
  - **Interactive Dashboard:** Track Total, Completed, and Pending examinations
  - **Timed Exam Engine:** Countdown timer with automatic exam submission on expiration
  - **Instant Score Evaluation:** Automated grading and detailed performance breakdown
  - **Security:** BCrypt password hashing (`jbcrypt`) and HTTP session validation

---

## 🛠️ Tech Stack

| Layer | Technologies |
| :--- | :--- |
| **Backend** | Java 21, Jakarta Servlet API 5.0, JDBC, BCrypt Security |
| **Database** | MySQL 8.0 (`online_exam_db`) |
| **Frontend** | HTML5, CSS3, JavaScript (ES6+), Bootstrap 5 |
| **Server & DevOps** | Apache Tomcat 10.1, Maven, Multi-Stage Docker, Render Cloud |

---

## 🚀 Running Locally (Eclipse & Tomcat 10.1)

1. Clone the repository:
   ```bash
   git clone https://github.com/SiddhiArote0420/Online-Examination-System.git
   ```
2. Import the project into Eclipse as an **Existing Maven Project**.
3. Execute `database.sql` in MySQL to create `online_exam_db` and configure credentials in `src/main/java/org/repository/DBConnection.java` (or via environment variables `DB_URL`, `DB_USER`, `DB_PASSWORD`).
4. Run on **Apache Tomcat v10.1 Server**.

---

## ☁️ Cloud Deployment (Docker & Render)

This repository includes a multi-stage `Dockerfile` (Maven + Tomcat 10.1 + JDK 21) and `render.yaml` for cloud deployment:
- **Live URL:** [https://online-examination-system-3cvc.onrender.com/](https://online-examination-system-3cvc.onrender.com/)

---

## 👩‍💻 Author

**Siddhi Arote**
- 🌐 Portfolio: [siddhiarote0420.github.io/Portfolio](https://siddhiarote0420.github.io/Portfolio/)
- 🐙 GitHub: [@SiddhiArote0420](https://github.com/SiddhiArote0420)
