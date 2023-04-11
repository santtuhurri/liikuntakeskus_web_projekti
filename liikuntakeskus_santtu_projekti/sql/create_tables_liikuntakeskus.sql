CREATE TABLE ryhmaliikunta(
id integer not null,
nimi varchar(30) not null,
kuvaus varchar(200) not null,
tyyppi varchar(40) not null,
kesto integer not null,
hinta decimal(5,2) not null,
ohjaaja varchar(50) not null,
paikka varchar(30) not null,
PRIMARY KEY(id)
);
