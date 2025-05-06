SELECT
    l.limit_id,
    l.max_amount,
    l.used_amount,
    l.category_id,
    l.user_id,
    l.period,
    l.start_date,
    (l.max_amount - l.used_amount) AS remaining_amount
FROM
    "limit" l
WHERE
    l.user_id = ?;