-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: tripiciano
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine` (
  `idMachine` int unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `status` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idMachine`),
  KEY `idUser_Ref_idx` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine`
--

LOCK TABLES `machine` WRITE;
/*!40000 ALTER TABLE `machine` DISABLE KEYS */;
INSERT INTO `machine` VALUES (1,'d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',NULL),(2,'d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',NULL);
/*!40000 ALTER TABLE `machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idProduct` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(4,2) unsigned NOT NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Caffè corto',0.50),(2,'Caffè lungo',0.50),(3,'Acqua naturale',0.50),(4,'Acqua frizzante',0.50),(5,'Succo di frutta',0.80),(6,'Crackers',0.70),(7,'Patatine',0.70),(8,'Biscotti',0.80),(9,'Snack cioccolato',1.00),(10,'Tramezzino',1.50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `idMachine_Ref` int unsigned NOT NULL,
  `idProduct_Ref` int unsigned NOT NULL,
  `qty` int unsigned NOT NULL,
  PRIMARY KEY (`idMachine_Ref`,`idProduct_Ref`),
  KEY `idMachine_Ref_idx` (`idMachine_Ref`),
  KEY `product_Ref_idx` (`idProduct_Ref`),
  CONSTRAINT `machine_Ref` FOREIGN KEY (`idMachine_Ref`) REFERENCES `machine` (`idMachine`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_Ref` FOREIGN KEY (`idProduct_Ref`) REFERENCES `product` (`idProduct`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,1,50),(1,2,50),(1,3,10),(1,4,10),(1,5,10),(1,6,10),(1,7,10),(1,8,10),(1,9,10),(1,10,10);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `credit` decimal(15,2) unsigned NOT NULL DEFAULT '0.00',
  `type` tinyint unsigned NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `user_chk_1` CHECK (((`type` >= 0) and (`type` <= 2)))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci KEY_BLOCK_SIZE=2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mario','Rossi','mario.rossi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',20.00,2),(2,'Antonio','Bianchi','antonio.bianchi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',10.00,1),(3,'Francesco','Verdi','francesco.verdi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',10.00,1),(4,'Giuseppe','Rossi','giuseppe.rossi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',5.00,0),(5,'Sofia','Bianchi','sofia.bianchi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',2.50,0),(6,'Aurora','Verdi','aurora.verdi@gmail.com','d3dec3f35387156495cbc21471313f87155f878f3435b693f50077c2be479033',0.00,0);
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

-- Dump completed on 2022-11-07 15:38:58
