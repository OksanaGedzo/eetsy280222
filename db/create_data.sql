-- user_role data
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Administrator');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Moderator');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'Seller');
INSERT INTO public.role (id, description) VALUES (DEFAULT, 'User');

-- user

-- sub_group

-- seller

-- role

-- primary_group

-- picture

-- delivery_method
INSERT INTO public.delivery_method (id, price, name, description, delivery_time) VALUES (DEFAULT, 8, 'Itella', null, '3 päeva');
-- payment_method
INSERT INTO public.payment_method (id, paymant_type) VALUES (DEFAULT, 'Bank');
-- order
INSERT INTO public."order" (id, user_id, payment_method_id, order_number, order_date, order_status, total_price) VALUES (DEFAULT, 1, 1, '1', '2022-02-14 15:11:01.000000', 'W', 10);
-- delivery
INSERT INTO public.delivery (id, delivery_method_id, order_id) VALUES (DEFAULT, 1, 1);