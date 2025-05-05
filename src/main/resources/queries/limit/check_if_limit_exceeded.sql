SELECT
    l.max_amount,
    l.used_amount,
    l.category_id,
    l.user_id,
    (l.max_amount - l.used_amount) AS remaining_amount
FROM
    "limit" l
WHERE
    l.user_id = ?
    AND l.category_id = ?;
