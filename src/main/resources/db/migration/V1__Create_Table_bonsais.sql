DROP TABLE IF EXISTS `bonsais`;
CREATE TABLE `bonsais` (
  `id` bigint NOT NULL,
  `age` int NOT NULL,
  `bonsai_creation_date` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)