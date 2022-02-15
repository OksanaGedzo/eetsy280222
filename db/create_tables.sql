-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-02-14 13:24:33.528

-- tables
-- Table: contact
CREATE TABLE contact (
                         id serial  NOT NULL,
                         first_name varchar(100)  NOT NULL,
                         last_name varchar(100)  NOT NULL,
                         address varchar(250)  NULL,
                         post_index varchar(10)  NULL,
                         country varchar(50)  NULL,
                         phone_number varchar(20)  NULL,
                         email varchar(50)  NOT NULL,
                         CONSTRAINT contact_pk PRIMARY KEY (id)
);

-- Table: delivery
CREATE TABLE delivery (
                          id serial  NOT NULL,
                          delivery_method_id int  NOT NULL,
                          order_id int  NOT NULL,
                          CONSTRAINT delivery_pk PRIMARY KEY (id)
);

-- Table: delivery_method
CREATE TABLE delivery_method (
                                 id serial  NOT NULL,
                                 price decimal(10,2)  NOT NULL,
                                 name varchar(50)  NOT NULL,
                                 description varchar(100)  NULL,
                                 delivery_time varchar(50)  NOT NULL,
                                 CONSTRAINT delivery_method_pk PRIMARY KEY (id)
);

-- Table: item
CREATE TABLE item (
                      id serial  NOT NULL,
                      seller_id int  NOT NULL,
                      name varchar(100)  NOT NULL,
                      price decimal(10,2)  NOT NULL,
                      description varchar(1000)  NULL,
                      CONSTRAINT item_pk PRIMARY KEY (id)
);

CREATE INDEX item_idx_1 on item (seller_id ASC);

-- Table: item_picture
CREATE TABLE item_picture (
                              id serial  NOT NULL,
                              item_id int  NOT NULL,
                              picture_id int  NOT NULL,
                              CONSTRAINT item_picture_pk PRIMARY KEY (id)
);

-- Table: order
CREATE TABLE "order" (
                         id serial  NOT NULL,
                         user_id int  NOT NULL,
                         payment_method_id int  NOT NULL,
                         order_number varchar(10)  NOT NULL,
                         order_date timestamp  NOT NULL,
                         order_status char(1)  NOT NULL,
                         total_price decimal(10,2)  NOT NULL,
                         CONSTRAINT order_pk PRIMARY KEY (id)
);

-- Table: order_item
CREATE TABLE order_item (
                            id serial  NOT NULL,
                            order_id int  NOT NULL,
                            item_id int  NOT NULL,
                            quantity int  NOT NULL,
                            sum decimal(10,2)  NOT NULL,
                            CONSTRAINT order_item_pk PRIMARY KEY (id)
);

-- Table: payment_method
CREATE TABLE payment_method (
                                id serial  NOT NULL,
                                paymant_type varchar(20)  NOT NULL,
                                CONSTRAINT payment_method_pk PRIMARY KEY (id)
);

-- Table: picture
CREATE TABLE picture (
                         id serial  NOT NULL,
                         data bytea  NOT NULL,
                         CONSTRAINT picture_pk PRIMARY KEY (id)
);

-- Table: primary_group
CREATE TABLE primary_group (
                               id serial  NOT NULL,
                               picture_id int  NOT NULL,
                               name varchar(50)  NOT NULL,
                               CONSTRAINT primary_group_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
                      id serial  NOT NULL,
                      description varchar(50)  NOT NULL,
                      CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: seller
CREATE TABLE seller (
                        id serial  NOT NULL,
                        logo_picture_id int  NULL,
                        name varchar(100)  NOT NULL,
                        email varchar(50)  NOT NULL,
                        telephone varchar(20)  NULL,
                        index varchar(10)  NOT NULL,
                        aadress varchar(100)  NOT NULL,
                        website varchar(100)  NULL,
                        validated boolean  NOT NULL DEFAULT false,
                        CONSTRAINT seller_pk PRIMARY KEY (id)
);

-- Table: sub_group
CREATE TABLE sub_group (
                           id serial  NOT NULL,
                           primary_group_id int  NOT NULL,
                           item_id int  NOT NULL,
                           picture_id int  NOT NULL,
                           name varchar(50)  NOT NULL,
                           CONSTRAINT sub_group_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
                        id serial  NOT NULL,
                        contact_id int  NOT NULL,
                        seller_id int  NULL,
                        user_name varchar(50)  NOT NULL,
                        password varchar(50)  NOT NULL,
                        CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: user_role
CREATE TABLE user_role (
                           id serial  NOT NULL,
                           user_id int  NOT NULL,
                           role_id int  NOT NULL,
                           CONSTRAINT user_role_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: delivery_delivery_method (table: delivery)
ALTER TABLE delivery ADD CONSTRAINT delivery_delivery_method
    FOREIGN KEY (delivery_method_id)
        REFERENCES delivery_method (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: delivery_order (table: delivery)
ALTER TABLE delivery ADD CONSTRAINT delivery_order
    FOREIGN KEY (order_id)
        REFERENCES "order" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: item_picture_item (table: item_picture)
ALTER TABLE item_picture ADD CONSTRAINT item_picture_item
    FOREIGN KEY (item_id)
        REFERENCES item (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: item_picture_picture (table: item_picture)
ALTER TABLE item_picture ADD CONSTRAINT item_picture_picture
    FOREIGN KEY (picture_id)
        REFERENCES picture (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: item_seller (table: item)
ALTER TABLE item ADD CONSTRAINT item_seller
    FOREIGN KEY (seller_id)
        REFERENCES seller (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_item_item (table: order_item)
ALTER TABLE order_item ADD CONSTRAINT order_item_item
    FOREIGN KEY (item_id)
        REFERENCES item (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_item_order (table: order_item)
ALTER TABLE order_item ADD CONSTRAINT order_item_order
    FOREIGN KEY (order_id)
        REFERENCES "order" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_payment (table: order)
ALTER TABLE "order" ADD CONSTRAINT order_payment
    FOREIGN KEY (payment_method_id)
        REFERENCES payment_method (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_user (table: order)
ALTER TABLE "order" ADD CONSTRAINT order_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: primary_group_picture (table: primary_group)
ALTER TABLE primary_group ADD CONSTRAINT primary_group_picture
    FOREIGN KEY (picture_id)
        REFERENCES picture (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: seller_picture (table: seller)
ALTER TABLE seller ADD CONSTRAINT seller_picture
    FOREIGN KEY (logo_picture_id)
        REFERENCES picture (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: sub_group_item (table: sub_group)
ALTER TABLE sub_group ADD CONSTRAINT sub_group_item
    FOREIGN KEY (item_id)
        REFERENCES item (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: sub_group_picture (table: sub_group)
ALTER TABLE sub_group ADD CONSTRAINT sub_group_picture
    FOREIGN KEY (picture_id)
        REFERENCES picture (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: sub_group_primary_group (table: sub_group)
ALTER TABLE sub_group ADD CONSTRAINT sub_group_primary_group
    FOREIGN KEY (primary_group_id)
        REFERENCES primary_group (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_contact (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_contact
    FOREIGN KEY (contact_id)
        REFERENCES contact (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_role_role (table: user_role)
ALTER TABLE user_role ADD CONSTRAINT user_role_role
    FOREIGN KEY (role_id)
        REFERENCES role (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_role_user (table: user_role)
ALTER TABLE user_role ADD CONSTRAINT user_role_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_seller (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_seller
    FOREIGN KEY (seller_id)
        REFERENCES seller (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.

