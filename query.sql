

-- Get a quiz by id
select title from quizes where id = 0;

-- Get a question by id in the same quiz
select id, title from questions where quiz_id = 0 order by questions.id limit 1 offset 3 ;

-- Get all answers
select answers.id, answer, title from answers left join questions on question_id = questions.id  where question_id = 2;


/*
-- Get solutions of by user and quiz id
select questions.title, questions.correct_answer, solutions.answer
from solutions
left join questions on solutions.question_id = questions.id
where quiz_id = 0;
*/