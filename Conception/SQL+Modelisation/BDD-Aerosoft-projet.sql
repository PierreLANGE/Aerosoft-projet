DROP DATABASE IF EXISTS aerosoft;

CREATE DATABASE aerosoft;

USE aerosoft;

CREATE TABLE ROLES (
    IdRole       	varchar(20) primary key,
    RoleNom 		varchar(50) not null
) ENGINE InnoDB;

CREATE TABLE UTILISATEUR (
    IdUtilisateur 	varchar(20) primary key,
	Mail 		    varchar(50) not null,
    MotDePasse 		varchar(50) not null,
    Statut          boolean,
	IdRole 	        varchar(20), foreign key(IdRole) references ROLES(IdRole) ON DELETE SET null
) ENGINE InnoDB;

CREATE TABLE CONSTRUCTEUR (
	IdConstructeur 	integer primary key auto_increment,
	NomConstructeur varchar(50) not null
) ENGINE InnoDB;

CREATE TABLE AEROPORT (
	IdAeroport			varchar(3) primary key,
	NomAeroport			varchar(50) not null,
	NomVilleDesservie	varchar(50) not null
) ENGINE InnoDB;

CREATE TABLE PILOTE (
	IdPilote		integer primary key auto_increment,
	NomPilote		varchar(50) not null,
	PrenomPilote 	varchar(50) not null,
    Matricule       varchar(50) not null
) ENGINE InnoDB;

CREATE TABLE DETAILAVION (
	TypeAvion 		varchar(50) primary key,
	Capacite 		integer not null,
	IdConstructeur 	integer not null, foreign key(IdConstructeur) references CONSTRUCTEUR(IdConstructeur) ON DELETE CASCADE
) ENGINE InnoDB;

CREATE TABLE AVION (
	NumAvion 		integer primary key auto_increment,
	TypeAvion 		varchar(50) not null, foreign key(TypeAvion) references DETAILAVION(TypeAvion) ON DELETE CASCADE,
	BaseAeroport 	varchar(3), foreign key(BaseAeroport) references AEROPORT(IdAeroport) ON DELETE SET NULL
) ENGINE InnoDB;

CREATE TABLE VOL (
	NumVol			varchar(50) primary key,
	AeroportDept 	varchar(3) not null, foreign key(AeroportDept) references AEROPORT(IdAeroport) ON DELETE CASCADE,
	HDepart			time not null,
	AeroportArr 	varchar(3) not null, foreign key(AeroportArr) references AEROPORT(IdAeroport) ON DELETE CASCADE,
	HArrivee		time not null
) ENGINE InnoDB;

CREATE TABLE AFFECTATION (
	IdAffectation	varchar(30) primary key,
	NumVol			varchar(50) not null, foreign key(NumVol) references VOL(NumVol) ON DELETE CASCADE,
	DateVol 		date,
    AffectationCode boolean,
	NumAvion		integer not null, foreign key(NumAvion) references AVION(NumAvion) ON DELETE CASCADE,
	IdPilote 		integer not null, foreign key(IdPilote) references PILOTE(IdPilote) ON DELETE CASCADE
) ENGINE InnoDB;

-- création des tuples

INSERT INTO ROLES VALUES 
('01011','Chargé Clientèle'),
('44444','Technicien d''exploitation'),
('55555','Administrateur'),
('11111','Pilote')
;

INSERT INTO UTILISATEUR VALUES 
(14562148,'clientele@aerosoft.com', 'pass123', TRUE, '01011'),
(27895410,'technique@aerosoft.com', 'pass123', TRUE, '44444'),
(37845651,'admin@aerosoft.com', 'pass123', TRUE, '55555'),
(47894512,'pilote@aerosoft.com', 'pass123', TRUE, '11111')
;

INSERT INTO CONSTRUCTEUR VALUES 
(1,'Aerospatiale'),
(2,'Boeing'),
(3,'Cessna'),
(4,'Douglas')
;

