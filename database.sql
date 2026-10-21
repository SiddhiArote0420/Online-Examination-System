-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: online_exam_db
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'siddhi','siddhi@gmail.com','siddhi123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `exam_id` int NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(100) NOT NULL,
  `subject_id` int DEFAULT NULL,
  `total_questions` int DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `exam_duration` int DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'Java Basics Test',1,10,100,30),(2,'DBMS Mid Exam',2,10,100,30),(3,'Java Test',1,20,20,10),(4,'SQL Test',2,25,25,40),(7,'Java Basics Test',1,5,50,30),(8,'Advanced Java Test',1,5,50,30),(9,'DBMS Test',2,5,50,30),(10,'Python Test',3,5,50,30),(23,'DBMS Mid Exam',2,5,10,20),(26,'Web Development Test',3,3,30,20),(27,'Frontend Development Test',3,5,50,30),(28,'Web Development Final Test',3,5,50,30);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int DEFAULT NULL,
  `question_text` varchar(500) NOT NULL,
  `option1` varchar(200) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `option3` varchar(200) NOT NULL,
  `option4` varchar(200) NOT NULL,
  `correct_answer` varchar(200) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,1,'What is mean by Java?','Programming Language','Database','OS','Browser','Programming Language'),(2,1,'Which keyword is used for inheritance?','this','extends','super','implements','extends'),(3,2,'What does DBMS stand for?','Database Management System','Data Base Model System','None','Data Backup System','Database Management System'),(4,1,'What is JVM?','Java Virtual Machine','Java Vendor Machine','Joint VM','None','Java Virtual Machine'),(5,1,'Which keyword is used for inheritance?','this','extends','super','final','extends'),(6,2,'Which command is used to fetch data?','INSERT','DELETE','SELECT','UPDATE','SELECT'),(11,1,'What is mean by Java?','Programming Language','Database','Browser','OS','Programming Language'),(12,1,'Which keyword is used for inheritance?','this','super','extends','final','extends'),(13,1,'What is JVM?','Java Virtual Machine','Java Vendor Machine','Joint VM','None','Java Virtual Machine'),(14,1,'Which package is automatically imported?','java.lang','java.util','java.sql','java.io','java.lang'),(15,1,'Which loop executes at least once?','for','while','do while','foreach','do while'),(16,2,'Which concept allows same method name?','Inheritance','Polymorphism','Abstraction','Encapsulation','Polymorphism'),(17,2,'Which collection stores unique values?','List','ArrayList','Set','Vector','Set'),(18,2,'Which keyword handles exception?','catch','final','throw','try','try'),(19,2,'Which JDBC driver connects Java to DB?','ODBC','JDBC','Servlet','JSP','JDBC'),(20,2,'Which keyword creates object?','new','this','super','create','new'),(21,2,'Which concept allows same method name?','Inheritance','Polymorphism','Abstraction','Encapsulation','Polymorphism'),(22,2,'Which collection stores unique values?','List','ArrayList','Set','Vector','Set'),(23,2,'Which keyword handles exception?','catch','final','throw','try','try'),(24,2,'Which JDBC driver connects Java to DB?','ODBC','JDBC','Servlet','JSP','JDBC'),(25,2,'Which keyword creates object?','new','this','super','create','new'),(26,3,'What does DBMS stand for?','Database Management System','Data Backup Management System','Digital Base Management System','None','Database Management System'),(27,3,'Which command fetches data?','INSERT','DELETE','SELECT','UPDATE','SELECT'),(28,3,'Primary key is?','Duplicate Key','Unique Key','Foreign Key','Null Key','Unique Key'),(29,3,'Which normal form removes redundancy?','1NF','2NF','3NF','BCNF','3NF'),(30,3,'SQL stands for?','Structured Query Language','Simple Query Language','System Query Language','None','Structured Query Language'),(31,4,'Python is?','Compiler','Programming Language','Database','Browser','Programming Language'),(32,4,'Which symbol is used for comments?','//','#','/*','--','#'),(33,4,'Which datatype stores text?','int','float','str','bool','str'),(34,4,'Which keyword defines function?','method','define','fun','def','def'),(35,4,'Which loop repeats code?','if','loop','for','switch','for'),(43,7,'What is Servlet?','Java API','Java Web Component','Database','Framework','Java Web Component'),(44,7,'Which protocol is used in web apps?','FTP','SMTP','HTTP','TCP','HTTP'),(45,3,'What is JDBC?','Java Database Connectivity','Java Design Component','Joint Database Connector','None','Java Database Connectivity'),(46,3,'Which keyword creates object?','class','new','this','extends','new'),(47,4,'Which SQL command removes table?','DELETE','REMOVE','DROP','CLEAR','DROP'),(48,4,'Which clause filters rows?','ORDER BY','WHERE','GROUP BY','HAVING','WHERE'),(56,3,'Which key uniquely identifies each record in a table?','Primary Key','Foreign Key','Candidate Key','Composite Key','Primary Key'),(57,3,'Which SQL command is used to add new data?','UPDATE','INSERT','ADD','CREATE','INSERT'),(58,3,'Which JOIN returns matching records from both tables?','LEFT JOIN','RIGHT JOIN','INNER JOIN','FULL JOIN','INNER JOIN'),(59,3,'Which constraint prevents duplicate values?','NOT NULL','CHECK','UNIQUE','DEFAULT','UNIQUE'),(60,3,'Which SQL clause is used for sorting data?','GROUP BY','ORDER BY','SORT BY','HAVING','ORDER BY'),(61,3,'Which aggregate function counts rows?','SUM()','AVG()','COUNT()','TOTAL()','COUNT()'),(62,3,'Which command deletes all rows but keeps table structure?','DROP','REMOVE','TRUNCATE','DELETE TABLE','TRUNCATE'),(63,3,'Which normal form removes partial dependency?','1NF','2NF','3NF','BCNF','2NF'),(64,3,'Which SQL operator is used for pattern matching?','=','LIKE','IN','BETWEEN','LIKE'),(67,2,'gghjas','as','as','fd','sd','as'),(68,9,'What is Java?','Programming Language','Database','Operating System','Browser','Programming Language'),(69,9,'What is Java?','Programming Language','Database','Operating System','Browser','Programming Language'),(70,9,'Which keyword is used for inheritance in Java?','this','extends','super','final','extends'),(71,9,'Which method is the entry point of Java program?','start()','run()','main()','execute()','main()'),(72,9,'Which collection stores unique values?','List','ArrayList','Set','Vector','Set'),(73,9,'Which keyword is used to create an object?','class','new','this','object','new'),(79,26,'Which language is used to create webpage structure?','HTML','CSS','Java','SQL','HTML'),(80,26,'Which language is used for webpage styling?','HTML','CSS','Python','Java','CSS'),(81,26,'Which language adds functionality to webpages?','HTML','CSS','JavaScript','MySQL','JavaScript'),(82,10,'Python is which type of language?','Programming Language','Database','Operating System','Browser','Programming Language'),(83,10,'Which symbol is used for comments in Python?','//','#','/* */','--','#'),(84,10,'Which data type stores text in Python?','int','float','str','bool','str'),(85,10,'Which keyword is used to define a function in Python?','function','define','def','fun','def'),(86,10,'Which loop is used to iterate over a sequence in Python?','if','for','switch','case','for'),(97,28,'What does HTML stand for?','Hyper Text Markup Language','High Text Machine Language','Hyperlink Text Language','Home Tool Markup Language','Hyper Text Markup Language'),(98,28,'Which tag is used to create a link in HTML?','<link>','<a>','<href>','<url>','<a>'),(99,28,'Which CSS property changes the background color?','background-color','color-text','font-style','text-align','background-color'),(100,28,'Which JavaScript keyword is used to declare a variable?','var','select','define','variable','var'),(101,28,'Which framework is used for frontend UI development?','React','MySQL','JDBC','Servlet','React');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `result_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `exam_id` int DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `obtained_marks` int DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`result_id`),
  UNIQUE KEY `uq_student_exam` (`student_id`,`exam_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,1,1,20,18,90,'PASS'),(2,1,2,25,20,80,'PASS'),(3,2,4,70,30,42.857142857142854,'PASS'),(4,2,2,120,90,75,'PASS'),(8,1,3,7,7,100,'PASS'),(9,1,7,2,0,0,'FAIL'),(11,2,9,6,6,100,'PASS'),(12,2,1,9,7,77.77777777777777,'PASS'),(13,4,26,3,3,100,'PASS'),(14,4,10,5,4,80,'PASS'),(15,4,28,5,5,100,'PASS');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `course` varchar(100) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Prital','prital43@gmail.com','prital123','java','8733287643'),(2,'sakshi','sakshi@gmail.com','gopal123','Database','2435246374'),(3,'Ankita ','ankita23@gamil.com','ankita123','web devlopment','9876543213'),(4,'Aditya ','Aditya@gmail.com','aditya123','Web Development','6764532459'),(5,'Gayatri ','gayatri@gmail.com','gayatri123','web devlopment','8745321456'),(6,'Bhakti ','bhakti@gmail.com','bhakti123','Database','7656432554'),(7,'Ved ','ved12@gmail.com','ved123','Database','9453245674'),(8,'Vidhi ','vidhi@gmail.com','vidhi123','AWS','5643543234');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Java'),(2,'Database'),(3,'Web Development'),(4,'Python'),(5,'C++'),(13,'AWS'),(17,'math');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-21 13:37:45
