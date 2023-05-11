CREATE DATABASE  IF NOT EXISTS `storage` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `storage`;
-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (x86_64)
--
-- Host: 127.0.0.1    Database: storage
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `amministratore`
--

DROP TABLE IF EXISTS `amministratore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amministratore` (
  `codiceAmministratore` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`codiceAmministratore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amministratore`
--

LOCK TABLES `amministratore` WRITE;
/*!40000 ALTER TABLE `amministratore` DISABLE KEYS */;
/*!40000 ALTER TABLE `amministratore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienteGuest`
--

DROP TABLE IF EXISTS `clienteGuest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienteGuest` (
  `codiceGuest` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(15) NOT NULL,
  `cognome` varchar(15) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `indirizzo` varchar(45) NOT NULL,
  PRIMARY KEY (`codiceGuest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienteGuest`
--

LOCK TABLES `clienteGuest` WRITE;
/*!40000 ALTER TABLE `clienteGuest` DISABLE KEYS */;
/*!40000 ALTER TABLE `clienteGuest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienti`
--

DROP TABLE IF EXISTS `clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienti` (
  `codiceCliente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(15) NOT NULL,
  `cognome` varchar(15) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `indirizzo` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`codiceCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienti`
--

LOCK TABLES `clienti` WRITE;
/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordini`
--

DROP TABLE IF EXISTS `ordini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordini` (
  `codiceOrdine` int NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `codiceAdmin` int NOT NULL,
  `codiceClienti` int DEFAULT NULL,
  `codiceGuests` int DEFAULT NULL,
  PRIMARY KEY (`codiceOrdine`),
  KEY `codiceCliente_idx` (`codiceClienti`),
  KEY `codiceGuest_idx` (`codiceGuests`),
  KEY `codiceAmministratore_idx` (`codiceAdmin`),
  CONSTRAINT `codiceAdmin` FOREIGN KEY (`codiceAdmin`) REFERENCES `amministratore` (`codiceAmministratore`),
  CONSTRAINT `codiceClienti` FOREIGN KEY (`codiceClienti`) REFERENCES `clienti` (`codiceCliente`),
  CONSTRAINT `codiceGuests` FOREIGN KEY (`codiceGuests`) REFERENCES `clienteGuest` (`codiceGuest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordini`
--

LOCK TABLES `ordini` WRITE;
/*!40000 ALTER TABLE `ordini` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotti`
--

DROP TABLE IF EXISTS `prodotti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotti` (
  `codiceProdotto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `prezzo` double NOT NULL,
  `codiceAmministratore` int NOT NULL,
  PRIMARY KEY (`codiceProdotto`),
  KEY `codiceAmministratore_idx` (`codiceAmministratore`),
  CONSTRAINT `codiceAmministratore` FOREIGN KEY (`codiceAmministratore`) REFERENCES `amministratore` (`codiceAmministratore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotti`
--

LOCK TABLES `prodotti` WRITE;
/*!40000 ALTER TABLE `prodotti` DISABLE KEYS */;
/*!40000 ALTER TABLE `prodotti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodottiVenduti`
--

DROP TABLE IF EXISTS `prodottiVenduti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodottiVenduti` (
  `codiceProdotto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `prezzo` double NOT NULL,
  `codiceOrdine` int NOT NULL,
  PRIMARY KEY (`codiceProdotto`),
  KEY `codiceOrdine_idx` (`codiceOrdine`),
  CONSTRAINT `codiceOrdine` FOREIGN KEY (`codiceOrdine`) REFERENCES `ordini` (`codiceOrdine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodottiVenduti`
--

LOCK TABLES `prodottiVenduti` WRITE;
/*!40000 ALTER TABLE `prodottiVenduti` DISABLE KEYS */;
/*!40000 ALTER TABLE `prodottiVenduti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Promozione`
--

DROP TABLE IF EXISTS `Promozione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Promozione` (
  `codicePromozione` char(5) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `codiceAdministrator` int NOT NULL,
  PRIMARY KEY (`codicePromozione`),
  KEY `codiceAmministratore_idx` (`codiceAdministrator`),
  CONSTRAINT `codiceAdministrator` FOREIGN KEY (`codiceAdministrator`) REFERENCES `amministratore` (`codiceAmministratore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Promozione`
--

LOCK TABLES `Promozione` WRITE;
/*!40000 ALTER TABLE `Promozione` DISABLE KEYS */;
/*!40000 ALTER TABLE `Promozione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 20:01:5


INSERT INTO clienti (codiceCliente, nome, cognome, telefono, indirizzo, email, password )
VALUES (1, "pippo", "pluto", "3456158","via tizio caio e sempronio","prova@gmail.com","aszdxcytuhsdasidtcrqw67etuyiqk412");