INSERT INTO AEROPORT VALUES 
('BAS','Poretta','Bastia'),
('BLA','Blagnac','Toulouse'),
('BRI','Brive','Brive'),
('CDG','Roissy','Paris'),
('GRE','Saint Geoir','Grenoble'),
('LYS','Saint Exupery','Lyon'),
('NAN','Saint Herblain','Nantes'),
('NIC','Nice Cote D Azur','Nice'),
('ORL','Orly','Paris')
;

INSERT INTO PILOTE VALUES 
(1,'BARBARO','EVA', '154MD'),
(2,'ZELLAMA','FAYROUZ', '148OP'),
(3,'DACRUZ','SANDRA', '789UY'),
(4,'KHALFI','HEDIA', '421OP'),
(5,'MONDON','NATHALIE', '419TY'),
(7,'DAVID','XAVIER', '412OP'),
(8,'JARRY','KEVIN', '789RE'),
(9,'CONFRERE','ROMAIN', '148YT')
;

INSERT INTO DETAILAVION VALUES 
('A320',300,1),
('A340',350,1),
('ATR42',50,1),
('B707',250,2),
('B727',300,2),
('B747',400,2),
('DC10',200,4)
;

INSERT INTO AVION VALUES 
(100,'A320','NIC'),
(101,'B707','CDG'),
(102,'A320','BLA'),
(103,'DC10','BLA'),
(104,'B747','ORL'),
(105,'A320','GRE'),
(106,'ATR42','CDG'),
(107,'B727','LYS'),
(108,'B727','NAN'),
(109,'A340','BAS')
;

INSERT INTO VOL VALUES 
('IT100','NIC','07:00:00','CDG','09:00:00'),
('IT101','ORL','11:00:00','BLA','12:00:00'),
('IT102','CDG','12:00:00','NIC','14:00:00'),
('IT103','GRE','09:00:00','BLA','11:00:00'),
('IT104','BLA','17:00:00','GRE','19:00:00'),
('IT105','LYS','06:00:00','ORL','07:00:00'),
('IT106','BAS','10:00:00','ORL','13:00:00'),
('IT107','NIC','07:00:00','BRI','08:00:00'),
('IT108','BRI','19:00:00','ORL','20:00:00'),
('IT109','NIC','18:00:00','ORL','19:00:00'),
('IT110','ORL','15:00:00','NIC','16:00:00'),
('IT111','NIC','17:00:00','NAN','19:00:00')
;

INSERT INTO AFFECTATION VALUES 
('A','IT100','2001-04-06', TRUE, 100,1),
('B','IT100','2001-04-07', TRUE, 101,2),
('C','IT101','2001-04-06',TRUE, 100,2),
('D','IT101','2001-04-07', FALSE, 103,4),
('E','IT102','2001-04-06', FALSE, 101,1),
('F','IT102','2001-04-07', TRUE, 102,3),
('G','IT103','2001-04-06', TRUE, 105,3),
('H','IT103','2001-04-07', TRUE, 104,2),
('I','IT104','2001-04-06', TRUE, 105,3),
('J','IT104','2001-04-07', TRUE, 107,8),
('K','IT105','2001-04-06', TRUE, 107,7),
('L','IT105','2001-04-07', TRUE, 106,7),
('M','IT106','2001-04-06', TRUE, 109,8),
('N','IT106','2001-04-07', TRUE, 104,5),
('O','IT107','2001-04-06', TRUE, 106,9),
('P','IT107','2001-04-07', TRUE, 103,8),
('Q','IT108','2001-04-06', TRUE, 106,9),
('R','IT108','2001-04-07', FALSE, 106,5),
('S','IT109','2001-04-06', FALSE, 107,7),
('T','IT109','2001-04-07', FALSE, 105,1),
('U','IT110','2001-04-06', FALSE, 102,2),
('V','IT110','2001-04-07', FALSE, 104,3),
('W','IT111','2001-04-06', FALSE, 101,4),
('X','IT111','2001-04-07', FALSE, 100,8)
;

UPDATE AFFECTATION SET IdAffectation=CONCAT(NumVol, DateVol)