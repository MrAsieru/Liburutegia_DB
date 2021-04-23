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
    lib_isbn            int(13),
    erab_nan	        varchar(9),
    izena              	varchar(30),
    Primary Key (erab_nan, izena, lib_isbn),
    Foreign Key(lib_isbn) references Liburua(isbn),
    Foreign Key(erab_nan, izena) references Kolekzioa(erab_nan, izena)
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
