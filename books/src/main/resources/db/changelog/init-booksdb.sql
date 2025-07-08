-- создание пользователя
CREATE USER books_user WITH PASSWORD 'books_password';

-- создание базы
CREATE DATABASE booksdb OWNER books_user;
-- создание схемы
CREATE SCHEMA IF NOT EXISTS books;
-- выдача прав на схему
GRANT ALL ON SCHEMA books TO books_user;