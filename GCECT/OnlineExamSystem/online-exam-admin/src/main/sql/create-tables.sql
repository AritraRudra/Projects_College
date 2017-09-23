CREATE TABLE ONLINE_EXAM_USERS(
	USER_ID integer not null auto_increment,
	USER_IS_ADMIN char(1) not null,
	FIRST_NAME varchar(30),
	LAST_NAME varchar(50) not null,
	EMAIL varchar(255) not null,
	PASSWORD varchar(255) not null,
	primary key (USER_ID)
);

CREATE TABLE ONLINE_EXAM_QUESTIONS(
	QUESTION_ID integer not null auto_increment,
	SUBJECT varchar(50) not null,
	DIFFICULTY_LEVEL varchar(20) not null,
	QUESTION varchar(255) not null,
	OPTION_1 varchar(255) not null,
	OPTION_2 varchar(255) not null,
	OPTION_3 varchar(255) not null,
	OPTION_4 varchar(255) not null,
	CORRECT_OPTION varchar(9) not null,
	primary key (QUESTION_ID)
	/*
	CONSTRAINT chk_Correct_option CHECK (CORRECT_OPTION IN ('OPTION_1', 'OPTION_2', 'OPTION_3', 'OPTION_4'))
	MySQL doesn't support CHECK constraint
	https://stackoverflow.com/questions/7522026/how-do-i-add-a-check-constraint-to-a-table
	*/
)

CREATE TABLE EXAM_DETAILS(
	EXAM_ID integer not null auto_increment,
	USER_ID integer,
	QUESTION_ID integer,
	MARKS integer not null,
	date_time timestamp not null,
	primary key (EXAM_ID),
	foreign key (USER_ID) references ONLINE_EXAM_USERS(USER_ID)	
        ON DELETE SET NULL
        ON UPDATE CASCADE,
	foreign key (QUESTION_ID) references ONLINE_EXAM_QUESTIONS(QUESTION_ID)
		ON DELETE SET NULL
        ON UPDATE CASCADE
);
/*
CREATE TABLE EXAM_ID_QUESTION_ID_MAPPER(
	QUESTION_ID integer
)
*/