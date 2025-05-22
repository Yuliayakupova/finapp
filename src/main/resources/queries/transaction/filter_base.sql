SELECT t.id, t.amount, t.description, t.created_at, t.category_id, t.moneybox_id, c.type AS category_type
FROM "transaction" t
JOIN category c ON t.category_id = c.id
WHERE 1=1
AND c.type = 'expense'
AND t.user_id = ?

