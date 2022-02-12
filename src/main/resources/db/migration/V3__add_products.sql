INSERT INTO products (id, price, title)
VALUES (1, 230 , 'Cheese'),
       (2, 120 , 'Beer'),
       (3, 230 , 'Milk'),
       (4, 530 , 'Tomato'),
       (5, 435 , 'Bread');

ALTER SEQUENCE product_seq RESTART WITH 6;