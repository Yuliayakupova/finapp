CREATE TABLE IF NOT EXISTS "user" (
    user_id SERIAL PRIMARY KEY,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    name TEXT,
    registration_date DATE NOT NULL DEFAULT CURRENT_DATE,
    is_authenticated BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(20) CHECK (type IN ('income', 'expense')) NOT NULL
);

CREATE TABLE IF NOT EXISTS moneybox (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    goal DECIMAL NOT NULL,
    current_amount DECIMAL NOT NULL,
    target_date DATE NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id)
);

CREATE TABLE IF NOT EXISTS transaction (
    id SERIAL PRIMARY KEY,
    amount DECIMAL NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category_id INT,
    moneybox_id INT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (moneybox_id) REFERENCES moneybox (id)
);
