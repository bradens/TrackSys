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
drop database tracksys;
CREATE database tracksys;
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
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (166,42,3,'2011-11-30 00:00:00','2011-11-30 02:00:00','2011-11-29 07:58:44','My first booking'),(167,42,7,'2011-11-30 14:00:00','2011-11-30 18:00:00','2011-11-29 07:59:07','Weekly practise'),(168,42,7,'2011-12-07 14:00:00','2011-12-07 18:00:00','2011-11-29 07:59:07','Weekly practise'),(171,42,3,'2011-11-02 16:00:00','2011-11-02 17:00:00','2011-11-29 08:00:19','First weekly'),(172,42,3,'2011-11-09 17:00:00','2011-11-09 18:00:00','2011-11-29 08:00:19','First weekly'),(173,42,3,'2011-11-16 17:00:00','2011-11-16 18:00:00','2011-11-29 08:00:19','First weekly'),(174,42,3,'2011-11-23 17:00:00','2011-11-23 18:00:00','2011-11-29 08:00:19','First weekly'),(175,43,4,'2011-11-29 14:00:00','2011-11-30 01:00:00','2011-11-29 08:13:30','All day booking'),(176,43,2,'2011-12-15 14:00:00','2011-12-15 18:00:00','2011-11-29 08:14:02','100m dash practise'),(177,43,7,'2011-12-01 02:00:00','2011-12-01 06:00:00','2011-11-29 08:20:45','Night booking'),(178,44,1,'2011-11-28 18:00:00','2011-11-28 20:00:00','2011-11-29 08:21:34','Weekly booking'),(179,44,1,'2011-12-05 18:00:00','2011-12-05 20:00:00','2011-11-29 08:21:34','Weekly booking'),(181,44,1,'2011-12-19 18:00:00','2011-12-19 20:00:00','2011-11-29 08:21:34','Weekly booking'),(182,44,3,'2011-11-29 20:00:00','2011-11-30 00:00:00','2011-11-29 08:21:58','Afternoon Canada booking'),(183,45,1,'2011-11-29 14:00:00','2011-11-29 18:00:00','2011-11-29 08:22:58','Morning practise'),(184,45,1,'2011-12-30 14:00:00','2011-12-30 18:00:00','2011-11-29 08:23:15','Morning practise'),(186,46,2,'2011-11-29 18:00:00','2011-11-29 21:00:00','2011-11-29 08:57:12','first meeting trial'),(189,12,7,'2011-11-30 22:00:00','2011-12-01 01:00:00','2011-11-29 09:14:18','***MAINTENANCE***'),(190,12,3,'2011-11-14 14:00:00','2011-11-14 15:00:00','2011-11-29 09:39:49','***MAINTENANCE***');
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1 COMMENT='Stores clubs';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (12,'admin','password','dsf','dsfsd','sdfds','dsfds','sdfds','sdfs',1,1,0,0),(42,'Jordan Jumpers','pass','123 Fake St','Victoria','BC','V8T 6J1','jordan@me.org','1-250-555-1234',0,1,1,0),(43,'Running Wild','pass','46 Marquis St.','Victoria','BC','S4S 5J9','manager@runningwild.com','1-250-555-9876',0,1,1,0),(44,'Team Canada','pass','7934 Young St.','Toronto','ON','O9F 3G6','canada@gov.org','1-718-555-3745',0,1,1,800),(45,'Speedsters','pass','45 Douglas St.','Victoria','BC','V9F 4R6','speed@hotmail.com','1-250-555-3859',0,0,1,200),(46,'iRun','pass','1400 Hillside avenue','Victoria','BC','V2T 2HV','iRun@uvc.com','1-250-555-4521',0,1,1,200),(47,'Team Braden','something','1337 Hillside Ave.','Victoria','BC','V8T 2H2','braden@run.ca','250-323-2952',0,1,1,800);
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
INSERT INTO `notifications` VALUES (40,'TrackSys Online','TrackSys is now online and usable by all teams!','2011-11-29 08:05:41'),(41,'Fee Payment','Make sure to check your payment tab at the start of each month!','2011-11-29 08:18:46'),(42,'Tool Tip','You can view which tracks are booked for which time slots in the daily view tab!','2011-11-29 08:19:16'),(43,'Maintenance!','Don\'t forget that Track 3 is undergoing maintenance on Nov 14, 2011 from 6AM to 7AM.  Sorry for the inconvenience.','2011-11-29 10:32:26');
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
INSERT INTO `tracks` VALUES (1,1),(2,1),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (45,42,250,'2011-11-29 08:03:51','Paying half our fee.'),(46,42,250,'2011-11-29 08:04:13','Paying remaining half.'),(47,42,200,'2011-11-29 08:29:07','Paying off fee.'),(48,48,100,'2011-11-29 08:58:28','Paying'),(49,48,-100,'2011-11-29 08:58:54','Paying again');
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

-- Dump completed on 2011-11-29  2:38:29
