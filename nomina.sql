DROP DATABASE IF EXISTS nominas;
CREATE DATABASE nominas DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE nominas;

CREATE TABLE empresa (
    idEmp INT PRIMARY KEY,
    cif VARCHAR(9) NOT NULL UNIQUE,
    domicilio VARCHAR(30) NOT NULL,
    ccc VARCHAR(15) NOT NULL UNIQUE,
    nomEmp VARCHAR(20) NOT NULL
);
INSERT INTO empresa VALUES('1', 'A58695412', 'calle illueca, 28,03206 elche','011103140593687','severo ochoa s.a.');
INSERT INTO empresa VALUES('2', 'A55667788', 'calle cruz roja, 8,03202 elche','011103140594444','ocielx');

CREATE TABLE trabajador (
    idTrab INT,
    idEmp INT,
    nomTrab VARCHAR(20) NOT NULL,
    ape1Trab VARCHAR(20) NOT NULL,
    ape2Trab VARCHAR(20) NOT NULL,
    dni VARCHAR(10) NOT NULL UNIQUE,
    naf VARCHAR(12) NOT NULL UNIQUE,
    fechaAntiguedad DATE NOT NULL,
    grupoProfesional INT(2) NOT NULL,
    grupoCotizacion INT(2) NOT NULL,
    nivelCotizacion INT(1) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    convenio VARCHAR(50) NOT NULL,
    CONSTRAINT fk_empresa_trabajador FOREIGN KEY (idEmp) REFERENCES empresa(idEmp) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idTrab, idEmp)
);
INSERT INTO trabajador VALUES('1','1', 'Ana', 'García', 'Pérez', '21548942T', '031258974586', '2021/11/01','3','3','2','programador','Oficinas y despachos');
INSERT INTO trabajador VALUES('2','1', 'Pepe', 'Paredes', 'Pérez', '21785442N', '078459745867', '2022/09/01','4','4','2','analista','Oficinas y despachos');
INSERT INTO trabajador VALUES('3','1', 'Óscar', 'Santos', 'Sabater', '18758942O','687592974586', '1994/01/19','1','1','1','titulado superior','Oficinas y despachos');
INSERT INTO trabajador VALUES('4','2', 'Daniela', 'Fernández', 'Martínez', '21356842P', '031259785646', '2000/01/01','5','5','3','capataz','Oficinas y despachos');
INSERT INTO trabajador VALUES('5','2', 'Roberto', 'López', 'Morales', '27841542S', '036748174586', '1980/08/14','6','6','1','dibujante','Oficinas y despachos');

CREATE TABLE nomina (
    idNomina INT,
    idTrab INT,
    totalDeducir DECIMAL(6,2) NOT NULL,
    totalLiquido DECIMAL(6,2) NOT NULL,
    totalDevengado DECIMAL(6,2) NOT NULL,
    totalAporEmp DECIMAL(6,2) NOT NULL,
    fechaInicio DATE NOT NULL, 
    fechaFin DATE NOT NULL,
    CONSTRAINT fk_trabajador_nomina FOREIGN KEY (idTrab) REFERENCES trabajador(idTrab) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idNomina, idTrab)
);


