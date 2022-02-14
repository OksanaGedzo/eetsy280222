
INSERT INTO public.picture (id, path) VALUES (DEFAULT, 'https://img.astri.ee/5fe06f2f863aa673de1b5282/156.jpg');
INSERT INTO public.payment_method (id, paymant_type) VALUES (DEFAULT, 'Bank');
INSERT INTO public.delivery_method (id, price, name, description, delivery_time) VALUES (DEFAULT, 8.00, 'Itella', null, '3 päeva');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Admin');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Moderator');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Seller');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'User');
INSERT INTO public.contact (id, first_name, last_name, address, post_index, country, phone_number, email) VALUES (DEFAULT, 'Mart', 'tamm', 'Tallinn', '12345', 'Estonia', '1234567', 'tallinn@gmail.com');
INSERT INTO public.seller (id, logo_picture_id, name, email, telephone, index, aadress, website, validated) VALUES (DEFAULT, 1, 'Apple', 'apple@gmail.com', '1111111', '54321', 'Tallinn', null, DEFAULT);
INSERT INTO public."user" (id, contact_id, seller_id, user_name, password) VALUES (DEFAULT, 1, null, 'marttamm', 'parool');
INSERT INTO public.user_role (id, user_id, role_id) VALUES (DEFAULT, 1, 1);
INSERT INTO public.primary_group (id, picture_id, name) VALUES (DEFAULT, 1, 'Handcraft');
INSERT INTO public.item (id, seller_id, name, price, description) VALUES (DEFAULT, 1, 'müts', 10.00, 'villane müts');
INSERT INTO public.item_picture (id, item_id, picture_id) VALUES (DEFAULT, 1, 1);
INSERT INTO public.sub_group (id, primary_group_id, item_id, picture_id, name) VALUES (DEFAULT, 1, 1, 1, 'For your head');
INSERT INTO public."order" (id, user_id, payment_method_id, order_number, order_date, order_status, total_price) VALUES (DEFAULT, 1, 1, '1', '2022-02-14 16:07:36.000000', 'W', 10.00);
INSERT INTO public.delivery (id, delivery_method_id, order_id) VALUES (DEFAULT, 1, 1);
INSERT INTO public.order_item (id, order_id, item_id, quantity, sum) VALUES (DEFAULT, 1, 1, 1, 18.00);

