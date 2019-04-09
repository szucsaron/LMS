

select title, answer, question_id, answer_id, correct from solutions
left join answers on solutions.answer_id = answers.id
left join questions on answers.question_id = questions.id
where user_name = 'Jancsi' and quiz_id = 0;


/*
-- Get solutions of by user and quiz id
select questions.title, questions.correct_answer, solutions.answer
from solutions
left join questions on solutions.question_id = questions.id
where quiz_id = 0;
*/