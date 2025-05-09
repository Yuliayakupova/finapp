SELECT COALESCE(SUM(amount), 0) FROM "transaction" WHERE moneybox_id = ? AND user_id = ?
