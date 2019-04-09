DROP TABLE IF EXISTS solutions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS evaluations;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	email VARCHAR(100),
	user_name VARCHAR(100) primary key,
	passwd VARCHAR(50),
	role_id INT,
	progress INT
);

CREATE TABLE quizzes (
	id serial primary key,
	title VARCHAR(200)
);

create table evaluations (
	user_name VARCHAR(100) references users(user_name),
	quiz_id int references quizzes(id),
	status int,
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



INSERT INTO quizzes VALUES (0,'How to goat?'),
(1,'1 goats + 1 goats equals?'),
(2,'Goats and moral nihilism'),
(3,'Goats and refactoring'),
(4,'Goats and needs'),
(5,'Public');

INSERT INTO questions (id, quiz_id, title) VALUES
(0,0,'Which on is the perfect name for a goat?'),
(1,0,'Whats the most frequent illness among goats?'),
(2,0,'How do you start a daily goat checkup?'),
(3,0,'What is a goat?'),
(4,1,'1 goats + 1 goats equals?'),
(5,1,'Can you breed too many goats?'),
(6,1,'What to do, if your goats wont breed'),
(7,2,'Are goats great?'),
(8,2,'What is the correct representation of the world?'),
(9,2,'What is the difference between a painting and a goat?'),
(10,3,'Do you understand the material?'),
(11,4,'How many foods can you feed a goat?'),
(12,4,'What can a goat eat?'),
(13,4,'What can eat a goat?'),
(14,5,'What to do if you are cornered in a dark alley by Dezső, a violent anti-goat reactionary?'),
(15,5,'Who is Dezsős mother'),
(16,5,'Remember the mantra: What are goats?');

INSERT INTO answers (id, question_id, answer, correct) VALUES
(0, 0, 'Billy','0'),
(1, 0, 'Beth','0'),
(2, 0, 'Hector Brown Jr.','0'),
(3, 0, 'Anyád','1'),
(4, 1, 'Fever','0'),
(5, 1, 'Cancer','0'),
(6, 1, 'Anyád','1'),
(7, 1, 'Parkinsons disease','0'),
(8, 2, 'By calling my goats.','1'),
(9, 2, 'By calling my goats by their names','0'),
(10, 2, 'By calling my goats names','0'),
(11, 2, 'No.','0'),
(12, 3, 'A mammal.','0'),
(13, 3, 'A duck','0'),
(14, 3, 'An infectious disease','0'),
(15, 3, 'A goat is a mammal with all the hooves and none of the decency.','1'),
(16, 4, '1 goat','0'),
(17, 4, '2 goats','0'),
(18, 4, 'Goat[] goats = new Goat[] {new Goat(), new Goat()}','1'),
(19, 4, 'Goats dont exist.','0'),
(20, 5, 'Yes','0'),
(21, 5, 'No','1'),
(22, 6, 'Accept yourself as a failure.','1'),
(23, 6, 'Try to get goats that actually breed','0'),
(24, 6, 'Try to improve conditions in the barn','0'),
(25, 6, 'Switch to ducks.','0'),
(26, 7, 'Hell yeah!','1'),
(27, 7, 'Nay!','0'),
(28, 7, 'Sort of','0'),
(29, 7, 'It cannot be determined, as goats have no intrinsic values assigned to them by nature','0'),
(32, 8, 'Meaning meaning = (Meaning) NonsenseFactory.getInstance().createNonsense()','0'),
(33, 8, 'Meaning meaning = Meaning.parseNonsense(NonsenseFactory.getInstance().createNonsense())','0'),
(34, 8, '; is missing','1'),
(30, 8, 'Goat goat = new Goat(Meaning.getNullInstance())','0'),
(31, 8, 'Goat goat = new Goat(Meaning.getObjectiveInstance())','0'),
(35, 9, 'Nothing. They live in the same physical reality.','0'),
(36, 9, 'Nothing. Their names have letters in common.','0'),
(37, 9, 'You can paint goats, but you cant goat paints','0'),
(38, 9, 'They are nice','1'),
(39, 9, 'They both lack things: The goat doesnt have wheels, as a bicycle doesnt have legs.','0'),
(40, 9, 'One is the ultimate expression of creativity, spirit and human nature; the exaltation of life force itself, the other is a wall decoration.','0'),
(41, 10, 'Yes','1'),
(42, 10, 'No','0'),
(43, 11, '5','0'),
(44, 11, '6','1'),
(45, 11, '77','0'),
(46, 11, '1','0'),
(48, 12, 'Void','0'),
(49, 12, 'Bastards eat everything','1'),
(50, 12, 'Diesel fuel','0'),
(47, 12, 'Cucumber','0'),
(51, 13, 'Animals that eat goats','1'),
(52, 13, 'Animals that dont eat goats','0'),
(53, 13, 'Both','0'),
(54, 13, 'None of the above','0'),
(55, 14, 'Cry','0'),
(56, 14, 'Spit','1'),
(57, 14, 'This is the correct answer','0'),
(58, 14, 'Call upon the powers of Pan','0'),
(59, 14, 'Invalidate his person by writing a 200 page manifesto of goat-criticism-criticism. Hell die of shame.','0'),
(60, 15, 'A nice person','0'),
(61, 15, 'A shameless piece of half-sentient, infectious flesh as useful as a heart attack','1'),
(62, 15, 'Someone, who had given birth to Dezső','0'),
(63, 15, 'Dezső','0'),
(64, 16, 'A calling','1'),
(65, 16, 'An obsession','0');

INSERT INTO articles (id, quiz_id, title, textcontent, lvl) VALUES
(0, 0, 'What is a goat?', ' A goat is a mammal with all the hooves and none of the decency. From this can be all derived. In a fact, it is such a universal claim, that to imagine a world without goats is a bleak and pitiful existence without ANY redeeming qualities. To the trainee goatist, the seemingly insurmountable odds of overcoming any irrational fear of upgoating can be overcome by discipline and precision of mind, which can be sharpened by defining these creatures of utmost importance. Lets see it in practise: <p> 1. A goat is everything, which is, by nature not not a goat. </p> <p> 2. A goat is an animal, on which the number of limbs attached by the forces of nature never exceeds that of a donkey or a horse, and while this number is inferior to that of cockroaches, it never entails any kind of species-wise inferiority. The reason behind that lies in a factor that predisposes goat limbs to be multi-faceted constructions of compound appendages due to them having thick fur, in which each piece of hair can be considered a separate leg by invertebrate standards. In a sense, it can be said, that goats have limbs in the number of hundred-thousands. Can donkey-hair or horse-hair considered as well to be an array of legs? No, as its quite obvious that equines and their relatives cannot jump on fences, rooftops or other elevated platforms, on the other hand, goats can. This can be only explained by the latter having a high number of legs (which provides great extra strength), and the former dont. In other words, to the caprine, fur is legs, to other hoofed kindred, fur is just ... fur. A goat is, therefore, very good. </p> ', 1),
(1, 1, 'Goat breeding', ' Try to raise your number of goats. Hint: Add goats to make more goats. If you have enough goats, you dont have enough goats. Ideal goat population approaches infinity. Ideal human population doesnt. Conclusion: dont have children, have goats instead and breed them. Or even better, find yourself a team of decently drunken surgeons, so that by the miracle of scalpels and blood and saws and blood and drills and blood and hammers and blood and sutures and blood and just a hint of neo-Freudean charlatanry, you can become a goat yourself. Find friends; become goats together; make doctors exhausted, overworked and devilishly rich. You poor bastard, deep inside you know, you can never become one of these fine creatures. So you come up with stupid ideas, like this? Shame! ', 1),
(2, 2, 'Teaching your goats moral nihilism', ' You can teach your goats the base values of moral nihilism like this: <p> public class MyGoat() extends Goat {<br> private Boolean goodOrBad = null;<br> }<br> </p> ', 3),
(3, 3, 'Goat refactoring 101', ' Goats are to be refactored constantly for the greater good of goatkind. It is good. In refactoring, the more always means better. If your goats lack eyes, just stick a few dozen on each of them, or even hundreds. If your goat doesnt like Chinese food, duct tape a few external digestive tracts on their bodies, which can process stuff like that. Dont bother with untangling seemingly overcomplicated goat bionics you recently installed, as its a huge time sink. Has Your goat become a labyrinthine mess of a biological framework? Dont give a crap! Goats are strong. Thingsll sort themselves out in the end. If its too much work, let it rot. Its refactoring 101.  ', 4),
(4, 4, 'Putting your goat before your needs', ' Die for your goat. No exception. ', 5),
(5, 5, 'Dealing with the public: It is a calling, not an obsession!', ' So far, you have learned valuable lessons stemming from the basic proposition: Goats are great. However, public support for the cause may diminish each time you talk about goats, refer to goat food or mimic the calls of goats. There is a reason for that: For others, goats are not that great. What to do? Its easy: have ample time to refer to your opponents mothers. This will undermine their statements and make their very rhetoric collapse by its own weight. ', 6);

insert into users (email, user_name, passwd, role_id, progress) values 
('student@codecool.com', 'Jancsi', '1234', 1, 1),
('student1@codecool.com', 'Ludovikusz', '1234', 1, 3),
('student2@codecool.com', 'Erzsi', '1234', 1, 6),
('mentor@codecool.com', 'Pali', '1234', 2, 6);

insert into evaluations (user_name, quiz_id, status) values
('Erzsi', 0, 1),
('Erzsi', 1, 1),
('Erzsi', 2, 1),
('Erzsi', 3, 1),
('Erzsi', 4, 0),
('Erzsi', 5, -1);
/*

insert into solutions (user_name, answer_id) values ('Jancsi', 0);
insert into solutions (user_name, answer_id) values ('Jancsi', 4);
insert into solutions (user_name, answer_id) values ('Jancsi', 8);
*/


