# INSERT INTO roles (id, role)
# VALUES (1, 'ADMIN');
# INSERT INTO roles (id, role)
# VALUES (2, 'USER');
#
# -- some test users
# INSERT INTO users (id, email, first_name, last_name, password, username)
# VALUES (1, 'admin@admin.com', 'Admin' ,'Adminov', 'f3f62bb27ba9d73fed9f9fb3ff3fa6782f4803d2db38fc45a2100030f030a328efd9d13872e97ba8', 'admin');
#
# INSERT INTO users (id, email, first_name, last_name, password, username)
# VALUES (2, 'user@user.com', 'User', 'Userov', 'f3f62bb27ba9d73fed9f9fb3ff3fa6782f4803d2db38fc45a2100030f030a328efd9d13872e97ba8',
#         'user');
#
# INSERT INTO users_roles (`user_entity_id`, `roles_id`)
# VALUES (1, 1);
# INSERT INTO users_roles (`user_entity_id`, `roles_id`)
# VALUES (1, 2);
# INSERT INTO users_roles (`user_entity_id`, `roles_id`)
# VALUES (2, 2);


INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on, ordered)
VALUES (1, 'Vans', 'X USPS® AUTHENTIC', 45, 1, 2, 'onaq ulica etam na lqvo vtorata presechka v dqsno i na kraq na ulicata tretata kushta v dqsno',
        'This season, Vans is proud to collaborate with the United States Postal Service® on a licensed collection inspired by the tireless
        dedication of our nation’s USPS® employees, and the vital service they have long performed for us all.', 379,
        'https://images.vans.com/is/image/Vans/KRDJZ8-HERO?$583x583$', 1, '2021-11-30', false);

INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on, ordered)
VALUES (2, 'Vans', 'Old Scool', 55, 2, 4, 'ulica vishneva 33 N2 122',
        'x USPS® Authentic comes equipped with metal eyelets, signature rubber
waffle outsoles, and a reflective flag label. The Tyvek exterior tongue label, which boasts
an “Official Licensed Product” seal, references both vintage sportswear and the iconic USPS®
mailing envelopes.
', 349,
        'https://images.vans.com/is/image/Vans/VC0836-HERO?$CUSTOMS-PDP-LARGE$' ,2, '2021-11-30 11:31:39', false);
INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on, ordered)
VALUES (3, 'Vans', 'Old Scool', 35, 2, 4, 'ulica геросаоо Sao Paolo 33 N2 122',
        ', including past and present branding, a vintage U.S. Mail logo and patch,
            and denim accents that give a nod to our Postal employees recognizable uniforms,
            the Vans x USPS® licensed collection was designed to reflect the year-round reality
            ', 249,
        'https://images.vans.com/is/image/Vans/VC0847-HERO?$CUSTOMS-PDP-LARGE$' ,2, '2021-11-30 11:31:39', false);
INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on, ordered)
VALUES (4, 'Vans', 'Skate', 42, 1, 2, 'peron 9 3/4 hogwarts express', 'The USPS® Authentic comes packaged in a custom “Priority Mail®” shoe box.', 149,
        'https://images.vans.com/is/image/Vans/VN0A5FCBY28-HERO?hei=1600&wid=1600&qlt=95', 1, '2021-11-30', false);
INSERT INTO shoes(id, brand, model, size, shoe_condition_entity_id, shoe_category_entity_id, location, description, price, image_url,creator_id, created_on, ordered)
VALUES (5, 'Vans', 'Skate', 42, 1, 2, 'peron 9 3/4 hogwarts express', 'The Sk8-Hi, Vans legendary lace-up high-top built for skating, has an Off the Wall persona thanks to the iconic leather sidestripe', 549,
        'https://images.vans.com/is/image/Vans/CS0058-HERO?$CUSTOMS-PDP-LARGE$', 1, '2021-11-30', false);


insert into comments(approved, created, text_content, author_id, shoe_id)
values (true, '2021-11-30 11:31:39', 'OT tuk natam bez da bugvam', 1, 1);

insert into comments(approved, created, text_content, author_id, shoe_id)
values (true, '2021-11-30 11:31:39', 'много точен. и преди съм поръчвал от
него много съм доволен.препоръчвам! ! !', 1, 1);

insert into comments(approved, created, text_content, author_id, shoe_id)
values (true, '2021-11-30 11:31:39', 'Голям лъжец преди месец ги имаше пуснати
същите за половин цена аз не бих се доверил! ! !', 2, 1);


insert into comments(approved, created, text_content, author_id, shoe_id)
values (true, '2021-11-30 11:31:39', 'OT tuk natam bez da bugvam', 2, 2);