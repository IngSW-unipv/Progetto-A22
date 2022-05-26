
UNLOCK TABLES;
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Tawa', 200, 400, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('MatPara', 200, 500, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('MatC', 200, 600, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Gio', 200, 700, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Luca', 200, 800, NULL, '123456789');

COMMIT;

--
-- Dumping data for table `lingua`
--
USE `ServerDomDB`;
LOCK TABLES `lingua` WRITE;
/*!40000 ALTER TABLE `lingua` DISABLE KEYS */;
INSERT INTO `lingua` VALUES 
('Lega1','INTERNO','SCRATCH','SCRATCH'),
('Lega10','INTERNO','HAKER ++','HAKER ++'),
('Lega2','INTERNO','Fortran ','Fortran '),
('Lega3','INTERNO','C','C'),
('Lega4','INTERNO','HTML','HTML'),
('Lega5','INTERNO','C SHARP','C SHARP'),
('Lega6','INTERNO','GO','GO'),
('Lega7','INTERNO','JAVA','JAVA'),
('Lega8','INTERNO','DELTA','DELTA'),
('Lega9','INTERNO','HAKER','HAKER');
/*!40000 ALTER TABLE `lingua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `obiettivi`
--
USE `ServerDomDB`;
LOCK TABLES `obiettivi` WRITE;
/*!40000 ALTER TABLE `obiettivi` DISABLE KEYS */;
INSERT INTO `obiettivi` VALUES 
(1,'Lega1',400),
(2,'Lega1',100),
(3,'Lega1',120),
(4,'Lega1',140),
(5,'Lega1',160),
(6,'Lega1',180),
(7,'Lega1',200),
(8,'Lega1',220),
(9,'Lega1',240),
(10,'Lega1',260),
(11,'Lega2',1600),
(12,'Lega2',100),
(13,'Lega2',120),
(14,'Lega2',140),
(15,'Lega2',160),
(16,'Lega2',180),
(17,'Lega2',200),
(18,'Lega2',220),
(19,'Lega2',240),
(20,'Lega2',260),
(21,'Lega3',3100),
(22,'Lega3',100),
(23,'Lega3',120),
(24,'Lega3',140),
(25,'Lega3',160),
(26,'Lega3',180),
(27,'Lega3',200),
(28,'Lega3',220),
(29,'Lega3',240),
(30,'Lega3',260),
(31,'Lega4',4600),
(32,'Lega4',100),
(33,'Lega4',120),
(34,'Lega4',140),
(35,'Lega4',160),
(36,'Lega4',180),
(37,'Lega4',200),
(38,'Lega4',220),
(39,'Lega4',240),
(40,'Lega4',260),
(41,'Lega5',6100),
(42,'Lega5',100),
(43,'Lega5',120),
(44,'Lega5',140),
(45,'Lega5',160),
(46,'Lega5',180),
(47,'Lega5',200),
(48,'Lega5',220),
(49,'Lega5',240),
(50,'Lega5',260),
(51,'Lega6',7600),
(52,'Lega6',100),
(53,'Lega6',120),
(54,'Lega6',140),
(55,'Lega6',160),
(56,'Lega6',180),
(57,'Lega6',200),
(58,'Lega6',220),
(59,'Lega6',240),
(60,'Lega6',260),
(61,'Lega6',280),
(62,'Lega6',300),
(63,'Lega6',320),
(64,'Lega6',340),
(65,'Lega6',360),
(66,'Lega6',380),
(67,'Lega6',400),
(68,'Lega6',420),
(69,'Lega6',440),
(70,'Lega7',10450),
(71,'Lega7',100),
(72,'Lega7',120),
(73,'Lega7',140),
(74,'Lega7',160),
(75,'Lega7',180),
(76,'Lega7',200),
(77,'Lega7',220),
(78,'Lega7',240),
(79,'Lega7',100),
(80,'Lega8',11950),
(81,'Lega8',100),
(82,'Lega8',120),
(83,'Lega8',140),
(84,'Lega8',160),
(85,'Lega8',180),
(86,'Lega8',200),
(87,'Lega8',220),
(88,'Lega8',240),
(89,'Lega8',260),
(90,'Lega9',13450),
(91,'Lega9',100),
(92,'Lega9',120),
(93,'Lega9',140),
(94,'Lega9',160),
(95,'Lega9',180),
(96,'Lega9',200),
(97,'Lega9',220),
(98,'Lega9',240),
(99,'Lega9',260),
(100,'Lega10',14950),
(101,'Lega10',100),
(102,'Lega10',120),
(103,'Lega10',140),
(104,'Lega10',160),
(105,'Lega10',180),
(106,'Lega10',200),
(107,'Lega10',220),
(108,'Lega10',240),
(109,'Lega10',260);
/*!40000 ALTER TABLE `obiettivi` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `ob_punteggio`
--
USE `ServerDomDB`;
LOCK TABLES `ob_punteggio` WRITE;
/*!40000 ALTER TABLE `ob_punteggio` DISABLE KEYS */;
INSERT INTO `ob_punteggio` VALUES 
(1,0),
(2,100),
(3,250),
(4,400),
(5,550),
(6,700),
(7,850),
(8,1000),
(9,1150),
(10,1300),
(11,1450),
(12,1600),
(13,1750),
(14,1900),
(15,2050),
(16,2200),
(17,2350),
(18,2500),
(19,2650),
(20,2800),
(21,2950),
(22,3100),
(23,3250),
(24,3400),
(25,3550),
(26,3700),
(27,3850),
(28,4000),
(29,4150),
(30,4300),
(31,4450),
(32,4600),
(33,4750),
(34,4900),
(35,5050),
(36,5200),
(37,5350),
(38,5500),
(39,5650),
(40,5800),
(41,5950),
(42,6100),
(43,6250),
(44,6400),
(45,6550),
(46,6700),
(47,6850),
(48,7000),
(49,7150),
(50,7300),
(51,7450),
(52,7600),
(53,7750),
(54,7900),
(55,8050),
(56,8200),
(57,8350),
(58,8500),
(59,8650),
(60,8800),
(61,8950),
(62,9100),
(63,9250),
(64,9400),
(65,9550),
(66,9700),
(67,9850),
(68,10000),
(69,10150),
(70,10300),
(71,10450),
(72,10600),
(73,10750),
(74,10900),
(75,11050),
(76,11200),
(77,11350),
(78,11500),
(79,11650),
(80,11800),
(81,11950),
(82,12100),
(83,12250),
(84,12400),
(85,12550),
(86,12700),
(87,12850),
(88,13000),
(89,13150),
(90,13300),
(91,13450),
(92,13600),
(93,13750),
(94,13900),
(95,14050),
(96,14200),
(97,14350),
(98,14500),
(99,14650),
(100,14800),
(101,15200),
(102,15700),
(103,16200),
(104,16700),
(105,17200),
(106,17700),
(107,18200),
(108,18700),
(109,19200);
/*!40000 ALTER TABLE `ob_punteggio` ENABLE KEYS */;
UNLOCK TABLES;


