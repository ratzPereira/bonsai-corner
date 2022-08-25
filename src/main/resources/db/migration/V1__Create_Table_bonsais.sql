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
-- Table structure for table `bonsais`
--

DROP TABLE IF EXISTS `bonsais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bonsais` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `age` int NOT NULL,
                           `bonsai_creation_date` date DEFAULT NULL,
                           `description` varchar(255) NOT NULL,
                           `name` varchar(255) NOT NULL,
                           `species_id` bigint DEFAULT NULL,
                           `user_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKj5p42puc0oiubtm7hbceo3ylt` (`species_id`),
                           KEY `FK8j79j519uqiqi84gwxdvooxl6` (`user_id`),
                           CONSTRAINT `FK8j79j519uqiqi84gwxdvooxl6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                           CONSTRAINT `FKj5p42puc0oiubtm7hbceo3ylt` FOREIGN KEY (`species_id`) REFERENCES `species` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonsais`
--

LOCK TABLES `bonsais` WRITE;
/*!40000 ALTER TABLE `bonsais` DISABLE KEYS */;
INSERT INTO `bonsais` VALUES (40,10,'2022-08-08','This is one bonsai','O meu Acer',38,53),(55,10,'2022-08-08','This is one bonsai','Best Acer',39,53),(56,3,'2022-08-08','this is my description for my test bonsai','Test update bonsai',37,999999),(57,10,'2022-08-08','This is one bonsai','Best Acer',39,53),(60,10,'2022-08-08','This is one bonsai','Best Acer with images',39,999999),(73,10,'2022-08-08','This is one bonsai','Best Acer with images',39,999999);
/*!40000 ALTER TABLE `bonsais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 12:59:17
