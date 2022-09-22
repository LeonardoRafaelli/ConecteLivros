-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: conectelivros
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
-- Table structure for table `editoras`
--

DROP TABLE IF EXISTS `editoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editoras` (
  `cnpj` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editoras`
--

LOCK TABLES `editoras` WRITE;
/*!40000 ALTER TABLE `editoras` DISABLE KEYS */;
/*!40000 ALTER TABLE `editoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livros`
--

DROP TABLE IF EXISTS `livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livros` (
  `isbn` int NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `status` enum('Aguardando revisão','Em revisão','Aprovado','Aguardando edição','Reprovado','Publicado') NOT NULL,
  `qtdPaginas` int NOT NULL,
  `AUTOR_cpf` char(11) NOT NULL,
  `EDITORAS_cnpj` int DEFAULT NULL,
  `REVISOR_cpf` char(11) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `AUTOR_cpf` (`AUTOR_cpf`),
  KEY `EDITORAS_cnpj` (`EDITORAS_cnpj`),
  KEY `REVISOR_cpf` (`REVISOR_cpf`),
  CONSTRAINT `livros_ibfk_1` FOREIGN KEY (`AUTOR_cpf`) REFERENCES `pessoas` (`cpf`),
  CONSTRAINT `livros_ibfk_2` FOREIGN KEY (`EDITORAS_cnpj`) REFERENCES `editoras` (`cnpj`),
  CONSTRAINT `livros_ibfk_3` FOREIGN KEY (`REVISOR_cpf`) REFERENCES `pessoas` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (1010,'LivroAtualizado','Aprovado',736,'12345678910',NULL,'98765432154'),(2020,'Diegolt o Destemido','Aguardando revisão',398,'12345678909',NULL,NULL),(123456,'TesteCadastro','Aguardando revisão',235,'12345678910',NULL,'98765432154');
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoas` (
  `cpf` char(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sobrenome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `genero` enum('Masculino','Feminino','Outro') NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipoAcesso` int NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES ('12345678909','Henrique','Cole','cole@','Masculino','123',1),('12345678910','leo','rafaelli','leo@','Masculino','123',1),('32165498765','Jean','Morales','diretor@','Masculino','123',3),('98765432154','Vytor','Zada','revisor@','Feminino','123',2);
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `viewlivroautor`
--

DROP TABLE IF EXISTS `viewlivroautor`;
/*!50001 DROP VIEW IF EXISTS `viewlivroautor`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `viewlivroautor` AS SELECT 
 1 AS `isbn`,
 1 AS `titulo`,
 1 AS `status`,
 1 AS `qtdPaginas`,
 1 AS `AUTOR_cpf`,
 1 AS `EDITORAS_cnpj`,
 1 AS `REVISOR_cpf`,
 1 AS `cpf`,
 1 AS `nome`,
 1 AS `sobrenome`,
 1 AS `email`,
 1 AS `genero`,
 1 AS `senha`,
 1 AS `tipoAcesso`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `viewlivroautor`
--

/*!50001 DROP VIEW IF EXISTS `viewlivroautor`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewlivroautor` AS select `l`.`isbn` AS `isbn`,`l`.`titulo` AS `titulo`,`l`.`status` AS `status`,`l`.`qtdPaginas` AS `qtdPaginas`,`l`.`AUTOR_cpf` AS `AUTOR_cpf`,`l`.`EDITORAS_cnpj` AS `EDITORAS_cnpj`,`l`.`REVISOR_cpf` AS `REVISOR_cpf`,`p`.`cpf` AS `cpf`,`p`.`nome` AS `nome`,`p`.`sobrenome` AS `sobrenome`,`p`.`email` AS `email`,`p`.`genero` AS `genero`,`p`.`senha` AS `senha`,`p`.`tipoAcesso` AS `tipoAcesso` from (`livros` `l` join `pessoas` `p` on((`l`.`AUTOR_cpf` = `p`.`cpf`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-22 20:00:19
