# MySQL-Front 5.1  (Build 1.5)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: mdp
# ------------------------------------------------------
# Server version 5.0.24a-community-nt

DROP DATABASE IF EXISTS `mdp`;
CREATE DATABASE `mdp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mdp`;

#
# Source for table keystore
#

CREATE TABLE `keystore` (
  `filenames` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `used` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table keystore
#
LOCK TABLES `keystore` WRITE;
/*!40000 ALTER TABLE `keystore` DISABLE KEYS */;

INSERT INTO `keystore` VALUES ('mynewkey','mynewkey','0');
INSERT INTO `keystore` VALUES ('kkkkkk','kkkkkk','0');
/*!40000 ALTER TABLE `keystore` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table microapps
#

CREATE TABLE `microapps` (
  `name` varchar(255) default NULL,
  `appurl` varchar(255) default NULL,
  `qrcode` varchar(255) default NULL,
  `specification` varchar(255) default NULL,
  `spec` varchar(500) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table microapps
#
LOCK TABLES `microapps` WRITE;
/*!40000 ALTER TABLE `microapps` DISABLE KEYS */;

INSERT INTO `microapps` VALUES ('prabash','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/prabash-release.apk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/prabash_QR_Code.PNG','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/Services.txt','WeatherInfo->PhoneStatusActivity->TakePhoto->Email->PhotoPreview');
INSERT INTO `microapps` VALUES ('prabash','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/prabash-release.apk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/prabash_QR_Code.PNG','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/Services.txt','WeatherInfo->PhoneStatusActivity->TakePhoto->Email->PhotoPreview->WeatherInfo->PhoneStatusActivity->TakePhoto->Email->PhotoPreview');
INSERT INTO `microapps` VALUES ('prabash','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/prabash-release.apk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/prabash_QR_Code.PNG','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp1/Services.txt','TakePhoto->PhotoPreview->GPSLocation->Email->TakePhoto->PhotoPreview->GPSLocation->Email');
INSERT INTO `microapps` VALUES ('prabash','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/prabash-release.apk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/prabash_QR_Code.PNG','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/Services.txt','TakePhoto->PhotoPreview->WeatherInfo->GPSLocation->Email');
INSERT INTO `microapps` VALUES ('kkkkkk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/kkkkkk-release.apk','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/kkkkkk_QR_Code.PNG','http://10.0.0.18:8888/ModelDrivenPattern/MicroApps/MicApp2/Services.txt','WeatherInfo->SMS->SMS->PhotoPreview->GPSLocation->TakePhoto->PhoneStatusActivity->SymmentricEncryption->VideoPreview->Email->SymmentricDecryption->ContactsList->VoiceRecognition->RecordVideo');
/*!40000 ALTER TABLE `microapps` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table registration
#

CREATE TABLE `registration` (
  `username` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `Name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `dob` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `phoneno` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  UNIQUE KEY `username` (`username`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table registration
#
LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;

/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table user_information
#

CREATE TABLE `user_information` (
  `s_no` int(11) NOT NULL auto_increment,
  `dob` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `phone_number` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  PRIMARY KEY  (`s_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table user_information
#
LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;

INSERT INTO `user_information` VALUES (1,'25/7/1993','p@gmail.com','Male','chennai','ppp','p','7708145816','ppp');
INSERT INTO `user_information` VALUES (2,'1/9/1993 ','pp@gmail.com','Male','chennai','pp','pp','7708245343','pp');
INSERT INTO `user_information` VALUES (3,'17/9/1992 ','iii@gmail.com','Male','chennai','iii','iii','6349666376','iii');
INSERT INTO `user_information` VALUES (4,'19/9/1991 ','dfdfd@gmail.com','Male','chennai','kfjlk','oooo','5656565655','fdfd');
INSERT INTO `user_information` VALUES (5,'20/9/1991 ','praba@gmail.com','Female','chennai','praba','praba','8989989898','praba');
INSERT INTO `user_information` VALUES (6,'12/10/1992 ','dfdf@gmail.com','Male','XCXC','edsf','hjdfjh','3334334344','dfdf');
INSERT INTO `user_information` VALUES (7,'24/11/1993 ','pp@gmail.com','Male','chennai','poop','p','8896563999','poi');
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
