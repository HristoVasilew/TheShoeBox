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

INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on)
VALUES (1, 'Adidas', 'Speedrun', 45, 1, 2, 'ulica vishneva 33 N2 122', 'Mnogo zapazeni edna baba v germaniq gi e polzvala samo do magazina da hodi!', 349,
        'https://media.gq-magazine.co.uk/photos/5edfa0838ed3b9c66debd302/master/w_1920,h_1280,c_limit/20200609-adidas-01.jpg', 1, 2021-11-30);

INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on)
VALUES (2, 'Vans', 'Old Scool', 45, 2, 4, 'ulica vishneva 33 N2 122', 'Mnogo zapazeni edna baba v germaniq gi e polzvala samo do magazina da hodi!', 349,
        'https://img.modivo.cloud/product(a/0/4/6/a046758cdd6e62ceae4127e6181cb28109c15233_0000197806133_01_ki.jpg,jpg)/vans-gumenki-classic-slip-on-vn-0eyebww.jpg' ,2, '2021-11-30 11:31:39');
INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on)
VALUES (3, 'Vans', 'Skate', 42, 1, 2, 'ulica vishneva 33 N2 122', 'Mnogo zapazeni edna baba v germaniq gi e polzvala samo do magazina da hodi!', 349,
        'https://images.vans.com/is/image/Vans/VN0A5FCBY28-HERO?hei=1600&wid=1600&qlt=95', 1, '2021-11-30');