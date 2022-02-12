INSERT INTO users (id, archive, email, name, password, role)
VALUES (1, false , 'mail@mail.ru', 'admin', '$2a$10$6yS1p2hk.8t54c5hDXoVPu2iaqPHO26OEUIkaNyd40ndpnwIrXal6', 'ADMIN');
ALTER SEQUENCE user_seq RESTART WITH 2;