SELECT
    COALESCE(SUM(
        CASE
            WHEN c.type = 'income' THEN t.amount
            ELSE -t.amount
        END
    ), 0) AS total_balance
FROM "transaction" t
JOIN category c ON t.category_id = c.id
WHERE t.user_id = ?;

