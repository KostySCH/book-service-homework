CREATE USER account_service with PASSWORD 'account_service';
CREATE SCHEMA account_service AUTHORIZATION account_service;
ALTER USER account_service SET search_path = 'account_service';
