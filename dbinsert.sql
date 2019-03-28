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

insert into questions (id, quiz_id, title) values 
(0, 0, 'What colour is the sky?');
insert into answers(id, question_id, answer, correct) values
(0, 0, 'Blue', '1'),
(1, 0, 'Red', '0'),
(2, 0, 'Yellow', '0'),
(3, 0, 'Green', '0');

insert into questions (id, quiz_id, title) values 
(1, 0, 'How many legs do you have?');
insert into answers(id, question_id, answer, correct) values
(4, 1, '1', '0'),
(5, 1, '3', '0'),
(6, 1, '2', '1'),
(7, 1, '6', '0');

insert into questions (id, quiz_id, title) values 
(2, 0, 'Do birds fly?');
insert into answers(id, question_id, answer, correct) values
(8, 2, 'Yes', '0'),
(9, 2, 'No', '0'),
(10, 2, 'Some birds dont', '1'),
(11, 2, 'Cucumber', '0');

-- Quiz 1

insert into quizes values (1, 'Test Quiz 1');

insert into questions (id, quiz_id, title) values 
(3, 1, 'What is the capital of Ireland?');
insert into answers(id, question_id, answer, correct) values
(12, 3, 'Budapest', '0'),
(13, 3, 'Dublin', '1'),
(14, 3, 'Your mom', '0'),
(15, 3, 'Texas', '0');

insert into questions (id, quiz_id, title) values 
(4, 1, 'How many hours are there in a day?');
insert into answers(id, question_id, answer, correct) values
(16, 4, '24', '1'),
(17, 4, '33', '0'),
(18, 4, '61', '0'),
(19, 4, '42', '0');

insert into questions (id, quiz_id, title) values 
(5, 1, 'What is a cat?');
insert into answers(id, question_id, answer, correct) values
(20, 5, 'a dog', '0'),
(21, 5, 'a mouse', '0'),
(22, 5, 'a cat', '1'),
(23, 5, 'not a mammal', '0');

--**** Inserting Solutions

insert into solutions (user_id, answer_id) values
(0, 0),
(0, 6),
(0, 8);
