-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: sd_hospital
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consultations`
--

DROP TABLE IF EXISTS `consultations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `patient` varchar(13) DEFAULT NULL,
  `doctor` varchar(15) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `doctorName` varchar(255) DEFAULT NULL,
  `patientName` varchar(255) DEFAULT NULL,
  `patientPersonalNumericCode` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `alertedDoctor` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84A77F0AE27B8B6B` (`doctor`),
  KEY `FK84A77F0A7ACAABE9` (`patient`),
  CONSTRAINT `FK84A77F0A7ACAABE9` FOREIGN KEY (`patient`) REFERENCES `patients` (`personalNumericCode`),
  CONSTRAINT `FK84A77F0AE27B8B6B` FOREIGN KEY (`doctor`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultations`
--

LOCK TABLES `consultations` WRITE;
/*!40000 ALTER TABLE `consultations` DISABLE KEYS */;
INSERT INTO `consultations` VALUES (184,'2016-04-24 22:20:27','2967148631064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'2967148631064',30,'\0'),(185,'2016-04-26 22:20:27','2967148631064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'2967148631064',30,'\0'),(186,'2016-04-23 22:20:27','2967148631064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'2967148631064',40,'\0'),(187,'2016-04-27 22:20:27','2967148631064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'2967148631064',40,'\0'),(188,'2016-04-22 22:20:27','2967148631064','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'2967148631064',50,'\0'),(189,'2016-04-28 22:20:27','2967148631064','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'2967148631064',50,'\0'),(190,'2016-04-21 22:20:27','2967148631064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'2967148631064',60,'\0'),(191,'2016-04-29 22:20:27','2967148631064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'2967148631064',60,'\0'),(192,'2016-04-20 22:20:27','2967148631064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'2967148631064',70,'\0'),(193,'2016-04-30 22:20:27','2967148631064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'2967148631064',70,'\0'),(194,'2016-04-24 22:20:27','1980476592863','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1980476592863',30,'\0'),(195,'2016-04-26 22:20:27','1980476592863','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1980476592863',30,'\0'),(196,'2016-04-23 22:20:27','1980476592863','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1980476592863',40,'\0'),(197,'2016-04-27 22:20:27','1980476592863','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1980476592863',40,'\0'),(198,'2016-04-22 22:20:28','1980476592863','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'1980476592863',50,'\0'),(199,'2016-04-28 22:20:28','1980476592863','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'1980476592863',50,'\0'),(200,'2016-04-21 22:20:28','1980476592863','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1980476592863',60,'\0'),(201,'2016-04-29 22:20:28','1980476592863','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1980476592863',60,'\0'),(202,'2016-04-20 22:20:28','1980476592863','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1980476592863',70,'\0'),(203,'2016-04-30 22:20:28','1980476592863','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1980476592863',70,'\0'),(204,'2016-04-24 22:20:28','1986834954064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1986834954064',30,'\0'),(205,'2016-04-26 22:20:28','1986834954064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1986834954064',30,'\0'),(206,'2016-04-23 22:20:28','1986834954064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1986834954064',40,'\0'),(207,'2016-04-27 22:20:28','1986834954064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1986834954064',40,'\0'),(208,'2016-04-22 22:20:28','1986834954064','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'1986834954064',50,'\0'),(209,'2016-04-28 22:20:28','1986834954064','doctor3','asd asdas dasdasdasdasda','Dr.Andrada',NULL,'1986834954064',50,'\0'),(210,'2016-04-21 22:20:28','1986834954064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1986834954064',60,'\0'),(211,'2016-04-29 22:20:28','1986834954064','doctor1','asd asdas dasdasdasdasda','Dr.Mircea',NULL,'1986834954064',60,'\0'),(212,'2016-04-20 22:20:28','1986834954064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1986834954064',70,'\0'),(213,'2016-04-30 22:20:28','1986834954064','doctor2','asd asdas dasdasdasdasda','Dr.Stefan',NULL,'1986834954064',70,'\0');
/*!40000 ALTER TABLE `consultations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `personalNumericCode` varchar(13) NOT NULL,
  `address` varchar(255) NOT NULL,
  `dateOfBirth` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`personalNumericCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES ('1980476592863','Dorobantilor 109','2016-04-25 22:20:21','Andrei'),('1986834954064','Dorobantilor 109','2016-04-25 22:20:21','Florin'),('2967148631064','Dorobantilor 109','2016-04-25 22:20:21','Bogdan'),('2972926153868','Dorobantilor 109','2016-04-25 22:20:21','Mircea');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `personalCode` varchar(13) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','Ceahlau 14','ROLE_ADMIN','','Andreea Muresan','6772dd2b0b86f127775d1ec154cf9665ffbb265f754583182dac2707774041b837a23cf5a379dacc','2972833165797'),('doctor1','Giulesti 10','ROLE_DOCTOR','','Dr.Mircea','0b590ff15af870502d490121cf9b3f58467c394ace9c5e38c34e5ab80731e745a55386566c8d40c1','1979961237204'),('doctor2','Giulesti 10','ROLE_DOCTOR','','Dr.Stefan','3758048f7cf2849d7c3c0f06e54326ae7c421807fa463b4d79b496e6c40e54cfa64a2863c2b11ee5','1993972912170'),('doctor3','Giulesti 10','ROLE_DOCTOR','','Dr.Andrada','9650dc9929b2071862073c3134b5d938ef7a73e816b85deda84bffc5bb4f1a06fd3c61b014f05589','1974282430877'),('secre','Dorobantilor 109','ROLE_SECRETARY','','Mihai Pop','53ca1a9daa5d5f6466456eaa8340cc14bac7512375c5f1d0689aa30305084acbcc1394fcfe035f20','1950386325801');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-25 22:21:24
