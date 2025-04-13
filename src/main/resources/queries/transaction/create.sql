CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(10, 2) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT now(),
    category TEXT
);