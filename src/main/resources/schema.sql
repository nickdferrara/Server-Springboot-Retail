
INSERT INTO product (id, name, description, brand) VALUES
(1, 'Item 1', 'Description for item 1', 'Brand A'),
(2, 'Item 2', 'Description for item 2', 'Brand B'),
(3, 'Item 3', 'Description for item 3', 'Brand C'),
(4, 'Item 4', 'Description for item 4', 'Brand D'),
(5, 'Item 5', 'Description for item 5', 'Brand E');

INSERT INTO orders (id, order_number, order_date, status, total_amount) VALUES
(1, 'ORD001', '2023-01-01', 'PENDING', 100.00),
(2, 'ORD002', '2023-01-02', 'CONFIRMED', 200.00),
(3, 'ORD003', '2023-01-03', 'FULFILLED', 300.00),
(4, 'ORD004', '2023-01-04', 'SHIPPED', 400.00),
(5, 'ORD005', '2023-01-05', 'COMPLETED', 500.00);

INSERT INTO addresses (id, street, city, state, zip) VALUES
(1, '123 Main St', 'City A', 'State A', '12345'),
(2, '456 Elm St', 'City B', 'State B', '23456'),
(3, '789 Oak St', 'City C', 'State C', '34567'),
(4, '101 Pine St', 'City D', 'State D', '45678'),
(5, '202 Maple St', 'City E', 'State E', '56789');

INSERT INTO stores (id, store_number, address_id) VALUES
(1, 'STORE001', 1),
(2, 'STORE002', 2),
(3, 'STORE003', 3),
(4, 'STORE004', 4),
(5, 'STORE005', 5);

INSERT INTO hours_of_operation (id, store_id, day_of_week, opening_time, closing_time) VALUES
(1, 1, 'MONDAY', '09:00:00', '17:00:00'),
(2, 1, 'TUESDAY', '09:00:00', '17:00:00'),
(3, 1, 'WEDNESDAY', '09:00:00', '17:00:00'),
(4, 1, 'THURSDAY', '09:00:00', '17:00:00'),
(5, 1, 'FRIDAY', '09:00:00', '17:00:00'),
(6, 2, 'MONDAY', '09:00:00', '17:00:00'),
(7, 2, 'TUESDAY', '09:00:00', '17:00:00'),
(8, 2, 'WEDNESDAY', '09:00:00', '17:00:00'),
(9, 2, 'THURSDAY', '09:00:00', '17:00:00'),
(10, 2, 'FRIDAY', '09:00:00', '17:00:00'),
(11, 3, 'MONDAY', '09:00:00', '17:00:00'),
(12, 3, 'TUESDAY', '09:00:00', '17:00:00'),
(13, 3, 'WEDNESDAY', '09:00:00', '17:00:00'),
(14, 3, 'THURSDAY', '09:00:00', '17:00:00'),
(15, 3, 'FRIDAY', '09:00:00', '17:00:00'),
(16, 4, 'MONDAY', '09:00:00', '17:00:00'),
(17, 4, 'TUESDAY', '09:00:00', '17:00:00'),
(18, 4, 'WEDNESDAY', '09:00:00', '17:00:00'),
(19, 4, 'THURSDAY', '09:00:00', '17:00:00'),
(20, 4, 'FRIDAY', '09:00:00', '17:00:00'),
(21, 5, 'MONDAY', '09:00:00', '17:00:00'),
(22, 5, 'TUESDAY', '09:00:00', '17:00:00'),
(23, 5, 'WEDNESDAY', '09:00:00', '17:00:00'),
(24, 5, 'THURSDAY', '09:00:00', '17:00:00'),
(25, 5, 'FRIDAY', '09:00:00', '17:00:00');
