SELECT
    COALESCE(SUM(CASE WHEN c.type = 'income' THEN t.amount ELSE 0 END), 0)
    -
    COALESCE((
        SELECT SUM(t2.amount)
        FROM transaction t2
        JOIN moneybox m ON t2.moneybox_id = m.id
        WHERE t2.user_id = ?
    ), 0) AS available_income
FROM transaction t
JOIN category c ON t.category_id = c.id
WHERE t.user_id = ?;
