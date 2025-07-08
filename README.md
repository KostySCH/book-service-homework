# book-service-homework
📝 Домашнее задание 1

Добавить эндпоинт для вывода всех аккаунтов одним списком.

В контроллер `AccountController` был добавлен метод:
```java
@GetMapping
public List<AccountDto> getAllAccounts() {
    return accountService.getAllAccounts();
}
```

В сервисе `AccountService` реализован метод:
```java
public List<AccountDto> getAllAccounts() {
    return accountRepository.findAll().stream()
            .map(accountMapper::toDto)
            .collect(Collectors.toList());
}
```

📝 Домашнее задание 2

Реализовать систему хранения и учёта книг.

Выбран второй вариант с созданием отдельного микросервиса

**Создание пользователя, базы и схемы для хранения книг**

```sql
-- создание пользователя
CREATE USER books_user WITH PASSWORD 'books_password';

-- создание базы
CREATE DATABASE booksdb OWNER books_user;

-- создание схемы
CREATE SCHEMA IF NOT EXISTS books;

-- выдача прав на схему
GRANT ALL ON SCHEMA books TO books_user;
```

**Liquibase**

Для автоматического создания таблиц и наполнения их данными используются два SQL-файла:
- `schema-books.sql` — создаёт таблицу книг:
  ```sql
  CREATE TABLE IF NOT EXISTS books.book (
      id SERIAL PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      author VARCHAR(255) NOT NULL,
      status VARCHAR(32),
      user_id BIGINT
  );
  ```
- `data-books.sql` — наполняет таблицу тестовыми данными:
  ```sql
  INSERT INTO books.book (title, author, status, user_id) VALUES
  ('Война и мир', 'Лев Толстой', 'AVAILABLE', NULL),
  ('Преступление и наказание', 'Фёдор Достоевский', 'AVAILABLE', NULL),
  ('Мастер и Маргарита', 'Михаил Булгаков', 'AVAILABLE', NULL);
  ```

**Контроллер**

`BookController` предоставляет API для работы с книгами:
- получение всех книг: `GET /v1/books`
- получение книги по id: `GET /v1/books/{id}`
- выдача книги пользователю: `POST /v1/books/{id}/issue?userId=...`
- возврат книги: `POST /v1/books/{id}/return?userId=...`

**Использование FeignClient**

Для проверки существования пользователя при выдаче/возврате книги используется FeignClient:
```java
@FeignClient(name = "user-service", url = "http://localhost:8080/v1")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
```

**Взаимодействие BookService с сервисом аккаунтов**

В сервисе `BookService` при выдаче книги вызывается метод FeignClient для проверки существования пользователя:
```java
UserDto user = userClient.getUserById(userId);
if (user == null) {
    throw new RuntimeException("Пользователь не найден");
}
```
Если пользователь существует, книга помечается как выданная этому пользователю. При возврате также проверяется, что книгу возвращает тот же пользователь



