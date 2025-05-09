SELECT
    COALESCE(SUM(amount), 0) AS total
FROM
    transaction
WHERE
    moneybox_id = ? AND user_id = ?;
