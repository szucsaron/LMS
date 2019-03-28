--**** Inserting users

insert into users (id, user_name) values
(0, 'Jóska'),
(1, 'Pista'),
(2, 'Kázmér'),
(3, 'Irén'),
(4, 'Hajnalka'),
(5, 'Gizike');


--**** Inserting quizzes

-- Quiz 0

/*
insert into questions (id, quiz_id, title) values 
(, , '');
insert into answers(question_id, id, answer, correct) values
(, , '', '0'),
(, , '', '0'),
(, , '', '0'),
(, , '', '0');
 */

insert into quizes values (0, 'How to goat?');

insert into questions (id, quiz_id, title) values 
(0, 0, 'Which on is the perfect name for a goat?');
insert into answers(question_id, id, answer, correct) values
(0, 0, 'Billy', '0'),
(0, 1, 'Beth', '0'),
(0, 2, 'Hector Brown Jr.', '0'),
(0, 3, 'Anyád', '1');

insert into questions (id, quiz_id, title) values 
(1, 0, 'What is the most frequent illness among goats?');
insert into answers(question_id, id, answer, correct) values
(1, 4, 'Fever', '0'),
(1, 5, 'Cancer', '0'),
(1, 6, 'Anyád', '1'),
(1, 7, 'Parkinsons disease', '0');

insert into questions (id, quiz_id, title) values 
(2, 0, 'How do you start a daily goat checkup?');
insert into answers(question_id, id, answer, correct) values
(2, 8, 'By calling my goats.', '1'),
(2, 9, 'By calling my goats by their names', '0'),
(2, 10, 'By calling my goats names', '0'),
(2, 11, 'No.', '0');

insert into questions (id, quiz_id, title) values 
(3, 0, 'What is a goat?');
insert into answers(question_id, id, answer, correct) values
(3, 12, 'A mammal.', '0'),
(3, 13, 'A duck', '0'),
(3, 14, 'An infectious disease', '0'),
(3, 15, 'A goat is a mammal with all the hooves and none of the decency.', '1');



--**** Inserting Solutions

insert into solutions (user_id, answer_id) values
(0, 0),
(0, 6),
(0, 8);
