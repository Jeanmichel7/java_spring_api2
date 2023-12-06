DROP TABLE IF EXISTS employees;
 
CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);
 
INSERT INTO employees (first_name, last_name, email, password) VALUES
  ('Laurent', 'GINA', 'laurentgina@email.com', 'laurent'),
  ('Sophie', 'FONCEK', 'sophiefoncek@email.com', 'sophie'),
  ('Agathe', 'FEELING', 'agathefeeling@email.com', 'agathe');