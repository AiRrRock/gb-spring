create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('cat1'),
       ('cat2');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);

insert into products (title, price, category_id)
values ('p1', 80, 1),
       ('p2', 82, 1),
       ('p3', 83, 1),
       ('p4', 84, 1),
       ('p5', 85, 1),
       ('p6', 86, 1),
       ('p7', 87, 1),
       ('p8', 88, 1),
       ('p9', 89, 1),
       ('p10', 800, 2),
       ('p11', 801, 2),
       ('p12', 802, 2),
       ('p13', 803, 2),
       ('p14', 804, 2),
       ('p15', 805, 2),
       ('p16', 806, 2),
       ('p17', 807, 2),
       ('p18', 808, 2),
       ('p19', 809, 2),
       ('p20', 2000, 2);