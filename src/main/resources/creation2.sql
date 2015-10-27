DROP TABLE IF EXISTS USERS CASCADE ;
DROP TABLE IF EXISTS VISITOR;
DROP TABLE IF EXISTS TEACHER CASCADE ;
DROP TABLE IF EXISTS STUDENT CASCADE;
DROP TABLE IF EXISTS MAINTENANCE_TECHNICIAN;
DROP TABLE IF EXISTS RESPONSABLE;
DROP TABLE IF EXISTS NETWORK_ENGINEER;
DROP TABLE IF EXISTS STUDENT_TEACHER;
DROP TABLE IF EXISTS STUDENT_TUTELAGE;
DROP TABLE IF EXISTS SPECIFIC_TEACHER;
DROP TABLE IF EXISTS DELEGATED_STUDENT;
DROP TABLE IF EXISTS COURSE CASCADE ;
DROP TABLE IF EXISTS ROOM;
DROP TABLE IF EXISTS BATIMENT;
DROP TABLE IF EXISTS PROMOTION CASCADE ;
DROP TABLE IF EXISTS GROUPE CASCADE ;
DROP TABLE IF EXISTS COURSEPROMOTION;
DROP TABLE IF EXISTS COURSEGROUP;
DROP VIEW IF EXISTS VIEWCOURSEPROMOTION;
DROP VIEW IF EXISTS VIEWCOURSEGROUP;

DROP DOMAIN IF EXISTS typePromotion;
DROP DOMAIN IF EXISTS Domaine;
DROP DOMAIN IF EXISTS Sexe;
DROP DOMAIN IF EXISTS Institut;
DROP DOMAIN IF EXISTS Statut;
DROP DOMAIN IF EXISTS Roles;
DROP DOMAIN IF EXISTS typeOfRoom;
DROP DOMAIN IF EXISTS typeCourse;

CREATE DOMAIN typeCourse AS VARCHAR
CHECK (
  VALUE IN ('TP', 'CM', 'TD')
);

CREATE DOMAIN typeOfRoom AS VARCHAR
CHECK (
  VALUE IN ('TP', 'Amphi', 'Gymnase', 'Stade', 'Chimie', 'TD')
);

CREATE DOMAIN typePromotion AS VARCHAR
CHECK (
  VALUE IN ('L1', 'L2', 'L3', 'M1', 'M2', 'D1', 'D2')
);

CREATE DOMAIN Domaine AS VARCHAR
CHECK (
  VALUE IN ('SI', 'BIO','PC','MASS','LEA','ECO','SUAPS')
);


CREATE DOMAIN Statut AS VARCHAR
CHECK(
  VALUE IN ('HOLDER', 'EXTERNAL_TEACHER','RESEARCHER','PARTICIPANT')
);


CREATE DOMAIN Sexe AS VARCHAR
CHECK(
  VALUE IN ('MAN','WOMAN')
);


CREATE DOMAIN Roles AS VARCHAR
CHECK(
  VALUE IN ('STUDENT', 'TEACHER','MAINTENANCE_TECHNICIAN','RESPONSABLE','NETWORK_ENGINEER','STUDENT_TEACHER','SPECIFIC_TEACHER','DELEGATED_STUDENT','VISITOR','STUDENT_TUTELAGE')
);


CREATE DOMAIN Institut AS VARCHAR
CHECK(
  VALUE IN ('FAC','IUT')
);

CREATE TABLE PROMOTION(
  idPromotion SERIAL,
  typePromotion typePromotion,
  domainePromotion Domaine,
  anneePromotion INTEGER,
  nombreInscrit INTEGER,
  PRIMARY KEY (idPromotion)
);

CREATE TABLE GROUPE(
  idGroup SERIAL,
  idPromotion INTEGER REFERENCES PROMOTION(idPromotion),
  numGroup INTEGER,
  nombreParticipant INTEGER,
  PRIMARY KEY (idGroup)
);

CREATE TABLE USERS(
  nom VARCHAR(100),
  prenom VARCHAR(100),
  role Roles,
  login VARCHAR(100),
  password VARCHAR(100),
  PRIMARY KEY(login)
);


CREATE TABLE TEACHER(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  statut Statut NOT NULL,
  subject VARCHAR(100),
  PRIMARY KEY(login)
);

CREATE TABLE STUDENT(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  domaine Domaine NOT NULL,
  promotion INTEGER REFERENCES PROMOTION(idPromotion),
  institut Institut NOT NULL,
  PRIMARY KEY(login)
);


CREATE TABLE MAINTENANCE_TECHNICIAN(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  PRIMARY KEY(login)
);

CREATE TABLE RESPONSABLE(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  PRIMARY KEY(login)
);

CREATE TABLE NETWORK_ENGINEER(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  PRIMARY KEY(login)
);

CREATE TABLE STUDENT_TEACHER(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  suject VARCHAR(100) NOT NULL,
  PRIMARY KEY(login)
);

CREATE TABLE STUDENT_TUTELAGE(
  login VARCHAR(100) REFERENCES USERS(login) UNIQUE,
  tutelage VARCHAR(100),
  PRIMARY KEY(login)
);

CREATE TABLE SPECIFIC_TEACHER(
  login VARCHAR(100) REFERENCES TEACHER(login) UNIQUE,
  subject VARCHAR(100),
  PRIMARY KEY(login)
);

