 /*
 ###########################--TEST TRIGGER ALL'INTERNO DI ASSET OWN--###########################
 */
 /*INSERISCO ASSET*/
 
insert into serverdomdb.ASSET values(default,20,'ASSET1','DESCRIZIONE_ASSET1', 1);
insert into serverdomdb.ASSET values(default,10,'ASSET2','DESCRIZIONE_ASSET2', 2);

 /*INSERISCO USER*/
insert into serverdomdb.user_account values('user1',default,default);
insert into serverdomdb.user_account values('user2',default,default);

########AGGIORNO GIOCATORI
UPDATE serverdomdb.user_account SET PUNTEGGIO=800 WHERE USERNAME='user1';
UPDATE serverdomdb.user_account SET PUNTEGGIO=500 WHERE USERNAME='user2';
/*INSERISCO OBIETTIVO*/
INSERT INTO serverdomdb.obiettivi VALUES(1,'LEGA 1',50);
INSERT INTO serverdomdb.obiettivi VALUES(2,'LEGA 2',100);

/*INSERISCO OBIETTIVI DI PUNTEGGIO*/
#######################LEGA 1
INSERT INTO serverdomdb.ob_punteggio VALUES(1,400);
#######################LEGA2
INSERT INTO serverdomdb.ob_punteggio VALUES(2,800);

/*ASSEGNO OBIETTIVI AGLI USER*/
INSERT INTO serverdomdb.obiettivi_user VALUES(default,'USER1',1);
INSERT INTO serverdomdb.obiettivi_user VALUES(default,'USER2',1);
INSERT INTO serverdomdb.obiettivi_user VALUES(default,'USER1',2);
INSERT INTO serverdomdb.obiettivi_user VALUES(default,'USER2',2);

########################################--IMPORTANTE--#########################################
						/*
                        RICORDARSI DI FARE IL CONTROLLO DELL'RAGGUNGIMENTO
                        OBIETTIVO ANCHE QUANDO UN OBIETTIVO NUOVO VIENE 
                        INSERITO
                        */
/*AGGIORNO PUNTEGGIO GIOCATORE DI MODO CHE RAGGIUNGANO GLI OBIETTIVI*/
UPDATE SERVERDOMDB.USER_ACCOUNT SET mny=401 WHERE USERNAME='USER1';
UPDATE SERVERDOMDB.USER_ACCOUNT SET mny=501 WHERE USERNAME='USER2';

/*
##--ASSET AQUISTABILI--##
ASSET_ID= 1: 
	COSTO 20
ASSET_ID= 2: 
	COSTO 20

##--GIOCATORE COMPRA ASSET--##
I SOLDI DA 50 
DEVONO ESSERE 10 PER ENTRABMI GLI USER_ACCOUNT
*/

INSERT INTO serverdomdb.ASET_OWN VALUES(1,'USER1',1);
INSERT INTO serverdomdb.ASET_OWN VALUES(1,'USER2',1);

INSERT INTO serverdomdb.ASET_OWN VALUES(2,'USER1',2);
INSERT INTO serverdomdb.ASET_OWN VALUES(2,'USER2',2);

/*
SE IL GIOCATORE POSSIEDE UN ASSET DI UNA CERTA TIPOLOGIA NON è POSSIBILE FARA L'INSER DI UN ASSET DELLA MEDESIMA TIPOLOGIA 
QUINDI NON SI Pò ESEGUIRE LA QUERY SEGUENTE QUESTO IN QUANTO: FATAL EROR : DUPLICATE PRIMARY KEE
INSERT INTO serverdomdb.ASET_OWN VALUES(1,'USER1',2);
*/

/*GIOCATORE USA ASSET*/
UPDATE ASET_OWN SET QUANTITA=1 WHERE  QUANTITA =2;

/*GIOCATORE COMPRA ASSET DELLA STESSA TIPOLOGIA CHE POSSIEDE */
#######soldi dei 2 giocatori devono essere=10-10=0
UPDATE ASET_OWN SET QUANTITA=2 WHERE  ASSET_idAsset=2;

/*adesso giocatori non hanno soldi quindi la prosima deve fallire*/
UPDATE ASET_OWN SET QUANTITA=2 WHERE  ASSET_idAsset=1;

#############################TEST PASSATO#############################
