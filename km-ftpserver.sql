/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.13-log : Database - ftp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ftp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ftp`;

/*Table structure for table `ftp_user` */

DROP TABLE IF EXISTS `ftp_user`;

CREATE TABLE `ftp_user` (
  `userid` varchar(64) NOT NULL,
  `userpassword` varchar(64) DEFAULT NULL,
  `homedirectory` varchar(128) NOT NULL,
  `enableflag` tinyint(1) DEFAULT '1',
  `writepermission` tinyint(1) DEFAULT '0',
  `idletime` int(11) DEFAULT '0',
  `uploadrate` int(11) DEFAULT '0',
  `downloadrate` int(11) DEFAULT '0',
  `maxloginnumber` int(11) DEFAULT '0',
  `maxloginperip` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ftp_user` */

insert  into `ftp_user`(`userid`,`userpassword`,`homedirectory`,`enableflag`,`writepermission`,`idletime`,`uploadrate`,`downloadrate`,`maxloginnumber`,`maxloginperip`) values ('admin','admin','/home',1,1,0,0,0,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