CREATE TABLE DELEGATED_STUDENT(
  login VARCHAR(100) REFERENCES STUDENT(login) UNIQUE,
  PRIMARY KEY(login)
);

CREATE TABLE BATIMENT(
  batiment VARCHAR(100),
  domaineBatiment Domaine,
  PRIMARY KEY (batiment)
);

CREATE TABLE ROOM(
  idRoom SERIAL,
  batiment VARCHAR(100) REFERENCES BATIMENT(batiment),
  numRoom INTEGER,
  typeRoom typeOfRoom,
  capacity INTEGER,
  PRIMARY KEY (idRoom)
);

CREATE TABLE COURSE(
  idCourse SERIAL,
  typeCourse typeCourse,
  hour INTEGER,
  day INTEGER,
  idweek INTEGER,
  month INTEGER,
  subject VARCHAR(100),
  duree INTEGER,
  teacher VARCHAR REFERENCES TEACHER(login),
  idRoom INTEGER REFERENCES ROOM(idRoom),
  PRIMARY KEY (idCourse)
);

CREATE TABLE CoursePromotion(
  idCoursePromotion INTEGER REFERENCES COURSE(idCourse) ,
  idPromotion INTEGER REFERENCES PROMOTION(idPromotion) ,
  PRIMARY KEY(idCoursePromotion, idPromotion)
);

CREATE TABLE CourseGroup(
  idCourseGroup INTEGER REFERENCES COURSE(idCourse),
  idGroup INTEGER REFERENCES GROUPE(idGroup),
  PRIMARY KEY(idCourseGroup, idGroup)
);

INSERT INTO PROMOTION(typePromotion, domainePromotion, anneePromotion, nombreInscrit) VALUES ('M1', 'SI', 2015, 200);
INSERT INTO PROMOTION(typePromotion, domainePromotion, anneePromotion, nombreInscrit) VALUES ('L1', 'BIO', 2015, 200);
INSERT INTO PROMOTION(typePromotion, domainePromotion, anneePromotion, nombreInscrit) VALUES ('L1', 'SI', 2015, 200);
INSERT INTO PROMOTION(typePromotion, domainePromotion, anneePromotion, nombreInscrit) VALUES ('L2', 'SI', 2015, 200);

INSERT INTO GROUPE(idPromotion, numGroup, nombreParticipant) VALUES (1, 1, 30);
INSERT INTO GROUPE(idPromotion, numGroup, nombreParticipant) VALUES (1, 2, 30);

INSERT INTO BATIMENT VALUES ('A', 'SI');
INSERT INTO BATIMENT VALUES ('B', 'SI');
INSERT INTO BATIMENT VALUES ('C', 'BIO');

INSERT INTO ROOM(batiment, numRoom, typeRoom, capacity) VALUES ('A', 1, 'TD', 30);
INSERT INTO ROOM(batiment, numRoom, typeRoom, capacity)  VALUES ('A', 2, 'TP', 30);
INSERT INTO ROOM(batiment, numRoom, typeRoom, capacity)  VALUES ('B',1, 'Amphi', 100);
INSERT INTO ROOM(batiment, numRoom, typeRoom, capacity)  VALUES ('B', 2, 'TP', 30);

INSERT INTO USERS VALUES ('Guillon', 'julien','TEACHER','jguillon725','$2a$12$e8r2pbPJe/Dze7Hs1Ot88eCHmQlyGtxAE3zPpGTI909lX5OVOdbKC');
INSERT INTO USERS VALUES ('Caltot', 'stephan','STUDENT','scaltot904','$2a$12$Gjd4fzLC6iGypHtNn74qNOM5fvCMMjWPOs.vaZ6xteCokhE7YpRNu');

INSERT INTO STUDENT VALUES ('scaltot904', 'SI', 1, 'FAC');
INSERT INTO TEACHER VALUES ('jguillon725', 'HOLDER', 'math');

INSERT INTO COURSE(typeCourse, hour, day, idweek, month, subject, duree, teacher, idRoom) VALUES ('CM', 8, 26, 44, 10, 'MATH', 1, 'jguillon725', 2);
INSERT INTO COURSE(typeCourse, hour, day, idweek, month, subject, duree, teacher, idRoom) VALUES ('TP', 14, 26, 44, 10, 'C', 3, 'jguillon725', 3);
INSERT INTO COURSE(typeCourse, hour, day, idweek, month, subject, duree, teacher, idRoom) VALUES ('TD', 10, 28, 44, 10, 'JAVA', 2, 'jguillon725', 1);

INSERT INTO CoursePromotion VALUES (1, 1);

INSERT INTO CourseGroup VALUES (2, 1);

INSERT INTO CourseGroup VALUES (3, 2);

CREATE VIEW VIEWCOURSEPROMOTION AS
  SELECT CoursePromotion.idPromotion, typeCourse, hour, day, idweek, month, subject, duree, teacher, idRoom
  FROM CoursePromotion, COURSE
  WHERE idCoursePromotion = idCourse;

CREATE VIEW VIEWCOURSEGROUP AS
  SELECT CourseGroup.idGroup, typeCourse, hour, day, idweek, month, subject, duree, teacher, idRoom
  FROM CourseGroup, COURSE
  WHERE idCourseGroup = idCourse;
