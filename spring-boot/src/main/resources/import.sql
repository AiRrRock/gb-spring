DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, name VARCHAR(255),price INTEGER, PRIMARY KEY (id));
INSERT INTO products (id, name, price) VALUES (1,'box',1);
INSERT INTO products (id, name, price) VALUES (2,'box2',11);
INSERT INTO products (id, name, price) VALUES (3,'box3',12);
INSERT INTO products (id, name, price) VALUES (4,'box4',13);
INSERT INTO products (id, name, price) VALUES (5,'box5',14);

DROP TABLE customers IF EXISTS;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO customers (id, name) VALUES (1,'Bob');
INSERT INTO customers (id, name) VALUES (2,'John');
INSERT INTO customers (id, name) VALUES (3,'Sam');


DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial, customer_id bigserial, product_id bigserial, price INTEGER, PRIMARY KEY (id));
INSERT INTO orders (id, customer_id, product_id, price) VALUES (1,1,2,12);
INSERT INTO orders (id, customer_id, product_id, price) VALUES (2,1,2,13);
INSERT INTO orders (id, customer_id, product_id, price) VALUES (3,1,2,15);
INSERT INTO orders (id, customer_id, product_id, price) VALUES (4,2,2,12);
