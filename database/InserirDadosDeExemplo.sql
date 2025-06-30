-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: easytrip
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Dumping data for table `disponibilidade`
--

LOCK TABLES `disponibilidade` WRITE;
/*!40000 ALTER TABLE `disponibilidade` DISABLE KEYS */;
INSERT INTO `disponibilidade` VALUES (1,1,'2025-07-15','RESERVADO'),(2,1,'2025-07-16','DISPONIVEL'),(3,1,'2025-07-17','DISPONIVEL'),(4,1,'2025-07-18','DISPONIVEL'),(5,1,'2025-07-19','DISPONIVEL'),(6,1,'2025-07-20','DISPONIVEL'),(7,1,'2025-07-21','DISPONIVEL'),(8,1,'2025-07-22','DISPONIVEL'),(9,1,'2025-07-23','DISPONIVEL'),(10,1,'2025-07-24','DISPONIVEL'),(11,1,'2025-07-25','DISPONIVEL'),(12,1,'2025-07-26','DISPONIVEL'),(13,1,'2025-07-27','DISPONIVEL'),(14,2,'2025-07-18','RESERVADO'),(15,2,'2025-07-19','RESERVADO'),(16,2,'2025-07-20','RESERVADO'),(17,8,'2025-07-18','RESERVADO'),(18,8,'2025-07-19','RESERVADO'),(19,8,'2025-07-20','RESERVADO');
/*!40000 ALTER TABLE `disponibilidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'27580000','400','Rua das Hortênsias','Penedo','Casa','Itatiaia','RJ'),(2,'28930000','50','Rua dos Navegantes','Praia Grande','Bloco B, Casa 3','Arraial Do Cabo','RJ'),(3,'01310200','900','Avenida Paulista','Bela Vista','Apartamento 152, Bloco C','São Paulo','SP'),(4,'45818000','30','Rua do Bosque','Centro ','Casa','Porto Seguro','BA'),(5,'70070100','101','Via S1, Sudoeste','Asa Sul','Bloco A, Apartamento 503','Brasília','DF'),(6,'58039000','75','Rua Professora Ana Aurora','Tambaú','Casa 2','João Pessoa','PB'),(7,'90050190','850','Rua Demétrio Ribeiro','Menino Deus','Torre B, Apartamento 1201','Porto Alegre','RS'),(8,'69900000','45','Rua das Castanheiras','Bosque','Próximo ao Parque da Maternidade  ','Rio Branco','AC'),(9,'78190000','0','Estrada para Água Fria, km 25','Zona Rural  ','Próximo à Cachoeira Véu de Noiva','Chapada dos Guimarães','MT');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favoritagrupo`
--

LOCK TABLES `favoritagrupo` WRITE;
/*!40000 ALTER TABLE `favoritagrupo` DISABLE KEYS */;
INSERT INTO `favoritagrupo` VALUES (2,1,8),(3,1,6),(4,1,9);
/*!40000 ALTER TABLE `favoritagrupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favoritahospede`
--

LOCK TABLES `favoritahospede` WRITE;
/*!40000 ALTER TABLE `favoritahospede` DISABLE KEYS */;
INSERT INTO `favoritahospede` VALUES (4,1);
/*!40000 ALTER TABLE `favoritahospede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Amigos');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hospedagem`
--

LOCK TABLES `hospedagem` WRITE;
/*!40000 ALTER TABLE `hospedagem` DISABLE KEYS */;
INSERT INTO `hospedagem` VALUES (1,1,1,'Chalé Romântico','Escondido entre as montanhas e cercado por uma exuberante paisagem natural.\nÉ o refúgio perfeito para casais que buscam momentos inesquecíveis. \nCom arquitetura charmosa em madeira, o chalé oferece um ambiente acolhedor e intimista, com lareira central, banheira de hidromassagem com vista panorâmica. O quarto é decorado com delicadeza, iluminação suave e roupas de cama macias que completam a atmosfera de encanto. \nIdeal para celebrações especiais, como luas de mel e aniversários de casamento, este chalé proporciona o equilíbrio perfeito entre conforto, privacidade e conexão com a natureza','CHALE',4,600.00,'12:00:00','09:00:00',5.00),(2,1,2,'Casa de Praia Aconchegante em Arraial do Cabo','Desfrute de dias ensolarados nesta charmosa casa de praia, localizada a poucos passos das águas cristalinas da Praia Grande. \nIdeal para famílias e grupos de amigos que buscam conforto e tranquilidade. \nCom churrasqueira e vista parcial para o mar.','CASA',8,450.00,'14:00:00','11:00:00',5.00),(3,1,3,'Apartamento Moderno na Avenida Paulista','Elegante apartamento totalmente mobiliado e equipado, localizado no coração financeiro e cultural de São Paulo. \nPerfeito para viagens de negócios ou turismo, com fácil acesso ao metrô, museus, teatros e restaurantes renomados.\nDesfrute da vista da cidade e da conveniência de estar no centro de tudo.','APARTAMENTO',4,340.00,'15:00:00','11:00:00',5.00),(4,1,4,'Casa de Charme com Piscina em Trancoso','A poucos minutos do Quadrado e das praias, esta casa espaçosa oferece o refúgio perfeito em Trancoso. \nCom piscina privativa, área gourmet e um belo jardim, é ideal para famílias e grupos de amigos que buscam conforto e a atmosfera única da Bahia.','CASA',14,700.00,'16:00:00','12:00:00',5.00),(5,1,5,'Apartamento Luxuoso no Coração de Brasília','Desfrute de uma estadia sofisticada e confortável neste apartamento moderno, ideal para executivos e turistas.\nLocalizado estrategicamente no Plano Piloto, oferece fácil acesso aos principais pontos turísticos, centros comerciais e órgãos governamentais. \nDecorado com requinte e totalmente equipado.','APARTAMENTO',2,600.00,'15:00:00','11:00:00',5.00),(6,1,6,'Casa Aconchegante na Praia de Tambaú','Desfrute de uma estadia relaxante nesta charmosa casa, localizada a poucos metros da Praia de Tambaú, uma das mais belas de João Pessoa. Ideal para famílias, oferece conforto, espaço e fácil acesso a bares, restaurantes e feiras de artesanato.','CASA',7,800.00,'14:00:00','11:00:00',5.00),(7,1,7,'Apartamento Central e Moderno em Porto Alegre',' Confortável e moderno apartamento localizado no coração de Porto Alegre, ideal para viagens de negócios ou turismo. Próximo a shoppings, restaurantes, parques e pontos turísticos. Equipado com tudo que você precisa para uma estadia agradável na capital gaúcha.','APARTAMENTO',4,220.00,'15:00:00','09:00:00',5.00),(8,1,8,'Casa Acolhedora com Toque Amazônico em Rio Branco','Desfrute de uma estadia tranquila nesta casa espaçosa e bem localizada em Rio Branco. Com arquitetura que valoriza a ventilação natural e áreas verdes, é perfeita para famílias ou grupos que buscam conforto e uma imersão na cultura local amazônica. Próxima a mercados e parques.\n\n','CASA',6,520.00,'14:00:00','11:00:00',5.00),(9,1,9,'Sítio Ecológico com Atividades Rurais em Chapada dos Guimarães','Um refúgio tranquilo no coração do Mato Grosso, perfeito para quem busca imersão na natureza e vivência rural. Este sítio oferece trilhas, cachoeiras próximas e a oportunidade de observar a flora e fauna do Cerrado e transição para Pantanal. Ideal para famílias e grupos que apreciam o ecoturismo e o agronegócio.','SITIO',15,1350.00,'16:00:00','12:00:00',5.00);
/*!40000 ALTER TABLE `hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hospedegrupo`
--

LOCK TABLES `hospedegrupo` WRITE;
/*!40000 ALTER TABLE `hospedegrupo` DISABLE KEYS */;
INSERT INTO `hospedegrupo` VALUES (1,4,1,'RESPONSAVEL',0),(2,2,1,'MEMBRO',0);
/*!40000 ALTER TABLE `hospedegrupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `imagem`
--

LOCK TABLES `imagem` WRITE;
/*!40000 ALTER TABLE `imagem` DISABLE KEYS */;
INSERT INTO `imagem` VALUES (2,1,'imagemHospedagens/hospedagem1-3502f1ab-a084-451d-98c6-e3150bda19c3.jpeg'),(3,2,'imagemHospedagens/hospedagem2-5a18953e-4bca-49d0-825b-c8af7dafebbf.jpeg'),(4,3,'imagemHospedagens/hospedagem3-ebb88f4f-ad92-4204-9af6-7bb9187e5b2d.jpeg'),(7,4,'imagemHospedagens/hospedagem4-203c55fe-356b-42c9-8715-5d8011cd74a1.jpeg'),(8,5,'imagemHospedagens/hospedagem5-a8734384-daf3-4794-bedb-ea853d4813f2.jpeg'),(9,6,'imagemHospedagens/hospedagem6-b8120a94-867b-43cc-8903-7bf67dcb9708.jpeg'),(10,7,'imagemHospedagens/hospedagem7-a47f4e37-3135-4dd9-a69d-fabba9efa357.jpeg'),(11,8,'imagemHospedagens/hospedagem8-da7780e9-6838-49bf-b935-af615ac878e0.jpeg'),(12,9,'imagemHospedagens/hospedagem9-08ee9af4-f050-4971-8fb9-b0e443c40f30.jpeg');
/*!40000 ALTER TABLE `imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,2,2,2,'2025-07-18','2025-07-21',3,450.00,'CONFIRMADA',NULL),(2,3,1,3,'2025-07-15','2025-07-16',1,600.00,'PENDENTE',NULL),(3,4,8,2,'2025-07-18','2025-07-21',3,520.00,'PENDENTE',NULL);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Bruce Wayne','brucewayne10@gmail.com','Naosouobatman123','1989-02-22','53884188046'),(2,'Peter Parker','peterparker@gmail.com','Peter123','2000-08-10','11565312090'),(3,'Fulano da Silva','fulanosilva@gmail.com','Senha123','2002-05-09','57012333081'),(4,'Ciclano da Silva','ciclanodasilva@gmail.com','Senhaforte','1998-03-16','03080727070');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `votacaofavoritas`
--

LOCK TABLES `votacaofavoritas` WRITE;
/*!40000 ALTER TABLE `votacaofavoritas` DISABLE KEYS */;
INSERT INTO `votacaofavoritas` VALUES (1,2),(2,2),(1,3);
/*!40000 ALTER TABLE `votacaofavoritas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-30 15:55:34
