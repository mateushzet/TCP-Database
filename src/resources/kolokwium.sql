-- MariaDB dump 10.19  Distrib 10.10.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: kolokwium
-- ------------------------------------------------------
-- Server version	10.10.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `odpowiedzi_uczniów`
--

DROP TABLE IF EXISTS `odpowiedzi_uczniów`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `odpowiedzi_uczniów` (
  `idOdpowiedzi` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(30) NOT NULL,
  `odpowiedz` varchar(30) DEFAULT NULL,
  `numerPytania` int(2) NOT NULL,
  PRIMARY KEY (`idOdpowiedzi`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odpowiedzi_uczniów`
--

LOCK TABLES `odpowiedzi_uczniów` WRITE;
/*!40000 ALTER TABLE `odpowiedzi_uczniów` DISABLE KEYS */;
INSERT INTO `odpowiedzi_uczniów` VALUES
(1,'k','a',1),
(2,'k','d',2),
(3,'k','d',3),
(4,'k','d',4),
(5,'jakub','timeout',1),
(6,'jakub','timeout',2),
(7,'jakub','d',3),
(8,'jakub','a',4),
(9,'aa','a',1),
(10,'aa','a',2),
(11,'aa','a',3),
(12,'aa','a',4),
(13,'DWAD','A',1),
(14,'DWAD','A',2),
(15,'DWAD','A',3),
(16,'DWAD','A',4);
/*!40000 ALTER TABLE `odpowiedzi_uczniów` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poprawne_odpowiedzi`
--

DROP TABLE IF EXISTS `poprawne_odpowiedzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poprawne_odpowiedzi` (
  `numerPytania` int(2) NOT NULL,
  `odpowiedz` varchar(30) NOT NULL,
  PRIMARY KEY (`numerPytania`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poprawne_odpowiedzi`
--

LOCK TABLES `poprawne_odpowiedzi` WRITE;
/*!40000 ALTER TABLE `poprawne_odpowiedzi` DISABLE KEYS */;
INSERT INTO `poprawne_odpowiedzi` VALUES
(1,'c'),
(2,'b'),
(3,'c'),
(4,'a');
/*!40000 ALTER TABLE `poprawne_odpowiedzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pytania`
--

DROP TABLE IF EXISTS `pytania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pytania` (
  `numerPytania` int(2) NOT NULL,
  `pytanie` varchar(100) NOT NULL,
  `a` varchar(100) NOT NULL,
  `b` varchar(100) NOT NULL,
  `c` varchar(100) NOT NULL,
  `d` varchar(100) NOT NULL,
  PRIMARY KEY (`numerPytania`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pytania`
--

LOCK TABLES `pytania` WRITE;
/*!40000 ALTER TABLE `pytania` DISABLE KEYS */;
INSERT INTO `pytania` VALUES
(1,'Gdzie leży Warszawa?','małopolska','wielkopolska','mazowieckie','Podlaskie'),
(2,'Kto wymyślił prawo newtona','Orbit','Newton','Einstein','Płaneta'),
(3,'Kto spada na cztery łapy','pies','Zięcina','felis catus','lew'),
(4,'Na ile oceniasz tą aplikacje','5/5','4/5','3/5','0/5 (wybierasz na wlasna odpowiedzialnosc)');
/*!40000 ALTER TABLE `pytania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wyniki`
--

DROP TABLE IF EXISTS `wyniki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wyniki` (
  `idWyniku` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(30) NOT NULL,
  `punkty` int(2) NOT NULL,
  PRIMARY KEY (`idWyniku`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wyniki`
--

LOCK TABLES `wyniki` WRITE;
/*!40000 ALTER TABLE `wyniki` DISABLE KEYS */;
INSERT INTO `wyniki` VALUES
(3,'jakub',1),
(4,'aa',1);
/*!40000 ALTER TABLE `wyniki` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09 19:04:01
