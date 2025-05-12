SELECT c.id AS category_id, c.name AS category_name, c.type AS category_type
FROM category c
WHERE c.type = 'expense' AND c.user_id = ?
ORDER BY c.name;
