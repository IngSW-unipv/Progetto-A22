/*TEST_TRIGGER CONTROLLO RAGGIUNGIMENTO OBIETTIVI*/
	/*CON GLI STESSI DATI DI: Test_triggerAggiornamentoPunteggioGiocatore.... ESEGUO.....*/


/*INSERISCO OBIETTIVO*/
INSERT INTO serverdomdb.obiettivi VALUES(1,'LEGA 1',50);
INSERT INTO serverdomdb.obiettivi VALUES(2,'LEGA 2',100);

/*INSERISCO OBIETTIVI DI PUNTEGGIO*/
#######################LEGA 1
INSERT INTO serverdomdb.ob_punteggio VALUES(1,400);

#######################LEGA2
INSERT INTO serverdomdb.ob_punteggio VALUES(2,800);

/*ASSEGNO OBIETTIVI AGLI USER*/
INSERT INTO serverdomdb.obiettivi_user VALUES('USER1',1);
INSERT INTO serverdomdb.obiettivi_user VALUES('USER2',1);
INSERT INTO serverdomdb.obiettivi_user VALUES('USER1',2);
INSERT INTO serverdomdb.obiettivi_user VALUES('USER2',2);


/*AGGIORNO PUNTEGGIO*/

UPDATE SERVERDOMDB.USER_ACCOUNT SET PUNTEGGIO=400 WHERE USERNAME='USER1';
UPDATE SERVERDOMDB.USER_ACCOUNT SET PUNTEGGIO=500 WHERE USERNAME='USER2';

#############################TEST PASSATO#############################


