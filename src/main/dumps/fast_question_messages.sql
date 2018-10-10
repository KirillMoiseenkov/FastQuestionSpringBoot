-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: fast_question
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `message` varchar(256) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id_idx` (`question_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `questions` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1870 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1823,345,'Здаров',NULL),(1824,347,'молотом',NULL),(1825,347,'молотом',NULL),(1826,347,'молотом',NULL),(1827,350,'как нибудь',NULL),(1828,350,'как нибудь',NULL),(1829,346,'как нибудь',NULL),(1830,347,'молотом',NULL),(1831,345,'хай',NULL),(1832,345,'хай',NULL),(1833,350,'учить надо его',NULL),(1834,347,'убрать',NULL),(1835,348,'возьми свечку',NULL),(1836,348,'купи зажигалку',NULL),(1837,348,'купи зажигалку',NULL),(1838,349,'в магазине',NULL),(1839,348,'купи свечу',NULL),(1840,354,'где боженька пошлёт!',NULL),(1841,353,'куку',NULL),(1842,347,'лопатой',NULL),(1843,350,'спиши ',NULL),(1844,351,'купи ответы',NULL),(1845,353,'кукуку',NULL),(1846,352,'сначала купи саженец... ',NULL),(1847,348,'включи лампу',NULL),(1848,350,'как хочет препод ',NULL),(1849,347,'убирай стекло ',NULL),(1850,351,'учебником',NULL),(1851,352,'купить саженец, вырыть яму...',NULL),(1852,348,'включи фонарь ',NULL),(1853,350,'как учил так и учи... норм будет;)',NULL),(1854,346,'чётк',NULL),(1855,355,'заткни уши',NULL),(1856,349,'в гитарном магазине',NULL),(1857,359,'попробуй ещё раз, мб получится))) ',NULL),(1858,349,'сначала купи гитару, там и поймёшь где струны покупать)',NULL),(1859,354,'мвидео ',NULL),(1860,351,'читы по жизни надо использовать)',NULL),(1861,354,'на базаре',NULL),(1862,357,'по крупночи ',NULL),(1863,351,'скатай',NULL),(1864,347,'с ноги',NULL),(1865,351,'не учи - пойми',NULL),(1866,354,'где нибудь на улице...',NULL),(1867,360,'не очень, но лучше не проверять)',NULL),(1868,360,'ну так то да...',NULL),(1869,360,'ну немного ',NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-10 13:00:52
