/********************************************DATU BASEA SORTZEKO********************************************************/

create database Liburutegia;

use Liburutegia;

create table Idazlea (
    idazlea_znb  	int(10) AUTO_INCREMENT,
    izena           varchar(20),
    abizenak        varchar(20),
    generoa         varchar(20),
    herrialdea      varchar(30),
    Primary Key(idazlea_znb)
);

create table Argitaletxe (
    argitaletxea_ifk	varchar(9),
    izena       		varchar(20),
    helbidea    		varchar(20),
    Primary Key (argitaletxea_ifk)
);

create table Erabiltzailea (
    eraebiltzailea_nan	varchar(9),
    izena       		varchar(20),
    abizena				varchar(20),
    jaiotze_data 		date,
    generoa      		varchar(20),
    liburuzaina_da  	bit NOT NULL,
    pasahitza 			varchar(50) NOT NULL,
    Primary Key (eraebiltzailea_nan)
);

create table Liburua (
    liburua_isbn        int(13),
    izena               varchar(200),
    argitaratze_data    date,
    lengoaia        	varchar(20),
    mailegatuta         bit,
    erreserbatuta       bit,
    erabiltzailea_nan   varchar(9),
    idazlea_znb     	int(10),
    argitaletxea_ifk    varchar(9),
    Primary Key(liburua_isbn),
    Foreign Key(erabiltzailea_nan) references Erabiltzailea(erabiltzailea_nan),
    Foreign Key(idazlea_znb) references Idazlea(idazlea_znb),
    Foreign Key(argitaletxea_ifk) references Argitaletxe(argitaletxea_ifk)
);

create table Kolekzioa (
    erabiltzailea_nan   varchar(9),
    izena               varchar(30),
    Primary Key (erabiltzailea_nan, izena),
    Foreign Key(erabiltzailea_nan) references Erabiltzailea(erabiltzailea_nan)
);

create table Kolekzio_Liburua (
    liburua_isbn        int(13),
    erabiltzailea_nan	varchar(9),
    izena              	varchar(30),
    Primary Key (erabiltzailea_nan, izena, liburua_isbn),
    Foreign Key(liburua_isbn) references Liburua(liburua_isbn),
    Foreign Key(kolekzioa_erabiltzailea_nan, izena) references Kolekzioa(erabiltzailea_nan, izena)
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
