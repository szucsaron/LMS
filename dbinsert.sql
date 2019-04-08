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
insert into answers(question_id, answer, correct) values
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


insert into quizes values (1, 'Goats and moral nihilism');

insert into questions (id, quiz_id, title) values 
(4, 1, 'Are goats great?');
insert into answers(question_id, id, answer, correct) values
(4, 16, 'Hell yeah!', '1'),
(4, 17, 'Nay!', '0'),
(4, 18, 'Sort of', '0'),
(4, 19, 't cannot be determined, as goats have no intrinsic values assigned to them by nature', '0');

insert into questions (id, quiz_id, title) values 
(5, 1, 'What is the correct representation of the world?');
insert into answers(question_id, id, answer, correct) values
(5, 20, 'Goat goat = new Goat(Meaning.getNullInstance())', '0'),
(5, 21, 'Goat goat = new Goat(Meaning.getObjectiveInstance())', '0'),
(5, 22, 'Meaning meaning = (Meaning) NonsenseFactory.getInstance().createNonsense()', '0'),
(5, 23, 'Meaning meaning = Meaning.parseNonsense(NonsenseFactory.getInstance().createNonsense())', '0'),
(4, 24, '; is missing', '1');

insert into questions (id, quiz_id, title) values 
(6, 1, 'What is the difference between a painting and a goat?');
insert into answers(question_id, id, answer, correct) values
(6, 25, 'Nothing. They live in the same physical reality.', '0'),
(6, 26, 'Nothing. Their names have letters in common.', '0'),
(6, 27, 'You can paint goats, but you cannot goat paints', '0'),
(6, 28, 'They are nice', '1'),
(6, 29, 'They both lack things: The goat doesnt have wheels, as a bicycle doesnt have legs.', '0'),
(6, 30, 'One is the ultimate expression of creativity, spirit and human nature; the exaltation of life force itself, the other is a wall decoration.', '0');


insert into quizes values (3, 'Goats and refactoring?');
insert into questions (id, quiz_id, title) values 
(7, 3, 'Do you understand the material?');
insert into answers(question_id, id, answer, correct) values
(7, , 'Yes', '1'),
(7, , 'No', '0');

insert into questions (id, quiz_id, title) values 
(8, 3, 'How many foods can you feed a goat?');
insert into answers(question_id, id, answer, correct) values
(8, , '5', '0'),
(8, , '6', '1'),
(8, , '77', '0'),
(8, , '1', '0');

insert into questions (id, quiz_id, title) values 
(9, 3, 'What can a goat eat');
insert into answers(question_id, id, answer, correct) values
(9, , 'Cucumber', '0'),
(9, , 'Void', '0'),
(9, , 'Bastards eat everything', '0'),
(9, , 'Diesel fuel', '0');

insert into questions (id, quiz_id, title) values 
(10, 3, 'What can eat a goat?');
insert into answers(question_id, id, answer, correct) values
(10, , 'Animals that eat goats', '0'),
(10, , 'Animals that do not eat goats', '0'),
(10, , 'Both', '0'),
(10, , 'None of the above', '0');
--**** Inserting Solutions

insert into solutions (user_id, answer_id) values
(0, 0),
(0, 6),
(0, 8);
