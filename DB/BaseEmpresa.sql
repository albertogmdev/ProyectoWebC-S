CREATE DATABASE IF NOT EXISTS M2;


#drop table ____ para borrar tablas
#describe ____ muestra la estructura de una tabla
#Seleccionar base de datos
USE M2;

drop table EmpleadoRRHH;
drop table Proyecto_Empleado;
drop table Proyecto;
drop table Calendario;
drop table DiaLibre;
drop table EmpleadoEmpresa;
drop table Empresa;

#Creamos las tablas sin relaciones
CREATE TABLE IF NOT EXISTS EmpleadoRRHH(
	IdEmpleadoRRHH INT NOT NULL,
    Nombre VARCHAR(45) NOT NULL,
    Apellidos VARCHAR(45) NOT NULL,
    Telefono INT NOT NULL,
    Correo VARCHAR(45),
    Contrasenia VARCHAR(45) CHARACTER SET BINARY,
    PRIMARY KEY(Correo)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS EmpleadoEmpresa(
	IdEmpleadoEmpresa INT NOT NULL,
    Nombre VARCHAR(45) NOT NULL,
    Apellidos VARCHAR(45) NOT NULL,
    Telefono INT NOT NULL,
    Correo VARCHAR(45),
    Contrasenia VARCHAR(45) CHARACTER SET BINARY,
    PRIMARY KEY(Correo)    
)ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS Empresa(
	IdEmpresa INT NOT NULL,
    Nombre VARCHAR(45) NOT NULL,
    Calle VARCHAR(45) NOT NULL,
    CodigoPostal INT NOT NULL,
    Correo VARCHAR(45),
    Telefono INT NOT NULL,
    PRIMARY KEY(IdEmpresa)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Proyecto(
	IdProyecto INT NOT NULL,
    PRIMARY KEY(IdProyecto),
    Empresa_IdEmpresa INT NOT NULL,
    CONSTRAINT FKProyecto_empresa
    FOREIGN KEY(Empresa_IdEmpresa)
    REFERENCES Empresa(IdEmpresa)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Calendario(
	Fecha DATE NOT NULL,
    HoraEntrada TIME NOT NULL,
    HoraSalida TIME NOT NULL,
    PRIMARY KEY(Fecha),
    Correo VARCHAR(45) NOT NULL, #creo una clave foranea para relacion 1 a 1 con EmpleadoEmpresa pero al ser unica no se puede repetir
    id_proyecto INT NOT NULL,
    FOREIGN KEY (Correo)
    REFERENCES EmpleadoEmpresa(Correo)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Proyecto_Empleado(
	Horas INT,
	proyecto_id_proyecto INT NOT NULL,
	empleado_correo VARCHAR(45) NOT NULL,
    PRIMARY KEY (proyecto_id_proyecto,empleado_correo),
    CONSTRAINT fkempleado_proyecto_empleado
    FOREIGN KEY(empleado_correo)
    REFERENCES EmpleadoEmpresa(Correo),
    CONSTRAINT fkproyecto_empleado_proyecto
    FOREIGN KEY(proyecto_id_proyecto)
    REFERENCES Proyecto(IdProyecto)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS DiaLibre(
	FechaInicio DATE,
    FechaFin DATE,
    Motivo VARCHAR(120),
    Leido BOOL,
    Aprobado BOOL,
    Tramitado BOOL,
  --  PRIMARY KEY(FechaInicio),--
    EmpleadoEmpresa_Correo VARCHAR(45),
    CONSTRAINT FKDiaLibre_EmpleadoEmpresa
    FOREIGN KEY(EmpleadoEmpresa_Correo)
    REFERENCES EmpleadoEmpresa(Correo)
)ENGINE=INNODB;

INSERT INTO EmpleadoEmpresa VALUES ('0000','Pruebas', 'Pruebas Pruebas','000000000','pruebaspruebas@correo.com','Pruebas');

INSERT INTO EmpleadoEmpresa VALUES ('0001','Francisco', 'Cabrera Sanchez','689447451','franciscocabrera@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0002','Javier', 'Casas Vidal','784516211','javiercasas@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0003','Marcos', 'Reyes Suarez','483954961','marcosreyes@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0004','Unai', 'Soler Moreno','586614262','unaisoler@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0005','Ruben', 'Ramirez Garcia','532687752','rubenramirez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0006','Guillermo', 'Parra Iglesias','873773953','guillermoparra@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0007','Rubén', 'Lopez Vicente','563159863','rubenlopez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0008','Fernando', 'Cortes Peña','819566664','fernandocortes@correo.com','1234');

INSERT INTO EmpleadoEmpresa VALUES ('0009','Arnau', 'Pujol Blas','467353694','arnaupujol@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0010','Santiago', 'Pena Pastor','391454725','santiagopena@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0011','Raquel', 'Garcia Panadero','242319235','raquelgarcia@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0012','Lara', 'Gonzalez Gonzalez','228475716','laragonzalez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0013','Alicia', 'Nunez Marin','519693636','alicianunez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0014','Helena', 'Campos Fuentes','445388867','helenacampos@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0015','Berta', 'Jimenez Romero','268494237','bertajimenez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0016','Alba', 'Gil Calvo','938143228','albagil@correo.com','1234');

INSERT INTO EmpleadoEmpresa VALUES ('0017','Mara', 'Sola Blanco','685258148','marasola@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0018','Miriam', 'Crespo Gomez','135631649','miriamcrespo@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0019','Candela', 'Lozano Soto','443798749','candelalozano@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0020','Emma', 'Serra Gallego','368185799','emmaserra@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0021','Nicolas', 'Gilabert de la Iglesia','533390863','nicolasgilabert@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0022','Aurora', 'Guardia Arango','501498843','auroraguardia@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0023','Rafael', 'Gabarri Seijas','226127742','rafaelgabarri@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0024','Maria Carmen', 'Cantanero Herranz','691579014','maricarmencantanero@correo.com','1234');

INSERT INTO EmpleadoEmpresa VALUES ('0025','Guillermo', 'Plata Aceituno','223656754','guillermoplata@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0026','Natalia', 'Villalonga Milla','171770709','nataliavillalonga@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0027','Ricardo', 'Pinto Echevarria','731483533','ricardopinto@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0028','Yolanda', 'Conde Gea','168405118','yolandaconde@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0029','Salvador', 'Rangel Melendez','608614620','salvadorangel@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0030','Sonia', 'Portela Vaca','519684120','soniaportela@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0031','Ismael', 'Calvino Zabala','765453569','ismaelcalvino@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0032','Luisa', 'Candela Cavero','949818409','luisacandela@correo.com','1234');

INSERT INTO EmpleadoEmpresa VALUES ('0033','Ricardo', 'Macho Mas','176927422','ricardomucho@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0034','Maria', 'Solana Chaparro','601006598','mariasolana@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0035','Jose Manuel', 'Simo Feliu','765794376','josemanuelsimo@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0036','Angela', 'Goncavales Alcaniz','535475527','angelagoncavales@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0037','Hector', 'Curiel Tomas','435928947','hectorcuriel@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0038','Catalina', 'Melendez Palma','317959593','catalinamelendez@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0039','Fernando', 'Marquina Sacristan','339132454','fernandomarquina@correo.com','1234');
INSERT INTO EmpleadoEmpresa VALUES ('0040','Maria Isabel', 'Justicia Pomares','477183353','mariaisabeljusticia@correo.com','1234');


INSERT INTO Empresa VALUES ('1023', 'Empresa1', 'YazminVille','926', 'empresa1@correo.com','916783220');
INSERT INTO Empresa VALUES ('2376', 'Empresa2', 'LambertJunction','0210', 'empresa2@correo.com','916573579');
INSERT INTO Empresa VALUES ('8742', 'Empresa3', 'DanielMountain','39088', 'empresa3@correo.com','916427559');
INSERT INTO Empresa VALUES ('9658', 'Empresa4', 'GradyWell','53037','empresa4@correo.com','919744369');
INSERT INTO Empresa VALUES ('3458', 'Empresa5', 'StokesJunctions','04670','empresa5@correo.com','919555369');


INSERT INTO proyecto  VALUES ('123456789','1023');
INSERT INTO proyecto  VALUES ('15695940','1023');
INSERT INTO proyecto  VALUES ('846825486','1023');
INSERT INTO proyecto  VALUES ('756497265','1023');
INSERT INTO proyecto  VALUES ('121246784','1023');
INSERT INTO proyecto  VALUES ('091251940','2376');
INSERT INTO proyecto  VALUES ('481483855','2376');
INSERT INTO proyecto  VALUES ('631212546','2376');
INSERT INTO proyecto  VALUES ('034812058','2376');
INSERT INTO proyecto  VALUES ('324699976','2376');
INSERT INTO proyecto  VALUES ('834612348','8742');
INSERT INTO proyecto  VALUES ('134812058','8742');
INSERT INTO proyecto  VALUES ('237812058','8742');
INSERT INTO proyecto  VALUES ('038554375','8742');
INSERT INTO proyecto  VALUES ('237662058','8742');
INSERT INTO proyecto  VALUES ('334467892','9658');
INSERT INTO proyecto  VALUES ('196578902','9658');
INSERT INTO proyecto  VALUES ('980980234','9658');
INSERT INTO proyecto  VALUES ('136000345','9658');
INSERT INTO proyecto  VALUES ('784567822','9658');
INSERT INTO proyecto  VALUES ('442386701','3458');
INSERT INTO proyecto  VALUES ('983211255','3458');
INSERT INTO proyecto  VALUES ('979744367','3458');
INSERT INTO proyecto  VALUES ('123443214','3458');
INSERT INTO proyecto  VALUES ('098765456','3458');


INSERT INTO EmpleadoRRHH VALUES ('0050','Admin', 'Admin Admin','938189928','AdminAdmin@correo.com','RRHH');
INSERT INTO EmpleadoRRHH VALUES ('0051','Mara', 'Lopez Blanco','682654848','maralopez@correo.com','5678');
INSERT INTO EmpleadoRRHH VALUES ('0052','Miriam', 'Iglesias Lozano','166631649','miriamiglesias@correo.com','5678');
INSERT INTO EmpleadoRRHH VALUES ('0053','Carmen', 'Lozano Soto','443799949','carmenlozano@correo.com','5678');
INSERT INTO EmpleadoRRHH VALUES ('0054','Emiliana', 'Llanos Gallego','345685799','emilianallanos@correo.com','5678');

#Proyectos de la empresa 1 (id empresa 1023)
INSERT INTO Proyecto_Empleado  VALUES (null,'123456789','franciscocabrera@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'123456789','javiercasas@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'123456789','marcosreyes@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'15695940','franciscocabrera@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'15695940','javiercasas@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'15695940','unaisoler@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'846825486','franciscocabrera@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'846825486','rubenlopez@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'756497265','rubenramirez@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'121246784','rubenlopez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'121246784','unaisoler@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'121246784','guillermoparra@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'121246784','fernandocortes@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'121246784','marcosreyes@correo.com');


#Proyectos de la empresa 2 (id empresa 2376)
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','santiagopena@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','arnaupujol@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','raquelgarcia@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','alicianunez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','helenacampos@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'091251940','laragonzalez@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'481483855','raquelgarcia@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'481483855','laragonzalez@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'631212546','raquelgarcia@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'631212546','bertajimenez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'631212546','alicianunez@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'034812058','raquelgarcia@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'034812058','alicianunez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'034812058','laragonzalez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'034812058','helenacampos@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'034812058','albagil@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'324699976','raquelgarcia@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'324699976','laragonzalez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'324699976','alicianunez@correo.com');


#Proyectos de la empresa 3 (id empresa 8742)
INSERT INTO Proyecto_Empleado  VALUES (null,'834612348','maricarmencantanero@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'834612348','miriamcrespo@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'134812058','marasola@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'134812058','candelalozano@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'237812058','marasola@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'237812058','miriamcrespo@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'237812058','emmaserra@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'237812058','nicolasgilabert@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'038554375','miriamcrespo@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'038554375','nicolasgilabert@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'038554375','rafaelgabarri@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'237662058','nicolasgilabert@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'237662058','auroraguardia@correo.com');


#Proyectos de la empresa 4 (id empresa 9658)
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','guillermoplata@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','nataliavillalonga@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','ricardopinto@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','yolandaconde@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','salvadorangel@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','soniaportela@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'334467892','ismaelcalvino@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'196578902','guillermoplata@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'196578902','ricardopinto@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'980980234','yolandaconde@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'136000345','guillermoplata@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'136000345','soniaportela@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'136000345','ismaelcalvino@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'136000345','salvadorangel@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'136000345','luisacandela@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'784567822','salvadorangel@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'784567822','soniaportela@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'784567822','ismaelcalvino@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'784567822','luisacandela@correo.com');


#Proyectos de la empresa 5 (id empresa 3458)
INSERT INTO Proyecto_Empleado  VALUES (null,'442386701','josemanuelsimo@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'983211255','mariasolana@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'983211255','ricardomucho@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'983211255','angelagoncavales@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'979744367','josemanuelsimo@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'979744367','mariasolana@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'979744367','ricardomucho@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'979744367','mariaisabeljusticia@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'123443214','angelagoncavales@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'123443214','mariasolana@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'123443214','hectorcuriel@correo.com');

INSERT INTO Proyecto_Empleado  VALUES (null,'098765456','fernandomarquina@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'098765456','ricardomucho@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'098765456','hectorcuriel@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'098765456','catalinamelendez@correo.com');
INSERT INTO Proyecto_Empleado  VALUES (null,'098765456','mariaisabeljusticia@correo.com');
