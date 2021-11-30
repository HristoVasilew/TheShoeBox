INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');

-- some test users
INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (1, 'admin@admin.com', 'Admin' ,'Adminov', 'f3f62bb27ba9d73fed9f9fb3ff3fa6782f4803d2db38fc45a2100030f030a328efd9d13872e97ba8', 'admin');

INSERT INTO users (id, email, first_name, last_name, password, username)
VALUES (2, 'user@user.com', 'User', 'Userov', 'f3f62bb27ba9d73fed9f9fb3ff3fa6782f4803d2db38fc45a2100030f030a328efd9d13872e97ba8',
        'user');

INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (2, 2);

-- INSERT INTO shoes(id, brand, model, )