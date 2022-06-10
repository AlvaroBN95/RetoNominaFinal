DROP DATABASE IF EXISTS nominas;
CREATE DATABASE nominas DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE nominas;

CREATE TABLE empresa (
    idemp INT PRIMARY KEY,
    cif VARCHAR(9) NOT NULL UNIQUE,
    domicilio VARCHAR(30) NOT NULL,
    ccc VARCHAR(15) NOT NULL UNIQUE,
    nomemp VARCHAR(20) NOT NULL
);
INSERT INTO empresa VALUES('1', 'A58695412', 'calle illueca, 28,03206 elche','011103140593687','severo ochoa s.a.');
INSERT INTO empresa VALUES('2', 'A55667788', 'calle cruz roja, 8,03202 elche','011103140594444','ocielx');

CREATE TABLE trabajador (
    idtrab INT,
    idemp INT,
    nomtrab VARCHAR(20) NOT NULL,
    ape1trab VARCHAR(20) NOT NULL,
    ape2trab VARCHAR(20) NOT NULL,
    dni VARCHAR(10) NOT NULL UNIQUE,
    naf VARCHAR(12) NOT NULL UNIQUE,
    fechaantiguedad DATE NOT NULL,
    grupoprofesional INT(2) NOT NULL,
    grupocotizacion INT(2) NOT NULL,
    nivelcotizacion INT(1) NOT NULL,
    letra VARCHAR (1),
    categoria VARCHAR(100) NOT NULL,
    convenio VARCHAR(50) NOT NULL,
    tipocontrato ENUM('indefinido', 'temporal') NOT NULL,

    CONSTRAINT fk_empresa_trabajador FOREIGN KEY (idEmp) REFERENCES empresa(idEmp) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idTrab, idEmp)
);
INSERT INTO trabajador VALUES('1','1', 'Ana', 'García', 'Pérez', '21548942T', '031258974586', '2021/11/01','3','3','2','A','programador','Oficinas y despachos', 'temporal');
INSERT INTO trabajador VALUES('2','1', 'Pepe', 'Paredes', 'Pérez', '21785442N', '078459745867', '2022/09/01','4','4','2','B','analista','Oficinas y despachos', 'indefinido');
INSERT INTO trabajador VALUES('3','1', 'Óscar', 'Santos', 'Sabater', '18758942O','687592974586', '1994/01/19','1','1','1','','titulado superior','Oficinas y despachos', 'temporal');
INSERT INTO trabajador VALUES('4','2', 'Daniela', 'Fernández', 'Martínez', '21356842P', '031259785646', '2000/01/01','5','5','3','','capataz','Oficinas y despachos', 'indefinido');
INSERT INTO trabajador VALUES('5','2', 'Roberto', 'López', 'Morales', '27841542S', '036748174586', '1980/08/14','6','6','1','','dibujante','Oficinas y despachos', 'temporal');


CREATE TABLE nomina (
    idnomina INT AUTO_INCREMENT,
    idtrab INT,
    domicilio VARCHAR(30) NOT NULL,
    grupoprofesional INT(2) NOT NULL,
    grupocotizacion INT(2) NOT NULL,
    nivelcotizacion INT(1) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    convenio VARCHAR(50) NOT NULL,
    salariobase DECIMAL(6,2) NOT NULL,
    plustransporte DECIMAL(5,2) NOT NULL,
    capacitacionprofesional DECIMAL(5,2) NOT NULL,
    complementos DECIMAL(5,2) NOT NULL,
    pagasextra DECIMAL(6,2) NOT NULL,
    totaldevengado DECIMAL(6,2) NOT NULL,
    diastrabajados INT (2) NOT NULL, 
    cctrab DECIMAL(5,2)NOT NULL,
    destrab DECIMAL(5,2)NOT NULL,
    fptrab DECIMAL(5,2)NOT NULL,
    horasextrafm DECIMAL(5,2)NOT NULL,
    horasextra DECIMAL(5,2)NOT NULL,
    irpf DECIMAL(5,2)NOT NULL,
    totaldeducir DECIMAL(6,2) NOT NULL,
    totalliquido DECIMAL(6,2) NOT NULL,
    atep DECIMAL(4,2) NOT NULL,
    desemp DECIMAL(4,2) NOT NULL,
    fpemp DECIMAL(4,2) NOT NULL,
    fogasa DECIMAL(4,2) NOT NULL,
    totalaporemp DECIMAL(6,2) NOT NULL,
    fechainicio DATE NOT NULL, 
    fechafin DATE NOT NULL,
    hetrab DECIMAL(5,2) NOT NULL, 
    hetrabfm DECIMAL(5,2) NOT NULL, 
    heemp DECIMAL(5,2) NOT NULL,
    heempfm DECIMAL(5,2) NOT NULL,
    ccemp DECIMAL(5,2)NOT NULL,
    dieta DECIMAL(5,2)NOT NULL,
    CONSTRAINT fk_trabajador_nomina FOREIGN KEY (idTrab) REFERENCES trabajador(idTrab) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idNomina, idTrab)
);