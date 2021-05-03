/********************************************DATU BASEA SORTZEKO********************************************************/

create database Liburutegia;

use Liburutegia;

create table Idazlea (
    znb  	        bigint(10) AUTO_INCREMENT,
    izena           varchar(20),
    abizenak        varchar(20),
    generoa         varchar(20),
    herrialdea      varchar(30),
    Primary Key(znb)
);

create table Argitaletxea (
    ifk	                varchar(9),
    izena       		varchar(30),
    helbidea    		varchar(50),
    Primary Key (ifk)
);

create table Erabiltzailea (
    nan	                varchar(9),
    izena       		varchar(20),
    abizena				varchar(20),
    jaiotze_data 		date,
    generoa      		varchar(20),
    liburuzaina_da  	bit NOT NULL,
    pasahitza 			varchar(50) NOT NULL,
    Primary Key (nan)
);

create table Liburua (
    isbn                bigint(13),
    izena               varchar(200),
    argitaratze_data    date,
    lengoaia        	varchar(20),
    mailegatuta         bit,
    erreserbatuta       bit,
    erab_nan            varchar(9),
    idz_znb     	    bigint(10),
    arg_ifk             varchar(9),
    Primary Key(isbn),
    Foreign Key(erab_nan) references Erabiltzailea(nan) ON DELETE SET NULL,
    Foreign Key(idz_znb) references Idazlea(znb) ON DELETE SET NULL,
    Foreign Key(arg_ifk) references Argitaletxea(ifk) ON DELETE SET NULL
);

create table Kolekzioa (
    erab_nan            varchar(9),
    izena               varchar(30),
    Primary Key (erab_nan, izena),
    Foreign Key(erab_nan) references Erabiltzailea(nan) ON DELETE CASCADE
);

create table Kolekzio_Liburua (
    erab_nan	        varchar(9),
    kol_izena           varchar(30),
    lib_isbn            bigint(13),
    Primary Key (erab_nan, kol_izena, lib_isbn),
    Foreign Key(lib_isbn) references Liburua(isbn) ON DELETE CASCADE,
    Foreign Key(erab_nan, kol_izena) references Kolekzioa(erab_nan, izena) ON DELETE CASCADE
);

/********************************************DATU BASEA POPULATU***************************************************/
INSERT INTO Erabiltzailea VALUES ('12345678A','Jon','Ruiz','2000-10-10','Mutila',0,'12');
INSERT INTO Erabiltzailea VALUES ('23456789B','Ander','Garcia','2001-01-10','Beste',0,'123');
INSERT INTO Erabiltzailea VALUES ('34567890C','Andoni','Ramos','2000-02-29','Mutila',0,'a1234');
INSERT INTO Erabiltzailea VALUES ('45678901D','Olatz','Garcia','2012-11-10','Neska',0,'4321');
INSERT INTO Erabiltzailea VALUES ('56789012E','Guillermo','Diaz','1998-03-14','Mutila',1,'liburuzaina');

INSERT INTO Argitaletxea VALUES('Q4818011B','EHU argitalpenak','Ingeniero Torres Quevedo Plaza, 1, 48013 Bilbao');
INSERT INTO Argitaletxea VALUES('Q2085301B','Elkar','Portuetxe Kalea, 88, 20018 Donostia');
INSERT INTO Argitaletxea VALUES('Q3152071B','Santillana','Av. Marcelo Celayeta, 54, 31014 Pamplona');
INSERT INTO Argitaletxea VALUES('Q9645721A','MIT publisher','77 Massachusetts Ave, Cambridge, MA 02139');

INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Ander','Martinez','Mutila','Espainia');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Ana','Hurtado','Neska','Frantzia');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Aitziber','Unzueta','Neska','Espainia');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Miguel','de Cervantes','Mutila','Espainia');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('William','Shakespeare','Mutila','Ingalaterra');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Donald','Chamberlin','Mutila','Estatu Batuak');

INSERT INTO Liburua VALUES (13579,'Don Quijote de la Mancha','1605-01-01', 'Gaztelera',0,0,NULL,4,NULL);
INSERT INTO Liburua VALUES (13575,'Hamlet','1603-01-01', 'Ingelesa',0,0,NULL,5,NULL);
INSERT INTO Liburua VALUES (9783484812874,'Kalkulua II','2000-05-11', 'Euskera',0,0,NULL,2,'Q4818011B');
INSERT INTO Liburua VALUES (9782482486312,'SQL manual','1997-05-11', 'Ingelesa',0,0,NULL,6,'Q9645721A');
INSERT INTO Liburua VALUES (9782482486313,'SQL manual (II)','1998-02-28', 'Ingelesa',1,0,'12345678A',6,'Q9645721A');
INSERT INTO Liburua VALUES (9782482486314,'SQL manual (III)','1998-09-13', 'Ingelesa',0,1,'45678901D',6,'Q9645721A');
INSERT INTO Liburua VALUES (9788484386377,'Ikerkuntza operatiboari begirada praktikoa ematen','2017-06-12', 'Euskera',0,0,NULL,3,'Q4818011B');

INSERT INTO Kolekzioa VALUES('12345678A', 'Klasikoak');
INSERT INTO Kolekzioa VALUES('12345678A', 'SQL Kolekzioa');
INSERT INTO Kolekzioa VALUES('12345678A', 'Hurrengo kurtsorako');
INSERT INTO Kolekzioa VALUES('45678901D', 'SQL Kolekzioa');
INSERT INTO Kolekzioa VALUES('34567890C', 'EHU liburuak');

INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'Klasikoak', 13579);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'Klasikoak', 13575);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'SQL Kolekzioa', 9782482486312);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'SQL Kolekzioa', 9782482486313);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'SQL Kolekzioa', 9782482486314);
INSERT INTO Kolekzio_Liburua VALUES('45678901D', 'SQL Kolekzioa', 9782482486314);
INSERT INTO Kolekzio_Liburua VALUES('34567890C', 'EHU liburuak', 9783484812874);
INSERT INTO Kolekzio_Liburua VALUES('34567890C', 'EHU liburuak', 9788484386377);