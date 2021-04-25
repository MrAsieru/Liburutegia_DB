/********************************************DATU BASEA SORTZEKO********************************************************/

create database Liburutegia;

use Liburutegia;

create table Idazlea (
    znb  	        int(10) AUTO_INCREMENT,
    izena           varchar(20),
    abizenak        varchar(20),
    generoa         varchar(20),
    herrialdea      varchar(30),
    Primary Key(znb)
);

create table Argitaletxe (
    ifk	                varchar(9),
    izena       		varchar(20),
    helbidea    		varchar(20),
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
    isbn                int(13),
    izena               varchar(200),
    argitaratze_data    date,
    lengoaia        	varchar(20),
    mailegatuta         bit,
    erreserbatuta       bit,
    erab_nan            varchar(9),
    idz_znb     	    int(10),
    arg_ifk             varchar(9),
    Primary Key(isbn),
    Foreign Key(erab_nan) references Erabiltzailea(nan),
    Foreign Key(idz_znb) references Idazlea(znb),
    Foreign Key(arg_ifk) references Argitaletxe(ifk)
);

create table Kolekzioa (
    erab_nan            varchar(9),
    izena               varchar(30),
    Primary Key (erab_nan, izena),
    Foreign Key(erab_nan) references Erabiltzailea(nan)
);

create table Kolekzio_Liburua (
    erab_nan	        varchar(9),
    kol_izena              	varchar(30),
    lib_isbn            int(13),
    Primary Key (erab_nan, kol_izena, lib_isbn),
    Foreign Key(lib_isbn) references Liburua(isbn),
    Foreign Key(erab_nan, kol_izena) references Kolekzioa(erab_nan, izena)
);

/********************************************DATU BASEA MANIPULATZEKO***************************************************/
/*
# TABLAK ADIERAZI:
show tables;

# TABLA GUZTIEN SORRERA ADIERAZI:
show create table Argitaletxe;
show create table Erabiltzailea;
show create table Idazlea;
show create table Kolekzio_Liburua;
show create table Kolekzioa;
show create table Liburua;

# TABLA OSOA EZABATU:
drop database Liburutegia;
*/
INSERT INTO Erabiltzailea VALUES ('12345678A','Jon','Ruiz','2000-10-10','Mutila',0,'12');
INSERT INTO Erabiltzailea VALUES ('23456789A','Ander','Garcia','2001-01-10','Beste',0,'123');
INSERT INTO Erabiltzailea VALUES ('34567890A','Andoni','Ramos','2000-02-29','Mutila',0,'a1234');
INSERT INTO Erabiltzailea VALUES ('45678901A','Olatz','Garcia','2012-11-10','Neska',0,'4321');
INSERT INTO Erabiltzailea VALUES ('1','Liburu','Zain','2000-11-10','Neska',1,'1');

INSERT INTO Argitaletxe VALUES('98765432A','LiburuArgitaletxe','Gran Via 45');
INSERT INTO Argitaletxe VALUES('98765432B','Elkar','Gran Via 40');
INSERT INTO Argitaletxe VALUES('98765432C','Santillana','Gran Via 43');

INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Ander','Martinez','Mutila','Espainia');
INSERT INTO Idazlea (izena, abizenak, generoa, herrialdea) VALUES('Ana','Hurtado','Neska','Frantzia');

INSERT INTO Liburua VALUES (13579,'Don Quijote','2004-05-10', 'Gaztelera',0,0,NULL,1,'98765432A');
INSERT INTO Liburua VALUES (45464,'Kalkulua II','2000-05-11', 'Euskera',0,0,NULL,2,'98765432B');
INSERT INTO Liburua VALUES (45468,'SQL manual','1997-05-11', 'Ingelesa',0,0,NULL,1,'98765432C');
INSERT INTO Liburua VALUES (45469,'SQL manual (II)','1997-05-12', 'Ingelesa',1,0,'12345678A',1,'98765432C');
INSERT INTO Liburua VALUES (45470,'SQL manual (III)','1997-05-13', 'Ingelesa',0,1,'45678901A',1,'98765432C');

INSERT INTO Kolekzioa VALUES('12345678A', 'Nire Kolekzioa');
INSERT INTO Kolekzioa VALUES('12345678A', 'Nire bigarren');
INSERT INTO Kolekzioa VALUES('12345678A', 'Nire hirugarren');
INSERT INTO Kolekzioa VALUES('45678901A', 'Nire');

INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'Nire Kolekzioa', 13579);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'Nire Kolekzioa', 45464);
INSERT INTO Kolekzio_Liburua VALUES('12345678A', 'Nire bigarren', 45468);
INSERT INTO Kolekzio_Liburua VALUES('45678901A', 'Nire', 45468);



