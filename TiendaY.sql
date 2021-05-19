create database TiendaY;

use TiendaY;

create table Proveedor(
	NoProveedor int not null primary key,
	NombreProveedor varchar(30) not null,
	Calle varchar(30),
	Colonia varchar(30),
	Numero int,
	Estado varchar(30),
	Municipio varchar(30),
	CP int,
	Telefono varchar(15),
	Email varchar(40)
);

create table Producto(
	Id int not null primary key,
	NombreProducto varchar(20),
	Marca varchar(20),
	ContNet float,
	Unidad varchar(4),
	Piezas int,
	Departamento varchar(30),
	PrecioPúblico float,
	Existencias int
);

create table ProvProducto(
Proveedor int not null foreign key references Proveedor(NoProveedor),
	Producto int not null foreign key references Producto(Id),
	Costo float
);
