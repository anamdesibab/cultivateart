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

CREATE TABLE mySqlTest.EVENT_INFO (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	TITLE varchar(150) NULL,
	DESCRIPTION varchar(1000) NULL,
	EVENT_DATE DATE NULL,
	LINK varchar(200) NULL,
	CONSTRAINT EVENT_INFO_PK PRIMARY KEY (ID)
);