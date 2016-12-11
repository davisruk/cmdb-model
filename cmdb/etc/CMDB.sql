-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: centd643512w5q    Database: cmdbnode
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `cm_component`
--

DROP TABLE IF EXISTS `cm_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_component` (
  `ComponentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ComponentName` varchar(50) NOT NULL,
  `PackageID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ComponentID`),
  KEY `IXFK_Component_CM_Package` (`PackageID`),
  CONSTRAINT `cm_component_ibfk_1` FOREIGN KEY (`PackageID`) REFERENCES `cm_package` (`PackageID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_component`
--

LOCK TABLES `cm_component` WRITE;
/*!40000 ALTER TABLE `cm_component` DISABLE KEYS */;
INSERT INTO `cm_component` VALUES (1,'Comp 1 - Bridge - 7.3.4 Package',2),(2,'Comp 2 - Bridge - 7.3.4 Package',2),(3,'Comp 1 - EAS - 7.3.4 Package',3),(4,'Comp 2 - EAS - 7.3.4 Package',3),(5,'Comp 1 - UIS - 7.3.4 Package',4),(6,'Comp 2 - UIS - 7.3.4 Package',4),(7,'Comp 1 - Bridge - 7.4.0 Package',5),(8,'Comp 2 - Bridge - 7.4.0 Package',5),(9,'Comp 1 - EAS - 7.4.0 Package',6),(10,'Comp 2 - EAS - 7.4.0 Package',6),(11,'Comp 1 - UIS - 7.4.0 Package',7),(12,'Comp 2 - UIS - 7.4.0 Package',7),(13,'Windows 2012',8),(14,'IIS',9),(15,'Oracle (Win)',10),(16,'RESIP DB',11);
/*!40000 ALTER TABLE `cm_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_componentconfig`
--

DROP TABLE IF EXISTS `cm_componentconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_componentconfig` (
  `CompConfigID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ComponentID` bigint(20) NOT NULL,
  `CompConfigParameter` varchar(50) DEFAULT NULL,
  `CompConfigValue` varchar(50) DEFAULT NULL,
  `CompConfigHieraAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CompConfigID`),
  KEY `IXFK_ComponentConfig_Component` (`ComponentID`),
  CONSTRAINT `cm_componentconfig_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `cm_component` (`ComponentID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_componentconfig`
--

LOCK TABLES `cm_componentconfig` WRITE;
/*!40000 ALTER TABLE `cm_componentconfig` DISABLE KEYS */;
INSERT INTO `cm_componentconfig` VALUES (1,1,'Param 1 - Comp 1 - Bridge - 7.3.4 Package','C1Param1Value','ROOT:{Release}:{ServType}:{AppName}:SettingA'),(2,1,'Param 2 - Comp 1 - Bridge - 7.3.4 Package','C1Param2Value','ROOT:{Release}:{ServType}:{AppName}:SettingB'),(3,2,'Param 1 - Comp 2 - Bridge - 7.3.4 Package','C2Param1Value','ROOT:{Release}:{ServType}:SettingC'),(4,2,'Param 2 - Comp 2 - Bridge - 7.3.4 Package','C2Param2Value','ROOT:{Release}:{ServType}:SettingD'),(5,3,'Param 1 - Comp 1 - EAS - 7.3.4 Package','C3Param1Value','ROOT:{Release}:{ServType}:SettingA'),(6,3,'Param 2 - Comp 1 - EAS - 7.3.4 Package','C3Param2Value','ROOT:{Release}:{ServType}:SettingB'),(7,4,'Param 1 - Comp 2 - EAS - 7.3.4 Package','C4Param1Value','ROOT:{Release}:{ServType}:SettingC'),(8,4,'Param 2 - Comp 2 - EAS - 7.3.4 Package','C4Param2Value','ROOT:{Release}:{ServType}:SettingD'),(9,5,'Param 1 - Comp 1 - UIS - 7.3.4 Package','C5Param1Value','ROOT:{Release}:{ServType}:{AppName}:SettingA'),(10,5,'Param 2 - Comp 1 - UIS - 7.3.4 Package','C5Param2Value','ROOT:{Release}:{ServType}:SettingB'),(11,6,'Param 1  -  Comp 2 - UIS - 7.3.4 Package','C6Param1Value','ROOT:{Release}:{ServType}:{AppName}:SettingC'),(12,6,'Param 2 - Comp 2 - UIS - 7.3.4 Package','C6Param2Value','ROOT:{Release}:{ServType}:SettingD'),(13,7,'Param 1 - Comp 1 - Bridge - 7.4.0 Package','C7Param1Value','ROOT:{Release}:{ServType}:SettingA'),(14,7,'Param 2 - Comp 1 - Bridge - 7.4.0 Package','C7Param2Value','ROOT:{Release}:{ServType}:SettingB'),(15,8,'Param 1 - Comp 2 - Bridge - 7.4.0 Package','C8Param1Value','ROOT:{Release}:{ServType}:{AppName}:SettingC'),(16,8,'Param 2 - Comp 2 - Bridge - 7.4.0 Package','C8Param2Value','ROOT:{Release}:{ServType}:SettingD'),(17,9,'Param 1 - Comp 1 - EAS - 7.4.0 Package','C9Param1Value','ROOT:{Release}:{ServType}:SettingA'),(18,9,'Param 2 - Comp 1 - EAS - 7.4.0 Package','C9Param2Value','ROOT:{Release}:{ServType}:SettingB'),(19,10,'Param 1 - Comp 2 - EAS - 7.4.0 Package','C10Param1Value','ROOT:{Release}:{ServType}:SettingC'),(20,10,'Param 2 - Comp 2 - EAS - 7.4.0 Package','C10Param2Value','ROOT:{Release}:{ServType}:SettingD'),(21,11,'Param 1 - Comp 1 - UIS - 7.4.0 Package','C11Param1Value','ROOT:{Release}:{ServType}:SettingA'),(22,11,'Param 2 - Comp 1 - UIS - 7.4.0 Package','C11Param2Value','ROOT:{Release}:{ServType}:SettingB'),(23,12,'Param 1 - Comp 2 - UIS - 7.4.0 Package','C12Param1Value','ROOT:{Release}:{ServType}:SettingC'),(24,12,'Param 2 - Comp 2 - UIS - 7.4.0 Package','C12Param2Value','ROOT:{Release}:{ServType}:SettingD');
/*!40000 ALTER TABLE `cm_componentconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_environment`
--

DROP TABLE IF EXISTS `cm_environment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_environment` (
  `EnvironmentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EnvironmentName` varchar(50) NOT NULL,
  `EnvironmentTypeID` bigint(20) NOT NULL,
  PRIMARY KEY (`EnvironmentID`),
  UNIQUE KEY `UQ_CM_Environment_EnvironmentName` (`EnvironmentName`),
  KEY `EnvironmentTypeID` (`EnvironmentTypeID`),
  CONSTRAINT `cm_environment_ibfk_1` FOREIGN KEY (`EnvironmentTypeID`) REFERENCES `cm_environmenttype` (`EnvironmentTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_environment`
--

LOCK TABLES `cm_environment` WRITE;
/*!40000 ALTER TABLE `cm_environment` DISABLE KEYS */;
INSERT INTO `cm_environment` VALUES (11,'FT1',1),(12,'FT2',1),(13,'FT3',1),(14,'FT4',1),(15,'FT5',1),(16,'IT1',2),(17,'IT2',2),(18,'IT3',2),(19,'IT4',2),(20,'IT5',2),(21,'ST1',4),(22,'TR1',5),(23,'TR2',5),(24,'UT1',6),(25,'UT2',6),(26,'PT1',4),(27,'PT2',4),(28,'PS1',7);
/*!40000 ALTER TABLE `cm_environment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_environmenttype`
--

DROP TABLE IF EXISTS `cm_environmenttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_environmenttype` (
  `EnvironmentTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EnvironmentTypeName` varchar(50) NOT NULL,
  PRIMARY KEY (`EnvironmentTypeID`),
  UNIQUE KEY `UQ_CM_EnvironmentType_EnvironmentTypeName` (`EnvironmentTypeName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_environmenttype`
--

LOCK TABLES `cm_environmenttype` WRITE;
/*!40000 ALTER TABLE `cm_environmenttype` DISABLE KEYS */;
INSERT INTO `cm_environmenttype` VALUES (1,'Functional Test'),(2,'Integration Test'),(4,'Performance Testing'),(7,'Prod Support'),(3,'Production'),(5,'Training'),(6,'User Acceptance');
/*!40000 ALTER TABLE `cm_environmenttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_globalconfig`
--

DROP TABLE IF EXISTS `cm_globalconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_globalconfig` (
  `GlobalConfigID` bigint(20) NOT NULL AUTO_INCREMENT,
  `GlobalConfigParameter` varchar(50) NOT NULL,
  `GlobalConfigValue` varchar(255) DEFAULT NULL,
  `GlobalConfigHieraAddress` varchar(255) DEFAULT NULL,
  `RecursiveByEnv` bit(1) NOT NULL DEFAULT b'0',
  `RecursiveByRel` bit(1) NOT NULL DEFAULT b'0',
  `RecursiveBySubEnv` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`GlobalConfigID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_globalconfig`
--

LOCK TABLES `cm_globalconfig` WRITE;
/*!40000 ALTER TABLE `cm_globalconfig` DISABLE KEYS */;
INSERT INTO `cm_globalconfig` VALUES (4,'root_pass','removed','ROOT:{ParamName}:','\0','\0','\0'),(5,'specpath','/root/spec','ROOT:{ParamName}:','\0','\0','\0'),(6,'repo_name','rhel-6.4-x86_64-puppet','ROOT:{ParamName}:-','\0','\0','\0'),(7,'repo_name','columbus-uk-boots','ROOT:{ParamName}:-','\0','\0','\0'),(8,'oneleonardo_jdk_release','1.6.0_37','ROOT:{ParamName}:','\0','\0','\0'),(9,'oneleonardo_jboss_release','7.1.1.Final','ROOT:{ParamName}:','\0','\0','\0'),(10,'oneleonardo_jboss_build','5HS.redhat6.4','ROOT:{ParamName}:','\0','\0','\0'),(11,'oracle_client_version','11.2.0.3','ROOT:{ParamName}:','\0','\0','\0'),(12,'httpuser','deployer','ROOT:columbus_delivery:{ParamName}:','\0','\0','\0'),(13,'httppass','removed','ROOT:columbus_delivery:{ParamName}:','\0','\0','\0'),(14,'httpserver','delivery.na-dc.ah.ab:8081','ROOT:columbus_delivery:{ParamName}:','\0','\0','\0'),(19,'httpurl','artifactory/libs-releases-local/com/ab/oneleo/columbus','ROOT:columbus_delivery:{ParamName}:','\0','\0','\0'),(20,'gid','1000','ROOT:group_tagbt:{ParamName}:','\0','\0','\0'),(21,'gid','1000','ROOT:group_tagbs:{ParamName}:','\0','\0','\0'),(22,'uid','1001','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(23,'gid','pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(24,'groups','pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(25,'home','/home/pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(26,'password','removed','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(27,'nofiles','10000','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(28,'jdk_release','1.6.0_37','ROOT:user_pmsgbrtt:{ParamName}:','\0','\0','\0'),(29,'devel','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:','\0','\0','\0'),(30,'boots_support','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:','\0','\0','\0'),(31,'boots_performance_test','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:','\0','\0','\0'),(32,'gid','1001','ROOT:group_pmsgbrtt:{ParamName}:','\0','\0','\0'),(33,'uid','4001','ROOT:user_pmsgbmtt:{ParamName}:','\0','\0','\0'),(34,'gid','pmsgbrtt','ROOT:user_pmsgbmtt:{ParamName}:','\0','\0','\0'),(35,'groups','pmsgbrtt','ROOT:user_pmsgbmtt:{ParamName}:','\0','\0','\0'),(36,'home','/home/pmsgbmtt','ROOT:user_pmsgbmtt:{ParamName}:','\0','\0','\0'),(37,'password','removed','ROOT:user_pmsgbmtt:{ParamName}:','\0','\0','\0'),(38,'boots_support','true','ROOT:user_pmsgbmtt:authorized_keys:{ParamName}:','\0','\0','\0'),(39,'boots_performance_test','true','ROOT:user_pmsgbmtt:authorized_keys:{ParamName}:','\0','\0','\0'),(40,'user','tagbtadm','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(41,'group','tagbt','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(42,'vg','rootvg','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(43,'size_envs','1G','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(44,'size_envf','1G','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(45,'size_envb','1G','ROOT:batchclient_test_info:{ParamName}','\0','\0','\0'),(46,'user','tagbsadm','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(47,'group','tagbs','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(48,'vg','rootvg','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(49,'size_envs','1G','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(50,'size_envf','1G','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(51,'size_envb','1G','ROOT:batchclient_stag_info:{ParamName}','\0','\0','\0'),(52,'build_version','4.2.2','ROOT:oneleo_release_map:columbus:uk-boots:{ParamName}','\0','\0','\0'),(70,'columbus','jobs/scripts/columbus','ROOT:projects_path:{ParamName}','\0','\0','\0'),(71,'optimus','jobs/scripts/optimus','ROOT:projects_path:{ParamName}','\0','\0','\0');
/*!40000 ALTER TABLE `cm_globalconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_node`
--

DROP TABLE IF EXISTS `cm_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_node` (
  `NodeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NodeType` enum('Server','Router','Switch','Load Balancer','VIP','TBC') NOT NULL,
  PRIMARY KEY (`NodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_node`
--

LOCK TABLES `cm_node` WRITE;
/*!40000 ALTER TABLE `cm_node` DISABLE KEYS */;
INSERT INTO `cm_node` VALUES (1,'Server'),(2,'Server'),(3,'Server'),(4,'Server'),(5,'Server'),(6,'Server'),(7,'Server'),(8,'Server'),(9,'Server'),(10,'Server'),(11,'Server'),(12,'Server'),(13,'Server'),(14,'Server'),(15,'Server'),(16,'Server'),(17,'Server'),(18,'Server'),(19,'Server'),(20,'Server'),(21,'Server'),(22,'Server'),(23,'Server'),(24,'Server'),(25,'Server'),(28,'Server'),(29,'Server'),(30,'Server'),(31,'Server'),(32,'Server'),(33,'Server'),(34,'Server'),(35,'Server'),(36,'Server'),(37,'Server'),(38,'Server'),(39,'Server'),(40,'Server'),(41,'Server'),(42,'Server'),(43,'Server'),(44,'Server'),(45,'Server'),(46,'Server'),(47,'Server'),(48,'Server'),(49,'Server'),(50,'Server'),(51,'Server'),(52,'Server'),(53,'Server'),(54,'Server'),(55,'Server'),(56,'Server'),(57,'Server'),(58,'Server'),(59,'Server'),(60,'Server'),(61,'Server'),(62,'Server'),(63,'Server'),(64,'Server'),(65,'Server'),(66,'Server'),(67,'Server'),(68,'Server'),(69,'Server'),(70,'Server'),(71,'Server'),(72,'Server'),(73,'Server'),(74,'Server'),(75,'Server'),(76,'Server'),(77,'Server'),(78,'Server'),(79,'Server'),(80,'Server'),(81,'Server'),(82,'Server'),(83,'Server'),(84,'Server'),(85,'Server'),(86,'Server'),(87,'Server'),(88,'Server'),(89,'Server'),(90,'Server'),(91,'Server'),(92,'Server'),(93,'Server'),(94,'Server'),(95,'Server'),(96,'Server'),(97,'Server'),(98,'Server'),(99,'Server'),(100,'Server'),(101,'Server'),(102,'Server'),(103,'Server'),(104,'Server'),(105,'Server'),(106,'Server'),(107,'Server'),(108,'Server'),(109,'Server'),(110,'Server'),(111,'Server'),(112,'Server'),(113,'Server'),(114,'Server'),(115,'Server'),(116,'Server'),(117,'Server'),(118,'Server'),(119,'Server'),(120,'Server'),(121,'Server'),(122,'Server'),(123,'Server'),(124,'Server'),(125,'Server'),(126,'Server'),(127,'Server'),(128,'Server'),(129,'Server'),(130,'Server'),(131,'Server'),(132,'Server'),(133,'Server'),(134,'VIP'),(135,'VIP'),(136,'VIP'),(137,'Server'),(138,'Server'),(139,'Server');
/*!40000 ALTER TABLE `cm_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_node_subenvironment`
--

DROP TABLE IF EXISTS `cm_node_subenvironment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_node_subenvironment` (
  `NodeSubID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NodeID` bigint(20) NOT NULL,
  `SubEnvironmentID` bigint(20) NOT NULL,
  PRIMARY KEY (`NodeSubID`),
  KEY `NodeID` (`NodeID`),
  KEY `SubEnvironmentID` (`SubEnvironmentID`),
  CONSTRAINT `node_subenvironment_ibfk_1` FOREIGN KEY (`NodeID`) REFERENCES `cm_node` (`NodeID`),
  CONSTRAINT `node_subenvironment_ibfk_2` FOREIGN KEY (`SubEnvironmentID`) REFERENCES `cm_subenvironment` (`SubEnvironmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_node_subenvironment`
--

LOCK TABLES `cm_node_subenvironment` WRITE;
/*!40000 ALTER TABLE `cm_node_subenvironment` DISABLE KEYS */;
INSERT INTO `cm_node_subenvironment` VALUES (1,36,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1),(13,13,1),(14,14,1),(15,19,2),(16,20,2),(17,21,2),(18,22,2),(19,23,2),(20,24,2),(21,25,2),(22,36,2),(23,12,2),(24,13,2),(25,14,2),(28,28,5),(29,29,6),(30,30,5),(31,31,6),(32,33,5),(33,34,6),(34,35,7),(35,36,7),(36,37,7),(37,38,7),(38,39,7),(39,40,7),(40,41,7),(41,42,7),(42,43,7),(43,44,7),(44,12,7),(45,13,7),(46,14,7),(47,45,8),(48,46,8),(49,36,8),(50,47,8),(51,48,8),(52,49,8),(53,50,8),(54,51,8),(55,52,8),(56,12,8),(57,13,8),(58,14,8),(59,53,9),(60,54,9),(61,36,9),(62,55,9),(63,56,9),(64,57,9),(65,58,9),(66,59,9),(67,12,9),(68,13,9),(69,14,9),(70,36,10),(71,60,10),(72,61,10),(73,62,10),(74,63,10),(75,64,10),(76,65,10),(77,66,10),(78,67,10),(79,68,10),(80,69,10),(81,12,10),(82,13,10),(83,14,10),(84,36,11),(86,74,11),(87,75,11),(88,76,11),(89,77,11),(90,78,11),(91,12,11),(92,13,11),(93,14,11),(94,79,20),(95,80,20),(96,36,12),(97,81,12),(98,82,12),(99,83,12),(100,84,12),(101,85,12),(102,12,12),(103,13,12),(104,14,12),(105,86,13),(106,87,13),(107,36,13),(108,88,13),(109,89,13),(110,90,13),(111,91,13),(112,92,13),(113,12,13),(114,13,13),(115,14,13),(116,93,14),(117,94,14),(118,36,14),(119,95,14),(120,96,14),(121,97,14),(122,98,14),(123,99,14),(124,12,14),(125,13,14),(126,14,14),(127,100,15),(128,101,15),(129,36,15),(131,102,16),(132,103,16),(133,36,16),(134,104,16),(135,105,16),(136,106,16),(137,107,16),(138,108,16),(139,12,16),(140,13,16),(141,14,16),(142,109,17),(143,110,17),(144,36,17),(145,111,17),(146,112,17),(147,113,17),(148,114,17),(149,115,17),(150,12,17),(151,13,17),(152,14,17),(153,1,1),(154,1,2),(155,1,8),(156,1,9),(157,1,10),(158,1,11),(159,1,12),(160,1,13),(161,1,14),(162,1,15),(163,1,16),(164,1,17),(165,1,7),(166,32,6),(167,32,5),(168,116,7),(169,116,8),(170,116,9),(171,116,10),(172,116,11),(173,116,12),(174,116,2),(175,116,13),(176,116,14),(177,116,15),(178,116,16),(179,116,17),(180,116,1),(181,117,7),(182,117,8),(183,117,9),(184,117,10),(185,117,11),(186,117,12),(187,117,2),(188,117,13),(189,117,14),(190,117,15),(191,117,16),(192,117,17),(193,117,1),(194,118,7),(195,119,7),(196,120,7),(197,120,8),(198,118,8),(199,119,8),(200,120,9),(201,118,9),(202,119,9),(203,120,10),(204,118,10),(205,119,10),(206,120,11),(207,118,11),(208,119,11),(209,120,12),(210,118,12),(211,119,12),(212,121,21),(213,122,21),(214,123,2),(215,120,2),(216,118,2),(217,119,2),(218,120,13),(219,118,13),(220,119,13),(221,120,14),(222,118,14),(223,119,14),(224,120,16),(225,118,16),(226,119,16),(227,120,17),(228,118,17),(229,119,17),(230,120,1),(231,118,1),(232,119,1),(233,124,7),(234,125,8),(235,126,9),(236,127,12),(237,128,2),(238,129,13),(239,130,14),(240,131,16),(241,132,17),(242,133,1),(243,72,18),(245,70,18),(246,71,19),(247,134,7),(248,135,7),(249,136,7),(250,134,8),(251,135,8),(252,136,8),(253,134,9),(254,135,9),(255,136,9),(256,134,10),(257,135,10),(258,136,10),(259,134,18),(260,135,18),(261,134,19),(262,135,19),(263,136,11),(264,134,20),(265,135,20),(266,134,21),(267,135,21),(268,136,12),(269,136,2),(270,135,2),(271,134,2),(272,134,13),(273,135,13),(274,136,13),(275,134,14),(276,135,14),(277,136,14),(278,134,15),(279,135,15),(280,136,15),(281,137,15),(282,134,16),(283,135,16),(284,136,16),(285,134,17),(286,135,17),(287,136,17),(288,134,1),(289,135,1),(290,136,1),(291,138,5),(293,139,6),(295,73,19);
/*!40000 ALTER TABLE `cm_node_subenvironment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_nodeip`
--

DROP TABLE IF EXISTS `cm_nodeip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_nodeip` (
  `NodeIPID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NodeSubID` bigint(20) DEFAULT NULL,
  `NodeIPAddress` varchar(50) NOT NULL,
  `NodeIPType` enum('VIRTUAL','PHYSICAL','WIRED') NOT NULL,
  PRIMARY KEY (`NodeIPID`),
  UNIQUE KEY `UQ_NodeIP_NodeSubIPAddress` (`NodeIPAddress`,`NodeSubID`),
  KEY `NodeSubID` (`NodeSubID`),
  CONSTRAINT `cm_nodeip_ibfk_2` FOREIGN KEY (`NodeSubID`) REFERENCES `cm_node_subenvironment` (`NodeSubID`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_nodeip`
--

LOCK TABLES `cm_nodeip` WRITE;
/*!40000 ALTER TABLE `cm_nodeip` DISABLE KEYS */;
INSERT INTO `cm_nodeip` VALUES (9,247,'10.141.129.247','VIRTUAL'),(10,248,'10.141.129.248','VIRTUAL'),(11,249,'10.141.129.249','VIRTUAL'),(12,250,'10.141.129.247','VIRTUAL'),(13,251,'10.141.129.248','VIRTUAL'),(14,252,'10.141.129.249','VIRTUAL'),(15,253,'10.141.129.247','VIRTUAL'),(16,254,'10.141.129.248','VIRTUAL'),(17,255,'10.141.129.249','VIRTUAL'),(18,256,'10.141.129.247','VIRTUAL'),(19,257,'10.141.129.248','VIRTUAL'),(20,258,'10.141.129.249','VIRTUAL'),(21,259,'10.141.129.247','VIRTUAL'),(22,261,'10.141.129.247','VIRTUAL'),(23,260,'10.141.129.248','VIRTUAL'),(24,262,'10.141.129.248','VIRTUAL'),(25,263,'10.141.129.249','VIRTUAL'),(26,165,'10.141.129.1','PHYSICAL'),(27,34,'10.141.129.3','PHYSICAL'),(28,36,'10.141.129.95','PHYSICAL'),(29,37,'10.141.129.10','PHYSICAL'),(30,38,'10.141.129.12','PHYSICAL'),(31,39,'10.141.129.13','PHYSICAL'),(32,168,'10.141.129.75','PHYSICAL'),(33,181,'10.141.129.70','PHYSICAL'),(34,43,'10.141.129.80','PHYSICAL'),(35,44,'10.141.129.84','PHYSICAL'),(37,40,'10.141.129.85','PHYSICAL'),(38,41,'10.141.129.241','PHYSICAL'),(39,42,'10.141.129.94','PHYSICAL'),(40,194,'10.141.129.227','PHYSICAL'),(41,195,'10.141.129.228','PHYSICAL'),(42,233,'10.141.129.39','PHYSICAL'),(43,45,'10.141.129.14','PHYSICAL'),(44,196,'10.141.129.16','PHYSICAL'),(45,46,'10.141.129.15','PHYSICAL'),(46,35,'10.141.129.251','PHYSICAL'),(47,155,'10.141.129.1','PHYSICAL'),(48,47,'10.141.129.4','PHYSICAL'),(49,48,'10.141.129.96','PHYSICAL'),(50,50,'10.141.129.19','PHYSICAL'),(51,51,'10.141.129.38','PHYSICAL'),(52,169,'10.141.129.75','PHYSICAL'),(53,182,'10.141.129.70','PHYSICAL'),(54,55,'10.141.129.81','PHYSICAL'),(55,56,'10.141.129.84','PHYSICAL'),(56,52,'10.141.129.86','PHYSICAL'),(57,53,'10.141.129.91','PHYSICAL'),(58,54,'10.141.129.77','PHYSICAL'),(59,198,'10.141.129.227','PHYSICAL'),(60,199,'10.141.129.228','PHYSICAL'),(61,234,'10.141.129.40','PHYSICAL'),(62,57,'10.141.129.14','PHYSICAL'),(63,197,'10.141.129.16','PHYSICAL'),(64,58,'10.141.129.15','PHYSICAL'),(65,49,'10.141.129.251','PHYSICAL'),(66,156,'10.141.129.1','PHYSICAL'),(67,59,'10.141.129.7','PHYSICAL'),(68,60,'10.141.129.8','PHYSICAL'),(69,62,'10.141.129.9','PHYSICAL'),(70,170,'10.141.129.75','PHYSICAL'),(71,183,'10.141.129.70','PHYSICAL'),(72,66,'10.141.129.20','PHYSICAL'),(73,67,'10.141.129.84','PHYSICAL'),(74,63,'10.141.129.21','PHYSICAL'),(75,64,'10.141.129.22','PHYSICAL'),(76,65,'10.141.129.23','PHYSICAL'),(77,201,'10.141.129.227','PHYSICAL'),(78,202,'10.141.129.228','PHYSICAL'),(79,235,'10.141.129.41','PHYSICAL'),(80,68,'10.141.129.14','PHYSICAL'),(81,200,'10.141.129.16','PHYSICAL'),(82,69,'10.141.129.15','PHYSICAL'),(83,61,'10.141.129.251','PHYSICAL'),(84,157,'10.141.129.1','PHYSICAL'),(85,71,'10.141.129.63','PHYSICAL'),(86,72,'10.141.129.64','PHYSICAL'),(87,73,'10.141.129.65','PHYSICAL'),(88,74,'10.141.129.66','PHYSICAL'),(89,75,'10.141.129.67','PHYSICAL'),(90,76,'10.141.129.68','PHYSICAL'),(91,171,'10.141.129.75','PHYSICAL'),(92,184,'10.141.129.70','PHYSICAL'),(93,80,'10.141.129.69','PHYSICAL'),(94,81,'10.141.129.84','PHYSICAL'),(95,77,'10.141.129.101','PHYSICAL'),(96,78,'10.141.129.102','PHYSICAL'),(97,79,'10.141.129.103','PHYSICAL'),(98,204,'10.141.129.227','PHYSICAL'),(99,205,'10.141.129.228','PHYSICAL'),(100,82,'10.141.129.14','PHYSICAL'),(101,203,'10.141.129.16','PHYSICAL'),(102,83,'10.141.129.15','PHYSICAL'),(103,70,'10.141.129.251','PHYSICAL'),(104,245,'10.141.129.104','PHYSICAL'),(105,243,'10.141.129.107','PHYSICAL'),(106,246,'10.141.129.106','PHYSICAL'),(108,158,'10.141.129.1','PHYSICAL'),(109,86,'10.141.129.112','PHYSICAL'),(110,172,'10.141.129.75','PHYSICAL'),(111,185,'10.141.129.70','PHYSICAL'),(112,90,'10.141.129.121','PHYSICAL'),(113,91,'10.141.129.84','PHYSICAL'),(114,87,'10.141.129.122','PHYSICAL'),(115,88,'10.141.129.123','PHYSICAL'),(116,89,'10.141.129.124','PHYSICAL'),(117,207,'10.141.129.227','PHYSICAL'),(118,208,'10.141.129.228','PHYSICAL'),(119,92,'10.141.129.14','PHYSICAL'),(120,206,'10.141.129.16','PHYSICAL'),(121,93,'10.141.129.15','PHYSICAL'),(122,84,'10.141.129.251','PHYSICAL'),(124,264,'10.141.129.247','VIRTUAL'),(125,265,'10.141.129.248','VIRTUAL'),(126,266,'10.141.129.247','VIRTUAL'),(127,267,'10.141.129.248','VIRTUAL'),(128,268,'10.141.129.249','VIRTUAL'),(130,94,'10.141.129.5','PHYSICAL'),(131,95,'10.141.129.97','PHYSICAL'),(132,212,'10.141.129.48','PHYSICAL'),(133,213,'10.141.129.49','PHYSICAL'),(134,159,'10.141.129.1','PHYSICAL'),(135,97,'10.141.129.17','PHYSICAL'),(136,173,'10.141.129.75','PHYSICAL'),(137,186,'10.141.129.70','PHYSICAL'),(138,101,'10.141.129.82','PHYSICAL'),(139,102,'10.141.129.84','PHYSICAL'),(140,98,'10.141.129.87','PHYSICAL'),(141,99,'10.141.129.92','PHYSICAL'),(142,100,'10.141.129.78','PHYSICAL'),(143,210,'10.141.129.227','PHYSICAL'),(144,211,'10.141.129.228','PHYSICAL'),(145,236,'10.141.129.42','PHYSICAL'),(146,103,'10.141.129.14','PHYSICAL'),(147,209,'10.141.129.16','PHYSICAL'),(148,104,'10.141.129.15','PHYSICAL'),(149,96,'10.141.129.251','PHYSICAL'),(150,271,'10.141.129.247','VIRTUAL'),(151,270,'10.141.129.248','VIRTUAL'),(152,269,'10.141.129.249','VIRTUAL'),(153,154,'10.141.129.1','PHYSICAL'),(154,15,'10.141.129.6','PHYSICAL'),(155,16,'10.141.129.98','PHYSICAL'),(156,17,'10.141.129.18','PHYSICAL'),(157,214,'10.141.129.51','PHYSICAL'),(158,174,'10.141.129.75','PHYSICAL'),(159,187,'10.141.129.70','PHYSICAL'),(160,21,'10.141.129.83','PHYSICAL'),(161,23,'10.141.129.84','PHYSICAL'),(162,18,'10.141.129.88','PHYSICAL'),(163,19,'10.141.129.93','PHYSICAL'),(164,20,'10.141.129.79','PHYSICAL'),(165,216,'10.141.129.227','PHYSICAL'),(166,217,'10.141.129.228','PHYSICAL'),(167,237,'10.141.129.43','PHYSICAL'),(168,24,'10.141.129.14','PHYSICAL'),(169,215,'10.141.129.16','PHYSICAL'),(170,25,'10.141.129.15','PHYSICAL'),(171,22,'10.141.129.251','PHYSICAL'),(172,272,'10.141.129.247','VIRTUAL'),(173,273,'10.141.129.248','VIRTUAL'),(174,274,'10.141.129.249','VIRTUAL'),(175,160,'10.141.129.1','PHYSICAL'),(176,105,'10.141.129.110','PHYSICAL'),(177,106,'10.141.129.111','PHYSICAL'),(178,108,'10.141.129.50','PHYSICAL'),(179,175,'10.141.129.75','PHYSICAL'),(180,188,'10.141.129.70','PHYSICAL'),(181,112,'10.141.129.113','PHYSICAL'),(182,113,'10.141.129.84','PHYSICAL'),(183,109,'10.141.129.99','PHYSICAL'),(184,110,'10.141.129.100','PHYSICAL'),(185,111,'10.141.129.90','PHYSICAL'),(186,219,'10.141.129.227','PHYSICAL'),(187,220,'10.141.129.228','PHYSICAL'),(188,238,'10.141.129.44','PHYSICAL'),(189,114,'10.141.129.14','PHYSICAL'),(190,218,'10.141.129.16','PHYSICAL'),(191,115,'10.141.129.15','PHYSICAL'),(192,107,'10.141.129.251','PHYSICAL'),(193,275,'10.141.129.247','VIRTUAL'),(194,276,'10.141.129.248','VIRTUAL'),(195,277,'10.141.129.249','VIRTUAL'),(197,161,'10.141.129.1','PHYSICAL'),(198,116,'10.141.129.24','PHYSICAL'),(199,117,'10.141.129.25','PHYSICAL'),(200,119,'10.141.129.26','PHYSICAL'),(201,176,'10.141.129.75','PHYSICAL'),(202,189,'10.141.129.70','PHYSICAL'),(203,123,'10.141.129.27','PHYSICAL'),(204,124,'10.141.129.84','PHYSICAL'),(205,120,'10.141.129.28','PHYSICAL'),(206,121,'10.141.129.29','PHYSICAL'),(207,122,'10.141.129.30','PHYSICAL'),(208,222,'10.141.129.227','PHYSICAL'),(209,223,'10.141.129.228','PHYSICAL'),(210,239,'10.141.129.45','PHYSICAL'),(211,125,'10.141.129.14','PHYSICAL'),(212,221,'10.141.129.16','PHYSICAL'),(213,126,'10.141.129.15','PHYSICAL'),(214,118,'10.141.129.251','PHYSICAL'),(215,278,'10.141.129.247','VIRTUAL'),(216,279,'10.141.129.248','VIRTUAL'),(217,280,'10.141.129.249','VIRTUAL'),(218,162,'10.141.129.1','PHYSICAL'),(219,127,'10.141.129.125','PHYSICAL'),(220,128,'10.141.129.126','PHYSICAL'),(221,177,'10.141.129.75','PHYSICAL'),(222,190,'10.141.129.70','PHYSICAL'),(223,281,'10.141.129.127','PHYSICAL'),(224,129,'10.141.129.251','PHYSICAL'),(225,282,'10.141.129.247','VIRTUAL'),(226,283,'10.141.129.248','VIRTUAL'),(227,284,'10.141.129.249','VIRTUAL'),(228,163,'10.141.129.1','PHYSICAL'),(229,131,'10.141.129.114','PHYSICAL'),(230,132,'10.141.129.115','PHYSICAL'),(231,134,'10.141.129.116','PHYSICAL'),(232,178,'10.141.129.75','PHYSICAL'),(233,191,'10.141.129.70','PHYSICAL'),(234,138,'10.141.129.117','PHYSICAL'),(235,139,'10.141.129.84','PHYSICAL'),(236,135,'10.141.129.118','PHYSICAL'),(237,136,'10.141.129.119','PHYSICAL'),(238,137,'10.141.129.120','PHYSICAL'),(239,225,'10.141.129.227','PHYSICAL'),(240,226,'10.141.129.228','PHYSICAL'),(241,240,'10.141.129.46','PHYSICAL'),(242,140,'10.141.129.14','PHYSICAL'),(243,224,'10.141.129.16','PHYSICAL'),(244,141,'10.141.129.15','PHYSICAL'),(245,133,'10.141.129.251','PHYSICAL'),(246,285,'10.141.129.247','VIRTUAL'),(247,286,'10.141.129.248','VIRTUAL'),(248,287,'10.141.129.249','VIRTUAL'),(249,164,'10.141.129.1','PHYSICAL'),(250,142,'10.141.129.31','PHYSICAL'),(251,143,'10.141.129.32','PHYSICAL'),(252,145,'10.141.129.33','PHYSICAL'),(253,179,'10.141.129.75','PHYSICAL'),(254,192,'10.141.129.70','PHYSICAL'),(255,149,'10.141.129.34','PHYSICAL'),(256,150,'10.141.129.84','PHYSICAL'),(257,146,'10.141.129.35','PHYSICAL'),(258,147,'10.141.129.36','PHYSICAL'),(259,148,'10.141.129.37','PHYSICAL'),(260,228,'10.141.129.227','PHYSICAL'),(261,229,'10.141.129.228','PHYSICAL'),(262,241,'10.141.129.47','PHYSICAL'),(263,151,'10.141.129.14','PHYSICAL'),(264,227,'10.141.129.16','PHYSICAL'),(265,152,'10.141.129.15','PHYSICAL'),(266,144,'10.141.129.251','PHYSICAL'),(267,288,'10.141.129.247','VIRTUAL'),(268,289,'10.141.129.248','VIRTUAL'),(269,290,'10.141.129.249','VIRTUAL'),(270,153,'10.141.129.1','PHYSICAL'),(271,2,'10.141.129.52','PHYSICAL'),(272,3,'10.141.129.53','PHYSICAL'),(273,4,'10.141.129.54','PHYSICAL'),(274,5,'10.141.129.55','PHYSICAL'),(275,6,'10.141.129.56','PHYSICAL'),(276,7,'10.141.129.57','PHYSICAL'),(277,180,'10.141.129.75','PHYSICAL'),(278,193,'10.141.129.70','PHYSICAL'),(279,11,'10.141.129.58','PHYSICAL'),(280,12,'10.141.129.84','PHYSICAL'),(281,8,'10.141.129.59','PHYSICAL'),(282,9,'10.141.129.60','PHYSICAL'),(283,10,'10.141.129.61','PHYSICAL'),(284,231,'10.141.129.227','PHYSICAL'),(285,232,'10.141.129.228','PHYSICAL'),(286,242,'10.141.129.62','PHYSICAL'),(287,13,'10.141.129.14','PHYSICAL'),(288,230,'10.141.129.16','PHYSICAL'),(289,14,'10.141.129.15','PHYSICAL'),(290,1,'10.141.129.251','PHYSICAL'),(291,167,'10.142.193.193','PHYSICAL'),(293,291,'10.142.193.173','PHYSICAL'),(294,28,'10.142.193.161','PHYSICAL'),(295,30,'10.142.193.165','PHYSICAL'),(297,32,'10.142.193.191','PHYSICAL'),(298,166,'10.142.193.193','PHYSICAL'),(300,29,'10.142.193.177','PHYSICAL'),(301,31,'10.142.193.181','PHYSICAL'),(302,33,'10.142.193.192','PHYSICAL'),(304,293,'10.142.193.174','PHYSICAL'),(306,295,'10.141.129.109','PHYSICAL');
/*!40000 ALTER TABLE `cm_nodeip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_nodeiprelationship`
--

DROP TABLE IF EXISTS `cm_nodeiprelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_nodeiprelationship` (
  `RelationshipID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PublishingNodeIPID` bigint(20) NOT NULL,
  `ConsumingNodeIPID` bigint(20) NOT NULL,
  `StartPort` bigint(20) NOT NULL,
  `EndPort` bigint(20) NOT NULL,
  `Protocol` enum('HTTP','NTP','HTTPS','TBC') NOT NULL,
  PRIMARY KEY (`RelationshipID`),
  KEY `cm_noderelationship_ibfk_1` (`PublishingNodeIPID`),
  KEY `cm_noderelationship_ibfk_2` (`ConsumingNodeIPID`),
  CONSTRAINT `cm_nodeiprelationship_ibfk_1` FOREIGN KEY (`PublishingNodeIPID`) REFERENCES `cm_nodeip` (`NodeIPID`),
  CONSTRAINT `cm_nodeiprelationship_ibfk_2` FOREIGN KEY (`ConsumingNodeIPID`) REFERENCES `cm_nodeip` (`NodeIPID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_nodeiprelationship`
--

LOCK TABLES `cm_nodeiprelationship` WRITE;
/*!40000 ALTER TABLE `cm_nodeiprelationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `cm_nodeiprelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_noderelationship`
--

DROP TABLE IF EXISTS `cm_noderelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_noderelationship` (
  `RelationshipID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PublishingNodeID` bigint(20) NOT NULL,
  `ConsumingNodeID` bigint(20) NOT NULL,
  `StartPort` bigint(20) NOT NULL,
  `EndPort` bigint(20) NOT NULL,
  `Protocol` enum('HTTP','NTP','HTTPS','TBC') NOT NULL,
  PRIMARY KEY (`RelationshipID`),
  KEY `cm_noderelationship_ibfk_1` (`PublishingNodeID`),
  KEY `cm_noderelationship_ibfk_2` (`ConsumingNodeID`),
  CONSTRAINT `cm_noderelationship_ibfk_1` FOREIGN KEY (`PublishingNodeID`) REFERENCES `cm_node` (`NodeID`),
  CONSTRAINT `cm_noderelationship_ibfk_2` FOREIGN KEY (`ConsumingNodeID`) REFERENCES `cm_node` (`NodeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_noderelationship`
--

LOCK TABLES `cm_noderelationship` WRITE;
/*!40000 ALTER TABLE `cm_noderelationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `cm_noderelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_package`
--

DROP TABLE IF EXISTS `cm_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_package` (
  `PackageID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PackageName` varchar(50) NOT NULL,
  `PackageTypeID` bigint(20) DEFAULT NULL,
  `ServerTypeID` bigint(20) DEFAULT NULL,
  `ReleaseID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PackageID`),
  UNIQUE KEY `UQ_CM_Package_PackageName` (`PackageName`),
  KEY `IXFK_CM_Package_CM_ServerType` (`ServerTypeID`),
  KEY `IXFK_CM_Package_Releases` (`ReleaseID`),
  KEY `IXFK_CM_Package_CM_PackageType` (`PackageTypeID`),
  CONSTRAINT `cm_package_ibfk_1` FOREIGN KEY (`ReleaseID`) REFERENCES `cm_release` (`ReleaseID`),
  CONSTRAINT `cm_package_ibfk_2` FOREIGN KEY (`PackageTypeID`) REFERENCES `cm_packagetype` (`PackageTypeID`),
  CONSTRAINT `cm_package_ibfk_3` FOREIGN KEY (`ServerTypeID`) REFERENCES `cm_servertype` (`ServerTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_package`
--

LOCK TABLES `cm_package` WRITE;
/*!40000 ALTER TABLE `cm_package` DISABLE KEYS */;
INSERT INTO `cm_package` VALUES (1,'TEST PACKAGE 1',1,1,1),(2,'Bridge - 7.3.4 Package',2,3,1),(3,'EAS - 7.3.4 Package',2,4,1),(4,'UIS - 7.3.4 Package',2,14,1),(5,'Bridge - 7.4.0 Package',2,3,2),(6,'EAS - 7.4.0 Package',2,4,2),(7,'UIS - 7.4.0 Package',2,14,2),(8,'RESIP-InfraRel-x.y.z',4,10,6),(9,'RESIP-InfraPlat-x.y.z',3,10,6),(10,'RESIP-AppPlat-x.y.z',1,10,6),(11,'RESIP-AppRel-x.y.z',2,10,6);
/*!40000 ALTER TABLE `cm_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_packagetype`
--

DROP TABLE IF EXISTS `cm_packagetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_packagetype` (
  `PackageTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PackageTypeName` varchar(50) NOT NULL,
  PRIMARY KEY (`PackageTypeID`),
  UNIQUE KEY `UQ_CM_PackageType_PackageTypeName` (`PackageTypeName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_packagetype`
--

LOCK TABLES `cm_packagetype` WRITE;
/*!40000 ALTER TABLE `cm_packagetype` DISABLE KEYS */;
INSERT INTO `cm_packagetype` VALUES (1,'App Platform Release'),(2,'App Release'),(3,'Infrastructure Platform Release'),(4,'Infrastructure Release');
/*!40000 ALTER TABLE `cm_packagetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_release`
--

DROP TABLE IF EXISTS `cm_release`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_release` (
  `ReleaseID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ReleaseName` varchar(50) NOT NULL,
  PRIMARY KEY (`ReleaseID`),
  UNIQUE KEY `UQ_Releases_ReleaseName` (`ReleaseName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_release`
--

LOCK TABLES `cm_release` WRITE;
/*!40000 ALTER TABLE `cm_release` DISABLE KEYS */;
INSERT INTO `cm_release` VALUES (3,'7.3.2'),(1,'7.3.4'),(2,'7.4.0'),(8,'7.4.2'),(6,'Training-x.y.z'),(7,'UNKNOWN');
/*!40000 ALTER TABLE `cm_release` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_releaseconfig`
--

DROP TABLE IF EXISTS `cm_releaseconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_releaseconfig` (
  `RelConfigID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ReleaseID` bigint(20) NOT NULL,
  `RelConfigParameter` varchar(255) DEFAULT NULL,
  `RelConfigValue` varchar(255) DEFAULT NULL,
  `RelConfigHieraAddress` varchar(255) DEFAULT NULL,
  `RecursiveByEnv` bit(1) NOT NULL DEFAULT b'0',
  `RecursiveBySubEnv` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`RelConfigID`),
  KEY `IXFK_ReleaseConfig_Release` (`ReleaseID`),
  CONSTRAINT `cm_releaseconfig_ibfk_1` FOREIGN KEY (`ReleaseID`) REFERENCES `cm_release` (`ReleaseID`)
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_releaseconfig`
--

LOCK TABLES `cm_releaseconfig` WRITE;
/*!40000 ALTER TABLE `cm_releaseconfig` DISABLE KEYS */;
INSERT INTO `cm_releaseconfig` VALUES (25,3,'createUpdateActualProductPack','3.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(27,3,'createUpdateAdverseReaction','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(28,3,'createUpdateExemption','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(29,3,'createUpdateFormulary','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(30,3,'createUpdateGeneralParameter','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(31,3,'createUpdateLabelInstruction','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(32,3,'createUpdateMedicalCondition','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(33,3,'createUpdatePractice','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(34,3,'createUpdatePreferredActualProductPack','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(35,3,'createUpdatePreferredProductSKU','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(36,3,'createUpdatePrescriber','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(37,3,'createUpdatePrescriberType','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(38,3,'createUpdatePrescription','5.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(39,3,'createUpdatePrescription_FM','5.7-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(40,3,'createUpdatePrescriptionFormType','3.5-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(41,3,'createUpdatePrescriptionGroup','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(42,3,'createUpdateProduct','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(43,3,'createUpdateProductBarcode','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(44,3,'createUpdateProductFlavour','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(45,3,'createUpdateProductLogistics','3.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(46,3,'createUpdateProductSKU','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(47,3,'createUpdateRole','2.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(48,3,'createUpdateSite','3.5-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(49,3,'createUpdateStockAvailability','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(50,3,'createUpdateStoreServiceCentreLink','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(51,3,'createUpdateSupplier','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(52,3,'createUpdateTwinningScheme','0.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(53,3,'executeSQL','2.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(54,3,'import_IG01','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(55,3,'import_IG02','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(56,3,'import_IG03','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(57,3,'import_IG04','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(58,3,'import_IG06','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(59,3,'import_IG09','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(60,3,'jobrunner','2.6-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(61,3,'merge_IG01','1.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(62,3,'merge_IG02','1.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(63,3,'merge_IG03','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(64,3,'merge_IG04','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(65,3,'merge_IG06','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(66,3,'merge_IG09','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(67,3,'tsfn-encryptionutil','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(68,3,'OptimusMigration','1.88-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(69,3,'StockPlus_Migration','4.25-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(70,3,'StockPlusFM_Migration','6.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(71,3,'StockPlusFM_Migration_Maps','6.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}','\0','\0'),(72,3,'IF033_01_general_practice:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(73,3,'IF033_02_general_practice_relationship:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(74,3,'IF033_03_general_dental_practitioners:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(75,3,'IF033_04_general_dental_practices:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(76,3,'IF033_05_general_dental_practitioners_relationships:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(77,3,'IF033_x_01_01_Monitor:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(78,3,'IF033_x_01_02_Download:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(79,3,'IF033_x_01_03_Extract:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(80,3,'IF033_x_01_04_Validate:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(81,3,'IF061_00_01_Download:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(82,3,'IF061_00_02_Extract:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(83,3,'IF061_01_01_DMDPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(84,3,'IF061_02_01_DMDVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(85,3,'IF061_03_01_MAPDMDToPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(86,3,'IF061_04_01_MAPDMDToVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(87,3,'IF061_05_01_MASPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(88,3,'IF061_06_01_MASUnitOfMeasure:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(89,3,'IF061_07_01_MASUnits:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(90,3,'IF061_08_01_MASVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(91,3,'IF061_09_01_MASPresDispList:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(92,3,'IF101_01_01_MR_adverse_reaction:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(93,3,'IF101_01_02_MR_adverse_reaction:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(94,3,'IF101_02_01_MR_exemption:','2.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(95,3,'IF101_02_02_MR_exemption:','2.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(96,3,'IF101_03_01_MR_flavour:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(97,3,'IF101_03_02_MR_flavour:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(98,3,'IF101_04_01_MR_formulary:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(99,3,'IF101_04_02_MR_formulary:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(100,3,'IF101_05_01_MR_medicalCondition:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(101,3,'IF101_05_02_MR_medicalCondition:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(102,3,'IF101_06_01_MR_prescriberType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(103,3,'IF101_06_02_MR_prescriberType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(104,3,'IF101_07_01_MR_prescriptionFormType:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(105,3,'IF101_07_02_MR_prescriptionFormType:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(106,3,'IF101_08_01_MR_prescriptionService:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(107,3,'IF101_09_01_MR_productClass:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(108,3,'IF101_10_01_MR_region:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(109,3,'IF101_11_01_MR_supplier:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(110,3,'IF101_11_02_MR_supplier:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(111,3,'IF101_12_01_MR_unitOfMeasure:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(112,3,'IF101_12_02_MR_unitOfMeasure:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(113,3,'IF101_13_01_MR_role:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(114,3,'IF101_13_02_MR_role:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(115,3,'IF101_14_01_MR_general:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(116,3,'IF101_14_02_MR_general:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(117,3,'IF101_20_01_MR_prescriberTypeToFormType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(118,3,'IF101_21_01_MR_levyRegionFee:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(119,3,'IF101_21_02_MR_levyRegionFee:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(120,3,'IF101_22_01_MR_formulation:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(121,3,'IF101_22_02_MR_formulation:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(122,3,'IF101_23_01_MR_EpsResponseMessage:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(123,3,'IF101_23_02_MR_EpsResponseMessage:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(124,3,'IF102_01_01_MR_prescriber:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(125,3,'IF102_01_02_MR_prescriber:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(126,3,'IF102_02_01_MR_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(127,3,'IF102_02_02_MR_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(128,3,'IF102_03_01_MR_prescriber_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(129,3,'IF103_01_01_MR_PrescribableProduct:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(130,3,'IF103_01_02_MR_PrescribableProduct:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(131,3,'IF103_02_01_MR_FormularyToPP:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(132,3,'IF103_03_01_MR_PptoLIText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(133,3,'IF103_04_01_MR_DispensingSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(134,3,'IF103_04_02_MR_DispensingSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(135,3,'IF103_05_01_MR_PptoDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(136,3,'IF103_06_01_MR_RegionalDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(137,3,'IF103_07_01_MR_DispensingProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(138,3,'IF103_07_02_MR_DispensingProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(139,3,'IF103_08_01_MR_RegionalDPP:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(140,3,'IF103_09_01_MR_LabelInstructionText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(141,3,'IF103_09_02_MR_LabelInstructionText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(142,3,'IF103_10_01_MR_PPPrices:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(143,3,'IF105_01_01_MR_PreferredDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(144,3,'IF105_01_02_MR_PreferredDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(145,3,'IF105_02_01_MR_PreferredActualProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(146,3,'IF105_02_02_MR_PreferredActualProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(147,3,'IF108_01_01_MR_store:','2.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(148,3,'IF108_01_02_MR_store:','2.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(149,3,'IF108_02_02_MR_storeServiceLink:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(150,3,'IF110_01_01_product:','0.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(151,3,'IF110_01_02_product:','0.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(152,3,'IF172_01_01_stockEnquiry:','0.8-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(153,3,'IF172_01_02_stockAvailability:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(154,3,'IF172_02_02_twinningScheme:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(155,3,'IF175_01_01_stockTake:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(156,3,'IF175_01_02_stockTake:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(157,3,'IF176_01_01_stockFile:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(158,3,'IF176_01_02_stockFile:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(159,3,'IF179_01_01_barcode:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(160,3,'IF179_01_02_barcode:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(161,3,'IF33_01_02:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(162,3,'tsfz-encryptionutil:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(163,3,'tsfz-xsd:','0.8-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}','\0','\0'),(164,3,'PCE_AutomatReceiver_main:','0.6-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(165,3,'PCE_AutomatTransmitter_main:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(166,3,'PCE_CPAS_SFTP_Sender:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(167,3,'PCE_DS_IF130_FinanceFeed:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(168,3,'PCE_DS_IF137_Audit:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(169,3,'PCE_DS_IF138_IMS_DTP:','0.6-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(170,3,'PCE_DS_IF138_Mftr_Audit:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(171,3,'PCE_DS_IF90_DeliveryRequest:','0.5-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(172,3,'PCE_IF108_Loader:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(173,3,'PCE_IF134_Loader:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(174,3,'PCE_IF59_MQ_Reader_from_PMS:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(175,3,'PCE_Merge:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(176,3,'PCE_MonitorAudit:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(177,3,'PCE_OutputSplit:','0.5-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(178,3,'PCE_toAutomat_Masterdata_IF134:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(179,3,'PCE_toAutomat_Picking:','0.3-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(180,3,'PCE_Util_STOP_All_Runtime_Jobs:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(181,3,'PCE_InputSplit:','0.7-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(182,3,'tsfz-encryptionutil:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(183,3,'pce-parameters-files:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}','\0','\0'),(184,3,'brg','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(185,3,'eas','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(186,3,'uis','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(187,3,'trainingversion','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(188,3,'wmqjmsraversion','7.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(189,3,'ehcache','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(190,3,'bouncycastle','1.5','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(191,3,'symds_version','3.7.34-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(192,3,'symds_ext_version','1.6-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(193,3,'acl_api_version','3.20.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(194,3,'asmversion','1.6.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(195,3,'eclipsepersistenceversion','2.4.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(196,3,'aclloggingversion','3.14-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(197,3,'jmxqueryversion','1.4-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(198,3,'batchclientversion','1.6.1-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(199,3,'oracle','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(200,3,'oracle_secure','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(201,8,'brg','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(202,8,'eas','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(203,8,'uis','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(204,8,'trainingversion','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(205,8,'pms_version','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(206,8,'stockplus_version','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(207,8,'centralfilling_version','\'7.4.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(208,8,'wmqjmsraversion','\'7.0\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(209,8,'asmversion','\'1.6.0\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(210,8,'eclipsepersistenceversion','\'2.4.2\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(211,8,'aclloggingversion','\'3.14\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(212,8,'jmxqueryversion','\'1.4\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(213,8,'batchclientversion','\'1.6.1\'','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}','\0','\0'),(217,8,'deploy_version','{Release}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(218,8,'columbus_db_version','{Release}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(219,8,'uis_deployments','columbus-desktop-7.4.1.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(220,8,'eas_deployments','columbus-pms-7.4.1.ear,columbus-stockplus-7.4.1.ear,columbus-centralfilling-7.4.1.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(221,8,'brg_deployments','columbus-pms-7.4.1.ear,columbus-stockplus-7.4.1.ear,columbus-centralfilling-7.4.1.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(222,8,'brg_datasources','java:jboss/datasources/columbus-ds-pms,java:jboss/datasources/columbus-ds-stockplus,java:jboss/datasources/columbus-ds-centralfilling','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(223,8,'eas_datasources','java:jboss/datasources/columbus-ds-pms,java:jboss/datasources/columbus-ds-stockplus,java:jboss/datasources/columbus-ds-centralfilling','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(224,8,'pms_url','http://localhost:${jboss.http.port}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(225,8,'stockplus_url','http://localhost:${jboss.http.port}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(226,8,'centfill_url','http://localhost:${jboss.http.port}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(227,8,'uis_init_heap_mem','5G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(228,8,'eas_init_heap_mem','6G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(229,8,'brg_init_heap_mem: ','6G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(230,8,'uis_max_heap_mem','5G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(231,8,'eas_max_heap_mem','6G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(232,8,'brg_max_heap_mem','6G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(233,8,'uis_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(234,8,'eas_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(235,8,'brg_ max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(236,8,'modularization','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(237,8,'uis_user','uisUser','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(238,8,'uis_system_name','uisUser','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(239,8,'eas_system_name','batchUserEAS','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(240,8,'brg_system_name','batchUserBRG','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}','','\0'),(241,8,'db_name','PMSSNR11','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}','','\0'),(242,8,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}','','\0'),(243,8,'db_server','sfnpmsdbsr11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}','','\0'),(244,8,'db_user','COLUMBUS','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}','','\0'),(245,8,'db_pass','columbus','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}','','\0'),(246,8,'db_name','PMSSNR11','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}','','\0'),(247,8,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}','','\0'),(248,8,'db_server','sfnpmsdbsr11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}','','\0'),(249,8,'db_user','COLUMBUS_STAGING','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}','','\0'),(250,8,'db_pass','columbus_staging','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}','','\0');
/*!40000 ALTER TABLE `cm_releaseconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_releasedata`
--

DROP TABLE IF EXISTS `cm_releasedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_releasedata` (
  `ReleaseID` bigint(20) DEFAULT NULL,
  `DataTypeID` bigint(20) DEFAULT NULL,
  `ReleaseParam` varchar(50) DEFAULT NULL,
  `ReleaseValue` varchar(50) DEFAULT NULL,
  `ReleaseDataID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ReleaseDataID`),
  KEY `IXFK_CM_ReleaseData_CM_ReleaseDataType` (`DataTypeID`),
  KEY `IXFK_CM_ReleaseData_CM_Releases` (`ReleaseID`),
  CONSTRAINT `cm_releasedata_ibfk_1` FOREIGN KEY (`ReleaseID`) REFERENCES `cm_release` (`ReleaseID`),
  CONSTRAINT `cm_releasedata_ibfk_2` FOREIGN KEY (`DataTypeID`) REFERENCES `cm_releasedatatype` (`DataTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_releasedata`
--

LOCK TABLES `cm_releasedata` WRITE;
/*!40000 ALTER TABLE `cm_releasedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `cm_releasedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_releasedatatype`
--

DROP TABLE IF EXISTS `cm_releasedatatype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_releasedatatype` (
  `DataTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DataTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DataTypeID`),
  UNIQUE KEY `UQ_CM_ReleaseDataType_DataTypeName` (`DataTypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_releasedatatype`
--

LOCK TABLES `cm_releasedatatype` WRITE;
/*!40000 ALTER TABLE `cm_releasedatatype` DISABLE KEYS */;
/*!40000 ALTER TABLE `cm_releasedatatype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_server`
--

DROP TABLE IF EXISTS `cm_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_server` (
  `ServerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ServerName` varchar(50) NOT NULL,
  `ServerTypeID` bigint(20) DEFAULT NULL,
  `NodeID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ServerID`),
  UNIQUE KEY `UQ_CM_Server_ServerName` (`ServerName`),
  KEY `IXFK_CM_Server_CM_ServerType` (`ServerTypeID`),
  KEY `NodeID` (`NodeID`),
  CONSTRAINT `cm_server_ibfk_1` FOREIGN KEY (`ServerTypeID`) REFERENCES `cm_servertype` (`ServerTypeID`),
  CONSTRAINT `cm_server_ibfk_2` FOREIGN KEY (`NodeID`) REFERENCES `cm_node` (`NodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_server`
--

LOCK TABLES `cm_server` WRITE;
/*!40000 ALTER TABLE `cm_server` DISABLE KEYS */;
INSERT INTO `cm_server` VALUES (7,'gbrpmsdbst01',8,1),(9,'gbrpmsuist01',14,2),(10,'gbrpmsuist02',14,3),(11,'gbrpmseast01',4,4),(12,'gbrpmseast02',4,5),(13,'gbrpmsbrgt01',3,6),(14,'gbrpmsbrgt02',3,7),(15,'gbrpmstalt01',12,8),(16,'gbrpmstalt02',13,9),(17,'gbrpmstalt03',11,10),(19,'gbrpmsappt01',6,11),(20,'gbrpmsdbst03',7,12),(21,'gbrcbiappt01',1,13),(22,'gbrcbidbst01',2,14),(24,'TESTBRIDGE',3,15),(25,'TESTEAS',4,16),(26,'TESTBRIDGE2',3,17),(27,'TESTEAS2',4,18),(42,'gbrpmsuisf11',14,19),(43,'gbrpmseasf11',4,20),(44,'gbrpmsbrgf11',3,21),(45,'gbrpmstalf11',12,22),(46,'gbrpmstalf12',13,23),(47,'gbrpmstalf13',11,24),(48,'gbrpmsappf11',6,25),(51,'gbrpmsuise01',14,28),(52,'gbrpmsuise11',14,29),(53,'gbrpmsease01',4,30),(54,'gbrpmsease11',4,31),(55,'gbrpmsdbse01',8,32),(56,'gbrpmstale01',13,33),(57,'gbrpmstale11',13,34),(61,'gbrpmsuisi01',14,35),(62,'gbrpmsrpxt01',5,36),(63,'gbrpmseasi01',4,37),(64,'gbrpmseasi02',4,38),(65,'gbrpmsbrgi01',3,39),(66,'gbrpmsbrgi02',3,40),(67,'gbrpmstali01',12,41),(68,'gbrpmstali02',13,42),(69,'gbrpmstali03',11,43),(70,'gbrpmsappi01',6,44),(71,'gbrpmsuisi11',14,45),(72,'gbrpmseasi11',4,46),(73,'gbrpmsbrgi11',3,47),(74,'gbrpmsbrgi12',3,48),(75,'gbrpmstali11',12,49),(76,'gbrpmstali12',13,50),(77,'gbrpmstali13',11,51),(78,'gbrpmsappi11',6,52),(79,'gbrpmsuisi21',14,53),(80,'gbrpmseasi21',4,54),(81,'gbrpmsbrgi21',3,55),(82,'gbrpmstali21',12,56),(83,'gbrpmstali22',13,57),(84,'gbrpmstali23',11,58),(85,'gbrpmsappi21',6,59),(86,'gbrpmsuisi31',14,60),(87,'gbrpmsuisi32',14,61),(88,'gbrpmseasi31',4,62),(89,'gbrpmseasi32',4,63),(90,'gbrpmsbrgi31',3,64),(91,'gbrpmsbrgi32',3,65),(92,'gbrpmstali31',12,66),(93,'gbrpmstali32',13,67),(94,'gbrpmstali33',11,68),(95,'gbrpmsappi31',6,69),(96,'gbrpmsuisi41',14,70),(97,'gbrpmsuisi42',14,71),(98,'gbrpmseasi41',4,72),(99,'gbrpmseasi42',4,73),(100,'gbrpmsbrgi41',3,74),(101,'gbrpmstali41',12,75),(102,'gbrpmstali42',13,76),(103,'gbrpmstali43',11,77),(104,'gbrpmsappi41',6,78),(105,'gbrpmsuisf01',14,79),(106,'gbrpmseasf01',4,80),(107,'gbrpmsbrgf01',3,81),(108,'gbrpmstalf01',12,82),(109,'gbrpmstalf02',13,83),(110,'gbrpmstalf03',11,84),(111,'gbrpmsappf01',6,85),(112,'gbrpmsuisf21',14,86),(113,'gbrpmseasf21',4,87),(114,'gbrpmsbrgf21',3,88),(115,'gbrpmstalf21',12,89),(116,'gbrpmstalf22',13,90),(117,'gbrpmstalf23',11,91),(118,'gbrpmsappf21',6,92),(119,'gbrpmsuisf31',14,93),(120,'gbrpmseasf31',4,94),(121,'gbrpmsbrgf31',3,95),(122,'gbrpmstalf31',12,96),(123,'gbrpmstalf32',13,97),(124,'gbrpmstalf33',11,98),(125,'gbrpmsappf31',6,99),(126,'gbrpmsuisf41',14,100),(127,'gbrpmseasf41',4,101),(128,'gbrpmsuisu01',14,102),(129,'gbrpmseasu01',4,103),(130,'gbrpmsbrgu01',3,104),(131,'gbrpmstalu01',12,105),(132,'gbrpmstalu02',13,106),(133,'gbrpmstalu03',11,107),(134,'gbrpmsappu01',6,108),(135,'gbrpmsuisu11',14,109),(136,'gbrpmseasu11',4,110),(137,'gbrpmsbrgu11',3,111),(138,'gbrpmstalu11',12,112),(139,'gbrpmstalu12',13,113),(140,'gbrpmstalu13',11,114),(141,'gbrpmsappu11',6,115),(142,'gbrpmsdbst05',10,116),(143,'gbrpmsdbst14',10,117),(145,'gbrpmsrept01',9,118),(146,'gbrpmsrept02',9,119),(147,'gbrcbiappt02',1,120),(148,'gbrpmsuisf02',14,121),(149,'gbrpmseasf02',4,122),(150,'gbrpmsbrgf12',3,123),(151,'gbrpmsmigi01',15,124),(152,'gbrpmsmigi11',15,125),(153,'gbrpmsmigi21',15,126),(154,'gbrpmsmigf01',15,127),(155,'gbrpmsmigf11',15,128),(156,'gbrpmsmigf21',15,129),(157,'gbrpmsmigf31',15,130),(158,'gbrpmsmigu01',15,131),(159,'gbrpmsmigu02',15,132),(160,'gbrpmsmigt01',15,133),(161,'gbrpmstalf41',12,137),(162,'gb1pmsdbse02',10,138),(163,'gb1pmsdbse03',10,139);
/*!40000 ALTER TABLE `cm_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_serverconfig`
--

DROP TABLE IF EXISTS `cm_serverconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_serverconfig` (
  `ServerID` bigint(20) DEFAULT NULL,
  `ServConfigID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ServConfigParameter` varchar(50) DEFAULT NULL,
  `ServConfigValue` varchar(50) DEFAULT NULL,
  `ServConfigHieraAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ServConfigID`),
  KEY `IXFK_CM_ServerConfig_CM_Server` (`ServerID`),
  CONSTRAINT `cm_serverconfig_ibfk_1` FOREIGN KEY (`ServerID`) REFERENCES `cm_server` (`ServerID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_serverconfig`
--

LOCK TABLES `cm_serverconfig` WRITE;
/*!40000 ALTER TABLE `cm_serverconfig` DISABLE KEYS */;
INSERT INTO `cm_serverconfig` VALUES (24,7,'RichConfigThing','Awesome','ROOT:{ENVID}:{ServType}:{ParamName}'),(24,8,'2nd_Config','Good','ROOT:{ENVID}:{ServType}:{ParamName}'),(25,9,'3rd_Config','Fair','ROOT:{ENVID}:{ServType}:{ParamName}'),(26,13,'Param1','1234','ROOT:{ENVID}:{ServType}:{ParamName}'),(26,14,'Param2','5678','ROOT:{ENVID}:{ServType}:{ParamName}'),(27,15,'Param1','9011','ROOT:{ENVID}:{ServType}:{ParamName}'),(27,16,'Param2','1213','ROOT:{ENVID}:{ServType}:UniqueTree:{ParamName}');
/*!40000 ALTER TABLE `cm_serverconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_servertype`
--

DROP TABLE IF EXISTS `cm_servertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_servertype` (
  `ServerTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ServerTypeName` varchar(50) NOT NULL,
  PRIMARY KEY (`ServerTypeID`),
  UNIQUE KEY `UQ_CM_ServerType_ServerTypeName` (`ServerTypeName`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_servertype`
--

LOCK TABLES `cm_servertype` WRITE;
/*!40000 ALTER TABLE `cm_servertype` DISABLE KEYS */;
INSERT INTO `cm_servertype` VALUES (1,'BI - Application'),(2,'BI - Oracle Database'),(3,'BRG'),(4,'EAS'),(5,'HA Proxy'),(6,'Master Data - Application'),(7,'Master Data - Database'),(15,'Nexphase SmartScript Migration Server'),(8,'PMS Oracle Database'),(9,'Reporting Server'),(10,'RESIP HDS'),(11,'TalenD - PCE'),(12,'TalenD - TSFN & Boots'),(13,'TalenD - TSFZ'),(14,'UIS');
/*!40000 ALTER TABLE `cm_servertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_subenvironment`
--

DROP TABLE IF EXISTS `cm_subenvironment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_subenvironment` (
  `SubEnvironmentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ReleaseID` bigint(20) DEFAULT NULL,
  `SubEnvironmentTypeID` bigint(20) DEFAULT NULL,
  `EnvironmentID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SubEnvironmentID`),
  UNIQUE KEY `EnvironmentID_SubEnvironmentType_UNIQUE` (`EnvironmentID`,`SubEnvironmentTypeID`),
  KEY `IXFK_CM_Environment_CM_Releases` (`ReleaseID`),
  KEY `SubEnvironmentTypeID` (`SubEnvironmentTypeID`),
  CONSTRAINT `cm_subenvironment_ibfk_1` FOREIGN KEY (`ReleaseID`) REFERENCES `cm_release` (`ReleaseID`),
  CONSTRAINT `cm_subenvironment_ibfk_2` FOREIGN KEY (`EnvironmentID`) REFERENCES `cm_environment` (`EnvironmentID`),
  CONSTRAINT `cm_subenvironment_ibfk_3` FOREIGN KEY (`SubEnvironmentTypeID`) REFERENCES `cm_subenvironmenttype` (`SubEnvironmentTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_subenvironment`
--

LOCK TABLES `cm_subenvironment` WRITE;
/*!40000 ALTER TABLE `cm_subenvironment` DISABLE KEYS */;
INSERT INTO `cm_subenvironment` VALUES (1,NULL,1,21),(2,3,1,12),(5,NULL,1,22),(6,NULL,1,23),(7,NULL,1,16),(8,NULL,1,17),(9,NULL,1,18),(10,NULL,1,19),(11,NULL,1,20),(12,NULL,1,11),(13,NULL,1,13),(14,NULL,1,14),(15,NULL,1,15),(16,NULL,1,24),(17,NULL,1,25),(18,NULL,2,20),(19,NULL,3,20),(20,NULL,2,11),(21,NULL,3,11),(22,NULL,1,26),(23,NULL,1,27),(24,NULL,1,28),(25,NULL,2,26),(26,NULL,3,26),(27,NULL,4,26),(28,NULL,2,28),(29,NULL,3,28),(30,NULL,4,28);
/*!40000 ALTER TABLE `cm_subenvironment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_subenvironmentconfig`
--

DROP TABLE IF EXISTS `cm_subenvironmentconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_subenvironmentconfig` (
  `SubEnvironmentID` bigint(20) NOT NULL,
  `SubEnvConfigID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SubEnvConfigParameter` varchar(255) DEFAULT NULL,
  `SubEnvConfigValue` varchar(255) DEFAULT NULL,
  `SubEnvConfigHieraAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SubEnvConfigID`),
  KEY `IXFK_CM_EnvironmentConfig_CM_Environment` (`SubEnvironmentID`),
  CONSTRAINT `cm_subenvironmentconfig_ibfk_1` FOREIGN KEY (`SubEnvironmentID`) REFERENCES `cm_subenvironment` (`SubEnvironmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=827 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_subenvironmentconfig`
--

LOCK TABLES `cm_subenvironmentconfig` WRITE;
/*!40000 ALTER TABLE `cm_subenvironmentconfig` DISABLE KEYS */;
INSERT INTO `cm_subenvironmentconfig` VALUES (2,7,'ccode','gbr','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,8,'env','test','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,9,'sub_env','{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,10,'distrib','EnterpriseLinux','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,11,'log_level','INFO','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,12,'deployment_timeout','900','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,13,'ora_wallet','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,14,'ora_wallet_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,15,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,16,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,17,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,18,'db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,19,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,20,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,21,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,22,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,23,'db_user','PMS{ENVID}_STG','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,24,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,25,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,26,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,27,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,28,'db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,29,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,30,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,31,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,32,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,33,'db_user','OPTMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,34,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,35,'application_user','pmsgbrtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,36,'application_group','pmsgbrtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,37,'maintenance_user','pmsgbmtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,38,'tsfn','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:mgmtusers:{ParamName}'),(2,39,'gss','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:mgmtusers:{ParamName}'),(2,40,'{ENVID}Admin','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,41,'{ENVID}Monitoring','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,42,'{ENVID}Operations','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,43,'{ENVID}Admin','jminix,ADMIN','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,44,'{ENVID}Monitoring','jminix,MONITORING','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,45,'{ENVID}Operations','jminix,OPERATIONS','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,46,'product_name','columbus','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,47,'implementation_name','uk-boots','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,48,'application_desc','Columbus uk-boots implementation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,49,'deploy_version','7.3.2','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,50,'release','1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,51,'volumegroup','rootvg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,52,'data_size','1G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,53,'file_size','2G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,54,'soft_size','1G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,55,'jboss_enable_monitoring','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,56,'jboss_management_native_port','9999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,57,'jboss_host','localhost','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,58,'gclog_num_files','10','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,59,'gclog_file_size','100m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,60,'columbus_env_id','D','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,61,'asm_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,62,'asm_enable_list','uis,eas,brg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,63,'deployments','columbus-chs-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,64,'datasources','java:jboss/datasources/columbus-ds-eas','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,65,'symds_enable','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,66,'training_enable','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,67,'symds_server','gbrpmseasf11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,68,'symds_port','8180','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,69,'sym_sec_path','${jboss.server.config.dir}/syncronization/EK4DK','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,70,'sym_sec_file_release','20150115','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,71,'symds_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,72,'symds_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,73,'symds_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,74,'symds_db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,75,'symds_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,76,'sym_cluster_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,77,'sym_cluster_lock_timeout','300000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,78,'osucontainer_concurrency','1-1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,79,'asncontainer_concurrency','1-1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,80,'wmq_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,81,'jmxquery_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,82,'centralized_logging_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,83,'BIQuery','${HOME}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,84,'jboss_management_http_port','9990','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,85,'asm_jboss_management_user','admin','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,86,'asm_jboss_management_user_psw','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,87,'durcheck_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,88,'clinicalcheck_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,89,'endorsement_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,90,'marchart_notifyMarChart_url','http://10.245.12.244:8090/DrugMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,91,'housekeeping_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,92,'enable_new_transmit_due_date_orders_implementation','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,93,'preview_due_now_orders_page_limit','120','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,94,'clinicalcheck_getdur_url','http://gbrpmsdbst14.corp.internal:80/HDS/ClinicalInformation.svc/GetDUR','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,95,'clinicalcheck_getdrug_url','http://gbrpmsdbst14.corp.internal:80/HDS/ClinicalInformation.svc/GetDrugInformation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,96,'reimbursement_getendorsement_url','http://gbrpmsdbst14.corp.internal:80/HDS/Reimbursement.svc/GetEndorsement','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,97,'emar_notifyPatientCommunity_url','http://10.245.12.244:8090/FacilityMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,98,'emar_notifyPatient_url','http://10.245.12.244:8090/PatientMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,99,'brg_server','gbrpmsbrgt00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,100,'vip_brg_port','8884','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,101,'brg_port','8380','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,102,'brg_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,103,'brg_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,104,'brg_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,105,'brg_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,106,'brg_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,107,'brg_spring_profile','outbound_api_channel,outbound_api_adapter,inbound_api_channel,inbound_api_adapter,import_batch,export_batch,internal_job_batch,external_job_batch,sym_chs','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,108,'brg_deployments','columbus-chs-7.3.2.ear,symmetricds-war-1.3.war','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,109,'brg_datasources','java:jboss/datasources/columbus-ds-eas,java:jboss/datasources/columbus-ds-symmetricds','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,110,'brg_asm_enabled_check_list','availabilityChecker,deployChecker,datasourceChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,111,'sync_url','https://gbrpmsbrgt00.corp.internal:8884/symmetricds/sync/chs-master','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,112,'resadapter_arc','wmq.jmsra.rar','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,113,'dispensed_db_name','EAS_DB','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,114,'env_roles_internal_jobs_enable','brg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,115,'ig32_ek4dk','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,116,'boots_patient_key_hash','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,117,'IG32_secret_key','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,118,'inbound_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,119,'inbound_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,120,'inbound_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,121,'inbound_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,122,'inbound_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,123,'inbound_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,124,'outbound_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,125,'outbound_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,126,'outbound_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,127,'outbound_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,128,'outbound_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,129,'outbound_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,130,'history_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,131,'history_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,132,'history_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,133,'history_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,134,'history_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,135,'history_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,136,'columbus_db_version','7.3.2','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,137,'eas_spring_profile','outbound_api_channel,outbound_api_adapter,offline','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,138,'eas_deployments','columbus-chs-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,139,'eas_datasources','java:jboss/datasources/columbus-ds-eas','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,140,'eas_asm_enabled_check_list','availabilityChecker,deployChecker,datasourceChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,141,'eas_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,142,'eas_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,143,'eas_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,144,'eas_db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,145,'eas_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,146,'eas_db_staging_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,147,'eas_db_staging_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,148,'eas_db_staging_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,149,'eas_db_staging_user','PMS{ENVID}_STG','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,150,'eas_db_staging_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,151,'eas_port','8180','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,152,'vip_eas_server','gbrpmseast00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,153,'vip_eas_port','8849','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,154,'eas_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,155,'eas_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,156,'eas_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,157,'eas_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,158,'eas_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,159,'CPAS_modulus','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,160,'CPAS_exponent','AQAB','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,161,'ig29_secret_keys','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,162,'boots_stockplus_key_hash','B5B','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,163,'ig24_secret_key','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,164,'ek4dk','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,165,'leo_queuemgr_name','*UNIALPD','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,166,'tote_order_line_ref_min_value','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,167,'tote_order_line_ref_max_value','999999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,168,'tote_ref_min_value','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,169,'tote_ref_max_value','9999999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,170,'use_tote_prefix','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,171,'tote_prefix','TOTE','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,172,'uis_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,173,'uis_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,174,'uis_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,175,'uis_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,176,'uis_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,177,'uis_server','gbrpmsuist00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,178,'uis_port','8280','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,179,'uis_sys_credential_file','SysCredential.properties','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,180,'uis_user','uisUser','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,181,'uis_deployments','columbus-desktop-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,182,'uis_asm_enabled_check_list','availabilityChecker,deployChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,183,'proxy_name','gbrpmsuist00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,184,'proxy_port','8849','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,185,'batchclient_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,186,'batch_client_ws_user','john','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,187,'batch_client_ws_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,188,'batch_client_ws_sec_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,189,'batch_client_ws_enc_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,190,'batch_user','batchJobUser1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,191,'batch_sys_credential_file','SysCredential_generatedByEAS.properties','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,192,'batch_max_retry_attempts','10','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,193,'exit_code_batch_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,194,'batchclient_ek4dk','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,195,'syscred_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,196,'syscred_passwordIssueDate','1471539187637','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,197,'createUpdatePrescription_INSERT_THREADS','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,198,'createUpdatePrescription_VALIDATION_THREADS','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,199,'tsfn_server','gbrpmstali21.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,200,'tsfz_environment_id','28','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,201,'tsfz_NASHxCf','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,202,'tsfz_inbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,203,'tsfz_inbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,204,'tsfz_inbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,205,'tsfz_inbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,206,'tsfz_inbound_columbus_csv_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,207,'tsfz_inbound_columbus_csv_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,208,'tsfz_inbound_columbus_csv_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,209,'tsfz_inbound_columbus_csv_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,210,'tsfz_outbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,211,'tsfz_outbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,212,'tsfz_outbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,213,'tsfz_outbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,214,'tsfz_internal_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,215,'tsfz_internal_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,216,'tsfz_internal_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,217,'tsfz_internal_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,218,'tsfz_rootZipFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,219,'tsfz_rootZipFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,220,'tsfz_rootZipFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,221,'tsfz_rootZipFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,222,'tsfz_insideCSVFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,223,'tsfz_insideCSVFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,224,'tsfz_insideCSVFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,225,'tsfz_insideCSVFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,226,'tsfz_insideZipFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,227,'tsfz_insideZipFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,228,'tsfz_insideZipFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,229,'tsfz_insideZipFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,230,'tsfz_archives_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,231,'tsfz_archives_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,232,'tsfz_archives_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,233,'tsfz_archives_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,234,'tsfz_history_inbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,235,'tsfz_history_inbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,236,'tsfz_history_inbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,237,'tsfz_history_inbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,238,'tsfz_history_outbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,239,'tsfz_history_outbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,240,'tsfz_history_outbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,241,'tsfz_history_outbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,242,'tsfz_history_inbound_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,243,'tsfz_history_inbound_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,244,'tsfz_history_inbound_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,245,'tsfz_history_inbound_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,246,'tsfz_history_outbound_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,247,'tsfz_history_outbound_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,248,'tsfz_history_outbound_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,249,'tsfz_history_outbound_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,250,'pce_environment_id','60','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,251,'pce_if108_domain_code','coretest','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,252,'pce_NASHxCf','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,253,'pce_rsa_exp','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,254,'pce_rsa_mod','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,255,'pce_outbound_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,256,'pce_outbound_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,257,'pce_outbound_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,258,'pce_qAutomatInput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,259,'pce_qAutomatInput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,260,'pce_qAutomatInput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,261,'pce_qAutomatInput_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,262,'pce_qAutomatInput_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,263,'pce_qAutomatInput_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,264,'pce_qAutomatOutput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,265,'pce_qAutomatOutput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,266,'pce_qAutomatOutput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,267,'pce_qAutomatOutput_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,268,'pce_qAutomatOutput_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,269,'pce_qAutomatOutput_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,270,'pce_qCombined_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,271,'pce_qCombined_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,272,'pce_qCombined_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,273,'pce_qCombined_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,274,'pce_qCombined_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,275,'pce_qCombined_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,276,'pce_qIF130_FinanceFeed_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,277,'pce_qIF130_FinanceFeed_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,278,'pce_qIF130_FinanceFeed_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,279,'pce_qIF130_FinanceFeed_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,280,'pce_qIF130_FinanceFeed_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,281,'pce_qIF130_FinanceFeed_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,282,'pce_qIF134_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,283,'pce_qIF134_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,284,'pce_qIF134_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,285,'pce_qIF137_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,286,'pce_qIF137_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,287,'pce_qIF137_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,288,'pce_qIF137_Audit_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,289,'pce_qIF137_Audit_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,290,'pce_qIF137_Audit_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,291,'pce_qIF138_IMS_DTP_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,292,'pce_qIF138_IMS_DTP_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,293,'pce_qIF138_IMS_DTP_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,294,'pce_qIF138_IMS_DTP_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,295,'pce_qIF138_IMS_DTP_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,296,'pce_qIF138_IMS_DTP_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,297,'pce_qIF138_Mftr_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,298,'pce_qIF138_Mftr_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,299,'pce_qIF138_Mftr_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,300,'pce_qIF138_Mftr_Audit_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,301,'pce_qIF138_Mftr_Audit_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,302,'pce_qIF138_Mftr_Audit_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,303,'pce_qIF59_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,304,'pce_qIF59_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,305,'pce_qIF59_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,306,'pce_qIF59_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,307,'pce_qIF59_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,308,'pce_qIF59_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,309,'pce_qIF90_DeliveryRequest_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,310,'pce_qIF90_DeliveryRequest_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,311,'pce_qIF90_DeliveryRequest_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,312,'pce_qIF90_DeliveryRequest_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,313,'pce_qIF90_DeliveryRequest_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,314,'pce_qIF90_DeliveryRequest_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,315,'pce_qLabelData_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,316,'pce_qLabelData_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,317,'pce_qLabelData_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,318,'pce_qLabels_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,319,'pce_qLabels_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,320,'pce_qLabels_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,321,'pce_qTote_Centric_Data_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,322,'pce_qTote_Centric_Data_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,323,'pce_qTote_Centric_Data_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,324,'pce_tmp_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,325,'pce_tmp_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,326,'pce_tmp_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,327,'pce_history_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,328,'pce_history_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,329,'pce_history_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,330,'pce_history_qAutomatInput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,331,'pce_history_qAutomatInput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,332,'pce_history_qAutomatInput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,333,'pce_history_qAutomatOutput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,334,'pce_history_qAutomatOutput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,335,'pce_history_qAutomatOutput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,336,'pce_history_qCombined_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,337,'pce_history_qCombined_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,338,'pce_history_qCombined_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,339,'pce_history_qIF130_FinanceFeed_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,340,'pce_history_qIF130_FinanceFeed_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,341,'pce_history_qIF130_FinanceFeed_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,342,'pce_history_qIF134_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,343,'pce_history_qIF134_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,344,'pce_history_qIF134_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,345,'pce_history_qIF137_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,346,'pce_history_qIF137_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,347,'pce_history_qIF137_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,348,'pce_history_qIF138_IMS_DTP_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,349,'pce_history_qIF138_IMS_DTP_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,350,'pce_history_qIF138_IMS_DTP_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,351,'pce_history_qIF138_Mftr_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,352,'pce_history_qIF138_Mftr_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,353,'pce_history_qIF138_Mftr_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,354,'pce_history_qIF90_DeliveryRequest_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,355,'pce_history_qIF90_DeliveryRequest_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,356,'pce_history_qIF90_DeliveryRequest_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,357,'pce_history_qLabelData_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,358,'pce_history_qLabelData_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,359,'pce_history_qLabelData_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,360,'pce_history_qTote_Centric_Data_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,361,'pce_history_qTote_Centric_Data_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,362,'pce_history_qTote_Centric_Data_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,363,'pce_history_outbound_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,364,'pce_history_outbound_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,365,'pce_history_outbound_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,366,'pce_logs_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,367,'pce_logs_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,368,'pce_logs_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,369,'pce_automat_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,370,'pce_automat_settrace','all','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,371,'pce_db_audit_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,372,'pce_db_audit_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,373,'pce_db_audit_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,374,'pce_db_audit_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,375,'pce_db_audit_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,376,'pce_db_audit_schema','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,377,'pce_db_if138_mftr_audit_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,378,'pce_db_if138_mftr_audit_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,379,'pce_db_if138_mftr_audit_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,380,'pce_db_if138_mftr_audit_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,381,'pce_db_if138_mftr_audit_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,382,'pce_db_if138_mftr_audit_schema','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,383,'pce_db_masterdata_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,384,'pce_db_masterdata_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,385,'pce_db_masterdata_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,386,'pce_db_masterdata_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,387,'pce_db_masterdata_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,388,'pce_db_masterdata_schema_obd','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,389,'pce_db_masterdata_schema_pubsta','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,390,'pce_ftp_host','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,391,'pce_ftp_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,392,'pce_ftp_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,393,'pce_ftp_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,394,'pce_queue_manager','*UNIALPD','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,395,'pce_queue_manager_if90_leo','*NAPUKTA','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,396,'optimus_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,397,'optimus_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,398,'optimus_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,399,'optimus_db_user','OPTMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,400,'optimus_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,401,'stockplus_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,402,'stockplus_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,403,'stockplus_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,404,'stockplus_db_user','STKMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,405,'stockplus_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,406,'fullmig_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,407,'fullmig_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,408,'fullmig_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,409,'fullmig_db_user','FULLMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,410,'fullmig_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,411,'masterdata_db_server','gb2pmsdbst11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,412,'masterdata_db_name','MasterData','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,413,'masterdata_db_port','51649','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,414,'masterdata_db_user','talendT','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,415,'masterdata_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,416,'boots_masterdata_db_schema','PublishedStaging','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,417,'boots_masterdata_db_instance','MASTERDATADB{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,418,'boots_interface_loglevel','INFO','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,419,'if056_rsa_pub_modulus','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,420,'if056_rsa_pub_exp','xxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,421,'ftp_nxp_host','ftp.cegedimrx.co.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,422,'ftp_nxp_user','bootscolumbus','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,423,'ftp_nxp_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,424,'ftp_nhs_host','ftp.isd.hscic.gov.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,425,'ftp_nhs_user','TRUD3-11585@trud.nhs.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,426,'ftp_nhs_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,427,'one_leo_db_host','10.179.20.187','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,428,'one_leo_db_port','60000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,429,'one_leo_db_name','unichemd','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,430,'one_leo_db_user','enquire','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,431,'one_leo_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,432,'ah_talend_server','unknown','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,433,'ah_talend_user','tagbXadm','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,434,'columbus_release','2070000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,435,'columbus_masterdata_4_status','1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,436,'sec_file_release','20150121','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,437,'auditd_local_rules','true','ROOT:{ParamName}'),(2,438,'srcurl','http://gb2inffilp1.resources.corp.internal/SOURCES/','ROOT:{ParamName}'),(2,439,'os_version','centos-6.6-x86_64','ROOT:{ParamName}'),(2,440,'interface','eth1','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,441,'router_id','60','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,442,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,443,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,444,'ipaddress','10.141.129.248/24','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,445,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,446,'interface','eth1','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,447,'router_id','61','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,448,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,449,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,450,'ipaddress','10.141.129.249/24','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,451,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,452,'interface','eth1','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,453,'router_id','62','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,454,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,455,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,456,'ipaddress','10.141.129.247/24','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,457,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,458,'mode','http','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,459,'bind_address','10.141.129.249','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,460,'bind_port','8884','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,461,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,462,'options','stick on src','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,463,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,464,'backends','gbrpmsbrgf11 gbrpmsbrgf11.corp.internal:8380 check','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,465,'backends','gbrpmsbrgf12 gbrpmsbrgf12.corp.internal:8380 check','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,466,'mode','http','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,467,'bind_address','10.141.129.248','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,468,'bind_port','8849','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,469,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,470,'options','stick on src','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,471,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,472,'backends','gbrpmseasf11 gbrpmseasf11.corp.internal:8180 check','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,473,'mode','http','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,474,'bind_address','10.141.129.247','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,475,'bind_port','8849','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,476,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,477,'options','stick on src','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,478,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,479,'backends','gbrpmsuisf11 gbrpmsuisf11.corp.internal:8280 check','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,480,'provide','base','ROOT:mysql_conf:{ParamName}'),(2,481,'env','test','ROOT:mysql_conf:talend_test:{ParamName}'),(2,482,'ccode','gbr','ROOT:mysql_conf:talend_test:{ParamName}'),(2,483,'app','tal','ROOT:mysql_conf:talend_test:{ParamName}'),(2,484,'user','tagbtdb','ROOT:mysql_conf:talend_test:{ParamName}'),(2,485,'password','removed','ROOT:mysql_conf:talend_test:{ParamName}'),(2,486,'group','tagbtdb','ROOT:mysql_conf:talend_test:{ParamName}'),(2,487,'vg','rootvg','ROOT:mysql_conf:talend_test:{ParamName}'),(2,488,'isbinding','true','ROOT:mysql_conf:talend_test:{ParamName}'),(2,489,'isreplication','false','ROOT:mysql_conf:talend_test:{ParamName}'),(2,490,'db','10G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,491,'files','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,492,'soft','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,493,'inno','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,494,'binlog','3G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,495,'save','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,496,'isbinlog','false','ROOT:mysql_conf:talend_test:{ParamName}'),(2,497,'port','3306','ROOT:mysql_conf:talend_test:override_options:client{ParamName}'),(2,498,'bind_address','0.0.0.0','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,499,'port','3306','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,500,'innodb_log_file_size','64M','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,501,'root_pass','removed','ROOT:mysql_conf:talend_test:{ParamName}'),(2,502,'name','tsfz_portal','ROOT:mysql_db:tsfz_portal_test:{ParamName}'),(2,503,'engine','talend_test','ROOT:mysql_db:tsfz_portal_test:{ParamName}'),(2,504,'name','tagbtdb','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,505,'host','%','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,506,'grant','ALL','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,507,'name','job_conf','ROOT:mysql_db:job_conf_test:{ParamName}'),(2,508,'engine','talend_test','ROOT:mysql_db:job_conf_test:{ParamName}'),(2,509,'name','tagbtdb','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,510,'host','%','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,511,'grant','ALL','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,512,'test','tsfz_portal_test','ROOT:talend_databases:{ParamName}'),(2,513,'test','job_conf_test','ROOT:talend_databases:{ParamName}'),(2,514,'env','test','ROOT:talend_test_info:{ParamName}'),(2,515,'ccode','gbr','ROOT:talend_test_info:{ParamName}'),(2,516,'app','tal','ROOT:talend_test_info:{ParamName}'),(2,517,'user','tagbtadm','ROOT:talend_test_info:{ParamName}'),(2,518,'group','tagbt','ROOT:talend_test_info:{ParamName}'),(2,519,'nfsserver','127.0.0.1','ROOT:talend_test_info:{ParamName}'),(2,520,'installinterfaces','true','ROOT:talend_test_info:{ParamName}'),(2,521,'installoracli','false','ROOT:talend_test_info:{ParamName}'),(2,522,'installdb2cli','false','ROOT:talend_test_info:{ParamName}'),(2,523,'jdk_version','1.6.0_37','ROOT:talend_test_info:{ParamName}'),(2,524,'jdk_release','0HS','ROOT:talend_test_info:{ParamName}'),(2,525,'db2_vers','97','ROOT:talend_test_info:{ParamName}'),(2,526,'envs_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,527,'envf_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,528,'envb_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,529,'vg','rootvg','ROOT:talend_test_info:{ParamName}'),(2,530,'size_envs_tal','3G','ROOT:talend_test_info:{ParamName}'),(2,531,'size_envf_tal','1G','ROOT:talend_test_info:{ParamName}'),(2,532,'size_envb_tal','2G','ROOT:talend_test_info:{ParamName}'),(2,533,'size_exchange','2G','ROOT:talend_test_info:{ParamName}'),(2,534,'size_history','5G','ROOT:talend_test_info:{ParamName}'),(2,535,'size_internal','1G','ROOT:talend_test_info:{ParamName}'),(2,536,'istsfn','true','ROOT:talend_test_info:{ParamName}'),(2,537,'uid','1001','ROOT:user_tagbtdb:{ParamName}'),(2,538,'gid','1000','ROOT:user_tagbtdb:{ParamName}'),(2,539,'groups','tagbtdb','ROOT:user_tagbtdb:{ParamName}'),(2,540,'home','/home/tagbtdb','ROOT:user_tagbtdb:{ParamName}'),(2,541,'password','removed','ROOT:user_tagbtdb:{ParamName}'),(2,542,'authorized_keys',NULL,'ROOT:user_tagbtdb:{ParamName}'),(2,543,'uid','1000','ROOT:user_tagbtadm:{ParamName}'),(2,544,'gid','1000','ROOT:user_tagbtadm:{ParamName}'),(2,545,'groups','tagbt','ROOT:user_tagbtadm:{ParamName}'),(2,546,'home','/home/tagbtadm','ROOT:user_tagbtadm:{ParamName}'),(2,547,'password','removed','ROOT:user_tagbtadm:{ParamName}'),(2,548,'authorized_keys_file','oneleonardo/ssh/authorized_keys.erb','ROOT:user_tagbtadm:{ParamName}'),(2,549,'TSFN_columbus','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,550,'integration','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,551,'boots_support','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,552,'boots_performance_test','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,553,'devel','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,554,'envb','/exchange/outbound','ROOT:talend_dirs:common:{ParamName}'),(2,555,'envb','/exchange/inbound','ROOT:talend_dirs:common:{ParamName}'),(2,556,'envb','/exchange/outbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,557,'envb','/exchange/outbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,558,'envb','/exchange/inbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,559,'envb','/exchange/inbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,560,'envb','/parameters/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,561,'envb','/parameters/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,562,'envb','/internal/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,563,'envb','/internal/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,564,'envf','/logs/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,565,'envf','/logs/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,566,'envf','/history/storage','ROOT:talend_dirs:common:{ParamName}'),(2,567,'envf','/history/inbound','ROOT:talend_dirs:common:{ParamName}'),(2,568,'envf','/history/outbound','ROOT:talend_dirs:common:{ParamName}'),(2,569,'envf','/archives/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,570,'envf','/archives/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,571,'envf','/history/inbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,572,'envf','/history/inbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,573,'envf','/history/outbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,574,'envf','/history/outbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,575,'envs','/jobs/scripts/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,576,'envs','/jobs/scripts/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,577,'envb','/parameters/columbus/EK4DK','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,578,'envb','/exchange/outbound/optimus/data/Drop','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,579,'envb','/exchange/outbound/optimus/data/FailedOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,580,'envb','/exchange/outbound/optimus/data/IntermediateOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,581,'envb','/exchange/outbound/optimus/data/NotProcessed','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,582,'envb','/exchange/outbound/optimus/data/ProcessedOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,583,'envb','/exchange/outbound/optimus/data/ProcessedSource','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,584,'envb','/exchange/outbound/optimus/data/tmp','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,585,'envb','/exchange/outbound/optimus/emailpickupfolder','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,586,'envs','/jobs/scripts/columbus/move-scripts','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,587,'envb','/exchange/inbound/columbus/bad','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,588,'envb','/exchange/inbound/columbus/csv','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,589,'envb','/exchange/inbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,590,'envb','/exchange/inbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,591,'envb','/exchange/inbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,592,'envb','/exchange/inbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,593,'envb','/exchange/inbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,594,'envb','/exchange/outbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,595,'envb','/exchange/outbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,596,'envb','/exchange/outbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,597,'envb','/exchange/outbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,598,'envb','/exchange/outbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,599,'envb','/exchange/NFM/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,600,'envb','/internal/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,601,'envb','/internal/columbus/rootZipFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,602,'envb','/internal/columbus/insideCSVFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,603,'envb','/internal/columbus/insideZipFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,604,'envb','/internal/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,605,'envf','/history/inbound/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,606,'envf','/history/inbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,607,'envf','/history/inbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,608,'envf','/history/inbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,609,'envf','/history/inbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,610,'envf','/history/inbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,611,'envf','/history/inbound/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,612,'envf','/history/outbound/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,613,'envf','/history/outbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,614,'envf','/history/outbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,615,'envf','/history/outbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,616,'envf','/history/outbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,617,'envf','/history/outbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,618,'envf','/history/outbound/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,619,'envs','/script/admin','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,620,'envs','/jobs/scripts/columbus/move-scripts','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,621,'envb','/exchange/outbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,622,'envb','/internal/optimus/pce/qAutomatInput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,623,'envb','/internal/optimus/pce/qAutomatInput/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,624,'envb','/internal/optimus/pce/qAutomatOutput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,625,'envb','/internal/optimus/pce/qAutomatOutput/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,626,'envb','/internal/optimus/pce/qCombined','ROOT:talend_dirs:pce_only:{ParamName}'),(2,627,'envb','/internal/optimus/pce/qCombined/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,628,'envb','/internal/optimus/pce/qCombined/tmp','ROOT:talend_dirs:pce_only:{ParamName}'),(2,629,'envb','/internal/optimus/pce/qIF130_FinanceFeed','ROOT:talend_dirs:pce_only:{ParamName}'),(2,630,'envb','/internal/optimus/pce/qIF130_FinanceFeed/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,631,'envb','/internal/optimus/pce/qIF134','ROOT:talend_dirs:pce_only:{ParamName}'),(2,632,'envb','/internal/optimus/pce/qIF137_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,633,'envb','/internal/optimus/pce/qIF137_Audit/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,634,'envb','/internal/optimus/pce/qIF138_IMS_DTP','ROOT:talend_dirs:pce_only:{ParamName}'),(2,635,'envb','/internal/optimus/pce/qIF138_IMS_DTP/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,636,'envb','/internal/optimus/pce/qIF138_Mftr_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,637,'envb','/internal/optimus/pce/qIF138_Mftr_Audit/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,638,'envb','/internal/optimus/pce/qIF59','ROOT:talend_dirs:pce_only:{ParamName}'),(2,639,'envb','/internal/optimus/pce/qIF59/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,640,'envb','/internal/optimus/pce/qIF90_DeliveryRequest','ROOT:talend_dirs:pce_only:{ParamName}'),(2,641,'envb','/internal/optimus/pce/qIF90_DeliveryRequest/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,642,'envb','/internal/optimus/pce/qLabelData','ROOT:talend_dirs:pce_only:{ParamName}'),(2,643,'envb','/internal/optimus/pce/qLabels','ROOT:talend_dirs:pce_only:{ParamName}'),(2,644,'envb','/internal/optimus/pce/qTote_Centric_Data1','ROOT:talend_dirs:pce_only:{ParamName}'),(2,645,'envb','/internal/optimus/pce/tmp','ROOT:talend_dirs:pce_only:{ParamName}'),(2,646,'envb','/parameters/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,647,'envs','/script/admin','ROOT:talend_dirs:pce_only:{ParamName}'),(2,648,'envs','/admin/scripts/filterOutDownStream','ROOT:talend_dirs:pce_only:{ParamName}'),(2,649,'envf','/history/inbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,650,'envf','/history/internal/optimus/pce/qAutomatInput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,651,'envf','/history/internal/optimus/pce/qAutomatOutput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,652,'envf','/history/internal/optimus/pce/qCombined','ROOT:talend_dirs:pce_only:{ParamName}'),(2,653,'envf','/history/internal/optimus/pce/qIF130_FinanceFeed','ROOT:talend_dirs:pce_only:{ParamName}'),(2,654,'envf','/history/internal/optimus/pce/qIF134','ROOT:talend_dirs:pce_only:{ParamName}'),(2,655,'envf','/history/internal/optimus/pce/qIF137_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,656,'envf','/history/internal/optimus/pce/qIF138_IMS_DTP','ROOT:talend_dirs:pce_only:{ParamName}'),(2,657,'envf','/history/internal/optimus/pce/qIF138_Mftr_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,658,'envf','/history/internal/optimus/pce/qIF90_DeliveryRequest','ROOT:talend_dirs:pce_only:{ParamName}'),(2,659,'envf','/history/internal/optimus/pce/qLabelData','ROOT:talend_dirs:pce_only:{ParamName}'),(2,660,'envf','/history/internal/optimus/pce/qTote_Centric_Data1','ROOT:talend_dirs:pce_only:{ParamName}'),(2,661,'envf','/history/outbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,662,'envf','/logs/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,663,'envf','/jobs/scripts/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(7,664,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(7,665,'UIS VIP IP','10.141.129.247',NULL),(7,666,'UIS VIP Port','8845',NULL),(7,667,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(7,668,'EAS VIP IP','10.141.129.248',NULL),(7,669,'EAS VIP Port','8845',NULL),(7,670,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(7,672,'BRG VIP Port','8880',NULL),(8,673,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(8,674,'UIS VIP IP','10.141.129.247',NULL),(8,675,'UIS VIP Port','8846',NULL),(8,676,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(8,677,'EAS VIP IP','10.141.129.248',NULL),(8,678,'EAS VIP Port','8846',NULL),(8,679,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(8,680,'BRG VIP IP','10.141.129.249',NULL),(8,681,'BRG VIP Port','8881',NULL),(9,682,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(9,683,'UIS VIP IP','10.141.129.247',NULL),(9,684,'UIS VIP Port','8847',NULL),(9,685,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(9,686,'EAS VIP IP','10.141.129.248',NULL),(9,687,'EAS VIP Port','8847',NULL),(9,688,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(9,689,'BRG VIP IP','10.141.129.249',NULL),(9,690,'BRG VIP Port','8882',NULL),(10,691,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(10,692,'UIS VIP IP','10.141.129.247',NULL),(10,693,'UIS VIP Port','8893',NULL),(10,694,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(10,695,'EAS VIP IP','10.141.129.248',NULL),(10,696,'EAS VIP Port','8893',NULL),(10,697,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(10,698,'BRG VIP IP','10.141.129.249',NULL),(10,699,'BRG VIP Port','8890',NULL),(18,700,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(18,701,'UIS VIP IP','10.141.129.247',NULL),(18,702,'UIS VIP Port','8894',NULL),(18,703,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(18,704,'EAS VIP IP','10.141.129.248',NULL),(18,705,'EAS VIP Port','8898',NULL),(19,706,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(19,707,'UIS VIP IP','10.141.129.247',NULL),(19,708,'UIS VIP Port','8894',NULL),(19,709,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(19,710,'EAS VIP IP','10.141.129.248',NULL),(19,711,'EAS VIP Port','8898',NULL),(11,712,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(11,713,'BRG VIP IP','10.141.129.249',NULL),(11,714,'BRG VIP Port','8891',NULL),(20,715,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(20,716,'UIS VIP IP','10.141.129.247',NULL),(20,717,'UIS VIP Port','8848',NULL),(20,718,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(20,719,'EAS VIP IP','10.141.129.248',NULL),(20,720,'EAS VIP Port','8848',NULL),(21,721,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(21,722,'UIS VIP IP','10.141.129.247',NULL),(21,723,'UIS VIP Port','8858',NULL),(21,724,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(21,725,'EAS VIP IP','10.141.129.248',NULL),(21,726,'EAS VIP Port','8858',NULL),(12,727,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(12,728,'BRG VIP IP','10.141.129.249',NULL),(12,729,'BRG VIP Port','8883',NULL),(2,730,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(2,732,'UIS VIP Port','8849',NULL),(2,733,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(2,734,'EAS VIP IP','10.141.129.248',NULL),(2,735,'EAS VIP Port','8849',NULL),(2,736,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(2,737,'BRG VIP IP','10.141.129.249',NULL),(2,738,'BRG VIP Port','8884',NULL),(13,739,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(13,740,'UIS VIP IP','10.141.129.247',NULL),(13,741,'UIS VIP Port','8850',NULL),(13,742,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(13,743,'EAS VIP IP','10.141.129.248',NULL),(13,744,'EAS VIP Port','8850',NULL),(13,745,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(13,746,'BRG VIP IP','10.141.129.249',NULL),(13,747,'BRG VIP Port','8885',NULL),(14,748,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(14,749,'UIS VIP IP','10.141.129.247',NULL),(14,750,'UIS VIP Port','8851',NULL),(14,751,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(14,752,'EAS VIP IP','10.141.129.248',NULL),(14,753,'EAS VIP Port','8851',NULL),(14,754,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(14,755,'BRG VIP IP','10.141.129.249',NULL),(14,756,'BRG VIP Port','8886',NULL),(15,757,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(15,759,'UIS VIP Port','8895',NULL),(15,760,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(15,761,'EAS VIP IP','10.141.129.248',NULL),(15,762,'EAS VIP Port','8895',NULL),(15,763,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(15,764,'BRG VIP IP','10.141.129.249',NULL),(15,765,'BRG VIP Port','8892',NULL),(16,766,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(16,767,'UIS VIP IP','10.141.129.247',NULL),(16,768,'UIS VIP Port','8852',NULL),(16,769,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(16,771,'EAS VIP Port','8852',NULL),(16,772,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(16,773,'BRG VIP IP','10.141.129.249',NULL),(16,774,'BRG VIP Port','8887',NULL),(17,775,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(17,776,'UIS VIP IP','10.141.129.247',NULL),(17,777,'UIS VIP Port','8853',NULL),(17,778,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(17,779,'EAS VIP IP','10.141.129.248',NULL),(17,780,'EAS VIP Port','8853',NULL),(17,781,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(17,783,'BRG VIP Port','8888',NULL),(1,784,'UIS VIP hostname','gbrpmsuist00.corp.internal',NULL),(1,785,'UIS VIP IP','10.141.129.247',NULL),(1,786,'UIS VIP Port','8854',NULL),(1,787,'EAS VIP hostname','gbrpmseast00.corp.internal',NULL),(1,788,'EAS VIP IP','10.141.129.248',NULL),(1,789,'EAS VIP Port','8854',NULL),(1,790,'BRG VIP hostname','gbrpmsbrgt00.corp.internal',NULL),(1,791,'BRG VIP IP','10.141.129.249',NULL),(1,792,'BRG VIP Port','8889',NULL);
/*!40000 ALTER TABLE `cm_subenvironmentconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_subenvironmenttype`
--

DROP TABLE IF EXISTS `cm_subenvironmenttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_subenvironmenttype` (
  `SubEnvironmentTypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SubEnvironmentTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SubEnvironmentTypeID`),
  UNIQUE KEY `UQ_CM_SubEnvironmentType_SubEnvironmentTypeName` (`SubEnvironmentTypeName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_subenvironmenttype`
--

LOCK TABLES `cm_subenvironmenttype` WRITE;
/*!40000 ALTER TABLE `cm_subenvironmenttype` DISABLE KEYS */;
INSERT INTO `cm_subenvironmenttype` VALUES (1,'MAIN'),(2,'XLeg'),(3,'YLeg'),(4,'ZLeg');
/*!40000 ALTER TABLE `cm_subenvironmenttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_vip`
--

DROP TABLE IF EXISTS `cm_vip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_vip` (
  `VIPID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VIPName` varchar(50) NOT NULL,
  `NodeID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`VIPID`),
  UNIQUE KEY `UQ_CM_Server_ServerName` (`VIPName`),
  KEY `NodeID` (`NodeID`),
  CONSTRAINT `cm_vip_ibfk_1` FOREIGN KEY (`NodeID`) REFERENCES `cm_node` (`NodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_vip`
--

LOCK TABLES `cm_vip` WRITE;
/*!40000 ALTER TABLE `cm_vip` DISABLE KEYS */;
INSERT INTO `cm_vip` VALUES (1,'gbrpmsuist00.corp.internal',134),(2,'gbrpmseast00.corp.internal',135),(3,'gbrpmsbrgt00.corp.internal',136);
/*!40000 ALTER TABLE `cm_vip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `environment_release_view`
--

DROP TABLE IF EXISTS `environment_release_view`;
/*!50001 DROP VIEW IF EXISTS `environment_release_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `environment_release_view` AS SELECT 
 1 AS `EnvironmentName`,
 1 AS `ReleaseName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `environment_server_view`
--

DROP TABLE IF EXISTS `environment_server_view`;
/*!50001 DROP VIEW IF EXISTS `environment_server_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `environment_server_view` AS SELECT 
 1 AS `EnvironmentName`,
 1 AS `ServerName`,
 1 AS `ServerTypeName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `package_view`
--

DROP TABLE IF EXISTS `package_view`;
/*!50001 DROP VIEW IF EXISTS `package_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `package_view` AS SELECT 
 1 AS `packageID`,
 1 AS `PackageName`,
 1 AS `PackageTypeName`,
 1 AS `ServerTypeName`,
 1 AS `ReleaseName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `release_contents_view`
--

DROP TABLE IF EXISTS `release_contents_view`;
/*!50001 DROP VIEW IF EXISTS `release_contents_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `release_contents_view` AS SELECT 
 1 AS `ReleaseName`,
 1 AS `PackageTypeName`,
 1 AS `PackageName`,
 1 AS `ServerTypeName`,
 1 AS `ComponentName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `releasedata_view`
--

DROP TABLE IF EXISTS `releasedata_view`;
/*!50001 DROP VIEW IF EXISTS `releasedata_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `releasedata_view` AS SELECT 
 1 AS `ReleaseName`,
 1 AS `DataTypeName`,
 1 AS `ReleaseParam`,
 1 AS `ReleaseValue`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'TEST_ROLE_1'),(4,'TEST_ROLE_2');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `servertype_view`
--

DROP TABLE IF EXISTS `servertype_view`;
/*!50001 DROP VIEW IF EXISTS `servertype_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `servertype_view` AS SELECT 
 1 AS `ServerTypeName`,
 1 AS `ServerName`,
 1 AS `EnvironmentName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'richard.davis@boots.co.uk',1,'$2a$06$YqQHNQgPEvAxYgeNtFCiZem6VPcCvUA90nkxtZsOwNEnWlgKPqDUK','rich'),(2,'duncan.eatch@boots.co.uk',1,'$2a$06$MBruHWlhdHs45qezRoF7qe.paM0eQDtxl6ueOc7KDrXqw9LMTmTu6','duncan');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2),(1,4),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cmdbnode'
--
/*!50003 DROP PROCEDURE IF EXISTS `AddLeg` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddLeg`(inEnvironmentName varchar(50), inSubEnvTypeName varchar(50))
BEGIN  

IF ((select count(*)  
	from cm_subenvironment 
    where SubEnvironmentTypeID = (select SubEnvironmentTypeID from cm_subenvironmenttype where SubEnvironmentTypeName = inSubEnvTypeName)
    and environmentID = (select environmentID from cm_environment where EnvironmentName = inEnvironmentName )
    ) 
    = 0) THEN
	insert into cm_subenvironment (SubEnvironmentTypeID, environmentID) values (
	(select SubEnvironmentTypeID from cm_subenvironmenttype where SubEnvironmentTypeName = inSubEnvTypeName)
	,
	(select environmentID from cm_environment where EnvironmentName = inEnvironmentName )
	);
else
	select 'Leg Exists';
End if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddServer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddServer`(inServerName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50), inServerTypeID bigint)
BEGIN   

IF ((select count(*) from cm_server where servername = inServerName) = 0) THEN
	Insert into cm_node (NodeType) values ('Server');
	Insert into cm_server (servername, servertypeid, nodeid) values (inServerName, inServerTypeID, (select max(nodeid) from cm_node));
else
	Select 'Server existed';
END IF;

IF (
	(select count(*) 
    from cm_node_subenvironment 
    where NodeID = 		(select NodeID from cm_server where servername = inServerName)
	and SubenvironmentID = 	(select SubEnvironmentID 
							from cm_subenvironment SEnv
							left join cm_environment E on senv.environmentID = e.environmentID
							left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
							where e.EnvironmentName = inEnvironmentName
							and styp.subenvironmenttypename = inSubEnvTypeName)
	)
	=0) THEN
	insert into cm_node_subenvironment (NodeID, SubEnvironmentID) values
	(
		(
        select NodeID from cm_server where servername = inServerName
        )
        ,(
        select SubEnvironmentID 
		from cm_subenvironment SEnv
		left join cm_environment E on senv.environmentID = e.environmentID
		left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
		where e.EnvironmentName = inEnvironmentName
		and styp.subenvironmenttypename = inSubEnvTypeName
		)
	);
ELSE
	Select 'Server link existed';
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddVIP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddVIP`(inVIPName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50))
BEGIN   

IF ((select count(*) from cm_vip where vipname = inVIPName) = 0) THEN
	Insert into cm_node (NodeType) values ('VIP');
	Insert into cm_vip (vipname, nodeid) values (inVIPName,  (select max(nodeid) from cm_node));
else
	Select 'VIP existed';
END IF;

IF (
	(select count(*) 
    from cm_node_subenvironment 
    where NodeID = 		(select NodeID from cm_vip where vipname = inVIPName)
	and SubenvironmentID = 	(select SubEnvironmentID 
							from cm_subenvironment SEnv
							left join cm_environment E on senv.environmentID = e.environmentID
							left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
							where e.EnvironmentName = inEnvironmentName
							and styp.subenvironmenttypename = inSubEnvTypeName)
	)
	=0) THEN
	insert into cm_node_subenvironment (nodeID, subenvironmentID) values
	(
		(
        select NodeID from cm_vip where vipname = inVIPName
        )
        ,(
        select SubEnvironmentID 
		from cm_subenvironment SEnv
		left join cm_environment E on senv.environmentID = e.environmentID
		left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
		where e.EnvironmentName = inEnvironmentName
		and styp.subenvironmenttypename = inSubEnvTypeName
		)
	);
ELSE
	Select 'VIP link existed';
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssignIP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AssignIP`(inServerName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50), inIPAddr varchar(50), inIPTypeName varchar(50))
BEGIN   

IF(		(
		select 	count(*) 
		from 	cm_nodeIP 
		where 	nodesubid = 	(select 	NodeSubID 
								from 	cm_node_subenvironment
								where 	NodeID = 			(select nodeid from cm_Server where servername = inServerName)
								and 	SubEnvironmentID = 	(select SubEnvironmentID 
															from cm_subenvironment SEnv
															left join cm_environment E on senv.environmentID = e.environmentID
															left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
															where e.EnvironmentName = inEnvironmentName
															and styp.subenvironmenttypename = inSubEnvTypeName
															)
								)
		and nodeipaddress = inIPAddr
		and nodeiptype = inIPTypeName
		) = 0) THEN
        
    insert into cm_nodeIP (nodesubid, nodeipaddress, nodeiptype) 
    values(
			(select 	NodeSubID 
			from 	cm_node_subenvironment
			where 	NodeID = 			(select nodeid from cm_Server where servername = inServerName)
			and 	SubEnvironmentID = 	(select SubEnvironmentID 
										from cm_subenvironment SEnv
										left join cm_environment E on senv.environmentID = e.environmentID
										left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
										where e.EnvironmentName = inEnvironmentName
										and styp.subenvironmenttypename = inSubEnvTypeName
										)
			)
		,    inIPAddr
		,    inIPTypeName
		);
else
	Select 'IP existed' ;	
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssignServerIP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AssignServerIP`(inServerName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50), inIPAddr varchar(50), inIPTypeName varchar(50))
BEGIN   

IF(		(
		select 	count(*) 
		from 	cm_nodeIP 
		where 	nodesubid = 	(select 	NodeSubID 
								from 	cm_node_subenvironment
								where 	NodeID = 			(select nodeid from cm_Server where servername = inServerName)
								and 	SubEnvironmentID = 	(select SubEnvironmentID 
															from cm_subenvironment SEnv
															left join cm_environment E on senv.environmentID = e.environmentID
															left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
															where e.EnvironmentName = inEnvironmentName
															and styp.subenvironmenttypename = inSubEnvTypeName
															)
								)
		and nodeipaddress = inIPAddr
		and nodeiptype = inIPTypeName
		) = 0) THEN
        
    insert into cm_nodeIP (nodesubid, nodeipaddress, nodeiptype) 
    values(
			(select 	NodeSubID 
			from 	cm_node_subenvironment
			where 	NodeID = 			(select nodeid from cm_Server where servername = inServerName)
			and 	SubEnvironmentID = 	(select SubEnvironmentID 
										from cm_subenvironment SEnv
										left join cm_environment E on senv.environmentID = e.environmentID
										left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
										where e.EnvironmentName = inEnvironmentName
										and styp.subenvironmenttypename = inSubEnvTypeName
										)
			)
		,    inIPAddr
		,    inIPTypeName
		);
else
	Select 'IP existed' ;	
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssignVIPIP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AssignVIPIP`(inVIPName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50), inIPAddr varchar(50), inIPTypeName varchar(50))
BEGIN   

IF(		(
		select 	count(*) 
		from 	cm_nodeIP 
		where 	nodesubid = 	(select 	NodeSubID 
								from 	cm_node_subenvironment
								where 	NodeID = 			(select nodeid from cm_VIP where VIPname = inVIPName)
								and 	SubEnvironmentID = 	(select SubEnvironmentID 
															from cm_subenvironment SEnv
															left join cm_environment E on senv.environmentID = e.environmentID
															left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
															where e.EnvironmentName = inEnvironmentName
															and styp.subenvironmenttypename = inSubEnvTypeName
															)
								)
		and nodeipaddress = inIPAddr
		and nodeiptype = inIPTypeName
		) = 0) THEN
        
    insert into cm_nodeIP (nodesubid, nodeipaddress, nodeiptype) 
    values(
			(select 	NodeSubID 
			from 	cm_node_subenvironment
			where 	NodeID = 			(select nodeid from cm_vip where VIPname = inVIPName)
			and 	SubEnvironmentID = 	(select SubEnvironmentID 
										from cm_subenvironment SEnv
										left join cm_environment E on senv.environmentID = e.environmentID
										left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
										where e.EnvironmentName = inEnvironmentName
										and styp.subenvironmenttypename = inSubEnvTypeName
										)
			)
		,    inIPAddr
		,    inIPTypeName
		);
else
	Select 'IP existed' ;	
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetHiera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHiera`()
BEGIN   
	DECLARE EnvTag VARCHAR(50) DEFAULT '{ENVID}';
    DECLARE ServTypeTag VARCHAR(50) DEFAULT '{ServType}';
    DECLARE AppTag VARCHAR(50) DEFAULT '{AppName}';
    DECLARE ReleaseTag VARCHAR(50) DEFAULT '{Release}';
    Declare ParamNameTag VARCHAR(50) DEFAULT '{ParamName}';
    Declare LegTag VARCHAR(50) DEFAULT '{LegName}';
    -- DECLARE mystart  INT unsigned DEFAULT 1;  
    -- DECLARE myfinish INT unsigned DEFAULT 10;

    -- SELECT  mystart, myfinish;
	select 		replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter) 	as 'HieraAddress'
    , 			g.globalConfigValue 		as 'HieraValue'
    from 		cm_globalconfig	g
    where 		recursivebyenv <> 1
    and 		recursivebysubenv <> 1
    and 		recursivebyrel <> 1
	union all
	select 		replace(replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter),envtag,e.environmentname) 	as 'HieraAddress'
    , 			g.globalConfigValue 		as 'HieraValue'
    from 		cm_globalconfig	g
    left join 	cm_environment e on e.environmentname is not null
    where 		recursivebyenv = 1
	union all
	select 		replace(replace(replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter),LegTag,styp.subenvironmenttypename),envtag,e.environmentname) 	as 'HieraAddress'
    , 			g.globalConfigValue 		as 'HieraValue'
    from 		cm_globalconfig	g
    left join 	cm_subenvironment se on se.subenvironmentid is not null
    left join 	cm_environment e on se.environmentID = e.environmentID
    left join 	cm_subenvironmentType styp on styp.subenvironmenttypeid = se.subenvironmenttypeid
    where 		recursivebysubenv = 1
	union all
	select 		replace(replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter),ReleaseTag,r.releasename) 	as 'HieraAddress'
    , 			g.globalConfigValue 		as 'HieraValue'
    from 		cm_globalconfig	g
    left join 	cm_release r on r.releaseid is not null
    where 		recursivebyRel = 1
	union all
    select 		replace(replace(ec.subEnvConfigHieraAddress,ParamNameTag,ec.subEnvConfigParameter), envtag, e.environmentName) 	as 'HieraAddress'
    , 			replace(ec.subEnvConfigValue, EnvTag, e.environmentname)	as 'HieraValue' 
    from 		cm_environment 	e
    left join	cm_subenvironment se on se.environmentid = e.environmentid
	left join 	cm_subenvironmentconfig ec on se.subenvironmentid = ec.subenvironmentid
	union all
	select 		replace(replace(replace(sc.ServConfigHieraAddress, servtypetag, st.servertypename),EnvTag,e.environmentname),ParamNameTag,sc.ServConfigParameter)	as 'HieraAddress'
    , 			sc.ServConfigValue 																						as 'HieraValue'
    from 		cm_environment 	e
    left join	cm_subenvironment subE on subE.environmentid = e.environmentid
	-- left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
    left join 	cm_node_subenvironment nse on subE.subenvironmentid = nse.subenvironmentid
    left join	cm_node 		n	on n.nodeid = nse.nodeid
    left join 	cm_server 		s	on nse.nodeid = s.nodeid
	-- left join 	cm_server 		s	on sse.serverid = s.serverid
	left join 	cm_servertype 	st	on s.servertypeid = st.servertypeid
	left join 	cm_serverconfig sc 	on s.serverid = sc.serverid
	union all
	select replace(replace(replace(replace(replace(cc.CompConfigHieraAddress, AppTag, c.componentName), ReleaseTag, r.releasename), EnvTag, e.environmentname), servtypetag, st.servertypename),ParamNameTag,cc.CompConfigParameter) as 'HieraAddress'
    , cc.CompConfigValue as 'HieraValue'
    from 		cm_environment e
    left join	cm_subenvironment subE on e.environmentid = sube.environmentid
	left join 	cm_release r 		on sube.releaseid = r.releaseid
	left join 	cm_package p 		on r.releaseid = p.releaseid
    left join	cm_servertype st	on p.servertypeID = st.servertypeid
	left join 	cm_component c 		on p.packageid = c.packageid
	left join 	cm_componentconfig cc on c.componentid = cc.componentID
    union all
    select		replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_release r
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
    where 		recursivebyenv <> 1
    and 		recursivebysubenv <> 1
	union all
    select		replace(replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename),envtag,e.environmentname) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_release r
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
    left join 	cm_subenvironment se on se.releaseid  = r.releaseID
    left join 	cm_environment e on se.environmentID = e.environmentID
    where 		recursivebyenv = 1
	union all
    select		replace(replace(replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename),LegTag,styp.subenvironmenttypename),envtag,e.environmentname) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_release r
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
	left join 	cm_subenvironment se on se.releaseid  = r.releaseID
    left join 	cm_environment e on se.environmentID = e.environmentID
    left join 	cm_subenvironmentType styp on styp.subenvironmenttypeid = se.subenvironmenttypeid
    where 		recursivebysubenv = 1
	;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetHieraByEnvironment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHieraByEnvironment`(EnvironmentName varchar(50))
BEGIN   
	DECLARE EnvTag VARCHAR(50) DEFAULT '{ENVID}';
    DECLARE ServTypeTag VARCHAR(50) DEFAULT '{ServType}';
    DECLARE AppTag VARCHAR(50) DEFAULT '{AppName}';
    DECLARE ReleaseTag VARCHAR(50) DEFAULT '{Release}';
    Declare ParamNameTag VARCHAR(50) DEFAULT '{ParamName}';

	select 	replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter) 	as 'HieraAddress'
    , 		g.globalConfigValue 		as 'HieraValue'
    from 	cm_globalconfig	g
	union all
    select 		replace(replace(ec.subEnvConfigHieraAddress,ParamNameTag,ec.subEnvConfigParameter), envtag, e.environmentName) 	as 'HieraAddress'
    , 			replace(ec.subEnvConfigValue, EnvTag, e.environmentname)	as 'HieraValue' 
    from 		cm_environment 	e
    left join	cm_subenvironment se on se.environmentid = e.environmentid
	left join 	cm_subenvironmentconfig ec on se.subenvironmentid = ec.subenvironmentid
    where 		e.environmentname = EnvironmentName
	union all
	select 		replace(replace(replace(sc.ServConfigHieraAddress, servtypetag, st.servertypename),EnvTag,e.environmentname),ParamNameTag,sc.ServConfigParameter)	as 'HieraAddress'
    , 			sc.ServConfigValue 																						as 'HieraValue'
    from 		cm_environment 	e
    left join	cm_subenvironment subE on subE.environmentid = e.environmentid
    	-- left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
    left join 	cm_node_subenvironment nse on subE.subenvironmentid = nse.subenvironmentid
    left join	cm_node 		n	on n.nodeid = nse.nodeid
    left join 	cm_server 		s	on nse.nodeid = s.nodeid
	-- left join 	cm_server 		s	on sse.serverid = s.serverid
	left join 	cm_servertype 	st	on s.servertypeid = st.servertypeid
	left join 	cm_serverconfig sc 	on s.serverid = sc.serverid
    where 		e.environmentname = EnvironmentName
	union all
	select replace(replace(replace(replace(replace(cc.CompConfigHieraAddress, AppTag, c.componentName), ReleaseTag, r.releasename), EnvTag, e.environmentname), servtypetag, st.servertypename),ParamNameTag,cc.CompConfigParameter) as 'HieraAddress'
    , cc.CompConfigValue as 'HieraValue'
    from 		cm_environment e
    left join	cm_subenvironment subE on e.environmentid = sube.environmentid
	left join 	cm_release r 		on sube.releaseid = r.releaseid
	left join 	cm_package p 		on r.releaseid = p.releaseid
    left join	cm_servertype st	on p.servertypeID = st.servertypeid
	left join 	cm_component c 		on p.packageid = c.packageid
	left join 	cm_componentconfig cc on c.componentid = cc.componentID
    where 		e.environmentname = EnvironmentName
	union all
    select		replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_environment e 
	left join	cm_subenvironment subE on e.environmentid = sube.environmentid
    left join 	cm_release r on sube.releaseid = r.releaseid 
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
    where 		e.environmentname = EnvironmentName
	;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetHieraByRelease` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHieraByRelease`(ReleaseName varchar(50))
BEGIN   
	DECLARE EnvTag VARCHAR(50) DEFAULT '{ENVID}';
    DECLARE ServTypeTag VARCHAR(50) DEFAULT '{ServType}';
    DECLARE AppTag VARCHAR(50) DEFAULT '{AppName}';
    DECLARE ReleaseTag VARCHAR(50) DEFAULT '{Release}';
    Declare ParamNameTag VARCHAR(50) DEFAULT '{ParamName}';
	
    select 	replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter) 	as 'HieraAddress'
    , 		g.globalConfigValue 		as 'HieraValue'
    from 	cm_globalconfig	g
	union all
    select 		replace(replace(ec.subEnvConfigHieraAddress,ParamNameTag,ec.subEnvConfigParameter), envtag, e.environmentName) 	as 'HieraAddress'
    , 			replace(ec.subEnvConfigValue, EnvTag, e.environmentname)	as 'HieraValue'  
    from 		cm_environment 	e
    left join	cm_subenvironment se on se.environmentid = e.environmentid
	left join 	cm_subenvironmentconfig ec on se.subenvironmentid = ec.subenvironmentid
    left join	cm_release 	r			on se.releaseID = r.releaseID
    where 		r.releasename = ReleaseName
	union all
	select 		replace(replace(replace(sc.ServConfigHieraAddress, servtypetag, st.servertypename),EnvTag,e.environmentname),ParamNameTag,sc.ServConfigParameter)	as 'HieraAddress'
    , 			sc.ServConfigValue 																						as 'HieraValue'
    from 		cm_environment 	e
    left join	cm_subenvironment subE on subE.environmentid = e.environmentid
	-- left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
    left join 	cm_node_subenvironment nse on subE.subenvironmentid = nse.subenvironmentid
    left join	cm_node 		n	on n.nodeid = nse.nodeid
    left join 	cm_server 		s	on nse.nodeid = s.nodeid
	-- left join 	cm_server 		s	on sse.serverid = s.serverid
	left join 	cm_servertype 	st	on s.servertypeid = st.servertypeid
	left join 	cm_serverconfig sc 	on s.serverid = sc.serverid
    left join	cm_release 		r	on sube.releaseID = r.releaseID
    where 		r.releasename = ReleaseName
	union all
	select replace(replace(replace(replace(replace(cc.CompConfigHieraAddress, AppTag, c.componentName), ReleaseTag, r.releasename), EnvTag, e.environmentname), servtypetag, st.servertypename),ParamNameTag,cc.CompConfigParameter) as 'HieraAddress'
    , cc.CompConfigValue as 'HieraValue'
    from 		cm_environment e
    left join	cm_subenvironment subE on e.environmentid = sube.environmentid
	left join 	cm_release r 		on sube.releaseid = r.releaseid
	left join 	cm_package p 		on r.releaseid = p.releaseid
    left join	cm_servertype st	on p.servertypeID = st.servertypeid
	left join 	cm_component c 		on p.packageid = c.packageid
	left join 	cm_componentconfig cc on c.componentid = cc.componentID
    where 		r.releasename = ReleaseName
	union all
    select		replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_release r 
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
    where 		r.releasename = ReleaseName
	;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SearchConfig` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchConfig`(SearchText varchar(50))
begin

	select 	'Global' 					as 'Area'
    ,		g.globalconfigid				as 'ID'
    ,		g.globalConfigHieraAddress 	as 'HieraAddress'
    , 		g.globalConfigValue 		as 'HieraValue'
    ,		g.globalConfigParameter		as 'Parameter'
    from 	cm_globalconfig	g
    where g.globalconfighieraaddress like concat ( '%', SearchText, '%')
    or g.globalConfigValue like concat ( '%', SearchText, '%')
    or g.globalConfigParameter like concat ( '%', SearchText, '%')
	union all
    select 		'SubEnv' 				as 'Area'
    ,		ec.subenvconfigid				as 'ID'
    , 		ec.subEnvConfigHieraAddress	as 'HieraAddress'
    , 		ec.subEnvConfigValue		as 'HieraValue' 
    ,		ec.subEnvConfigParameter	as 'Parameter'
    from 		cm_environment 	e
    left join	cm_subenvironment se on se.environmentid = e.environmentid
	left join 	cm_subenvironmentconfig ec on se.subenvironmentid = ec.subenvironmentid
	where ec.subEnvConfigHieraAddress like concat ( '%', SearchText, '%')
    or ec.subEnvConfigValue like concat ( '%', SearchText, '%')
    or ec.subEnvConfigParameter like concat ( '%', SearchText, '%')
	union all
	select 		'Server' 				as 'Area'
    ,		sc.servconfigid				as 'ID'
    ,		sc.ServConfigHieraAddress	as 'HieraAddress'
    , 		sc.ServConfigValue			as 'HieraValue'
    ,		sc.ServConfigParameter		as 'Parameter'
    from 		cm_environment 	e
    left join	cm_subenvironment subE on subE.environmentid = e.environmentid
	-- left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
    left join 	cm_node_subenvironment nse on subE.subenvironmentid = nse.subenvironmentid
    left join	cm_node 		n	on n.nodeid = nse.nodeid
    left join 	cm_server 		s	on nse.nodeid = s.nodeid
	-- left join 	cm_server 		s	on sse.serverid = s.serverid
	left join 	cm_servertype 	st	on s.servertypeid = st.servertypeid
	left join 	cm_serverconfig sc 	on s.serverid = sc.serverid
	where sc.ServConfigHieraAddress like concat ( '%', SearchText, '%')
    or sc.ServConfigValue like concat ( '%', SearchText, '%')
    or sc.ServConfigParameter like concat ( '%', SearchText, '%')
    union all
	select 	'Component' 				as 'Area'
    ,		cc.compconfigid				as 'ID'
    ,		cc.CompConfigHieraAddress	as 'HieraAddress'
    , 		cc.CompConfigValue 			as 'HieraValue'
    ,		cc.CompConfigParameter		as 'Parameter'
    from 		cm_environment e
    left join	cm_subenvironment subE on e.environmentid = sube.environmentid
	left join 	cm_release r 		on sube.releaseid = r.releaseid
	left join 	cm_package p 		on r.releaseid = p.releaseid
    left join	cm_servertype st	on p.servertypeID = st.servertypeid
	left join 	cm_component c 		on p.packageid = c.packageid
	left join 	cm_componentconfig cc on c.componentid = cc.componentID
	where cc.CompConfigHieraAddress like concat ( '%', SearchText, '%')
    or cc.CompConfigValue like concat ( '%', SearchText, '%')
    or cc.CompConfigParameter like concat ( '%', SearchText, '%')
    union all
    select	'Release' 					as 'Area'
    ,		rc.relconfigid				as 'ID'
    ,		rc.relconfigHieraAddress 	as 'HieraAddress'
    ,		rc.relconfigvalue  			as 'HieraValue'
    ,		rc.relconfigparameter		as 'Parameter'
    from		cm_release r
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
	where rc.relconfigHieraAddress like concat ( '%', SearchText, '%')
    or rc.relconfigvalue like concat ( '%', SearchText, '%')
    or rc.relconfigparameter like concat ( '%', SearchText, '%')
	;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SearchHiera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchHiera`(SearchText varchar(50))
BEGIN
	DECLARE EnvTag VARCHAR(50) DEFAULT '{ENVID}';
    DECLARE ServTypeTag VARCHAR(50) DEFAULT '{ServType}';
    DECLARE AppTag VARCHAR(50) DEFAULT '{AppName}';
    DECLARE ReleaseTag VARCHAR(50) DEFAULT '{Release}';
    Declare ParamNameTag VARCHAR(50) DEFAULT '{ParamName}';
    -- DECLARE mystart  INT unsigned DEFAULT 1;  
    -- DECLARE myfinish INT unsigned DEFAULT 10;

    -- SELECT  mystart, myfinish;
	select 	'Global' as 'Area',
    replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter) 	as 'HieraAddress'
    , 		g.globalConfigValue 		as 'HieraValue'
    from 	cm_globalconfig	g
    where g.globalconfighieraaddress like concat ( '%', SearchText, '%')
    or g.globalConfigValue like concat ( '%', SearchText, '%')
    or g.globalConfigParameter like concat ( '%', SearchText, '%')
	union all
    select 		'SubEnv' as 'Area',
    replace(replace(ec.subEnvConfigHieraAddress,ParamNameTag,ec.subEnvConfigParameter), envtag, e.environmentName) 	as 'HieraAddress'
    , 			replace(ec.subEnvConfigValue, EnvTag, e.environmentname)	as 'HieraValue' 
    from 		cm_environment 	e
    left join	cm_subenvironment se on se.environmentid = e.environmentid
	left join 	cm_subenvironmentconfig ec on se.subenvironmentid = ec.subenvironmentid
	where ec.subEnvConfigHieraAddress like concat ( '%', SearchText, '%')
    or ec.subEnvConfigValue like concat ( '%', SearchText, '%')
    or ec.subEnvConfigParameter like concat ( '%', SearchText, '%')
	union all
	select 		'Server' as 'Area',
    replace(replace(replace(sc.ServConfigHieraAddress, servtypetag, st.servertypename),EnvTag,e.environmentname),ParamNameTag,sc.ServConfigParameter)	as 'HieraAddress'
    , 			sc.ServConfigValue 																						as 'HieraValue'
    from 		cm_environment 	e
    left join	cm_subenvironment subE on subE.environmentid = e.environmentid
	-- left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
    left join 	cm_node_subenvironment nse on subE.subenvironmentid = nse.subenvironmentid
    left join	cm_node 		n	on n.nodeid = nse.nodeid
    left join 	cm_server 		s	on nse.nodeid = s.nodeid
	-- left join 	cm_server 		s	on sse.serverid = s.serverid
	left join 	cm_servertype 	st	on s.servertypeid = st.servertypeid
	left join 	cm_serverconfig sc 	on s.serverid = sc.serverid
	where sc.ServConfigHieraAddress like concat ( '%', SearchText, '%')
    or sc.ServConfigValue like concat ( '%', SearchText, '%')
    or sc.ServConfigParameter like concat ( '%', SearchText, '%')
    union all
	select 'Component' as 'Area',
    replace(replace(replace(replace(replace(cc.CompConfigHieraAddress, AppTag, c.componentName), ReleaseTag, r.releasename), EnvTag, e.environmentname), servtypetag, st.servertypename),ParamNameTag,cc.CompConfigParameter) as 'HieraAddress'
    , cc.CompConfigValue as 'HieraValue'
    from 		cm_environment e
    left join	cm_subenvironment subE on e.environmentid = sube.environmentid
	left join 	cm_release r 		on sube.releaseid = r.releaseid
	left join 	cm_package p 		on r.releaseid = p.releaseid
    left join	cm_servertype st	on p.servertypeID = st.servertypeid
	left join 	cm_component c 		on p.packageid = c.packageid
	left join 	cm_componentconfig cc on c.componentid = cc.componentID
	where cc.CompConfigHieraAddress like concat ( '%', SearchText, '%')
    or cc.CompConfigValue like concat ( '%', SearchText, '%')
    or cc.CompConfigParameter like concat ( '%', SearchText, '%')
    union all
    select		'Release' as 'Area',
    replace(replace(rc.relconfigHieraAddress,ParamNameTag,rc.relconfigparameter), ReleaseTag,r.releasename) as 'HieraAddress'
    ,			rc.relconfigvalue  as 'HieraValue'
    from		cm_release r
    left join	cm_releaseconfig rc on rc.releaseid = r.releaseID
	where rc.relconfigHieraAddress like concat ( '%', SearchText, '%')
    or rc.relconfigvalue like concat ( '%', SearchText, '%')
    or rc.relconfigparameter like concat ( '%', SearchText, '%')
	;
End ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `environment_release_view`
--

/*!50001 DROP VIEW IF EXISTS `environment_release_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `environment_release_view` AS select `e`.`EnvironmentName` AS `EnvironmentName`,`r`.`ReleaseName` AS `ReleaseName` from ((`cm_environment` `e` left join `cm_subenvironment` `se` on((`se`.`EnvironmentID` = `e`.`EnvironmentID`))) left join `cm_release` `r` on((`se`.`ReleaseID` = `r`.`ReleaseID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `environment_server_view`
--

/*!50001 DROP VIEW IF EXISTS `environment_server_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `environment_server_view` AS select `e`.`EnvironmentName` AS `EnvironmentName`,`s`.`ServerName` AS `ServerName`,`st`.`ServerTypeName` AS `ServerTypeName` from (((((`cm_environment` `e` left join `cm_subenvironment` `sube` on((`sube`.`EnvironmentID` = `e`.`EnvironmentID`))) left join `cm_node_subenvironment` `nse` on((`sube`.`SubEnvironmentID` = `nse`.`SubEnvironmentID`))) left join `cm_node` `n` on((`n`.`NodeID` = `nse`.`NodeID`))) left join `cm_server` `s` on((`n`.`NodeID` = `s`.`NodeID`))) left join `cm_servertype` `st` on((`s`.`ServerTypeID` = `st`.`ServerTypeID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `package_view`
--

/*!50001 DROP VIEW IF EXISTS `package_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `package_view` AS select `p`.`PackageID` AS `packageID`,`p`.`PackageName` AS `PackageName`,`pt`.`PackageTypeName` AS `PackageTypeName`,`st`.`ServerTypeName` AS `ServerTypeName`,`r`.`ReleaseName` AS `ReleaseName` from (((`cm_package` `p` left join `cm_packagetype` `pt` on((`p`.`PackageTypeID` = `pt`.`PackageTypeID`))) left join `cm_servertype` `st` on((`p`.`ServerTypeID` = `st`.`ServerTypeID`))) left join `cm_release` `r` on((`p`.`ReleaseID` = `r`.`ReleaseID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `release_contents_view`
--

/*!50001 DROP VIEW IF EXISTS `release_contents_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `release_contents_view` AS select `r`.`ReleaseName` AS `ReleaseName`,`pt`.`PackageTypeName` AS `PackageTypeName`,`p`.`PackageName` AS `PackageName`,`s`.`ServerTypeName` AS `ServerTypeName`,`c`.`ComponentName` AS `ComponentName` from ((((`cm_release` `r` left join `cm_package` `p` on((`r`.`ReleaseID` = `p`.`ReleaseID`))) left join `cm_servertype` `s` on((`p`.`ServerTypeID` = `s`.`ServerTypeID`))) left join `cm_packagetype` `pt` on((`p`.`PackageTypeID` = `pt`.`PackageTypeID`))) left join `cm_component` `c` on((`p`.`PackageID` = `c`.`PackageID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `releasedata_view`
--

/*!50001 DROP VIEW IF EXISTS `releasedata_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `releasedata_view` AS select `r`.`ReleaseName` AS `ReleaseName`,`rdt`.`DataTypeName` AS `DataTypeName`,`rd`.`ReleaseParam` AS `ReleaseParam`,`rd`.`ReleaseValue` AS `ReleaseValue` from ((`cm_release` `r` left join `cm_releasedata` `rd` on((`r`.`ReleaseID` = `rd`.`ReleaseID`))) left join `cm_releasedatatype` `rdt` on((`rd`.`DataTypeID` = `rdt`.`DataTypeID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `servertype_view`
--

/*!50001 DROP VIEW IF EXISTS `servertype_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `servertype_view` AS select `st`.`ServerTypeName` AS `ServerTypeName`,`s`.`ServerName` AS `ServerName`,`e`.`EnvironmentName` AS `EnvironmentName` from (((((`cm_servertype` `st` left join `cm_server` `s` on((`st`.`ServerTypeID` = `s`.`ServerTypeID`))) left join `cm_node` `n` on((`s`.`NodeID` = `s`.`NodeID`))) left join `cm_node_subenvironment` `nse` on((`s`.`NodeID` = `nse`.`NodeID`))) left join `cm_subenvironment` `sube` on((`sube`.`SubEnvironmentID` = `nse`.`SubEnvironmentID`))) left join `cm_environment` `e` on((`sube`.`EnvironmentID` = `e`.`EnvironmentID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-11  8:57:16
