-- -----------------------------------------------------
-- ###############SERVER###############
-- MySQL Community Server - GPL

-- ###############VERSION###############
-- 8.0.XX
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Schema ServerDomDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ServerDomDB` ;

-- -----------------------------------------------------
-- Schema ServerDomDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ServerDomDB` DEFAULT CHARACTER SET utf8 ;
USE `ServerDomDB` ;

-- -----------------------------------------------------
-- Table `ServerDomDB`.`USER_ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`USER_ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`USER_ACCOUNT` (
  `USERNAME` VARCHAR(20) NOT NULL,
  `MNY` INT NOT NULL DEFAULT 0,
  `PUNTEGGIO` INT NULL DEFAULT 0,
  `EMAIL` VARCHAR(45) NULL,
  `PASSW` VARCHAR(45) NULL,
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`ASSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`ASSET` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`ASSET` (
  `idAsset` INT NOT NULL ,
  `COSTO` INT NULL DEFAULT 1000000,
  `NOME` VARCHAR(20) NULL,
  `DESCRIZIONE` VARCHAR(200) NULL,
  `LIVELLO` INT NOT NULL,
  PRIMARY KEY (`idAsset`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`ASET_Own`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`ASET_Own` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`ASET_Own` (
  `ASSET_idAsset` INT NOT NULL,
  `USER_ACCOUNT_USERNAME` VARCHAR(20) NOT NULL,
  `QUANTITA` INT NULL DEFAULT 0,
  PRIMARY KEY (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`),
  INDEX `fk_ASET_Own_ASSET1_idx` (`ASSET_idAsset` ASC) VISIBLE,
  INDEX `fk_ASET_Own_USER_ACCOUNT1_idx` (`USER_ACCOUNT_USERNAME` ASC) VISIBLE,
  CONSTRAINT `fk_ASET_Own_ASSET1`
    FOREIGN KEY (`ASSET_idAsset`)
    REFERENCES `ServerDomDB`.`ASSET` (`idAsset`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ASET_Own_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`OBIETTIVI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`OBIETTIVI` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`OBIETTIVI` (
  `idObiettivo` INT NOT NULL,
  `DESCRIZIONE` VARCHAR(200) NOT NULL,
  `RICOMPENSA` INT NOT NULL,
  PRIMARY KEY (`idObiettivo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`OBIETTIVI_USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`OBIETTIVI_USER` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`OBIETTIVI_USER` (
  `STATO` ENUM('COMPLETATO', 'NON COMPLETATO') NOT NULL DEFAULT 'NON COMPLETATO',
  `USER_ACCOUNT_USERNAME` VARCHAR(20) NOT NULL,
  `OBIETTIVI_idObiettivo` INT NOT NULL,
  PRIMARY KEY (`USER_ACCOUNT_USERNAME`, `OBIETTIVI_idObiettivo`),
  INDEX `fk_OBIETTIVI_USER_OBIETTIVI1_idx` (`OBIETTIVI_idObiettivo` ASC) VISIBLE,
  CONSTRAINT `fk_OBIETTIVI_USER_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_OBIETTIVI_USER_OBIETTIVI1`
    FOREIGN KEY (`OBIETTIVI_idObiettivo`)
    REFERENCES `ServerDomDB`.`OBIETTIVI` (`idObiettivo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`OB_PUNTEGGIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`OB_PUNTEGGIO` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`OB_PUNTEGGIO` (
  `OBIETTIVI_idObiettivo` INT NOT NULL,
  `PUNTEGGIO_OBIETTIVO` INT NOT NULL,
  PRIMARY KEY (`OBIETTIVI_idObiettivo`),
  INDEX `fk_OB_PUNTEGGIO_OBIETTIVI1_idx` (`OBIETTIVI_idObiettivo` ASC) VISIBLE,
  UNIQUE INDEX `PUNTEGGIO_OBIETTIVO_UNIQUE` (`PUNTEGGIO_OBIETTIVO` ASC) VISIBLE,
  CONSTRAINT `fk_OB_PUNTEGGIO_OBIETTIVI1`
    FOREIGN KEY (`OBIETTIVI_idObiettivo`)
    REFERENCES `ServerDomDB`.`OBIETTIVI` (`idObiettivo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `ServerDomDB`;

DELIMITER $$

USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`CONTROLLO_RAGGIUNGIMENTO_OBIETTIVO` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`CONTROLLO_RAGGIUNGIMENTO_OBIETTIVO` BEFORE UPDATE ON `USER_ACCOUNT` FOR EACH ROW
BEGIN
declare ricompensaData int;
SET ricompensaData=(
	select 
		SUM(T3.RICOMPENSA)
	from 	
		OBIETTIVI_USER	 	AS T1 
		JOIN 
		OB_PUNTEGGIO 		AS T2 
		ON(T1.OBIETTIVI_idObiettivo=T2.OBIETTIVI_idObiettivo) 
		JOIN 
		OBIETTIVI 		AS T3 
		ON(T1.OBIETTIVI_idObiettivo=T3.idObiettivo) 
	WHERE 
		T1.STATO='NON COMPLETATO' 
		AND 
		T2.PUNTEGGIO_OBIETTIVO<=NEW.PUNTEGGIO
		AND 
		T1.USER_ACCOUNT_USERNAME=NEW.USERNAME
);

IF OLD.PUNTEGGIO<>NEW.PUNTEGGIO
THEN
	SET NEW.MNY=OLD.MNY+ricompensaData;
    
	UPDATE obiettivi_user set stato='COMPLETATO' 
    WHERE USER_ACCOUNT_USERNAME=NEW.USERNAME 
    AND OBIETTIVI_idObiettivo 
		in
			(select 
				T3.idObiettivo
			from 
				OB_PUNTEGGIO 		AS T2 
				JOIN 
				OBIETTIVI 		AS T3 ON(T2.OBIETTIVI_idObiettivo=T3.idObiettivo) 
			WHERE 
				T2.PUNTEGGIO_OBIETTIVO<=NEW.PUNTEGGIO
			);
    end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`CONTROLLO_RAGGIUNGIMENTO_OB_PUNTEGGIO` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`CONTROLLO_RAGGIUNGIMENTO_OB_PUNTEGGIO` BEFORE INSERT ON `OBIETTIVI_USER` FOR EACH ROW
BEGIN
declare punteggioGiocatore int;
declare punteggioObiettivo int;
declare ricompensaData int;
declare mny_giocatore int;
SET punteggioGiocatore=(
		SELECT PUNTEGGIO 
        FROM USER_ACCOUNT 
        WHERE USERNAME=NEW.USER_ACCOUNT_USERNAME
);
SET punteggioObiettivo=(
		SELECT PUNTEGGIO_OBIETTIVO 
        FROM OB_PUNTEGGIO 	
        WHERE OBIETTIVI_idObiettivo=new.OBIETTIVI_idObiettivo
);
SET ricompensaData=(
		SELECT ricompensa 
        FROM OBIETTIVI 
        WHERE idObiettivo=NEW.OBIETTIVI_idObiettivo
);
SET mny_giocatore=(
		SELECT MNY 
        FROM USER_ACCOUNT 
        WHERE USERNAME=NEW.USER_ACCOUNT_USERNAME
);
IF punteggioGiocatore>=punteggioObiettivo AND NEW.STATO='NON COMPLETATO'
THEN
	UPDATE USER_ACCOUNT 
		SET MNY=mny_giocatore+ricompensaData
	WHERE USERNAME=NEW.USER_ACCOUNT_USERNAME;
	SET NEW.STATO='COMPLETATO';
END IF;
END$$


DELIMITER ;
SET SQL_MODE = '';
DROP USER IF EXISTS sd_sys;

SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'sd_sys' IDENTIFIED BY '12345678';

GRANT SELECT, UPDATE, insert, alter, create, delete, DROP ON `ServerDomDB`.* TO 'sd_sys';
