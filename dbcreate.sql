drop table if exists solutions;
drop table if exists answers;
drop table if exists articles;
drop table if exists questions;
drop table if exists users;
drop table if exists quizes;


create table users (
	id INT primary key,
	user_name VARCHAR(50),
	passwd VARCHAR(50)
);

create table quizes (
	id INT primary key,
	title VARCHAR(50)
);

create table questions (
	id serial primary key,
	quiz_id INT references quizes(id),
	title VARCHAR(200)
);

create table articles (
	id INT primary key,
	quiz_id INT references quizes(id),
	title VARCHAR(50),
	textcontent text
);

create table answers (
	id serial primary key,
	question_id INT references questions(id),
	answer VARCHAR(200),
	correct bit
);

create table solutions (
	user_id INT references users(id),
	answer_id INT references answers(id),
	primary key(user_id, answer_id)
);


