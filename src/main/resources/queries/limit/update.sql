UPDATE "limit"
SET max_amount = ?, period = ?, start_date = ?, category_id = ?
WHERE limit_id = ? AND user_id = ?;