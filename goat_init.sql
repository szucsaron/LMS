DROP TABLE IF EXISTS solutions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS evaluations;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	email VARCHAR(100) UNIQUE,
	user_name VARCHAR(100) primary key,
	passwd VARCHAR(50),
	role_id INT,
	progress INT
);

CREATE TABLE attendance (
	present VARCHAR(10),
	user_name VARCHAR(100) REFERENCES users(user_name),
	PRIMARY KEY (user_name, present)
);

CREATE TABLE quizzes (
	id serial primary key,
	title VARCHAR(200)
);

/* STATUS
 * -1: failed
 * 0: started
 * 1: finished
 * 2: passed
 */
create table evaluations (
	user_name VARCHAR(100) references users(user_name),
	quiz_id int references quizzes(id),
	status numeric(1),
	primary key (user_name, quiz_id)
);

CREATE TABLE questions (
	id serial primary key,
	quiz_id INT references quizzes(id),
	title VARCHAR(200)
);

CREATE TABLE articles (
	id serial primary key,
	quiz_id INT references quizzes(id),
	title VARCHAR(200),
	textcontent text,
	lvl INT
);

CREATE TABLE answers (
	id serial primary key,
	question_id INT references questions(id),
	answer VARCHAR(200),
	correct bit
);

CREATE TABLE solutions (
	user_name VARCHAR(100) references users(user_name),
	answer_id INT references answers(id),
	primary key(user_name, answer_id)
);



INSERT INTO quizzes (title) VALUES
('How to goat?'),
('1 goats + 1 goats equals?'),
('Goats and moral nihilism'),
('Goats and refactoring'),
('Goats and needs'),
('Dealing with public');

INSERT INTO questions (quiz_id, title) VALUES
(1,'Which on is the perfect name for a goat?'),
(1,'What''s the most frequent illness among goats?'),
(1,'How do you start a daily goat checkup?'),
(1,'What is a goat?'),
(2,'1 goats + 1 goats equals?'),
(2,'Can you breed too many goats?'),
(2,'What to do, if your goats wont breed'),
(3,'Are goats great?'),
(3,'What is the correct representation of the world?'),
(3,'What is the difference between a painting and a goat?'),
(4,'Do you understand the material?'),
(5,'How many foods can you feed a goat?'),
(5,'What can a goat eat?'),
(5,'What can eat a goat?'),
(6,'What to do if you are cornered in a dark alley by Dezső, a violent anti-goat reactionary?'),
(6,'Who is Dezsős mother'),
(6,'Remember the mantra: What are goats?');

INSERT INTO answers (question_id, answer, correct) VALUES
(1, 'Billy','0'),
(1, 'Beth','0'),
(1, 'Hector Brown Jr.','0'),
(1, 'Your Maternal Ancestor','1'),
(2, 'Fever','0'),
(2, 'Cancer','0'),
(2, 'Your Maternal Ancestor','1'),
(2, 'Parkinsons disease','0'),
(3, 'By calling my goats.','1'),
(3, 'By calling my goats by their names','0'),
(3, 'By calling my goats names','0'),
(3, 'No.','0'),
(4, 'A mammal.','0'),
(4, 'A duck','0'),
(4, 'An infectious disease','0'),
(4, 'A goat is a mammal with all the hooves and none of the decency.','1'),
(5, '1 goat','0'),
(5, '2 goats','0'),
(5, 'Goat[] goats = new Goat[] {new Goat(), new Goat()}','1'),
(5, 'Goats don''t exist.','0'),
(6, 'Yes','0'),
(6, 'No','1'),
(7, 'Accept yourself as a failure.','1'),
(7, 'Try to get goats that actually breed','0'),
(7, 'Try to improve conditions in the barn','0'),
(7, 'Switch to ducks.','0'),
(8, 'Hell yeah!','1'),
(8, 'Nay!','0'),
(8, 'Sort of','0'),
(8, 'It cannot be determined, as goats have no intrinsic values assigned to them by nature','0'),
(9, 'Meaning meaning = (Meaning) NonsenseFactory.getInstance().createNonsense()','0'),
(9, 'Meaning meaning = Meaning.parseNonsense(NonsenseFactory.getInstance().createNonsense())','0'),
(9, '; is missing','1'),
(9, 'Goat goat = new Goat(Meaning.getNullInstance())','0'),
(9, 'Goat goat = new Goat(Meaning.getObjectiveInstance())','0'),
(10, 'Nothing. They live in the same physical reality.','0'),
(10, 'Nothing. Their names have letters in common.','0'),
(10, 'You can paint goats, but you cant goat paints','0'),
(10, 'They are nice','1'),
(10, 'They both lack things: The goat doesn''t have wheels, as a bicycle doesn''t have legs.','0'),
(10, 'One is the ultimate expression of creativity, spirit and human nature; the exaltation of life force itself, the other is a wall decoration.','0'),
(11, 'Yes','1'),
(11, 'No','0'),
(12, '5','0'),
(12, '6','1'),
(12, '77','0'),
(12, '1','0'),
(13, 'Void','0'),
(13, 'Bastards eat everything','1'),
(13, 'Diesel fuel','0'),
(13, 'Cucumber','0'),
(14, 'Animals that eat goats','1'),
(14, 'Animals that don''t eat goats','0'),
(14, 'Both','0'),
(14, 'None of the above','0'),
(15, 'Cry','0'),
(15, 'Spit','1'),
(15, 'This is the correct answer','0'),
(15, 'Call upon the powers of Pan','0'),
(15, 'Invalidate his person by writing a 200 page manifesto of goat-criticism-criticism. Hell die of shame.','0'),
(16, 'A nice person','0'),
(16, 'A shameless piece of half-sentient, infectious flesh as useful as a heart attack','1'),
(16, 'Someone, who had given birth to Dezső','0'),
(16, 'Dezső','0'),
(17, 'A calling','1'),
(17, 'An obsession','0');

