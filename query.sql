 
DELETE FROM solutions  
WHERE answer_id IN   
    (SELECT id   
     FROM answers  
     WHERE question_id = 1);