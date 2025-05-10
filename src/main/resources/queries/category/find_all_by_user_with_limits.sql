SELECT
    c.id AS category_id,
    c.name,
    c.type,
    l.limit_id,
    l.max_amount,
    l.used_amount,
    l.period,
    l.start_date
FROM category c
LEFT JOIN "limit" l ON c.id = l.category_id AND l.user_id = ?
WHERE c.user_id = ? OR c.user_id IS NULL;
