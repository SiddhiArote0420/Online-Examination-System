package org.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	protected Connection conn;

	protected PreparedStatement stmt;

	protected ResultSet rs;

	private static volatile boolean schemaChecked = false;

	public DBConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = System.getenv("DB_URL") != null
					? System.getenv("DB_URL")
					: "jdbc:mysql://localhost:3306/online_exam_db";
			String user = System.getenv("DB_USER") != null
					? System.getenv("DB_USER")
					: "root";
			String pass = System.getenv("DB_PASSWORD") != null
					? System.getenv("DB_PASSWORD")
					: "MySql_Sid@20";

			conn = DriverManager.getConnection(url, user, pass);

			if (!schemaChecked && conn != null) {
				synchronized (DBConnection.class) {
					if (!schemaChecked) {
						ensureDatabaseInitialized(conn);
						schemaChecked = true;
					}
				}
			}

		}
		catch(Exception e) {

			e.printStackTrace();
		}
	}

	private static void ensureDatabaseInitialized(Connection connection) {
		try (Statement st = connection.createStatement()) {
			try (ResultSet checkRs = st.executeQuery("SELECT 1 FROM admin LIMIT 1")) {
				return; // Tables already exist
			} catch (Exception ignored) {
				// Table 'admin' does not exist yet; initialize schema & seed data
			}

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `subject` ("
					+ "`subject_id` int NOT NULL AUTO_INCREMENT,"
					+ "`subject_name` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`subject_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `admin` ("
					+ "`admin_id` int NOT NULL AUTO_INCREMENT,"
					+ "`admin_name` varchar(100) NOT NULL,"
					+ "`email` varchar(100) DEFAULT NULL,"
					+ "`password` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`admin_id`),"
					+ "UNIQUE KEY `email` (`email`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `students` ("
					+ "`student_id` int NOT NULL AUTO_INCREMENT,"
					+ "`student_name` varchar(100) NOT NULL,"
					+ "`email` varchar(100) DEFAULT NULL,"
					+ "`password` varchar(100) NOT NULL,"
					+ "`course` varchar(100) DEFAULT NULL,"
					+ "`mobile` varchar(15) DEFAULT NULL,"
					+ "PRIMARY KEY (`student_id`),"
					+ "UNIQUE KEY `email` (`email`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `exam` ("
					+ "`exam_id` int NOT NULL AUTO_INCREMENT,"
					+ "`exam_name` varchar(100) NOT NULL,"
					+ "`subject_id` int DEFAULT NULL,"
					+ "`total_questions` int DEFAULT NULL,"
					+ "`total_marks` int DEFAULT NULL,"
					+ "`exam_duration` int DEFAULT NULL,"
					+ "PRIMARY KEY (`exam_id`),"
					+ "KEY `subject_id` (`subject_id`),"
					+ "CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `questions` ("
					+ "`question_id` int NOT NULL AUTO_INCREMENT,"
					+ "`exam_id` int DEFAULT NULL,"
					+ "`question_text` varchar(500) NOT NULL,"
					+ "`option1` varchar(200) NOT NULL,"
					+ "`option2` varchar(200) NOT NULL,"
					+ "`option3` varchar(200) NOT NULL,"
					+ "`option4` varchar(200) NOT NULL,"
					+ "`correct_answer` varchar(200) NOT NULL,"
					+ "PRIMARY KEY (`question_id`),"
					+ "KEY `exam_id` (`exam_id`),"
					+ "CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS `result` ("
					+ "`result_id` int NOT NULL AUTO_INCREMENT,"
					+ "`student_id` int DEFAULT NULL,"
					+ "`exam_id` int DEFAULT NULL,"
					+ "`total_marks` int DEFAULT NULL,"
					+ "`obtained_marks` int DEFAULT NULL,"
					+ "`percentage` double DEFAULT NULL,"
					+ "`status` varchar(20) DEFAULT NULL,"
					+ "PRIMARY KEY (`result_id`),"
					+ "UNIQUE KEY `uq_student_exam` (`student_id`,`exam_id`),"
					+ "KEY `exam_id` (`exam_id`),"
					+ "CONSTRAINT `result_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),"
					+ "CONSTRAINT `result_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

			// Seed initial data
			st.executeUpdate("INSERT IGNORE INTO `admin` VALUES (1,'siddhi','siddhi@gmail.com','siddhi123')");
			st.executeUpdate("INSERT IGNORE INTO `subject` VALUES (1,'Java'),(2,'Database'),(3,'Web Development'),(4,'Python'),(5,'C++'),(13,'AWS'),(17,'math')");
			st.executeUpdate("INSERT IGNORE INTO `students` VALUES "
					+ "(1,'Prital','prital43@gmail.com','prital123','java','8733287643'),"
					+ "(2,'sakshi','sakshi@gmail.com','gopal123','Database','2435246374'),"
					+ "(3,'Ankita ','ankita23@gamil.com','ankita123','web devlopment','9876543213'),"
					+ "(4,'Aditya ','Aditya@gmail.com','aditya123','Web Development','6764532459'),"
					+ "(5,'Gayatri ','gayatri@gmail.com','gayatri123','web devlopment','8745321456'),"
					+ "(6,'Bhakti ','bhakti@gmail.com','bhakti123','Database','7656432554'),"
					+ "(7,'Ved ','ved12@gmail.com','ved123','Database','9453245674'),"
					+ "(8,'Vidhi ','vidhi@gmail.com','vidhi123','AWS','5643543234')");
			st.executeUpdate("INSERT IGNORE INTO `exam` VALUES "
					+ "(1,'Java Basics Test',1,10,100,30),(2,'DBMS Mid Exam',2,10,100,30),"
					+ "(3,'Java Test',1,20,20,10),(4,'SQL Test',2,25,25,40),"
					+ "(7,'Java Basics Test',1,5,50,30),(8,'Advanced Java Test',1,5,50,30),"
					+ "(9,'DBMS Test',2,5,50,30),(10,'Python Test',3,5,50,30),"
					+ "(23,'DBMS Mid Exam',2,5,10,20),(26,'Web Development Test',3,3,30,20),"
					+ "(27,'Frontend Development Test',3,5,50,30),(28,'Web Development Final Test',3,5,50,30)");
			st.executeUpdate("INSERT IGNORE INTO `questions` VALUES "
					+ "(1,1,'What is mean by Java?','Programming Language','Database','OS','Browser','Programming Language'),"
					+ "(2,1,'Which keyword is used for inheritance?','this','extends','super','implements','extends'),"
					+ "(3,2,'What does DBMS stand for?','Database Management System','Data Base Model System','None','Data Backup System','Database Management System'),"
					+ "(4,1,'What is JVM?','Java Virtual Machine','Java Vendor Machine','Joint VM','None','Java Virtual Machine'),"
					+ "(5,1,'Which keyword is used for inheritance?','this','extends','super','final','extends'),"
					+ "(6,2,'Which command is used to fetch data?','INSERT','DELETE','SELECT','UPDATE','SELECT'),"
					+ "(11,1,'What is mean by Java?','Programming Language','Database','Browser','OS','Programming Language'),"
					+ "(12,1,'Which keyword is used for inheritance?','this','super','extends','final','extends'),"
					+ "(13,1,'What is JVM?','Java Virtual Machine','Java Vendor Machine','Joint VM','None','Java Virtual Machine'),"
					+ "(14,1,'Which package is automatically imported?','java.lang','java.util','java.sql','java.io','java.lang'),"
					+ "(15,1,'Which loop executes at least once?','for','while','do while','foreach','do while'),"
					+ "(16,2,'Which concept allows same method name?','Inheritance','Polymorphism','Abstraction','Encapsulation','Polymorphism'),"
					+ "(17,2,'Which collection stores unique values?','List','ArrayList','Set','Vector','Set'),"
					+ "(18,2,'Which keyword handles exception?','catch','final','throw','try','try'),"
					+ "(19,2,'Which JDBC driver connects Java to DB?','ODBC','JDBC','Servlet','JSP','JDBC'),"
					+ "(20,2,'Which keyword creates object?','new','this','super','create','new'),"
					+ "(26,3,'What does DBMS stand for?','Database Management System','Data Backup Management System','Digital Base Management System','None','Database Management System'),"
					+ "(27,3,'Which command fetches data?','INSERT','DELETE','SELECT','UPDATE','SELECT'),"
					+ "(28,3,'Primary key is?','Duplicate Key','Unique Key','Foreign Key','Null Key','Unique Key'),"
					+ "(29,3,'Which normal form removes redundancy?','1NF','2NF','3NF','BCNF','3NF'),"
					+ "(30,3,'SQL stands for?','Structured Query Language','Simple Query Language','System Query Language','None','Structured Query Language'),"
					+ "(31,4,'Python is?','Compiler','Programming Language','Database','Browser','Programming Language'),"
					+ "(32,4,'Which symbol is used for comments?','//','#','/*','--','#'),"
					+ "(33,4,'Which datatype stores text?','int','float','str','bool','str'),"
					+ "(34,4,'Which keyword defines function?','method','define','fun','def','def'),"
					+ "(35,4,'Which loop repeats code?','if','loop','for','switch','for'),"
					+ "(43,7,'What is Servlet?','Java API','Java Web Component','Database','Framework','Java Web Component'),"
					+ "(44,7,'Which protocol is used in web apps?','FTP','SMTP','HTTP','TCP','HTTP'),"
					+ "(68,9,'What is Java?','Programming Language','Database','Operating System','Browser','Programming Language'),"
					+ "(70,9,'Which keyword is used for inheritance in Java?','this','extends','super','final','extends'),"
					+ "(71,9,'Which method is the entry point of Java program?','start()','run()','main()','execute()','main()'),"
					+ "(72,9,'Which collection stores unique values?','List','ArrayList','Set','Vector','Set'),"
					+ "(73,9,'Which keyword is used to create an object?','class','new','this','object','new'),"
					+ "(79,26,'Which language is used to create webpage structure?','HTML','CSS','Java','SQL','HTML'),"
					+ "(80,26,'Which language is used for webpage styling?','HTML','CSS','Python','Java','CSS'),"
					+ "(81,26,'Which language adds functionality to webpages?','HTML','CSS','JavaScript','MySQL','JavaScript'),"
					+ "(82,10,'Python is which type of language?','Programming Language','Database','Operating System','Browser','Programming Language'),"
					+ "(83,10,'Which symbol is used for comments in Python?','//','#','/* */','--','#'),"
					+ "(84,10,'Which data type stores text in Python?','int','float','str','bool','str'),"
					+ "(85,10,'Which keyword is used to define a function in Python?','function','define','def','fun','def'),"
					+ "(86,10,'Which loop is used to iterate over a sequence in Python?','if','for','switch','case','for'),"
					+ "(97,28,'What does HTML stand for?','Hyper Text Markup Language','High Text Machine Language','Hyperlink Text Language','Home Tool Markup Language','Hyper Text Markup Language'),"
					+ "(98,28,'Which tag is used to create a link in HTML?','<link>','<a>','<href>','<url>','<a>'),"
					+ "(99,28,'Which CSS property changes the background color?','background-color','color-text','font-style','text-align','background-color'),"
					+ "(100,28,'Which JavaScript keyword is used to declare a variable?','var','select','define','variable','var'),"
					+ "(101,28,'Which framework is used for frontend UI development?','React','MySQL','JDBC','Servlet','React')");
			st.executeUpdate("INSERT IGNORE INTO `result` VALUES "
					+ "(1,1,1,20,18,90,'PASS'),(2,1,2,25,20,80,'PASS'),"
					+ "(3,2,4,70,30,42.857142857142854,'PASS'),(4,2,2,120,90,75,'PASS'),"
					+ "(8,1,3,7,7,100,'PASS'),(9,1,7,2,0,0,'FAIL'),"
					+ "(11,2,9,6,6,100,'PASS'),(12,2,1,9,7,77.77777777777777,'PASS'),"
					+ "(13,4,26,3,3,100,'PASS'),(14,4,10,5,4,80,'PASS'),(15,4,28,5,5,100,'PASS')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}