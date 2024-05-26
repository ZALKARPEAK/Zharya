INSERT INTO users (id, name, phone_number, image)
VALUES
    (1, 'John Smith', '+72345678901', 'image'),
    (2, 'Alice Johnson', '+79876543210', 'image'),
    (3, 'Michael Brown', '+71223344556', 'image'),
    (4, 'Emma Wilson', '+71234567890', 'image'),
    (5, 'David Thompson', '+71234567891', 'image'),
    (6, 'Olivia Davis', '+71234567892', 'image'),
    (7, 'James Anderson', '+71234567893', 'image'),
    (8, 'Sophia Martinez', '+71234567894', 'image'),
    (9, 'Daniel Taylor', '+71234567895', 'image'),
    (10, 'Isabella Moore', '+71234567896', 'image');

INSERT INTO user_info (id, email, password, role, user_id)
VALUES
    (1, 'admin@gmail.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'ADMIN', 1),
    (2, 'alice@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 2),
    (3, 'michael@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 3),
    (4, 'emma@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 4),
    (5, 'david@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 5),
    (6, 'olivia@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 6),
    (7, 'james@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 7),
    (8, 'sophia@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 8),
    (9, 'daniel@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 9),
    (10, 'isabella@example.com', '$2a$04$q/5hoHH1.96f6IUIF4oEIO4EAjuLOPvJSa/hPCo5XEERI3cC2pm4K', 'USER', 10);