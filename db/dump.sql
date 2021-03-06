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

USE tracksys;

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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (2,0,1,'2011-11-24 17:00:00','2011-11-24 18:00:00','2011-11-21 01:58:39','Comment'),(3,0,1,'2011-11-26 17:00:00','2011-11-26 18:00:00','2011-11-21 02:03:55','Comment'),(4,13,1,'2011-11-25 17:00:00','2011-11-25 18:00:00','2011-11-21 02:21:51','Comment'),(5,13,1,'2011-11-27 17:00:00','2011-11-27 18:00:00','2011-11-21 02:22:51','Comment'),(6,13,7,'2011-11-28 17:00:00','2011-11-28 18:00:00','2011-11-21 02:24:02','Comment'),(8,13,6,'2011-12-01 03:00:00','2011-11-30 18:00:00','2011-11-21 02:38:04','I am a comment'),(9,13,5,'2011-11-30 22:00:00','2011-12-01 01:00:00','2011-11-21 02:48:48','Comment'),(28,19,8,'2011-11-25 17:00:00','2011-11-25 18:00:00','2011-11-21 06:32:04','So stoked!'),(29,18,8,'2011-11-23 17:00:00','2011-11-23 18:00:00','2011-11-21 06:32:24','Randy Dandy'),(40,20,3,'2011-11-23 17:00:00','2011-11-24 05:00:00','2011-11-22 02:02:18','Snape granted a special permission for Slytherins practice. All day.'),(54,21,8,'2011-11-30 17:00:00','2011-11-30 18:00:00','2011-11-22 07:36:59','adsfasfdasdfadsf'),(56,20,6,'2011-11-01 13:00:00','2011-11-01 19:00:00','2011-11-22 21:00:19','Fall practice '),(90,33,4,'2011-11-28 14:00:00','2011-11-28 18:00:00','2011-11-22 23:17:04','Monday booking'),(95,33,3,'2011-11-28 14:00:00','2011-11-28 18:00:00','2011-11-22 23:20:54','test2'),(118,39,1,'2011-12-07 14:00:00','2011-12-07 15:00:00','2011-11-24 22:37:30','asdfasfas'),(119,20,1,'2011-11-28 16:00:00','2011-11-28 20:00:00','2011-11-25 21:58:11','extra practice'),(120,20,1,'2011-12-05 16:00:00','2011-12-05 20:00:00','2011-11-25 21:58:11','extra practice'),(121,20,1,'2011-12-12 16:00:00','2011-12-12 20:00:00','2011-11-25 21:58:11','extra practice'),(124,22,3,'2011-11-15 14:00:00','2011-11-15 15:00:00','2011-11-28 02:53:23','Brisk morning jog'),(125,22,3,'2011-11-22 14:00:00','2011-11-22 15:00:00','2011-11-28 02:53:23','Brisk morning jog'),(126,22,3,'2011-11-29 14:00:00','2011-11-29 15:00:00','2011-11-28 02:53:23','Brisk morning jog'),(127,22,3,'2011-12-06 14:00:00','2011-12-06 15:00:00','2011-11-28 02:53:23','Brisk morning jog');
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1 COMMENT='Stores clubs';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (2,'ClubTest2','pass','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,0,0,100),(3,'ClubTest3','password','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,1,0,1000),(11,'ClubTest4','pass','123 Fake St','Victoria','BC','V8T 3Y9','fake@you.com','1-250-555-1234',0,0,0,0),(12,'admin','password','dsf','dsfsd','sdfds','dsfds','sdfds','sdfs',1,1,0,0),(14,'clubtest5','pass','huigg','vy','yvuv','uyv','vyu','vy',0,0,0,0),(20,'Slytherins','mudblood','Hogwarts','Forbidden Forest','MAGIC','strawgoh','hogwarts@uk.com','none',0,1,0,-132),(21,'jazzlers','a','asdf','asdf','asdf','asdf','asdf','asdf',0,0,0,0),(22,'Brians Ballers','ballin','somewhere on','quadra','bc','v8v 8v8','b.richter3@gmail.com','250 896 2896',0,0,0,-5000000),(23,'running people','asdf','asdf','asdf','asdf','asdf','asdf','aasdf',0,0,0,0),(33,'Jordan','pass','123 Fake St','Victoria','BC','V8T 4T6','jordan@fake.org','1-250-555-1234',0,1,1,0),(38,'','','','','','','','',0,1,1,0),(40,'clubbin','something','1234 fake','Victoria','BC','VAD SSD','b@c.ca','1-234-1234',0,1,1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (39,'SOmething','something new','2011-11-24 05:25:57');
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
INSERT INTO `tracks` VALUES (1,1),(2,0),(3,0),(4,1),(5,0),(6,0),(7,0),(8,1);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clubid` int(11) DEFAULT NULL,
  `paymentfee` float DEFAULT NULL,
  `paymenttime` timestamp NULL DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,20,100,'2011-11-23 04:12:54','triet paid'),(2,20,100,'2011-11-23 04:12:54','triet paid2 '),(3,20,105,'2011-11-23 04:37:08','triet paid3'),(4,20,-122,'2011-11-23 04:47:08','sdfdsf'),(5,20,20,'2011-11-23 04:52:48','new payment'),(6,20,30,'2011-11-23 05:07:26','paid by credit'),(7,20,40,'2011-11-23 05:12:27','dffdsfsdfsdf'),(8,20,20,'2011-11-23 05:25:15','newest'),(9,20,15,'2011-11-23 05:27:42','minimum payment'),(10,20,24,'2011-11-23 05:29:10','pay moar'),(11,20,43,'2011-11-23 05:33:42','humm'),(12,15,50,'2011-11-23 05:34:52','paid by triet'),(13,15,40,'2011-11-23 05:38:22','repaid by t'),(14,15,45,'2011-11-23 06:05:53','overtime'),(15,15,32,'2011-11-23 06:07:00','d'),(16,15,1234,'2011-11-24 04:02:50','adsafasdaf'),(17,22,5000000,'2011-11-24 07:29:02','paid 5 milly'),(18,15,123412000000,'2011-11-24 22:15:46','');
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

-- Dump completed on 2011-11-27 18:55:39
