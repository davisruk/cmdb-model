CREATE DATABASE  IF NOT EXISTS `cmdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cmdb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: envcmdb
-- ------------------------------------------------------
-- Server version	5.7.11

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_environment`
--

LOCK TABLES `cm_environment` WRITE;
/*!40000 ALTER TABLE `cm_environment` DISABLE KEYS */;
INSERT INTO `cm_environment` VALUES (11,'FT1',1),(12,'FT2',1),(13,'FT3',1),(14,'FT4',1),(15,'FT5',1),(16,'IT1',2),(17,'IT2',2),(18,'IT3',2),(19,'IT4',2),(20,'IT5',2),(21,'ST1',4),(22,'Training1',5),(23,'Training2',5),(24,'UT1',6),(25,'UT2',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_environmenttype`
--

LOCK TABLES `cm_environmenttype` WRITE;
/*!40000 ALTER TABLE `cm_environmenttype` DISABLE KEYS */;
INSERT INTO `cm_environmenttype` VALUES (1,'Functional Test'),(2,'Integration Test'),(4,'Performance Testing'),(3,'Production'),(5,'Training'),(6,'Unit Testing');
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
  PRIMARY KEY (`GlobalConfigID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_globalconfig`
--

LOCK TABLES `cm_globalconfig` WRITE;
/*!40000 ALTER TABLE `cm_globalconfig` DISABLE KEYS */;
INSERT INTO `cm_globalconfig` VALUES (4,'root_pass','removed','ROOT:{ParamName}:'),(5,'specpath','/root/spec','ROOT:{ParamName}:'),(6,'repo_name','rhel-6.4-x86_64-puppet','ROOT:{ParamName}:-'),(7,'repo_name','columbus-uk-boots','ROOT:{ParamName}:-'),(8,'oneleonardo_jdk_release','1.6.0_37','ROOT:{ParamName}:'),(9,'oneleonardo_jboss_release','7.1.1.Final','ROOT:{ParamName}:'),(10,'oneleonardo_jboss_build','5HS.redhat6.4','ROOT:{ParamName}:'),(11,'oracle_client_version','11.2.0.3','ROOT:{ParamName}:'),(12,'httpuser','deployer','ROOT:columbus_delivery:{ParamName}:'),(13,'httppass','removed','ROOT:columbus_delivery:{ParamName}:'),(14,'httpserver','delivery.na-dc.ah.ab:8081','ROOT:columbus_delivery:{ParamName}:'),(19,'httpurl','artifactory/libs-releases-local/com/ab/oneleo/columbus','ROOT:columbus_delivery:{ParamName}:'),(20,'gid','1000','ROOT:group_tagbt:{ParamName}:'),(21,'gid','1000','ROOT:group_tagbs:{ParamName}:'),(22,'uid','1001','ROOT:user_pmsgbrtt:{ParamName}:'),(23,'gid','pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:'),(24,'groups','pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:'),(25,'home','/home/pmsgbrtt','ROOT:user_pmsgbrtt:{ParamName}:'),(26,'password','removed','ROOT:user_pmsgbrtt:{ParamName}:'),(27,'nofiles','10000','ROOT:user_pmsgbrtt:{ParamName}:'),(28,'jdk_release','1.6.0_37','ROOT:user_pmsgbrtt:{ParamName}:'),(29,'devel','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:'),(30,'boots_support','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:'),(31,'boots_performance_test','true','ROOT:user_pmsgbrtt:authorized_keys:{ParamName}:'),(32,'gid','1001','ROOT:group_pmsgbrtt:{ParamName}:'),(33,'uid','4001','ROOT:user_pmsgbmtt:{ParamName}:'),(34,'gid','pmsgbrtt','ROOT:user_pmsgbmtt:{ParamName}:'),(35,'groups','pmsgbrtt','ROOT:user_pmsgbmtt:{ParamName}:'),(36,'home','/home/pmsgbmtt','ROOT:user_pmsgbmtt:{ParamName}:'),(37,'password','removed','ROOT:user_pmsgbmtt:{ParamName}:'),(38,'boots_support','true','ROOT:user_pmsgbmtt:authorized_keys:{ParamName}:'),(39,'boots_performance_test','true','ROOT:user_pmsgbmtt:authorized_keys:{ParamName}:'),(40,'user','tagbtadm','ROOT:batchclient_test_info:{ParamName}'),(41,'group','tagbt','ROOT:batchclient_test_info:{ParamName}'),(42,'vg','rootvg','ROOT:batchclient_test_info:{ParamName}'),(43,'size_envs','1G','ROOT:batchclient_test_info:{ParamName}'),(44,'size_envf','1G','ROOT:batchclient_test_info:{ParamName}'),(45,'size_envb','1G','ROOT:batchclient_test_info:{ParamName}'),(46,'user','tagbsadm','ROOT:batchclient_stag_info:{ParamName}'),(47,'group','tagbs','ROOT:batchclient_stag_info:{ParamName}'),(48,'vg','rootvg','ROOT:batchclient_stag_info:{ParamName}'),(49,'size_envs','1G','ROOT:batchclient_stag_info:{ParamName}'),(50,'size_envf','1G','ROOT:batchclient_stag_info:{ParamName}'),(51,'size_envb','1G','ROOT:batchclient_stag_info:{ParamName}'),(52,'build_version','4.2.2','ROOT:oneleo_release_map:columbus:uk-boots:{ParamName}'),(70,'columbus','jobs/scripts/columbus','ROOT:projects_path:{ParamName}'),(71,'optimus','jobs/scripts/optimus','ROOT:projects_path:{ParamName}');
/*!40000 ALTER TABLE `cm_globalconfig` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_release`
--

LOCK TABLES `cm_release` WRITE;
/*!40000 ALTER TABLE `cm_release` DISABLE KEYS */;
INSERT INTO `cm_release` VALUES (3,'7.3.2'),(1,'7.3.4'),(2,'7.4.0'),(6,'Training-x.y.z'),(7,'UNKNOWN');
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
  `RelConfigValue` varchar(50) DEFAULT NULL,
  `RelConfigHieraAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RelConfigID`),
  KEY `IXFK_ReleaseConfig_Release` (`ReleaseID`),
  CONSTRAINT `cm_releaseconfig_ibfk_1` FOREIGN KEY (`ReleaseID`) REFERENCES `cm_release` (`ReleaseID`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_releaseconfig`
--

LOCK TABLES `cm_releaseconfig` WRITE;
/*!40000 ALTER TABLE `cm_releaseconfig` DISABLE KEYS */;
INSERT INTO `cm_releaseconfig` VALUES (25,3,'createUpdateActualProductPack','3.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(27,3,'createUpdateAdverseReaction','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(28,3,'createUpdateExemption','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(29,3,'createUpdateFormulary','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(30,3,'createUpdateGeneralParameter','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(31,3,'createUpdateLabelInstruction','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(32,3,'createUpdateMedicalCondition','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(33,3,'createUpdatePractice','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(34,3,'createUpdatePreferredActualProductPack','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(35,3,'createUpdatePreferredProductSKU','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(36,3,'createUpdatePrescriber','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(37,3,'createUpdatePrescriberType','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(38,3,'createUpdatePrescription','5.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(39,3,'createUpdatePrescription_FM','5.7-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(40,3,'createUpdatePrescriptionFormType','3.5-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(41,3,'createUpdatePrescriptionGroup','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(42,3,'createUpdateProduct','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(43,3,'createUpdateProductBarcode','3.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(44,3,'createUpdateProductFlavour','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(45,3,'createUpdateProductLogistics','3.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(46,3,'createUpdateProductSKU','3.4-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(47,3,'createUpdateRole','2.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(48,3,'createUpdateSite','3.5-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(49,3,'createUpdateStockAvailability','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(50,3,'createUpdateStoreServiceCentreLink','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(51,3,'createUpdateSupplier','3.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(52,3,'createUpdateTwinningScheme','0.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(53,3,'executeSQL','2.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(54,3,'import_IG01','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(55,3,'import_IG02','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(56,3,'import_IG03','3.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(57,3,'import_IG04','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(58,3,'import_IG06','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(59,3,'import_IG09','1.1-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(60,3,'jobrunner','2.6-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(61,3,'merge_IG01','1.3-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(62,3,'merge_IG02','1.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(63,3,'merge_IG03','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(64,3,'merge_IG04','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(65,3,'merge_IG06','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(66,3,'merge_IG09','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(67,3,'tsfn-encryptionutil','1.2-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(68,3,'OptimusMigration','1.88-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(69,3,'StockPlus_Migration','4.25-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(70,3,'StockPlusFM_Migration','6.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(71,3,'StockPlusFM_Migration_Maps','6.0-1','ROOT:talend_interfaces_{Release}:tsfn:{ParamName}'),(72,3,'IF033_01_general_practice:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(73,3,'IF033_02_general_practice_relationship:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(74,3,'IF033_03_general_dental_practitioners:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(75,3,'IF033_04_general_dental_practices:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(76,3,'IF033_05_general_dental_practitioners_relationships:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(77,3,'IF033_x_01_01_Monitor:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(78,3,'IF033_x_01_02_Download:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(79,3,'IF033_x_01_03_Extract:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(80,3,'IF033_x_01_04_Validate:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(81,3,'IF061_00_01_Download:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(82,3,'IF061_00_02_Extract:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(83,3,'IF061_01_01_DMDPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(84,3,'IF061_02_01_DMDVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(85,3,'IF061_03_01_MAPDMDToPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(86,3,'IF061_04_01_MAPDMDToVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(87,3,'IF061_05_01_MASPhysDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(88,3,'IF061_06_01_MASUnitOfMeasure:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(89,3,'IF061_07_01_MASUnits:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(90,3,'IF061_08_01_MASVirtDrugs:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(91,3,'IF061_09_01_MASPresDispList:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(92,3,'IF101_01_01_MR_adverse_reaction:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(93,3,'IF101_01_02_MR_adverse_reaction:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(94,3,'IF101_02_01_MR_exemption:','2.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(95,3,'IF101_02_02_MR_exemption:','2.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(96,3,'IF101_03_01_MR_flavour:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(97,3,'IF101_03_02_MR_flavour:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(98,3,'IF101_04_01_MR_formulary:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(99,3,'IF101_04_02_MR_formulary:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(100,3,'IF101_05_01_MR_medicalCondition:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(101,3,'IF101_05_02_MR_medicalCondition:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(102,3,'IF101_06_01_MR_prescriberType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(103,3,'IF101_06_02_MR_prescriberType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(104,3,'IF101_07_01_MR_prescriptionFormType:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(105,3,'IF101_07_02_MR_prescriptionFormType:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(106,3,'IF101_08_01_MR_prescriptionService:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(107,3,'IF101_09_01_MR_productClass:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(108,3,'IF101_10_01_MR_region:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(109,3,'IF101_11_01_MR_supplier:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(110,3,'IF101_11_02_MR_supplier:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(111,3,'IF101_12_01_MR_unitOfMeasure:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(112,3,'IF101_12_02_MR_unitOfMeasure:','2.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(113,3,'IF101_13_01_MR_role:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(114,3,'IF101_13_02_MR_role:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(115,3,'IF101_14_01_MR_general:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(116,3,'IF101_14_02_MR_general:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(117,3,'IF101_20_01_MR_prescriberTypeToFormType:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(118,3,'IF101_21_01_MR_levyRegionFee:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(119,3,'IF101_21_02_MR_levyRegionFee:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(120,3,'IF101_22_01_MR_formulation:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(121,3,'IF101_22_02_MR_formulation:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(122,3,'IF101_23_01_MR_EpsResponseMessage:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(123,3,'IF101_23_02_MR_EpsResponseMessage:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(124,3,'IF102_01_01_MR_prescriber:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(125,3,'IF102_01_02_MR_prescriber:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(126,3,'IF102_02_01_MR_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(127,3,'IF102_02_02_MR_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(128,3,'IF102_03_01_MR_prescriber_practice:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(129,3,'IF103_01_01_MR_PrescribableProduct:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(130,3,'IF103_01_02_MR_PrescribableProduct:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(131,3,'IF103_02_01_MR_FormularyToPP:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(132,3,'IF103_03_01_MR_PptoLIText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(133,3,'IF103_04_01_MR_DispensingSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(134,3,'IF103_04_02_MR_DispensingSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(135,3,'IF103_05_01_MR_PptoDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(136,3,'IF103_06_01_MR_RegionalDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(137,3,'IF103_07_01_MR_DispensingProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(138,3,'IF103_07_02_MR_DispensingProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(139,3,'IF103_08_01_MR_RegionalDPP:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(140,3,'IF103_09_01_MR_LabelInstructionText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(141,3,'IF103_09_02_MR_LabelInstructionText:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(142,3,'IF103_10_01_MR_PPPrices:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(143,3,'IF105_01_01_MR_PreferredDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(144,3,'IF105_01_02_MR_PreferredDSKU:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(145,3,'IF105_02_01_MR_PreferredActualProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(146,3,'IF105_02_02_MR_PreferredActualProductPack:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(147,3,'IF108_01_01_MR_store:','2.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(148,3,'IF108_01_02_MR_store:','2.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(149,3,'IF108_02_02_MR_storeServiceLink:','2.0-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(150,3,'IF110_01_01_product:','0.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(151,3,'IF110_01_02_product:','0.3-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(152,3,'IF172_01_01_stockEnquiry:','0.8-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(153,3,'IF172_01_02_stockAvailability:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(154,3,'IF172_02_02_twinningScheme:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(155,3,'IF175_01_01_stockTake:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(156,3,'IF175_01_02_stockTake:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(157,3,'IF176_01_01_stockFile:','0.2-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(158,3,'IF176_01_02_stockFile:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(159,3,'IF179_01_01_barcode:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(160,3,'IF179_01_02_barcode:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(161,3,'IF33_01_02:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(162,3,'tsfz-encryptionutil:','0.1-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(163,3,'tsfz-xsd:','0.8-1','ROOT:talend_interfaces_{Release}:tsfz:{ParamName}'),(164,3,'PCE_AutomatReceiver_main:','0.6-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(165,3,'PCE_AutomatTransmitter_main:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(166,3,'PCE_CPAS_SFTP_Sender:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(167,3,'PCE_DS_IF130_FinanceFeed:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(168,3,'PCE_DS_IF137_Audit:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(169,3,'PCE_DS_IF138_IMS_DTP:','0.6-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(170,3,'PCE_DS_IF138_Mftr_Audit:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(171,3,'PCE_DS_IF90_DeliveryRequest:','0.5-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(172,3,'PCE_IF108_Loader:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(173,3,'PCE_IF134_Loader:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(174,3,'PCE_IF59_MQ_Reader_from_PMS:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(175,3,'PCE_Merge:','0.4-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(176,3,'PCE_MonitorAudit:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(177,3,'PCE_OutputSplit:','0.5-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(178,3,'PCE_toAutomat_Masterdata_IF134:','0.2-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(179,3,'PCE_toAutomat_Picking:','0.3-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(180,3,'PCE_Util_STOP_All_Runtime_Jobs:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(181,3,'PCE_InputSplit:','0.7-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(182,3,'tsfz-encryptionutil:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(183,3,'pce-parameters-files:','0.1-1','ROOT:talend_interfaces_{Release}:pce:{ParamName}'),(184,3,'brg','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(185,3,'eas','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(186,3,'uis','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(187,3,'trainingversion','7.3.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(188,3,'wmqjmsraversion','7.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(189,3,'ehcache','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(190,3,'bouncycastle','1.5','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(191,3,'symds_version','3.7.34-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(192,3,'symds_ext_version','1.6-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(193,3,'acl_api_version','3.20.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(194,3,'asmversion','1.6.0-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(195,3,'eclipsepersistenceversion','2.4.2-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(196,3,'aclloggingversion','3.14-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(197,3,'jmxqueryversion','1.4-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(198,3,'batchclientversion','1.6.1-1','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(199,3,'oracle','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}'),(200,3,'oracle_secure','1.0','ROOT:oneleo_release_map:columbus:uk-boots:rpm_{Release}:{ParamName}');
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
  PRIMARY KEY (`ServerID`),
  UNIQUE KEY `UQ_CM_Server_ServerName` (`ServerName`),
  KEY `IXFK_CM_Server_CM_ServerType` (`ServerTypeID`),
  CONSTRAINT `cm_server_ibfk_1` FOREIGN KEY (`ServerTypeID`) REFERENCES `cm_servertype` (`ServerTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_server`
--

LOCK TABLES `cm_server` WRITE;
/*!40000 ALTER TABLE `cm_server` DISABLE KEYS */;
INSERT INTO `cm_server` VALUES (7,'gbrpmsdbst01',8),(9,'gbrpmsuist01',14),(10,'gbrpmsuist02',14),(11,'gbrpmseast01',4),(12,'gbrpmseast02',4),(13,'gbrpmsbrgt01',3),(14,'gbrpmsbrgt02',3),(15,'gbrpmstalt01',12),(16,'gbrpmstalt02',13),(17,'gbrpmstalt03',11),(19,'gbrpmsappt01',6),(20,'gbrpmsdbst03',7),(21,'gbrcbiappt01',1),(22,'gbrcbidbst01',2),(24,'TESTBRIDGE',3),(25,'TESTEAS',4),(26,'TESTBRIDGE2',3),(27,'TESTEAS2',4),(42,'gbrpmsuisf11',14),(43,'gbrpmseasf11',4),(44,'gbrpmsbrgf11',3),(45,'gbrpmstalf11',12),(46,'gbrpmstalf12',13),(47,'gbrpmstalf13',11),(48,'gbrpmsappf11',6),(49,'gbrpmsdbse02',10),(50,'gbrpmsdbse03',10),(51,'gbrpmsuise01',14),(52,'gbrpmsuise11',14),(53,'gbrpmsease01',4),(54,'gbrpmsease11',4),(55,'gbrpmsdbse01',8),(56,'gbrpmstale01',13),(57,'gbrpmstale11',13),(61,'gbrpmsuisi01',14),(62,'gbrpmsrpxt01',5),(63,'gbrpmseasi01',4),(64,'gbrpmseasi02',4),(65,'gbrpmsbrgi01',3),(66,'gbrpmsbrgi02',3),(67,'gbrpmstali01',12),(68,'gbrpmstali02',13),(69,'gbrpmstali03',11),(70,'gbrpmsappi01',6),(71,'gbrpmsuisi11',14),(72,'gbrpmseasi11',4),(73,'gbrpmsbrgi11',3),(74,'gbrpmsbrgi12',3),(75,'gbrpmstali11',12),(76,'gbrpmstali12',13),(77,'gbrpmstali13',11),(78,'gbrpmsappi11',6),(79,'gbrpmsuisi21',14),(80,'gbrpmseasi21',4),(81,'gbrpmsbrgi21',3),(82,'gbrpmstali21',12),(83,'gbrpmstali22',13),(84,'gbrpmstali23',11),(85,'gbrpmsappi21',6),(86,'gbrpmsuisi31',14),(87,'gbrpmsuisi32',14),(88,'gbrpmseasi31',4),(89,'gbrpmseasi32',4),(90,'gbrpmsbrgi31',3),(91,'gbrpmsbrgi32',3),(92,'gbrpmstali31',12),(93,'gbrpmstali32',13),(94,'gbrpmstali33',11),(95,'gbrpmsappi31',6),(96,'gbrpmsuisi41',14),(97,'gbrpmsuisi42',14),(98,'gbrpmseasi41',4),(99,'gbrpmseasi42',4),(100,'gbrpmsbrgi41',3),(101,'gbrpmstali41',12),(102,'gbrpmstali42',13),(103,'gbrpmstali43',11),(104,'gbrpmsappi41',6),(105,'gbrpmsuisf01',14),(106,'gbrpmseasf01',4),(107,'gbrpmsbrgf01',3),(108,'gbrpmstalf01',12),(109,'gbrpmstalf02',13),(110,'gbrpmstalf03',11),(111,'gbrpmsappf01',6),(112,'gbrpmsuisf21',14),(113,'gbrpmseasf21',4),(114,'gbrpmsbrgf21',3),(115,'gbrpmstalf21',12),(116,'gbrpmstalf22',13),(117,'gbrpmstalf23',11),(118,'gbrpmsappf21',6),(119,'gbrpmsuisf31',14),(120,'gbrpmseasf31',4),(121,'gbrpmsbrgf31',3),(122,'gbrpmstalf31',12),(123,'gbrpmstalf32',13),(124,'gbrpmstalf33',11),(125,'gbrpmsappf31',6),(126,'gbrpmsuisf41',14),(127,'gbrpmseasf41',4),(128,'gbrpmsuisu01',14),(129,'gbrpmseasu01',4),(130,'gbrpmsbrgu01',3),(131,'gbrpmstalu01',12),(132,'gbrpmstalu02',13),(133,'gbrpmstalu03',11),(134,'gbrpmsappu01',6),(135,'gbrpmsuisu11',14),(136,'gbrpmseasu11',4),(137,'gbrpmsbrgu11',3),(138,'gbrpmstalu11',12),(139,'gbrpmstalu12',13),(140,'gbrpmstalu13',11),(141,'gbrpmsappu11',6),(142,'gbrpmsdbst05',10),(143,'gbrpmsdbst14',10),(145,'gbrpmsrept01',9),(146,'gbrpmsrept02',9),(147,'gbrcbiappt02',1),(148,'gbrpmsuisf02',14),(149,'gbrpmseasf02',4),(150,'gbrpmsbrgf12',3),(151,'gbrpmsmigi01',15),(152,'gbrpmsmigi11',15),(153,'gbrpmsmigi21',15),(154,'gbrpmsmigf01',15),(155,'gbrpmsmigf11',15),(156,'gbrpmsmigf21',15),(157,'gbrpmsmigf31',15),(158,'gbrpmsmigu01',15),(159,'gbrpmsmigu02',15),(160,'gbrpmsmigt01',15);
/*!40000 ALTER TABLE `cm_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_server_subenvironment`
--

DROP TABLE IF EXISTS `cm_server_subenvironment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_server_subenvironment` (
  `ServerID` bigint(20) NOT NULL,
  `SubEnvironmentID` bigint(20) NOT NULL,
  KEY `ServerID` (`ServerID`),
  KEY `EnvironmentID` (`SubEnvironmentID`),
  CONSTRAINT `server_subenvironment_ibfk_1` FOREIGN KEY (`ServerID`) REFERENCES `cm_server` (`ServerID`),
  CONSTRAINT `server_subenvironment_ibfk_2` FOREIGN KEY (`SubEnvironmentID`) REFERENCES `cm_subenvironment` (`SubEnvironmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_server_subenvironment`
--

LOCK TABLES `cm_server_subenvironment` WRITE;
/*!40000 ALTER TABLE `cm_server_subenvironment` DISABLE KEYS */;
INSERT INTO `cm_server_subenvironment` VALUES (62,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(19,1),(20,1),(21,1),(22,1),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2),(62,2),(20,2),(21,2),(22,2),(49,5),(50,6),(51,5),(52,6),(53,5),(54,6),(56,5),(57,6),(61,7),(62,7),(63,7),(64,7),(65,7),(66,7),(67,7),(68,7),(69,7),(70,7),(20,7),(21,7),(22,7),(71,8),(72,8),(62,8),(73,8),(74,8),(75,8),(76,8),(77,8),(78,8),(20,8),(21,8),(22,8),(79,9),(80,9),(62,9),(81,9),(82,9),(83,9),(84,9),(85,9),(20,9),(21,9),(22,9),(62,10),(86,10),(87,10),(88,10),(89,10),(90,10),(91,10),(92,10),(93,10),(94,10),(95,10),(20,10),(21,10),(22,10),(62,11),(96,11),(97,11),(98,11),(99,11),(100,11),(101,11),(102,11),(103,11),(104,11),(20,11),(21,11),(22,11),(105,12),(106,12),(62,12),(107,12),(108,12),(109,12),(110,12),(111,12),(20,12),(21,12),(22,12),(112,13),(113,13),(62,13),(114,13),(115,13),(116,13),(117,13),(118,13),(20,13),(21,13),(22,13),(119,14),(120,14),(62,14),(121,14),(122,14),(123,14),(124,14),(125,14),(20,14),(21,14),(22,14),(126,15),(127,15),(62,15),(82,15),(128,16),(129,16),(62,16),(130,16),(131,16),(132,16),(133,16),(134,16),(20,16),(21,16),(22,16),(135,17),(136,17),(62,17),(137,17),(138,17),(139,17),(140,17),(141,17),(20,17),(21,17),(22,17),(7,1),(7,2),(7,8),(7,9),(7,10),(7,11),(7,12),(7,13),(7,14),(7,15),(7,16),(7,17),(7,7),(55,6),(55,5),(142,7),(142,8),(142,9),(142,10),(142,11),(142,12),(142,2),(142,13),(142,14),(142,15),(142,16),(142,17),(142,1),(143,7),(143,8),(143,9),(143,10),(143,11),(143,12),(143,2),(143,13),(143,14),(143,15),(143,16),(143,17),(143,1),(145,7),(146,7),(147,7),(147,8),(145,8),(146,8),(147,9),(145,9),(146,9),(147,10),(145,10),(146,10),(147,11),(145,11),(146,11),(147,12),(145,12),(146,12),(148,12),(149,12),(150,2),(147,2),(145,2),(146,2),(147,13),(145,13),(146,13),(147,14),(145,14),(146,14),(147,16),(145,16),(146,16),(147,17),(145,17),(146,17),(147,1),(145,1),(146,1),(151,7),(152,8),(153,9),(154,12),(155,2),(156,13),(157,14),(158,16),(159,17),(160,1);
/*!40000 ALTER TABLE `cm_server_subenvironment` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_subenvironment`
--

LOCK TABLES `cm_subenvironment` WRITE;
/*!40000 ALTER TABLE `cm_subenvironment` DISABLE KEYS */;
INSERT INTO `cm_subenvironment` VALUES (1,NULL,1,21),(2,NULL,1,12),(5,NULL,1,22),(6,NULL,1,23),(7,NULL,1,16),(8,NULL,1,17),(9,NULL,1,18),(10,NULL,1,19),(11,NULL,1,20),(12,NULL,1,11),(13,NULL,1,13),(14,NULL,1,14),(15,NULL,1,15),(16,NULL,1,24),(17,NULL,1,25);
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
) ENGINE=InnoDB AUTO_INCREMENT=664 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_subenvironmentconfig`
--

LOCK TABLES `cm_subenvironmentconfig` WRITE;
/*!40000 ALTER TABLE `cm_subenvironmentconfig` DISABLE KEYS */;
INSERT INTO `cm_subenvironmentconfig` VALUES (2,7,'ccode','gbr','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,8,'env','test','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,9,'sub_env','{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,10,'distrib','EnterpriseLinux','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,11,'log_level','INFO','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,12,'deployment_timeout','900','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,13,'ora_wallet','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,14,'ora_wallet_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,15,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,16,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,17,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,18,'db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,19,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB\':{ParamName}'),(2,20,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,21,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,22,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,23,'db_user','PMS{ENVID}_STG','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,24,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'EAS_DB_STG\':{ParamName}'),(2,25,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,26,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,27,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,28,'db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,29,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'SYMDS_DB\':{ParamName}'),(2,30,'db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,31,'db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,32,'db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,33,'db_user','OPTMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,34,'db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:ora_wallet_aliases:\'OPTIMUS\':{ParamName}'),(2,35,'application_user','pmsgbrtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,36,'application_group','pmsgbrtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,37,'maintenance_user','pmsgbmtt','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,38,'tsfn','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:mgmtusers:{ParamName}'),(2,39,'gss','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:mgmtusers:{ParamName}'),(2,40,'{ENVID}Admin','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,41,'{ENVID}Monitoring','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,42,'{ENVID}Operations','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:appusers:{ParamName}'),(2,43,'{ENVID}Admin','jminix,ADMIN','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,44,'{ENVID}Monitoring','jminix,MONITORING','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,45,'{ENVID}Operations','jminix,OPERATIONS','ROOT:oneleonardo_envs:columbus-{ENVID}:approles:{ParamName}'),(2,46,'product_name','columbus','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,47,'implementation_name','uk-boots','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,48,'application_desc','Columbus uk-boots implementation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,49,'deploy_version','7.3.2','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,50,'release','1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,51,'volumegroup','rootvg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,52,'data_size','1G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,53,'file_size','2G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,54,'soft_size','1G','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,55,'jboss_enable_monitoring','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,56,'jboss_management_native_port','9999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,57,'jboss_host','localhost','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,58,'gclog_num_files','10','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,59,'gclog_file_size','100m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,60,'columbus_env_id','D','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,61,'asm_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,62,'asm_enable_list','uis,eas,brg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,63,'deployments','columbus-chs-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,64,'datasources','java:jboss/datasources/columbus-ds-eas','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,65,'symds_enable','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,66,'training_enable','false','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,67,'symds_server','gbrpmseasf11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,68,'symds_port','8180','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,69,'sym_sec_path','${jboss.server.config.dir}/syncronization/EK4DK','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,70,'sym_sec_file_release','20150115','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,71,'symds_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,72,'symds_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,73,'symds_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,74,'symds_db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,75,'symds_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,76,'sym_cluster_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,77,'sym_cluster_lock_timeout','300000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,78,'osucontainer_concurrency','1-1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,79,'asncontainer_concurrency','1-1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,80,'wmq_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,81,'jmxquery_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,82,'centralized_logging_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,83,'BIQuery','${HOME}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,84,'jboss_management_http_port','9990','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,85,'asm_jboss_management_user','admin','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,86,'asm_jboss_management_user_psw','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,87,'durcheck_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,88,'clinicalcheck_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,89,'endorsement_licence','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,90,'marchart_notifyMarChart_url','http://10.245.12.244:8090/DrugMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,91,'housekeeping_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,92,'enable_new_transmit_due_date_orders_implementation','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,93,'preview_due_now_orders_page_limit','120','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,94,'clinicalcheck_getdur_url','http://gbrpmsdbst14.corp.internal:80/HDS/ClinicalInformation.svc/GetDUR','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,95,'clinicalcheck_getdrug_url','http://gbrpmsdbst14.corp.internal:80/HDS/ClinicalInformation.svc/GetDrugInformation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,96,'reimbursement_getendorsement_url','http://gbrpmsdbst14.corp.internal:80/HDS/Reimbursement.svc/GetEndorsement','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,97,'emar_notifyPatientCommunity_url','http://10.245.12.244:8090/FacilityMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,98,'emar_notifyPatient_url','http://10.245.12.244:8090/PatientMessageService.svc','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,99,'brg_server','gbrpmsbrgt00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,100,'vip_brg_port','8884','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,101,'brg_port','8380','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,102,'brg_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,103,'brg_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,104,'brg_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,105,'brg_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,106,'brg_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,107,'brg_spring_profile','outbound_api_channel,outbound_api_adapter,inbound_api_channel,inbound_api_adapter,import_batch,export_batch,internal_job_batch,external_job_batch,sym_chs','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,108,'brg_deployments','columbus-chs-7.3.2.ear,symmetricds-war-1.3.war','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,109,'brg_datasources','java:jboss/datasources/columbus-ds-eas,java:jboss/datasources/columbus-ds-symmetricds','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,110,'brg_asm_enabled_check_list','availabilityChecker,deployChecker,datasourceChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,111,'sync_url','https://gbrpmsbrgt00.corp.internal:8884/symmetricds/sync/chs-master','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,112,'resadapter_arc','wmq.jmsra.rar','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,113,'dispensed_db_name','EAS_DB','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,114,'env_roles_internal_jobs_enable','brg','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,115,'ig32_ek4dk','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,116,'boots_patient_key_hash','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,117,'IG32_secret_key','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,118,'inbound_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,119,'inbound_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,120,'inbound_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,121,'inbound_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,122,'inbound_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,123,'inbound_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,124,'outbound_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,125,'outbound_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,126,'outbound_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,127,'outbound_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,128,'outbound_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,129,'outbound_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,130,'history_dir','createUpdateStockTake','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,131,'history_dir','createUpdateUnitOfMeasureList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,132,'history_dir','createUpdateFormulation','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,133,'history_dir','createUpdatePatient','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,134,'history_dir','createUpdatePatientCommunity','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,135,'history_dir','createUpdateLevyFeeList','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,136,'columbus_db_version','7.3.2','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,137,'eas_spring_profile','outbound_api_channel,outbound_api_adapter,offline','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,138,'eas_deployments','columbus-chs-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,139,'eas_datasources','java:jboss/datasources/columbus-ds-eas','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,140,'eas_asm_enabled_check_list','availabilityChecker,deployChecker,datasourceChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,141,'eas_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,142,'eas_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,143,'eas_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,144,'eas_db_user','PMS{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,145,'eas_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,146,'eas_db_staging_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,147,'eas_db_staging_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,148,'eas_db_staging_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,149,'eas_db_staging_user','PMS{ENVID}_STG','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,150,'eas_db_staging_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,151,'eas_port','8180','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,152,'vip_eas_server','gbrpmseast00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,153,'vip_eas_port','8849','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,154,'eas_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,155,'eas_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,156,'eas_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,157,'eas_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,158,'eas_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,159,'CPAS_modulus','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,160,'CPAS_exponent','AQAB','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,161,'ig29_secret_keys','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,162,'boots_stockplus_key_hash','B5B','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,163,'ig24_secret_key','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,164,'ek4dk','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,165,'leo_queuemgr_name','*UNIALPD','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,166,'tote_order_line_ref_min_value','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,167,'tote_order_line_ref_max_value','999999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,168,'tote_ref_min_value','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,169,'tote_ref_max_value','9999999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,170,'use_tote_prefix','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,171,'tote_prefix','TOTE','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,172,'uis_init_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,173,'uis_max_heap_mem','5120m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,174,'uis_max_perm_size','512m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,175,'uis_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,176,'uis_max_new_size','1536m','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,177,'uis_server','gbrpmsuist00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,178,'uis_port','8280','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,179,'uis_sys_credential_file','SysCredential.properties','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,180,'uis_user','uisUser','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,181,'uis_deployments','columbus-desktop-7.3.2.ear','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,182,'uis_asm_enabled_check_list','availabilityChecker,deployChecker','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,183,'proxy_name','gbrpmsuist00.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,184,'proxy_port','8849','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,185,'batchclient_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,186,'batch_client_ws_user','john','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,187,'batch_client_ws_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,188,'batch_client_ws_sec_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,189,'batch_client_ws_enc_enabled','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,190,'batch_user','batchJobUser1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,191,'batch_sys_credential_file','SysCredential_generatedByEAS.properties','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,192,'batch_max_retry_attempts','10','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,193,'exit_code_batch_enable','true','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,194,'batchclient_ek4dk','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,195,'syscred_password','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,196,'syscred_passwordIssueDate','1471539187637','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,197,'createUpdatePrescription_INSERT_THREADS','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,198,'createUpdatePrescription_VALIDATION_THREADS','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,199,'tsfn_server','gbrpmstali21.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,200,'tsfz_environment_id','28','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,201,'tsfz_NASHxCf','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,202,'tsfz_inbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,203,'tsfz_inbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,204,'tsfz_inbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,205,'tsfz_inbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,206,'tsfz_inbound_columbus_csv_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,207,'tsfz_inbound_columbus_csv_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,208,'tsfz_inbound_columbus_csv_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,209,'tsfz_inbound_columbus_csv_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,210,'tsfz_outbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,211,'tsfz_outbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,212,'tsfz_outbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,213,'tsfz_outbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,214,'tsfz_internal_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,215,'tsfz_internal_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,216,'tsfz_internal_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,217,'tsfz_internal_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,218,'tsfz_rootZipFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,219,'tsfz_rootZipFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,220,'tsfz_rootZipFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,221,'tsfz_rootZipFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,222,'tsfz_insideCSVFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,223,'tsfz_insideCSVFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,224,'tsfz_insideCSVFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,225,'tsfz_insideCSVFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,226,'tsfz_insideZipFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,227,'tsfz_insideZipFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,228,'tsfz_insideZipFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,229,'tsfz_insideZipFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,230,'tsfz_archives_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,231,'tsfz_archives_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,232,'tsfz_archives_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,233,'tsfz_archives_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,234,'tsfz_history_inbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,235,'tsfz_history_inbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,236,'tsfz_history_inbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,237,'tsfz_history_inbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,238,'tsfz_history_outbound_columbus_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,239,'tsfz_history_outbound_columbus_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,240,'tsfz_history_outbound_columbus_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,241,'tsfz_history_outbound_columbus_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,242,'tsfz_history_inbound_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,243,'tsfz_history_inbound_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,244,'tsfz_history_inbound_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,245,'tsfz_history_inbound_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,246,'tsfz_history_outbound_columbus_readmeFiles_zipfile_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,247,'tsfz_history_outbound_columbus_readmeFiles_zip_time','7','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,248,'tsfz_history_outbound_columbus_readmeFiles_retain_time','30','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,249,'tsfz_history_outbound_columbus_readmeFiles_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,250,'pce_environment_id','60','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,251,'pce_if108_domain_code','coretest','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,252,'pce_NASHxCf','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,253,'pce_rsa_exp','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,254,'pce_rsa_mod','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,255,'pce_outbound_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,256,'pce_outbound_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,257,'pce_outbound_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,258,'pce_qAutomatInput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,259,'pce_qAutomatInput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,260,'pce_qAutomatInput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,261,'pce_qAutomatInput_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,262,'pce_qAutomatInput_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,263,'pce_qAutomatInput_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,264,'pce_qAutomatOutput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,265,'pce_qAutomatOutput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,266,'pce_qAutomatOutput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,267,'pce_qAutomatOutput_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,268,'pce_qAutomatOutput_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,269,'pce_qAutomatOutput_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,270,'pce_qCombined_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,271,'pce_qCombined_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,272,'pce_qCombined_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,273,'pce_qCombined_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,274,'pce_qCombined_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,275,'pce_qCombined_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,276,'pce_qIF130_FinanceFeed_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,277,'pce_qIF130_FinanceFeed_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,278,'pce_qIF130_FinanceFeed_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,279,'pce_qIF130_FinanceFeed_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,280,'pce_qIF130_FinanceFeed_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,281,'pce_qIF130_FinanceFeed_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,282,'pce_qIF134_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,283,'pce_qIF134_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,284,'pce_qIF134_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,285,'pce_qIF137_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,286,'pce_qIF137_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,287,'pce_qIF137_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,288,'pce_qIF137_Audit_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,289,'pce_qIF137_Audit_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,290,'pce_qIF137_Audit_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,291,'pce_qIF138_IMS_DTP_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,292,'pce_qIF138_IMS_DTP_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,293,'pce_qIF138_IMS_DTP_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,294,'pce_qIF138_IMS_DTP_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,295,'pce_qIF138_IMS_DTP_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,296,'pce_qIF138_IMS_DTP_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,297,'pce_qIF138_Mftr_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,298,'pce_qIF138_Mftr_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,299,'pce_qIF138_Mftr_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,300,'pce_qIF138_Mftr_Audit_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,301,'pce_qIF138_Mftr_Audit_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,302,'pce_qIF138_Mftr_Audit_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,303,'pce_qIF59_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,304,'pce_qIF59_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,305,'pce_qIF59_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,306,'pce_qIF59_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,307,'pce_qIF59_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,308,'pce_qIF59_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,309,'pce_qIF90_DeliveryRequest_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,310,'pce_qIF90_DeliveryRequest_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,311,'pce_qIF90_DeliveryRequest_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,312,'pce_qIF90_DeliveryRequest_bad_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,313,'pce_qIF90_DeliveryRequest_bad_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,314,'pce_qIF90_DeliveryRequest_bad_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,315,'pce_qLabelData_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,316,'pce_qLabelData_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,317,'pce_qLabelData_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,318,'pce_qLabels_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,319,'pce_qLabels_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,320,'pce_qLabels_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,321,'pce_qTote_Centric_Data_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,322,'pce_qTote_Centric_Data_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,323,'pce_qTote_Centric_Data_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,324,'pce_tmp_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,325,'pce_tmp_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,326,'pce_tmp_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,327,'pce_history_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,328,'pce_history_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,329,'pce_history_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,330,'pce_history_qAutomatInput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,331,'pce_history_qAutomatInput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,332,'pce_history_qAutomatInput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,333,'pce_history_qAutomatOutput_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,334,'pce_history_qAutomatOutput_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,335,'pce_history_qAutomatOutput_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,336,'pce_history_qCombined_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,337,'pce_history_qCombined_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,338,'pce_history_qCombined_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,339,'pce_history_qIF130_FinanceFeed_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,340,'pce_history_qIF130_FinanceFeed_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,341,'pce_history_qIF130_FinanceFeed_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,342,'pce_history_qIF134_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,343,'pce_history_qIF134_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,344,'pce_history_qIF134_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,345,'pce_history_qIF137_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,346,'pce_history_qIF137_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,347,'pce_history_qIF137_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,348,'pce_history_qIF138_IMS_DTP_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,349,'pce_history_qIF138_IMS_DTP_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,350,'pce_history_qIF138_IMS_DTP_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,351,'pce_history_qIF138_Mftr_Audit_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,352,'pce_history_qIF138_Mftr_Audit_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,353,'pce_history_qIF138_Mftr_Audit_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,354,'pce_history_qIF90_DeliveryRequest_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,355,'pce_history_qIF90_DeliveryRequest_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,356,'pce_history_qIF90_DeliveryRequest_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,357,'pce_history_qLabelData_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,358,'pce_history_qLabelData_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,359,'pce_history_qLabelData_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,360,'pce_history_qTote_Centric_Data_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,361,'pce_history_qTote_Centric_Data_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,362,'pce_history_qTote_Centric_Data_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,363,'pce_history_outbound_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,364,'pce_history_outbound_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,365,'pce_history_outbound_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,366,'pce_logs_zip_time','999999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,367,'pce_logs_retain_time','99999','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,368,'pce_logs_retain_number','0','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,369,'pce_automat_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,370,'pce_automat_settrace','all','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,371,'pce_db_audit_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,372,'pce_db_audit_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,373,'pce_db_audit_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,374,'pce_db_audit_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,375,'pce_db_audit_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,376,'pce_db_audit_schema','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,377,'pce_db_if138_mftr_audit_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,378,'pce_db_if138_mftr_audit_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,379,'pce_db_if138_mftr_audit_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,380,'pce_db_if138_mftr_audit_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,381,'pce_db_if138_mftr_audit_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,382,'pce_db_if138_mftr_audit_schema','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,383,'pce_db_masterdata_name','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,384,'pce_db_masterdata_server','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,385,'pce_db_masterdata_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,386,'pce_db_masterdata_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,387,'pce_db_masterdata_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,388,'pce_db_masterdata_schema_obd','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,389,'pce_db_masterdata_schema_pubsta','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,390,'pce_ftp_host','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,391,'pce_ftp_port','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,392,'pce_ftp_user','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,393,'pce_ftp_pass','','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,394,'pce_queue_manager','*UNIALPD','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,395,'pce_queue_manager_if90_leo','*NAPUKTA','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,396,'optimus_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,397,'optimus_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,398,'optimus_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,399,'optimus_db_user','OPTMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,400,'optimus_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,401,'stockplus_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,402,'stockplus_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,403,'stockplus_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,404,'stockplus_db_user','STKMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,405,'stockplus_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,406,'fullmig_db_server','gb2pmsdbst3.resources.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,407,'fullmig_db_port','1521','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,408,'fullmig_db_name','PMST1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,409,'fullmig_db_user','FULLMIG{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,410,'fullmig_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,411,'masterdata_db_server','gb2pmsdbst11.corp.internal','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,412,'masterdata_db_name','MasterData','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,413,'masterdata_db_port','51649','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,414,'masterdata_db_user','talendT','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,415,'masterdata_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,416,'boots_masterdata_db_schema','PublishedStaging','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,417,'boots_masterdata_db_instance','MASTERDATADB{ENVID}','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,418,'boots_interface_loglevel','INFO','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,419,'if056_rsa_pub_modulus','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,420,'if056_rsa_pub_exp','xxxx','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,421,'ftp_nxp_host','ftp.cegedimrx.co.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,422,'ftp_nxp_user','bootscolumbus','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,423,'ftp_nxp_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,424,'ftp_nhs_host','ftp.isd.hscic.gov.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,425,'ftp_nhs_user','TRUD3-11585@trud.nhs.uk','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,426,'ftp_nhs_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,427,'one_leo_db_host','10.179.20.187','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,428,'one_leo_db_port','60000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,429,'one_leo_db_name','unichemd','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,430,'one_leo_db_user','enquire','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,431,'one_leo_db_pass','removed','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,432,'ah_talend_server','unknown','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,433,'ah_talend_user','tagbXadm','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,434,'columbus_release','2070000','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,435,'columbus_masterdata_4_status','1','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,436,'sec_file_release','20150121','ROOT:oneleonardo_envs:columbus-{ENVID}:{ParamName}'),(2,437,'auditd_local_rules','true','ROOT:{ParamName}'),(2,438,'srcurl','http://gb2inffilp1.resources.corp.internal/SOURCES/','ROOT:{ParamName}'),(2,439,'os_version','centos-6.6-x86_64','ROOT:{ParamName}'),(2,440,'interface','eth1','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,441,'router_id','60','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,442,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,443,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,444,'ipaddress','10.141.129.248/24','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,445,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmseast00\':{ParamName}'),(2,446,'interface','eth1','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,447,'router_id','61','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,448,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,449,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,450,'ipaddress','10.141.129.249/24','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,451,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmsbrgt00\':{ParamName}'),(2,452,'interface','eth1','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,453,'router_id','62','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,454,'auth_type','PASS','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,455,'auth_pass','removed','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,456,'ipaddress','10.141.129.247/24','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,457,'track_int','eth1','ROOT:keepalived_conf:\'gbrpmsuist00\':{ParamName}'),(2,458,'mode','http','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,459,'bind_address','10.141.129.249','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,460,'bind_port','8884','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,461,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,462,'options','stick on src','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,463,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,464,'backends','gbrpmsbrgf11 gbrpmsbrgf11.corp.internal:8380 check','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,465,'backends','gbrpmsbrgf12 gbrpmsbrgf12.corp.internal:8380 check','ROOT:haproxy_conf:\'gbrpmsbrgft2\':{ParamName}'),(2,466,'mode','http','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,467,'bind_address','10.141.129.248','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,468,'bind_port','8849','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,469,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,470,'options','stick on src','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,471,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,472,'backends','gbrpmseasf11 gbrpmseasf11.corp.internal:8180 check','ROOT:haproxy_conf:\'gbrpmseasft2\':{ParamName}'),(2,473,'mode','http','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,474,'bind_address','10.141.129.247','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,475,'bind_port','8849','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,476,'balance','roundrobin','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,477,'options','stick on src','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,478,'options','stick-table type ip size 1m expire 3h','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,479,'backends','gbrpmsuisf11 gbrpmsuisf11.corp.internal:8280 check','ROOT:haproxy_conf:\'gbrpmsuisft2\':{ParamName}'),(2,480,'provide','base','ROOT:mysql_conf:{ParamName}'),(2,481,'env','test','ROOT:mysql_conf:talend_test:{ParamName}'),(2,482,'ccode','gbr','ROOT:mysql_conf:talend_test:{ParamName}'),(2,483,'app','tal','ROOT:mysql_conf:talend_test:{ParamName}'),(2,484,'user','tagbtdb','ROOT:mysql_conf:talend_test:{ParamName}'),(2,485,'password','removed','ROOT:mysql_conf:talend_test:{ParamName}'),(2,486,'group','tagbtdb','ROOT:mysql_conf:talend_test:{ParamName}'),(2,487,'vg','rootvg','ROOT:mysql_conf:talend_test:{ParamName}'),(2,488,'isbinding','true','ROOT:mysql_conf:talend_test:{ParamName}'),(2,489,'isreplication','false','ROOT:mysql_conf:talend_test:{ParamName}'),(2,490,'db','10G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,491,'files','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,492,'soft','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,493,'inno','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,494,'binlog','3G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,495,'save','1G','ROOT:mysql_conf:talend_test:sizes:{ParamName}'),(2,496,'isbinlog','false','ROOT:mysql_conf:talend_test:{ParamName}'),(2,497,'port','3306','ROOT:mysql_conf:talend_test:override_options:client{ParamName}'),(2,498,'bind_address','0.0.0.0','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,499,'port','3306','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,500,'innodb_log_file_size','64M','ROOT:mysql_conf:talend_test:override_options:mysqlid{ParamName}'),(2,501,'root_pass','removed','ROOT:mysql_conf:talend_test:{ParamName}'),(2,502,'name','tsfz_portal','ROOT:mysql_db:tsfz_portal_test:{ParamName}'),(2,503,'engine','talend_test','ROOT:mysql_db:tsfz_portal_test:{ParamName}'),(2,504,'name','tagbtdb','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,505,'host','%','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,506,'grant','ALL','ROOT:mysql_db:tsfz_portal_test:users:tsfz_portal_test_from_all_on_tsfz_portal_test:{ParamName}'),(2,507,'name','job_conf','ROOT:mysql_db:job_conf_test:{ParamName}'),(2,508,'engine','talend_test','ROOT:mysql_db:job_conf_test:{ParamName}'),(2,509,'name','tagbtdb','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,510,'host','%','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,511,'grant','ALL','ROOT:mysql_db:job_conf_test:users:job_conf_test_from_all_on_job_conf_test:{ParamName}'),(2,512,'test','tsfz_portal_test','ROOT:talend_databases:{ParamName}'),(2,513,'test','job_conf_test','ROOT:talend_databases:{ParamName}'),(2,514,'env','test','ROOT:talend_test_info:{ParamName}'),(2,515,'ccode','gbr','ROOT:talend_test_info:{ParamName}'),(2,516,'app','tal','ROOT:talend_test_info:{ParamName}'),(2,517,'user','tagbtadm','ROOT:talend_test_info:{ParamName}'),(2,518,'group','tagbt','ROOT:talend_test_info:{ParamName}'),(2,519,'nfsserver','127.0.0.1','ROOT:talend_test_info:{ParamName}'),(2,520,'installinterfaces','true','ROOT:talend_test_info:{ParamName}'),(2,521,'installoracli','false','ROOT:talend_test_info:{ParamName}'),(2,522,'installdb2cli','false','ROOT:talend_test_info:{ParamName}'),(2,523,'jdk_version','1.6.0_37','ROOT:talend_test_info:{ParamName}'),(2,524,'jdk_release','0HS','ROOT:talend_test_info:{ParamName}'),(2,525,'db2_vers','97','ROOT:talend_test_info:{ParamName}'),(2,526,'envs_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,527,'envf_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,528,'envb_base','/tests/talend','ROOT:talend_test_info:{ParamName}'),(2,529,'vg','rootvg','ROOT:talend_test_info:{ParamName}'),(2,530,'size_envs_tal','3G','ROOT:talend_test_info:{ParamName}'),(2,531,'size_envf_tal','1G','ROOT:talend_test_info:{ParamName}'),(2,532,'size_envb_tal','2G','ROOT:talend_test_info:{ParamName}'),(2,533,'size_exchange','2G','ROOT:talend_test_info:{ParamName}'),(2,534,'size_history','5G','ROOT:talend_test_info:{ParamName}'),(2,535,'size_internal','1G','ROOT:talend_test_info:{ParamName}'),(2,536,'istsfn','true','ROOT:talend_test_info:{ParamName}'),(2,537,'uid','1001','ROOT:user_tagbtdb:{ParamName}'),(2,538,'gid','1000','ROOT:user_tagbtdb:{ParamName}'),(2,539,'groups','tagbtdb','ROOT:user_tagbtdb:{ParamName}'),(2,540,'home','/home/tagbtdb','ROOT:user_tagbtdb:{ParamName}'),(2,541,'password','removed','ROOT:user_tagbtdb:{ParamName}'),(2,542,'authorized_keys',NULL,'ROOT:user_tagbtdb:{ParamName}'),(2,543,'uid','1000','ROOT:user_tagbtadm:{ParamName}'),(2,544,'gid','1000','ROOT:user_tagbtadm:{ParamName}'),(2,545,'groups','tagbt','ROOT:user_tagbtadm:{ParamName}'),(2,546,'home','/home/tagbtadm','ROOT:user_tagbtadm:{ParamName}'),(2,547,'password','removed','ROOT:user_tagbtadm:{ParamName}'),(2,548,'authorized_keys_file','oneleonardo/ssh/authorized_keys.erb','ROOT:user_tagbtadm:{ParamName}'),(2,549,'TSFN_columbus','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,550,'integration','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,551,'boots_support','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,552,'boots_performance_test','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,553,'devel','true','ROOT:user_tagbtadm:authorized_keys:{ParamName}'),(2,554,'envb','/exchange/outbound','ROOT:talend_dirs:common:{ParamName}'),(2,555,'envb','/exchange/inbound','ROOT:talend_dirs:common:{ParamName}'),(2,556,'envb','/exchange/outbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,557,'envb','/exchange/outbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,558,'envb','/exchange/inbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,559,'envb','/exchange/inbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,560,'envb','/parameters/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,561,'envb','/parameters/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,562,'envb','/internal/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,563,'envb','/internal/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,564,'envf','/logs/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,565,'envf','/logs/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,566,'envf','/history/storage','ROOT:talend_dirs:common:{ParamName}'),(2,567,'envf','/history/inbound','ROOT:talend_dirs:common:{ParamName}'),(2,568,'envf','/history/outbound','ROOT:talend_dirs:common:{ParamName}'),(2,569,'envf','/archives/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,570,'envf','/archives/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,571,'envf','/history/inbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,572,'envf','/history/inbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,573,'envf','/history/outbound/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,574,'envf','/history/outbound/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,575,'envs','/jobs/scripts/columbus','ROOT:talend_dirs:common:{ParamName}'),(2,576,'envs','/jobs/scripts/optimus','ROOT:talend_dirs:common:{ParamName}'),(2,577,'envb','/parameters/columbus/EK4DK','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,578,'envb','/exchange/outbound/optimus/data/Drop','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,579,'envb','/exchange/outbound/optimus/data/FailedOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,580,'envb','/exchange/outbound/optimus/data/IntermediateOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,581,'envb','/exchange/outbound/optimus/data/NotProcessed','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,582,'envb','/exchange/outbound/optimus/data/ProcessedOutput','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,583,'envb','/exchange/outbound/optimus/data/ProcessedSource','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,584,'envb','/exchange/outbound/optimus/data/tmp','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,585,'envb','/exchange/outbound/optimus/emailpickupfolder','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,586,'envs','/jobs/scripts/columbus/move-scripts','ROOT:talend_dirs:tsfn_only:{ParamName}'),(2,587,'envb','/exchange/inbound/columbus/bad','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,588,'envb','/exchange/inbound/columbus/csv','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,589,'envb','/exchange/inbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,590,'envb','/exchange/inbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,591,'envb','/exchange/inbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,592,'envb','/exchange/inbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,593,'envb','/exchange/inbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,594,'envb','/exchange/outbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,595,'envb','/exchange/outbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,596,'envb','/exchange/outbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,597,'envb','/exchange/outbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,598,'envb','/exchange/outbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,599,'envb','/exchange/NFM/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,600,'envb','/internal/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,601,'envb','/internal/columbus/rootZipFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,602,'envb','/internal/columbus/insideCSVFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,603,'envb','/internal/columbus/insideZipFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,604,'envb','/internal/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,605,'envf','/history/inbound/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,606,'envf','/history/inbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,607,'envf','/history/inbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,608,'envf','/history/inbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,609,'envf','/history/inbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,610,'envf','/history/inbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,611,'envf','/history/inbound/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,612,'envf','/history/outbound/columbus/readmeFiles','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,613,'envf','/history/outbound/columbus/release_2_1','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,614,'envf','/history/outbound/columbus/release_2_2','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,615,'envf','/history/outbound/columbus/release_2_3','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,616,'envf','/history/outbound/columbus/release_2_4','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,617,'envf','/history/outbound/columbus/release_7','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,618,'envf','/history/outbound/columbus/IG33','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,619,'envs','/script/admin','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,620,'envs','/jobs/scripts/columbus/move-scripts','ROOT:talend_dirs:tsfz_only:{ParamName}'),(2,621,'envb','/exchange/outbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,622,'envb','/internal/optimus/pce/qAutomatInput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,623,'envb','/internal/optimus/pce/qAutomatInput/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,624,'envb','/internal/optimus/pce/qAutomatOutput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,625,'envb','/internal/optimus/pce/qAutomatOutput/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,626,'envb','/internal/optimus/pce/qCombined','ROOT:talend_dirs:pce_only:{ParamName}'),(2,627,'envb','/internal/optimus/pce/qCombined/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,628,'envb','/internal/optimus/pce/qCombined/tmp','ROOT:talend_dirs:pce_only:{ParamName}'),(2,629,'envb','/internal/optimus/pce/qIF130_FinanceFeed','ROOT:talend_dirs:pce_only:{ParamName}'),(2,630,'envb','/internal/optimus/pce/qIF130_FinanceFeed/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,631,'envb','/internal/optimus/pce/qIF134','ROOT:talend_dirs:pce_only:{ParamName}'),(2,632,'envb','/internal/optimus/pce/qIF137_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,633,'envb','/internal/optimus/pce/qIF137_Audit/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,634,'envb','/internal/optimus/pce/qIF138_IMS_DTP','ROOT:talend_dirs:pce_only:{ParamName}'),(2,635,'envb','/internal/optimus/pce/qIF138_IMS_DTP/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,636,'envb','/internal/optimus/pce/qIF138_Mftr_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,637,'envb','/internal/optimus/pce/qIF138_Mftr_Audit/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,638,'envb','/internal/optimus/pce/qIF59','ROOT:talend_dirs:pce_only:{ParamName}'),(2,639,'envb','/internal/optimus/pce/qIF59/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,640,'envb','/internal/optimus/pce/qIF90_DeliveryRequest','ROOT:talend_dirs:pce_only:{ParamName}'),(2,641,'envb','/internal/optimus/pce/qIF90_DeliveryRequest/bad','ROOT:talend_dirs:pce_only:{ParamName}'),(2,642,'envb','/internal/optimus/pce/qLabelData','ROOT:talend_dirs:pce_only:{ParamName}'),(2,643,'envb','/internal/optimus/pce/qLabels','ROOT:talend_dirs:pce_only:{ParamName}'),(2,644,'envb','/internal/optimus/pce/qTote_Centric_Data1','ROOT:talend_dirs:pce_only:{ParamName}'),(2,645,'envb','/internal/optimus/pce/tmp','ROOT:talend_dirs:pce_only:{ParamName}'),(2,646,'envb','/parameters/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,647,'envs','/script/admin','ROOT:talend_dirs:pce_only:{ParamName}'),(2,648,'envs','/admin/scripts/filterOutDownStream','ROOT:talend_dirs:pce_only:{ParamName}'),(2,649,'envf','/history/inbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,650,'envf','/history/internal/optimus/pce/qAutomatInput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,651,'envf','/history/internal/optimus/pce/qAutomatOutput','ROOT:talend_dirs:pce_only:{ParamName}'),(2,652,'envf','/history/internal/optimus/pce/qCombined','ROOT:talend_dirs:pce_only:{ParamName}'),(2,653,'envf','/history/internal/optimus/pce/qIF130_FinanceFeed','ROOT:talend_dirs:pce_only:{ParamName}'),(2,654,'envf','/history/internal/optimus/pce/qIF134','ROOT:talend_dirs:pce_only:{ParamName}'),(2,655,'envf','/history/internal/optimus/pce/qIF137_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,656,'envf','/history/internal/optimus/pce/qIF138_IMS_DTP','ROOT:talend_dirs:pce_only:{ParamName}'),(2,657,'envf','/history/internal/optimus/pce/qIF138_Mftr_Audit','ROOT:talend_dirs:pce_only:{ParamName}'),(2,658,'envf','/history/internal/optimus/pce/qIF90_DeliveryRequest','ROOT:talend_dirs:pce_only:{ParamName}'),(2,659,'envf','/history/internal/optimus/pce/qLabelData','ROOT:talend_dirs:pce_only:{ParamName}'),(2,660,'envf','/history/internal/optimus/pce/qTote_Centric_Data1','ROOT:talend_dirs:pce_only:{ParamName}'),(2,661,'envf','/history/outbound/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,662,'envf','/logs/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}'),(2,663,'envf','/jobs/scripts/optimus/pce','ROOT:talend_dirs:pce_only:{ParamName}');
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
-- Dumping events for database 'envcmdb'
--

--
-- Dumping routines for database 'envcmdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `AddServer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddServer`(inServerName varchar(50), inEnvironmentName varchar(50), inSubEnvTypeName varchar(50), inServerTypeID bigint)
BEGIN   

IF ((select count(*) from cm_server where servername = inServerName) = 0) THEN
	Insert into cm_server (servername, servertypeid) values (inServerName, inServerTypeID);
else
	Select 'Server existed';
END IF;

IF (
	(select count(*) 
    from cm_server_subenvironment 
    where serverID = 		(select serverID from cm_server where servername = inServerName)
	and SubenvironmentID = 	(select SubEnvironmentID 
							from cm_subenvironment SEnv
							left join cm_environment E on senv.environmentID = e.environmentID
							left join cm_subenvironmenttype Styp on styp.subenvironmenttypeID = senv.subenvironmenttypeID
							where e.EnvironmentName = inEnvironmentName
							and styp.subenvironmenttypename = inSubEnvTypeName)
	)
	=0) THEN
	insert into cm_server_subenvironment values
	(
		(
        select serverID from cm_server where servername = inServerName
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
/*!50003 DROP PROCEDURE IF EXISTS `GetHiera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHiera`()
BEGIN   
	DECLARE EnvTag VARCHAR(50) DEFAULT '{ENVID}';
    DECLARE ServTypeTag VARCHAR(50) DEFAULT '{ServType}';
    DECLARE AppTag VARCHAR(50) DEFAULT '{AppName}';
    DECLARE ReleaseTag VARCHAR(50) DEFAULT '{Release}';
    Declare ParamNameTag VARCHAR(50) DEFAULT '{ParamName}';
    -- DECLARE mystart  INT unsigned DEFAULT 1;  
    -- DECLARE myfinish INT unsigned DEFAULT 10;

    -- SELECT  mystart, myfinish;
	select 	replace(g.globalConfigHieraAddress,ParamNameTag,g.globalConfigParameter) 	as 'HieraAddress'
    , 		g.globalConfigValue 		as 'HieraValue'
    from 	cm_globalconfig	g
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
    left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
	left join 	cm_server 		s	on sse.serverid = s.serverid
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
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
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
    left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
	left join 	cm_server 		s	on sse.serverid = s.serverid
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
/*!50003 SET sql_mode              = 'STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER' */ ;
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
    left join 	cm_server_subenvironment sse on subE.subenvironmentid = sse.subenvironmentid
	left join 	cm_server 		s	on sse.serverid = s.serverid
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
/*!50001 VIEW `environment_server_view` AS select `e`.`EnvironmentName` AS `EnvironmentName`,`s`.`ServerName` AS `ServerName`,`st`.`ServerTypeName` AS `ServerTypeName` from ((((`cm_environment` `e` left join `cm_subenvironment` `sube` on((`sube`.`EnvironmentID` = `e`.`EnvironmentID`))) left join `cm_server_subenvironment` `sse` on((`sube`.`SubEnvironmentID` = `sse`.`SubEnvironmentID`))) left join `cm_server` `s` on((`sse`.`ServerID` = `s`.`ServerID`))) left join `cm_servertype` `st` on((`s`.`ServerTypeID` = `st`.`ServerTypeID`))) */;
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
/*!50001 VIEW `servertype_view` AS select `st`.`ServerTypeName` AS `ServerTypeName`,`s`.`ServerName` AS `ServerName`,`e`.`EnvironmentName` AS `EnvironmentName` from ((((`cm_servertype` `st` left join `cm_server` `s` on((`st`.`ServerTypeID` = `s`.`ServerTypeID`))) left join `cm_server_subenvironment` `sse` on((`s`.`ServerID` = `sse`.`ServerID`))) left join `cm_subenvironment` `sube` on((`sube`.`SubEnvironmentID` = `sse`.`SubEnvironmentID`))) left join `cm_environment` `e` on((`sube`.`EnvironmentID` = `e`.`EnvironmentID`))) */;
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

-- Dump completed on 2016-11-24 12:28:34
