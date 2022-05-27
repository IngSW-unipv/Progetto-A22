

/*
 * DA USARE SE SI VUOLE FAR FUNZIONARE 
 * I TEST 
 */

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