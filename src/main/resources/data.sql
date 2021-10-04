INSERT INTO users(username,password,enabled)
VALUES
('user', '$2a$12$dJIxOWqZmuV8iESH10V7rOKP8BZuHvYV0krQoqCslV9wykki9VO2y',TRUE),
       ('admin','$2a$12$dJIxOWqZmuV8iESH10V7rOKP8BZuHvYV0krQoqCslV9wykki9VO2y',TRUE);

INSERT INTO authorities(username,authority)
VALUES
('user','ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');
