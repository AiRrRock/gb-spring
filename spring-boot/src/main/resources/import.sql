DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, name VARCHAR(255),price INTEGER, PRIMARY KEY (id));
INSERT INTO products (id, name, price) VALUES (1,'box',1);
INSERT INTO products (id, name, price) VALUES (2,'box2',11);
INSERT INTO products (id, name, price) VALUES (3,'box3',12);
INSERT INTO products (id, name, price) VALUES (4,'box4',13);
INSERT INTO products (id, name, price) VALUES (5,'box5',14);