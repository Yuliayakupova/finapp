SELECT COUNT(*)
FROM category
WHERE id = ? AND (user_id = ? OR user_id IS NULL)
