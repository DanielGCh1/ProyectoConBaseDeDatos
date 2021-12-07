
CREATE DATABASE Facturacion;

use Facturacion;

create table Cliente (
       cedula  varchar(10)  not null,
       nombre varchar(30) not null,  
       telefono  varchar(10)  not null,
       Primary Key (cedula)         
     );


create table Factura (
    numero varchar(10) not null,
    descripcion varchar(30) not null,
    monto int,
    cliente varchar(10),
    Primary Key (numero)
);

ALTER TABLE Factura ADD Foreign Key (cliente) REFERENCES Cliente(cedula);
