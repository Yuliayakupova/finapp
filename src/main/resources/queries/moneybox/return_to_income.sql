UPDATE category
SET balance = balance + ?
WHERE user_id = ? AND type = 'income';
