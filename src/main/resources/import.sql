INSERT INTO `role` (`role_id`, `role`) VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER');
INSERT INTO 'users' (id, enabled, password, username) VALUES (1, 1, '$2a$10$5c7QqEk4kmRM9BYkCmyqieGnGimDdU7/WjpZcCb99QxfAlqZv0kSi', 'admin');
INSERT INTO 'users' (id, enabled, password, username) VALUES (2, 1, '$2a$10$2Oese1jMygsHYIAKqXdvHOFtNfvsOjyb.OkmTPmQZ5t9qrrHoOE8.', 'user');
INSERT INTO 'user_role' (user_id, role_id) VALUES (1,1), (1, 2), (2,2);