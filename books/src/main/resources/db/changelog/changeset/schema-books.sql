
CREATE TABLE IF NOT EXISTS books.book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    status VARCHAR(32),
    user_id BIGINT
); 