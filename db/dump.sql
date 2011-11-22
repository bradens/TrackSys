-- MySQL dump 10.13  Distrib 5.5.9, for osx10.6 (i386)
--
-- Host: bradensimpson.com    Database: tracksys
-- ------------------------------------------------------
-- Server version	5.5.8-log

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
-- Table structure for table `accounts`
--
use tracksys;
DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `clubid` int(11) NOT NULL,
  `balance` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (15,500);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clubid` int(11) NOT NULL,
  `trackid` int(11) NOT NULL,
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `bookedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (2,0,1,'2011-11-24 17:00:00','2011-11-24 18:00:00','2011-11-21 01:58:39','Comment'),(3,0,1,'2011-11-26 17:00:00','2011-11-26 18:00:00','2011-11-21 02:03:55','Comment'),(4,13,1,'2011-11-25 17:00:00','2011-11-25 18:00:00','2011-11-21 02:21:51','Comment'),(5,13,1,'2011-11-27 17:00:00','2011-11-27 18:00:00','2011-11-21 02:22:51','Comment'),(6,13,7,'2011-11-28 17:00:00','2011-11-28 18:00:00','2011-11-21 02:24:02','Comment'),(7,13,1,'2011-11-30 17:00:00','2011-11-30 18:00:00','2011-11-21 02:36:49','null'),(8,13,6,'2011-12-01 03:00:00','2011-11-30 18:00:00','2011-11-21 02:38:04','I am a comment'),(9,13,5,'2011-11-30 22:00:00','2011-12-01 01:00:00','2011-11-21 02:48:48','Comment'),(28,19,8,'2011-11-25 17:00:00','2011-11-25 18:00:00','2011-11-21 06:32:04','So stoked!'),(29,18,8,'2011-11-23 17:00:00','2011-11-23 18:00:00','2011-11-21 06:32:24','Randy Dandy'),(35,15,4,'2011-11-03 16:00:00','2011-11-03 17:00:00','2011-11-21 09:45:26',''),(36,15,1,'2011-12-01 17:00:00','2011-12-01 18:00:00','2011-11-21 09:45:43',''),(37,15,4,'2011-12-17 02:00:00','2011-12-17 01:00:00','2011-11-21 09:46:04','');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `passwd` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `postal` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `admin` int(11) DEFAULT '0',
  `electronicbilling` tinyint(1) DEFAULT '0',
  `waiver` tinyint(1) DEFAULT '0',
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COMMENT='Stores clubs';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (2,'ClubTest2','pass','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,0,0,0),(3,'ClubTest3','password','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,0,0,0),(11,'ClubTest4','pass','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,0,0,0),(12,'admin','password','dsf','dsfsd','sdfds','dsfds','sdfds','sdfs',1,0,0,0),(14,'clubtest5','pass','huigg','vy','yvuv','uyv','vyu','vy',0,0,0,0),(15,'asdf','something','asdf','asdf','asdf','asdf','asdf','1234123412',0,0,0,0),(16,'','','','','','','','',0,0,0,0),(17,'','','','','','','','',0,0,0,0);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `message` text,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (12,'Track closed','Wooooooooooops','2011-11-21 02:49:37'),(13,'CHUNKY!','IT\"S RAINING SIDEWAYS','2011-11-21 03:18:44'),(14,'SENG 330 Evaluations','Don\'t forget to come prepared to complete the SENG 330 evaluations.','2011-11-21 05:47:33'),(15,'testttttttttt 1 2','yessssssssss','2011-11-21 09:28:10'),(16,'test22222','boom boom','2011-11-21 09:40:52'),(17,'CODES CONFLICT!','Brian, you and I are working on the same task -Clubs tab in Admin view!\nI kept yours and will work on the Tracks Tab then','2011-11-21 21:53:08');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
  `trackid` int(11) NOT NULL AUTO_INCREMENT,
  `isMaintenance` int(11) NOT NULL,
  PRIMARY KEY (`trackid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `clubid` int(11) DEFAULT NULL,
  `paymentfee` float DEFAULT NULL,
  `paymenttime` timestamp NULL DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (0,15,20,'2011-12-14 00:23:13','This is a comment');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-11-21 14:08:54
