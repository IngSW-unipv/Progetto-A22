--
-- Server 
-- 		Product: MySQL Community Server - GPL 
-- Version: 8.0
--
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
-- Table `ServerDomDB`.`ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`ACCOUNT` (
  `USERNAME` VARCHAR(20) NOT NULL,
  `EMAIL` VARCHAR(255) NULL DEFAULT 'null',
  `PASSW` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`USER_ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`USER_ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`USER_ACCOUNT` (
  `USERNAME` VARCHAR(20) NOT NULL,
  `MNY` INT NOT NULL DEFAULT 0,
  `PUNTEGGIO` INT NULL DEFAULT 0,
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`ASSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`ASSET` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`ASSET` (
  `idAsset` INT NOT NULL AUTO_INCREMENT,
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ASET_Own_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OBIETTIVI_USER_OBIETTIVI1`
    FOREIGN KEY (`OBIETTIVI_idObiettivo`)
    REFERENCES `ServerDomDB`.`OBIETTIVI` (`idObiettivo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`VIRUS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`VIRUS` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`VIRUS` (
  `CAPACITA_OFFENSIVA` INT NOT NULL,
  `TIPO` VARCHAR(45) NULL,
  `ASSET_idAsset` INT NOT NULL,
  PRIMARY KEY (`ASSET_idAsset`),
  INDEX `fk_VIRUS_ASSET_idx` (`ASSET_idAsset` ASC) VISIBLE,
  CONSTRAINT `fk_VIRUS_ASSET`
    FOREIGN KEY (`ASSET_idAsset`)
    REFERENCES `ServerDomDB`.`ASSET` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`ANTIVIRUS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`ANTIVIRUS` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`ANTIVIRUS` (
  `CAPACITA_DIFENSIVA` INT NOT NULL,
  `TIPO` VARCHAR(45) NULL,
  `ASSET_idAsset` INT NOT NULL,
  PRIMARY KEY (`ASSET_idAsset`),
  CONSTRAINT `fk_ANTIVIRUS_ASSET1`
    FOREIGN KEY (`ASSET_idAsset`)
    REFERENCES `ServerDomDB`.`ASSET` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`PARTITA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`PARTITA` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`PARTITA` (
  `idPARTITA` INT NOT NULL AUTO_INCREMENT,
  `INIZIO` DATETIME NULL,
  `FINE` DATETIME NULL,
  PRIMARY KEY (`idPARTITA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServerDomDB`.`GIOCATORE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ServerDomDB`.`GIOCATORE` ;

CREATE TABLE IF NOT EXISTS `ServerDomDB`.`GIOCATORE` (
  `idGIOCATORE` INT NOT NULL AUTO_INCREMENT,
  `POSIZIONE` INT NULL,
  `FINE_GIOCO` DATETIME NULL DEFAULT NOW(),
  `PARTITA_idPARTITA` INT NOT NULL,
  `USER_ACCOUNT_USERNAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idGIOCATORE`, `PARTITA_idPARTITA`, `USER_ACCOUNT_USERNAME`),
  INDEX `fk_GIOCATORE_PARTITA1_idx` (`PARTITA_idPARTITA` ASC) VISIBLE,
  INDEX `fk_GIOCATORE_USER_ACCOUNT1_idx` (`USER_ACCOUNT_USERNAME` ASC) VISIBLE,
  CONSTRAINT `fk_GIOCATORE_PARTITA1`
    FOREIGN KEY (`PARTITA_idPARTITA`)
    REFERENCES `ServerDomDB`.`PARTITA` (`idPARTITA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GIOCATORE_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `ServerDomDB`.`USER_ACCOUNT` (`USERNAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ServerDomDB`;

DELIMITER $$

USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`ACCOUNT_BEFORE_INSERT` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`ACCOUNT_BEFORE_INSERT` BEFORE INSERT ON `ACCOUNT` FOR EACH ROW
BEGIN
	if 
		new.username is not null 
        and 
        new.username not in(select USERNAME FROM USER_ACCOUNT)
	then
		insert into USER_ACCOUNT VALUES(new.username, default, default);   
	else
		KILL query connection_id();
    end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`ACCOUNT_BEFORE_UPDATE` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`ACCOUNT_BEFORE_UPDATE` BEFORE UPDATE ON `ACCOUNT` FOR EACH ROW
BEGIN
if (#se cambia username E USERNAME è GIA USATO
			old.username<>new.username		AND 
			new.username in(select USERNAME FROM USER_ACCOUNT))
	then
		kill query connection_id();
	else 
		if  old.username<>new.username
        then
			update USER_ACCOUNT set username=new.username where username=old.username;
		end if;
	end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`USER_ACCOUNT_BEFORE_UPDATE` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`USER_ACCOUNT_BEFORE_UPDATE` BEFORE UPDATE ON `USER_ACCOUNT` FOR EACH ROW
BEGIN
		IF 
			OLD.PUNTEGGIO<>NEW.PUNTEGGIO
			AND 
			(select count(*)
				from 	
						OBIETTIVI_USER	 	AS T1 
						JOIN 
						OB_PUNTEGGIO 		AS T2 
							ON(T1.OBIETTIVI_idObiettivo=T2.OBIETTIVI_idObiettivo) 
				WHERE 
						T1.STATO='NON COMPLETATO' 
						AND 
						T2.PUNTEGGIO_OBIETTIVO<=NEW.PUNTEGGIO
						AND 
						T1.USER_ACCOUNT_USERNAME=NEW.USERNAME
				)>0
	THEN
		SET NEW.MNY=OLD.MNY+
            (#RICOMPENSA DEL GIOCATORE 
            SELECT SUM(R)
            FROM
					(
						select 
								T1.USER_ACCOUNT_USERNAME,SUM(T3.RICOMPENSA) AS R
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
						GROUP BY T1.USER_ACCOUNT_USERNAME
						)AS T5
					);
		UPDATE obiettivi_user set stato='COMPLETATO' 
        WHERE USER_ACCOUNT_USERNAME=NEW.USERNAME 
        AND OBIETTIVI_idObiettivo 
				in
							(	select 
										T3.idObiettivo
								from 
										OB_PUNTEGGIO 		AS T2 
										JOIN 
										OBIETTIVI 		AS T3 
											ON(T2.OBIETTIVI_idObiettivo=T3.idObiettivo) 
								WHERE 
										T2.PUNTEGGIO_OBIETTIVO<=NEW.PUNTEGGIO
							);
    end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`ASET_Own_BEFORE_INSERT` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`ASET_Own_BEFORE_INSERT` BEFORE INSERT ON `ASET_Own` FOR EACH ROW
BEGIN
declare manyGiocatore int;
declare spesa int;
SET manyGiocatore=(#soldiGiocatore
		select MNY
		from USER_ACCOUNT AS T1
		where T1.USERNAME=new.USER_ACCOUNT_USERNAME);
SET spesa=(#quanto spende nella tranzsazione
			select Costo 
            from Asset
            where idAsset=new.ASSET_idAsset)*new.quantita;
if manyGiocatore<spesa
THEN 
	KILL QUERY connection_id();
ELSE 
	UPDATE USER_ACCOUNT SET MNY=manyGiocatore-spesa 
    WHERE USER_ACCOUNT.USERNAME=NEW.USER_ACCOUNT_USERNAME;
end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`ASET_Own_BEFORE_UPDATE` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`ASET_Own_BEFORE_UPDATE` BEFORE UPDATE ON `ASET_Own` FOR EACH ROW
BEGIN
declare manyGiocatore int;
declare spesa int;
declare QNT int;
IF new.quantita>=OLD.quantita
THEN
	SET QNT=new.quantita-OLD.quantita;
ELSE
	SET QNT=0;
END IF;
SET manyGiocatore=(#soldiGiocatore
		select MNY
		from USER_ACCOUNT AS T1
		where T1.USERNAME=new.USER_ACCOUNT_USERNAME);
SET spesa=(#quanto spende nella tranzsazione
			select Costo 
            from Asset
            where idAsset=new.ASSET_idAsset)*QNT;
if manyGiocatore<spesa
THEN 
	KILL QUERY connection_id();
ELSE 
	UPDATE USER_ACCOUNT SET MNY=manyGiocatore-spesa 
	WHERE USER_ACCOUNT.USERNAME=NEW.USER_ACCOUNT_USERNAME;
		
end if;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`OBIETTIVI_USER_AFTER_INSERT` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`OBIETTIVI_USER_AFTER_INSERT` BEFORE INSERT ON `OBIETTIVI_USER` FOR EACH ROW
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
        FROM 
        OBIETTIVI_USER 	AS T1
        JOIN 
		OB_PUNTEGGIO 	AS T2 
			ON T2.OBIETTIVI_idObiettivo=new.OBIETTIVI_idObiettivo
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


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`GIOCATORE_AFTER_INSERT` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`GIOCATORE_AFTER_INSERT` AFTER INSERT ON `GIOCATORE` FOR EACH ROW
BEGIN
	if new.POSIZIONE=1
    THEN
    UPDATE USER_ACCOUNT SET PUNTEGGIO=PUNTEGGIO+100 WHERE USER_ACCOUNT.USERNAME=new.USER_ACCOUNT_USERNAME;
    END IF;
END$$


USE `ServerDomDB`$$
DROP TRIGGER IF EXISTS `ServerDomDB`.`GIOCATORE_AFTER_UPDATE` $$
USE `ServerDomDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ServerDomDB`.`GIOCATORE_AFTER_UPDATE` AFTER UPDATE ON `GIOCATORE` FOR EACH ROW
BEGIN
	if new.POSIZIONE=1
    THEN
    UPDATE USER_ACCOUNT SET PUNTEGGIO=PUNTEGGIO+100 WHERE USER_ACCOUNT.USERNAME=new.USER_ACCOUNT_USERNAME;
    END IF;
END$$


DELIMITER ;
SET SQL_MODE = '';
DROP USER IF EXISTS sd_sys;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'sd_sys' IDENTIFIED BY '12345678';

GRANT ALL ON `ServerDomDB`.* TO 'sd_sys';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;