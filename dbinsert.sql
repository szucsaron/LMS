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

insert into quizes values (0, 'Test Quiz 0');

insert into questions (id, quiz_id, title, correct_answer) values 
(0, 0, 'What colour is the sky?', 0);
insert into answers(id, question_id, answer) values
(0, 0, 'Blue'),
(1, 0, 'Red'),
(2, 0, 'Yellow'),
(3, 0, 'Green');

insert into questions (id, quiz_id, title, correct_answer) values 
(1, 0, 'How many legs do you have?', 6);
insert into answers(id, question_id, answer) values
(4, 1, '1'),
(5, 1, '3'),
(6, 1, '2'),
(7, 1, '6');

insert into questions (id, quiz_id, title, correct_answer) values 
(2, 0, 'Do birds fly?', 10);
insert into answers(id, question_id, answer) values
(8, 2, 'Yes'),
(9, 2, 'No'),
(10, 2, 'Some birds dont'),
(11, 2, 'Cucumber');

-- Quiz 1

insert into quizes values (1, 'Test Quiz 1');

insert into questions (id, quiz_id, title, correct_answer) values 
(3, 1, 'What is the capital of Ireland?', 13);
insert into answers(id, question_id, answer) values
(12, 3, 'Budapest'),
(13, 3, 'Dublin'),
(14, 3, 'Your mom'),
(15, 3, 'Texas');

insert into questions (id, quiz_id, title, correct_answer) values 
(4, 1, 'How many hours are there in a day?', 16);
insert into answers(id, question_id, answer) values
(16, 4, '24'),
(17, 4, '33'),
(18, 4, '61'),
(19, 4, '42');

insert into questions (id, quiz_id, title, correct_answer) values 
(5, 1, 'What is a cat?', 22);
insert into answers(id, question_id, answer) values
(20, 5, 'a dog'),
(21, 5, 'a mouse'),
(22, 5, 'a cat'),
(23, 5, 'not a mammal');

--**** Inserting Solutions

insert into solutions (user_id, question_id, answer) values
(0, 0, 0),
(0, 1, 1),
(0, 2, 3),
(0, 3, 2);