INSERT INTO articles (quiz_id, title, textcontent, lvl) VALUES
(1, 'What is a goat?', ' A goat is a mammal with all the hooves and none of the decency. From this can be all derived. In fact, it is such a universal claim, that to imagine a world without goats is a bleak and pitiful existence without ANY redeeming qualities. To the trainee goatist, the seemingly insurmountable odds of overcoming any irrational fear of upgoating can be eased by discipline and precision of mind, which can be sharpened by defining these creatures of utmost importance. Lets see it in practise: <p> 1. A goat is everything, which is, by nature not not a goat. </p> <p> 2. A goat is an animal, on which the number of limbs attached by the forces of nature never exceeds that of a donkey or a horse, and while this number is inferior to that of cockroaches, it never entails any kind of species-wise inferiority. The reason behind that lies in a factor that predisposes goat limbs to be multi-faceted constructions of compound appendages due to them having thick fur, in which each piece of hair can be considered a separate leg by invertebrate standards. In a sense, it can be said, that goats have limbs in the number of hundred-thousands. Can donkey-hair or horse-hair considered as well to be an array of legs? No, as it''s quite obvious that equines and their relatives cannot jump on fences, rooftops or other elevated platforms, on the other hand, goats can. This can be only explained by the latter having a high number of legs (which provides great extra strength), and the former don''t. In other words, to the caprine, fur is legs; to other hoofed kindred, fur is just ... fur. A goat is, therefore, very good. </p> ', 1),
(2, 'Goat breeding', ' Try to raise your number of goats. Hint: Add goats to make more goats. If you have enough goats, you don''t have enough goats. Ideal goat population approaches infinity. Ideal human population doesn''t. Conclusion: don''t have children, have goats instead and breed them. Or even better, find yourself a team of decently drunken surgeons, so that by the miracle of scalpels and blood and saws and blood and drills and blood and hammers and blood and sutures and blood and just a hint of neo-Freudean charlatanry, you can become a goat yourself. Find friends; become goats together; make doctors exhausted, overworked and devilishly rich. You poor bastard, deep inside you know, you can never become one of these fine creatures. So you come up with stupid ideas, like this? Shame! ', 1),
(3, 'Teaching your goats moral nihilism', ' You can teach your goats the base values of moral nihilism like this: <p> public class MyGoat() extends Goat {<br> private Boolean goodOrBad = null;<br> }<br> </p> ', 3),
(4, 'Goat refactoring 101', ' Goats are to be refactored constantly for the greater good of goatkind. It is good. In refactoring, the more always means better. If your goats lack eyes, just stick a few dozen on each of them, or even hundreds. If your goat doesn''t like Chinese food, duct tape a few external digestive tracts on their bodies, which can process stuff like that. don''t bother with untangling seemingly overcomplicated goat bionics you recently installed, as its a huge time sink. Has Your goat become a labyrinthine mess of a biological framework? don''t give a crap! Goats are strong. Thingsll sort themselves out in the end. If its too much work, let it rot. Its refactoring 101.  ', 4),
(5, 'Putting your goat before your needs', ' Die for your goat. No exception. ', 5),
(6, 'Dealing with the public: It is a calling, not an obsession!', ' So far, you have learned valuable lessons stemming from the basic proposition: Goats are great. However, public support for the cause may diminish each time you talk about goats, refer to goat food or mimic the calls of goats. There is a reason for that: For others, goats are not that great. What to do? Its easy: have ample time to refer to your opponents mothers. This will undermine their statements and make their very rhetoric collapse by its own weight. ', 6);

insert into users (email, user_name, passwd, role_id, progress) values
('student@codecool.com', 'Jancsi', '1234', 1, 1),
('student1@codecool.com', 'Ludovikusz', '1234', 1, 3),
('student2@codecool.com', 'Erzsi', '1234', 1, 6),
('mentor@codecool.com', 'Pali', '1234', 2, 6);

insert into evaluations (user_name, quiz_id, status) values
('Erzsi', 1, 2),
('Erzsi', 2, 2),
('Erzsi', 3, 2),
('Erzsi', 4, 2),
('Erzsi', 5, -1),
('Erzsi', 6, 1);

INSERT INTO solutions (user_name, answer_id) VALUES
('Erzsi', 47),
('Erzsi', 49),
('Erzsi', 54),
('Erzsi', 60),
('Erzsi', 64),
('Erzsi', 65);

INSERT INTO attendance (user_name, present) VALUES
('Erzsi', '2019-01-01'),
('Erzsi', '2019-01-02'),
('Erzsi', '2019-01-03'),
('Erzsi', '2019-01-04'),
('Erzsi', '2019-01-06'),
('Jancsi', '2019-01-01'),
('Jancsi', '2019-01-02'),
('Jancsi', '2019-01-03'),
('Jancsi', '2019-01-04'),
('Jancsi', '2019-04-10'),
('Ludovikusz', '2019-01-01'),
('Ludovikusz', '2019-01-02'),
('Ludovikusz', '2019-01-06'),
('Ludovikusz', '2019-01-08'),
('Ludovikusz', '2019-01-10'),
('Ludovikusz', '2019-01-21')
;
/*

insert into solutions (user_name, answer_id) values ('Jancsi', 0);
insert into solutions (user_name, answer_id) values ('Jancsi', 4);
insert into solutions (user_name, answer_id) values ('Jancsi', 8);
*/
