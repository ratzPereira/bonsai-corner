-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bonsaicorner
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fertilizing` varchar(255) DEFAULT NULL,
  `propagation` varchar(255) DEFAULT NULL,
  `pruning` varchar(255) DEFAULT NULL,
  `regions` varchar(255) DEFAULT NULL,
  `soil` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sun_exposure` varchar(255) DEFAULT NULL,
  `transplant` varchar(255) DEFAULT NULL,
  `watering` varchar(255) DEFAULT NULL,
  `family_id` bigint NOT NULL,
  `tree_group_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsn8w0cfgvjq8hurr6jkf3unmn` (`family_id`),
  KEY `FKlnj3ouunuoygxdnbwwtin0pli` (`tree_group_id`),
  CONSTRAINT `FKlnj3ouunuoygxdnbwwtin0pli` FOREIGN KEY (`tree_group_id`) REFERENCES `tree_group` (`id`),
  CONSTRAINT `FKsn8w0cfgvjq8hurr6jkf3unmn` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (37,'nice species','Spring and Summer','seeds','In the Summer','Asia and Europe','Not acid and drenante','Acer Palmatum','Full light but in shade','3 in 3 years','once a day',2,6),(38,'nice species','Spring and Summer','seeds','In the Summer','Asia and Europe','Not acid and drenante','Acer Tridente','Full light but in shade','3 in 3 years','once a day',2,6),(39,'nice species','Spring and Summer','seeds','In the Summer','Asia and Europe','Not acid and drenante','Acer Dejojo','Full light but in shade','2 in 2 years','once a day',2,6);
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-10 14:52:14