/*
 * DA USARE SE SI VUOLE FAR FUNZIONARE 
 * I TEST 
 */
/*
-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`LINGUA`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'nome1', 'INTERNO','ITnome1','ENnome1');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'nome2', 'INTERNO','ITnome2','ENnome2');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'nome3', 'INTERNO','ITnome3','ENnome3');

INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'descrizione1', 'INTERNO','ITdescrizione1','ENdescrizione1');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'descrizione2', 'INTERNO','ITdescrizione2','ENdescrizione2');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'descrizione3', 'INTERNO','ITdescrizione3','ENdescrizione3');

INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'LEGA 1', 'INTERNO','IT LEGA 1','EN LEGA 1');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'LEGA 2', 'INTERNO','IT LEGA 2','EN LEGA 2');
INSERT INTO `ServerDomDB`.`LINGUA` (`CHIAVE`,`TIPO`,`ITALIANO`,`ENGLISH`)VALUES( 'LEGA 3', 'INTERNO','IT LEGA 3','EN LEGA 3');
COMMIT;

-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`USER_ACCOUNT`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Tawa', 200, 400, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('MatPara', 200, 500, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('MatC', 200, 600, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Gio', 200, 700, NULL, '123456789');
INSERT INTO `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`, `MNY`, `PUNTEGGIO`, `EMAIL`, `PASSW`) VALUES ('Luca', 200, 800, NULL, '123456789');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`ASSET`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`ASSET` (`idAsset`, `COSTO`, `NOME`, `DESCRIZIONE`, `LIVELLO`) VALUES (1, 10, 'nome1', 'descrizione1', 1);
INSERT INTO `ServerDomDB`.`ASSET` (`idAsset`, `COSTO`, `NOME`, `DESCRIZIONE`, `LIVELLO`) VALUES (2, 20, 'nome2', 'descrizione2', 1);
INSERT INTO `ServerDomDB`.`ASSET` (`idAsset`, `COSTO`, `NOME`, `DESCRIZIONE`, `LIVELLO`) VALUES (3, 30, 'nome3', 'descrizione3', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`ASET_Own`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (1, 'Tawa', 3);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (1, 'MatPara', 3);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (1, 'Matc', 3);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (2, 'Gio', 2);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (2, 'Luca', 2);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (2, 'MatPara', 2);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (3, 'Matc', 1);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (3, 'Tawa', 1);
INSERT INTO `ServerDomDB`.`ASET_Own` (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`, `QUANTITA`) VALUES (3, 'MatPara', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`OBIETTIVI`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`OBIETTIVI` (`idObiettivo`, `DESCRIZIONE`, `RICOMPENSA`) VALUES (1, 'LEGA 1', 100);
INSERT INTO `ServerDomDB`.`OBIETTIVI` (`idObiettivo`, `DESCRIZIONE`, `RICOMPENSA`) VALUES (2, 'LEGA 2', 200);
INSERT INTO `ServerDomDB`.`OBIETTIVI` (`idObiettivo`, `DESCRIZIONE`, `RICOMPENSA`) VALUES (3, 'LEGA 3', 400);

COMMIT;

-- -----------------------------------------------------
-- Data for table `ServerDomDB`.`OB_PUNTEGGIO`
-- -----------------------------------------------------
START TRANSACTION;
USE `ServerDomDB`;
INSERT INTO `ServerDomDB`.`OB_PUNTEGGIO` (`OBIETTIVI_idObiettivo`, `PUNTEGGIO_OBIETTIVO`) VALUES (1, 100);
INSERT INTO `ServerDomDB`.`OB_PUNTEGGIO` (`OBIETTIVI_idObiettivo`, `PUNTEGGIO_OBIETTIVO`) VALUES (2, 200);
INSERT INTO `ServerDomDB`.`OB_PUNTEGGIO` (`OBIETTIVI_idObiettivo`, `PUNTEGGIO_OBIETTIVO`) VALUES (3, 400);

COMMIT;

*/