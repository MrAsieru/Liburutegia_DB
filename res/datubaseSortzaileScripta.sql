/********************************************DATU BASEA SORTZEKO********************************************************/

create database Liburutegia;

use Liburutegia;

create table Idazlea (
    IdazleZenbakia  int(10) AUTO_INCREMENT,
    Izena           varchar(20),
    Abizenak        varchar(20),
    Genero          varchar(20),
    Herrialdea      varchar(30),
    Primary Key(IdazleZenbakia)
);

create table Argitaletxe (
    IFK         varchar(9),
    Izena       varchar(20),
    Helbidea    varchar(20),
    Primary Key (IFK)
);

create table Erabiltzailea (
    NAN         varchar(9),
    Izena       varchar(20),
    Abizena     varchar(20),
    JaiotzeData date,
    Genero      varchar(20),
    Admin       bit NOT NULL,
    Pasahitza varchar(50) NOT NULL,
    Primary Key (NAN)
);

create table Liburua (
    ISBN                int(13),
    Izena               varchar(200),
    ArgitaratzeEguna    date,
    Hizkuntza           varchar(20),
    Mailegatu           bit,
    Erreserbatua        bit,
    ErabiltzaileaNAN    varchar(9),
    IdazleZenbakia      int(10),
    ArgitaletxeIFK      varchar(9),
    Primary Key(ISBN),
    Foreign Key(ErabiltzaileaNAN) references Erabiltzailea(NAN),
    Foreign Key(IdazleZenbakia) references Idazlea(IdazleZenbakia),
    Foreign Key(ArgitaletxeIFK) references Argitaletxe(IFK)
);

create table Kolekzioa (
    ErabiltzaileaNAN    varchar(9),
    Izena               varchar(30),
    Primary Key (Izena, ErabiltzaileaNAN),
    Foreign Key(ErabiltzaileaNAN) references Erabiltzailea(NAN)
);

create table Kolekzio_Liburua (
    LiburuISBN                  int(13),
    KolekzioaErabiltzaileaNAN   varchar(9),
    KolekzioaIzena              varchar(30),
    Primary Key (LiburuISBN, KolekzioaErabiltzaileaNAN, KolekzioaIzena),
    Foreign Key(LiburuISBN) references Liburua(ISBN),
    Foreign Key(KolekzioaErabiltzaileaNAN) references Erabiltzailea(NAN),
    Foreign Key(KolekzioaIzena) references Kolekzioa(Izena)
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