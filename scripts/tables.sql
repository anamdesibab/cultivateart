CREATE TABLE USERS (
	USER_NAME varchar(30) NOT NULL,
	PASSWORD varchar(30) NOT NULL,
	LOGGEDIN BOOL NULL,
	CONSTRAINT USERS_PK PRIMARY KEY (USER_NAME)
);


CREATE TABLE SCHOOL_INFO (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME varchar(100) NOT NULL,
	PROFILE varchar(100) NULL,
	ADDRESS varchar(200) NULL,
	DISTRICT varchar(100) NULL,
	PHONE1 varchar(30) NULL,
	EMAIL varchar(100) NULL,
	PHONE2 varchar(30) NULL,
	WEBSITE varchar(100) NULL,
	LOGO varchar(200) NULL,
	CONSTRAINT SCHOOL_INFO_PK PRIMARY KEY (ID)
);

CREATE TABLE EVENT_INFO (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	TITLE varchar(150) NULL,
	DESCRIPTION varchar(1000) NULL,
	EVENT_DATE DATE NULL,
	LINK varchar(200) NULL,
	CONSTRAINT EVENT_INFO_PK PRIMARY KEY (ID)
);

CREATE TABLE STUDENT_INFO (
	ID INT NULL,
	NAME varchar(200) NULL,
	PHOTO varchar(100) NULL,
	SCHOOL_ID INT NULL,
	PARENT_NAME varchar(150) NULL,
	ADDRESS1 varchar(150) NULL,
	ADDRESS2 varchar(150) NULL,
	ADDRESS3 varchar(150) NULL,
	PHONE varchar(10) NULL,
	PHONE2 varchar(10) NULL,
	EMAIL_ID varchar(150) NULL,
	CATEGORY INT NULL,
	CONSTRAINT STUDENT_INFO_PK PRIMARY KEY (ID),
	CONSTRAINT STUDENT_INFO_SCHOOL_INFO_FK FOREIGN KEY (SCHOOL_ID) REFERENCES cultivateart.SCHOOL_INFO(ID)
);

CREATE TABLE `STUDENT_EVENTS` (
  `EVENT_ID` int(11) NOT NULL,
  `CATEGORY` int(11) DEFAULT NULL,
  `PRIZE_WONE` varchar(200) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `STUDENT_EVENTS_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`STUDENT_EVENTS_ID`)
)

CREATE TABLE EVENT_IMAGES (
	ID INT NOT NULL AUTO_INCREMENT,
	STUDENT_EVENT_ID INT NOT NULL,
	IMAGE_NAME varchar(200) NULL,
	IMAGE_DESCRIPTION varchar(150) NULL,
	CONSTRAINT EVENT_IMAGES_PK PRIMARY KEY (ID)
);


