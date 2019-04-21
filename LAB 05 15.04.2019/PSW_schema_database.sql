CREATE DATABASE  IF NOT EXISTS `psw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `psw`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: psw
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `event` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(45) DEFAULT NULL,
  `agenda` longtext,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'OrigamiUSA','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu nisi at arcu sagittis condimentum sed at ligula. Curabitur quis nulla ac lorem egestas elementum sodales vel lorem. Sed sed augue sit amet justo iaculis rutrum sed ut neque. Aenean consequat, mauris a consectetur porttitor, eros turpis ultricies erat, ut rutrum orci massa sit amet augue. Duis eget pulvinar nulla, at pellentesque eros. Proin eu condimentum metus. Praesent interdum massa in fringilla vestibulum.','2019-04-19'),(2,'Pyrkon','Fusce a scelerisque ante, eget viverra ipsum. Morbi et luctus dui. Nullam suscipit pellentesque massa id egestas. Sed libero turpis, auctor quis lectus nec, scelerisque viverra arcu. Aenean nulla neque, luctus et justo quis, lacinia vehicula turpis. Integer varius nisi et elit ullamcorper, at placerat risus efficitur. Phasellus mattis varius maximus. Suspendisse rhoncus dignissim tellus quis aliquet. ','2019-04-19'),(3,'E3','Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vivamus iaculis lorem enim, at tincidunt neque iaculis non. Proin sit amet tempor dolor. Praesent semper lorem sem, vitae efficitur leo viverra in. Mauris augue nisl, placerat eget mattis id, viverra quis turpis. Quisque eget purus dictum, dapibus nisl sed, ullamcorper ante. Phasellus placerat vel lectus in hendrerit. ','2019-04-19'),(4,'PolAndRock','Donec diam tortor, porta sed dignissim ac, placerat non orci. Praesent vehicula magna et lectus placerat, sit amet accumsan elit facilisis. Curabitur cursus ipsum dui, a tempus mi malesuada id. Aenean blandit auctor gravida. Phasellus non turpis dui. Mauris elit lectus, placerat non turpis non, finibus vehicula ligula. Vestibulum nunc augue, sodales non nibh vitae, sollicitudin lacinia elit. Cras imperdiet ac lacus vel pulvinar. ','2019-04-19'),(6,'Job fairs','Its time to get a job you lazy student!','2019-04-09');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events_entries`
--

DROP TABLE IF EXISTS `events_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `events_entries` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `participation_type` varchar(45) NOT NULL,
  `food_preferences` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'waiting',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events_entries`
--

LOCK TABLES `events_entries` WRITE;
/*!40000 ALTER TABLE `events_entries` DISABLE KEYS */;
INSERT INTO `events_entries` VALUES (1,3,5,'LISTENER','NO_PREFERENCES','accepted'),(5,1,2,'AUTHOR','NO_PREFERENCES','waiting'),(6,2,2,'AUTHOR','VEGETARIAN','waiting'),(7,4,2,'LISTENER','GLUTEN_FREE','canceled'),(8,3,2,'SPONSOR','NO_PREFERENCES','waiting');
/*!40000 ALTER TABLE `events_entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `permissions` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Temus','Origami','Temus','admin','temuso@wir.pl','administrator','2019-04-14 09:19:28'),(2,'Marcin','Wójcik','marwoj','123','temus@wir.pl','user','2019-04-14 15:43:39'),(5,'Stanisław','Rubaj','darmisco','dar123','dar@wir.pl','user','2019-04-18 16:21:20'),(6,'Joseph','Brzechwa','JB223','666','josehp@gmail.com','user','2019-04-20 15:45:18'),(11,'Jakub','Zygmunciak','jakzyg','pass','temusdrop@wp.pl','user','2019-04-21 12:17:16'),(12,'Jacek','Wilk','wilczur22','lubieowce$','temusdrop@wir.pl','user','2019-04-21 12:22:18'),(13,'Małgosia','Sosenka','drzewo89','lesnictwo67','temusdrop@wp.pl','user','2019-04-21 12:23:51');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-21 12:27:17
