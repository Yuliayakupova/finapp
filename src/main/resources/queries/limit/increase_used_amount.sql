UPDATE "limit"
SET used_amount = used_amount + ?
WHERE user_id = ?
  AND category_id = ?;