CREATE TABLE IF NOT EXISTS moneybox (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    goal DECIMAL NOT NULL,
    current_amount DECIMAL NOT NULL,
    target_date DATE NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);
