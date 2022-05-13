-- -----------------------------------------------------
-- ###############SERVER###############
-- MySQL Community Server - GPL

-- ###############VERSION###############
-- 8.0.XX
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Schema serverdomdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `serverdomdb` ;

-- -----------------------------------------------------
-- Schema serverdomdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `serverdomdb` DEFAULT CHARACTER SET utf8 ;
USE `serverdomdb` ;

-- -----------------------------------------------------
-- Table `serverdomdb`.`lingua`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`lingua` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`lingua` (
  `CHIAVE` VARCHAR(60) NOT NULL,
  `TIPO` ENUM('INTERNO', 'ESTERNO') NULL DEFAULT 'INTERNO',
  `ITALIANO` VARCHAR(200) NULL DEFAULT NULL,
  `ENGLISH` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`CHIAVE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`asset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`asset` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`asset` (
  `idAsset` INT NOT NULL,
  `COSTO` INT NULL DEFAULT '1000000',
  `NOME` VARCHAR(60) NOT NULL,
  `DESCRIZIONE` VARCHAR(60) NOT NULL,
  `LIVELLO` INT NOT NULL,
  PRIMARY KEY (`idAsset`),
  INDEX `fk_asset_LINGUA1` (`DESCRIZIONE` ASC) VISIBLE,
  INDEX `fk_asset_LINGUA2` (`NOME` ASC) VISIBLE,
  CONSTRAINT `fk_asset_LINGUA1`
    FOREIGN KEY (`DESCRIZIONE`)
    REFERENCES `serverdomdb`.`lingua` (`CHIAVE`),
  CONSTRAINT `fk_asset_LINGUA2`
    FOREIGN KEY (`NOME`)
    REFERENCES `serverdomdb`.`lingua` (`CHIAVE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`user_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`user_account` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`user_account` (
  `USERNAME` VARCHAR(20) NOT NULL,
  `MNY` INT NOT NULL DEFAULT '0',
  `PUNTEGGIO` INT NULL DEFAULT '0',
  `EMAIL` VARCHAR(45) NULL DEFAULT NULL,
  `PASSW` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`aset_own`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`aset_own` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`aset_own` (
  `ASSET_idAsset` INT NOT NULL,
  `USER_ACCOUNT_USERNAME` VARCHAR(20) NOT NULL,
  `QUANTITA` INT NULL DEFAULT '0',
  PRIMARY KEY (`ASSET_idAsset`, `USER_ACCOUNT_USERNAME`),
  INDEX `fk_ASET_Own_ASSET1_idx` (`ASSET_idAsset` ASC) VISIBLE,
  INDEX `fk_ASET_Own_USER_ACCOUNT1_idx` (`USER_ACCOUNT_USERNAME` ASC) VISIBLE,
  CONSTRAINT `fk_ASET_Own_ASSET1`
    FOREIGN KEY (`ASSET_idAsset`)
    REFERENCES `serverdomdb`.`asset` (`idAsset`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ASET_Own_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `serverdomdb`.`user_account` (`USERNAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`obiettivi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`obiettivi` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`obiettivi` (
  `idObiettivo` INT NOT NULL,
  `DESCRIZIONE` VARCHAR(60) NOT NULL,
  `RICOMPENSA` INT NOT NULL,
  PRIMARY KEY (`idObiettivo`),
  INDEX `fk_obiettivi_LINGUA1` (`DESCRIZIONE` ASC) VISIBLE,
  CONSTRAINT `fk_obiettivi_LINGUA1`
    FOREIGN KEY (`DESCRIZIONE`)
    REFERENCES `serverdomdb`.`lingua` (`CHIAVE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`ob_punteggio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`ob_punteggio` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`ob_punteggio` (
  `OBIETTIVI_idObiettivo` INT NOT NULL,
  `PUNTEGGIO_OBIETTIVO` INT NOT NULL,
  PRIMARY KEY (`OBIETTIVI_idObiettivo`),
  UNIQUE INDEX `PUNTEGGIO_OBIETTIVO_UNIQUE` (`PUNTEGGIO_OBIETTIVO` ASC) VISIBLE,
  INDEX `fk_OB_PUNTEGGIO_OBIETTIVI1_idx` (`OBIETTIVI_idObiettivo` ASC) VISIBLE,
  CONSTRAINT `fk_OB_PUNTEGGIO_OBIETTIVI1`
    FOREIGN KEY (`OBIETTIVI_idObiettivo`)
    REFERENCES `serverdomdb`.`obiettivi` (`idObiettivo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`obiettivi_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`obiettivi_user` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`obiettivi_user` (
  `STATO` ENUM('COMPLETATO', 'NON COMPLETATO') NOT NULL DEFAULT 'NON COMPLETATO',
  `USER_ACCOUNT_USERNAME` VARCHAR(20) NOT NULL,
  `OBIETTIVI_idObiettivo` INT NOT NULL,
  PRIMARY KEY (`USER_ACCOUNT_USERNAME`, `OBIETTIVI_idObiettivo`),
  INDEX `fk_OBIETTIVI_USER_OBIETTIVI1_idx` (`OBIETTIVI_idObiettivo` ASC) VISIBLE,
  CONSTRAINT `fk_OBIETTIVI_USER_OBIETTIVI1`
    FOREIGN KEY (`OBIETTIVI_idObiettivo`)
    REFERENCES `serverdomdb`.`obiettivi` (`idObiettivo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_OBIETTIVI_USER_USER_ACCOUNT1`
    FOREIGN KEY (`USER_ACCOUNT_USERNAME`)
    REFERENCES `serverdomdb`.`user_account` (`USERNAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`USERS` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`USERS` (
  `user_account_USERNAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_account_USERNAME`),
  CONSTRAINT `fk_USERS_user_account1`
    FOREIGN KEY (`user_account_USERNAME`)
    REFERENCES `serverdomdb`.`user_account` (`USERNAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `serverdomdb`.`id_punteggio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `serverdomdb`.`id_punteggio` ;

CREATE TABLE IF NOT EXISTS `serverdomdb`.`id_punteggio` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_punteggio_ob_punteggio1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_id_punteggio_ob_punteggio1`
    FOREIGN KEY (`id`)
    REFERENCES `serverdomdb`.`ob_punteggio` (`OBIETTIVI_idObiettivo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `serverdomdb`;

DELIMITER $$

USE `serverdomdb`$$
DROP TRIGGER IF EXISTS `serverdomdb`.`CONTROLLO_RAGGIUNGIMENTO_OBIETTIVO` $$
USE `serverdomdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `serverdomdb`.`CONTROLLO_RAGGIUNGIMENTO_OBIETTIVO`
BEFORE UPDATE ON `serverdomdb`.`user_account`
FOR EACH ROW
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
IF OLD.PUNTEGGIO<>NEW.PUNTEGGIO AND ricompensaData>0
THEN
	UPDATE obiettivi_user set stato='COMPLETATO' 
    WHERE	USER_ACCOUNT_USERNAME=NEW.USERNAME 
			AND OBIETTIVI_idObiettivo 
			in(
                select 
					T3.idObiettivo
				from 
					OB_PUNTEGGIO 	AS T2 
					JOIN 
					OBIETTIVI 		AS T3 ON(T2.OBIETTIVI_idObiettivo=T3.idObiettivo) 
			WHERE 
				T2.PUNTEGGIO_OBIETTIVO<=NEW.PUNTEGGIO
			);
            SET NEW.MNY=OLD.MNY+ricompensaData;
    end if;
END$$


USE `serverdomdb`$$
DROP TRIGGER IF EXISTS `serverdomdb`.`user_account_AFTER_INSERT` $$
USE `serverdomdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `serverdomdb`.`user_account_AFTER_INSERT` AFTER INSERT ON `user_account` FOR EACH ROW
BEGIN
	INSERT INTO ServerDomDB.USERS VALUES( NEW.USERNAME);
END$$


USE `serverdomdb`$$
DROP TRIGGER IF EXISTS `serverdomdb`.`ob_punteggio_AFTER_INSERT` $$
USE `serverdomdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `serverdomdb`.`ob_punteggio_AFTER_INSERT` AFTER INSERT ON `ob_punteggio` FOR EACH ROW
BEGIN
	insert into serverdomdb.id_punteggio values(NEW.OBIETTIVI_idObiettivo);
	
END$$


USE `serverdomdb`$$
DROP TRIGGER IF EXISTS `serverdomdb`.`CONTROLLO_RAGGIUNGIMENTO_OB_PUNTEGGIO` $$
USE `serverdomdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `serverdomdb`.`CONTROLLO_RAGGIUNGIMENTO_OB_PUNTEGGIO`
BEFORE INSERT ON `serverdomdb`.`obiettivi_user`
FOR EACH ROW
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


USE `serverdomdb`$$
DROP TRIGGER IF EXISTS `serverdomdb`.`id_punteggio_AFTER_INSERT` $$
USE `serverdomdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `serverdomdb`.`id_punteggio_AFTER_INSERT` AFTER INSERT ON `id_punteggio` FOR EACH ROW
BEGIN
	INSERT INTO serverdomdb.obiettivi_user
		SELECT 'NON COMPLETATO',users.user_account_USERNAME, NEW.id FROM users
		WHERE TRUE;
END$$


DELIMITER ;

DROP USER IF EXISTS sd_sys;

CREATE USER 'sd_sys' IDENTIFIED BY '12345678';

GRANT SELECT, UPDATE, insert, alter, create, delete, DROP ON `ServerDomDB`.* TO 'sd_sys';
