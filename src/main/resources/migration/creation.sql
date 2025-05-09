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
    type VARCHAR(20) CHECK (type IN ('income', 'expense')) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id)
);

CREATE TABLE IF NOT EXISTS moneybox (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    target_amount DECIMAL NOT NULL,
    current_amount DECIMAL NOT NULL DEFAULT 0,
    target_date DATE NOT NULL,
    user_id INT,
    deleted_at TIMESTAMP,
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

INSERT INTO category (name, type)
VALUES
    ('Groceries', 'expense'),
    ('Transport', 'expense'),
    ('Housing', 'expense'),
    ('Utilities', 'expense'),
    ('Health', 'expense'),
    ('Beauty', 'expense'),
    ('Entertainment', 'expense'),
    ('Sports', 'expense'),
    ('Kids', 'expense'),
    ('Dining Out', 'expense'),
    ('Education', 'expense'),
    ('Gifts & Donations', 'expense'),
    ('Clothing', 'expense'),
    ('Travel', 'expense'),
    ('Pets', 'expense'),
    ('Other', 'expense'),

    ('Salary', 'income'),
    ('Freelance', 'income'),
    ('Investments', 'income'),
    ('Gifts', 'income'),
    ('Refunds', 'income'),
    ('Other', 'income')
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS "limit" (
    limit_id SERIAL PRIMARY KEY,
    max_amount DECIMAL NOT NULL,
    used_amount DECIMAL DEFAULT 0,
    period TEXT NOT NULL,
    start_date DATE NOT NULL,
    category_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (user_id) REFERENCES "user" (user_id)
);