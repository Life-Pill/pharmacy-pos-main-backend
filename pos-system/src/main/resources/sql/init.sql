-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP SEQUENCE public.employer_bankdetails_employer_bank_details_id_seq;

CREATE SEQUENCE public.employer_bankdetails_employer_bank_details_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.item_category_category_id_seq;

CREATE SEQUENCE public.item_category_category_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;-- public.branch definition

-- Drop table

-- DROP TABLE public.branch;

CREATE TABLE public.branch (
                               branch_id int8 NOT NULL,
                               branch_address varchar(100) NOT NULL,
                               branch_contact varchar(12) NULL,
                               branch_created_by varchar(255) NULL,
                               branch_created_on varchar(255) NULL,
                               branch_description varchar(100) NULL,
                               branch_email varchar(50) NULL,
                               branch_fax varchar(12) NULL,
                               branch_image oid NULL,
                               branch_location varchar(100) NULL,
                               branch_name varchar(20) NOT NULL,
                               branch_profile_image_url varchar(255) NULL,
                               branch_status bool DEFAULT false NULL,
                               CONSTRAINT branch_pkey PRIMARY KEY (branch_id)
);


-- public.employer_bankdetails definition

-- Drop table

-- DROP TABLE public.employer_bankdetails;

CREATE TABLE public.employer_bankdetails (
                                             employer_bank_details_id bigserial NOT NULL,
                                             bank_account_number varchar(255) NULL,
                                             bank_branch_name varchar(255) NULL,
                                             bank_name varchar(255) NULL,
                                             employer_description varchar(255) NULL,
                                             employeer_id int8 NULL,
                                             cashier_monthly_payment float8 NULL,
                                             payment_status bool NULL,
                                             CONSTRAINT employer_bankdetails_pkey PRIMARY KEY (employer_bank_details_id)
);


-- public.item_category definition

-- Drop table

-- DROP TABLE public.item_category;

CREATE TABLE public.item_category (
                                      category_id bigserial NOT NULL,
                                      category_description varchar(100) NULL,
                                      category_image varchar(255) NULL,
                                      category_name varchar(100) NOT NULL,
                                      CONSTRAINT item_category_pkey PRIMARY KEY (category_id)
);


-- public.supplier_company definition

-- Drop table

-- DROP TABLE public.supplier_company;

CREATE TABLE public.supplier_company (
                                         company_id int8 NOT NULL,
                                         created_at timestamp NULL,
                                         created_by varchar(255) NULL,
                                         updated_at timestamp NULL,
                                         updated_by varchar(255) NULL,
                                         company_account_number varchar(255) NULL,
                                         company_address varchar(100) NULL,
                                         company_bank varchar(255) NULL,
                                         company_contact varchar(12) NULL,
                                         company_description varchar(255) NULL,
                                         company_email varchar(50) NULL,
                                         company_name varchar(100) NULL,
                                         company_status varchar(255) NULL,
                                         company_image varchar(255) NULL,
                                         CONSTRAINT supplier_company_pkey PRIMARY KEY (company_id)
);


-- public.employer definition

-- Drop table

-- DROP TABLE public.employer;

CREATE TABLE public.employer (
                                 employer_id int8 NOT NULL,
                                 employer_date_of_birth date NULL,
                                 employer_address varchar(100) NULL,
                                 employer_email varchar(50) NULL,
                                 employer_first_name varchar(50) NOT NULL,
                                 employer_last_name varchar(50) NULL,
                                 employer_nic varchar(12) NOT NULL,
                                 employer_nic_name varchar(50) NULL,
                                 employer_password varchar(255) NOT NULL,
                                 employer_phone varchar(12) NULL,
                                 employer_sallary float8 NULL,
                                 gender varchar(10) NOT NULL,
                                 is_active bool DEFAULT false NULL,
                                 pin int4 NULL,
                                 profile_image oid NULL,
                                 profile_image_url varchar(255) NULL,
                                 "role" varchar(15) NOT NULL,
                                 brach_id int8 NOT NULL,
                                 employer_bank_details_id int8 NULL,
                                 CONSTRAINT employer_pkey PRIMARY KEY (employer_id),
                                 CONSTRAINT uk_fkokx2ge291s0fgeij3jj5hrx UNIQUE (employer_email),
                                 CONSTRAINT fkhcwif40g8loacu6celn8e8l5n FOREIGN KEY (brach_id) REFERENCES public.branch(branch_id),
                                 CONSTRAINT fkif5ku88wg42y3eqhllnyvkk9f FOREIGN KEY (employer_bank_details_id) REFERENCES public.employer_bankdetails(employer_bank_details_id)
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
                               order_id int8 NOT NULL,
                               branch_id int8 NOT NULL,
                               order_date timestamp NULL,
                               total float8 NOT NULL,
                               employer_id int8 NOT NULL,
                               CONSTRAINT orders_pkey PRIMARY KEY (order_id),
                               CONSTRAINT fk80na4o2lg87jkic5q11okqfv9 FOREIGN KEY (employer_id) REFERENCES public.employer(employer_id)
);


-- public.payment_details definition

-- Drop table

-- DROP TABLE public.payment_details;

CREATE TABLE public.payment_details (
                                        payment_id int8 NOT NULL,
                                        paid_amount float8 NULL,
                                        payment_amount float8 NULL,
                                        payment_date timestamp NULL,
                                        payment_discount float8 NULL,
                                        payment_method varchar(255) NULL,
                                        payment_notes varchar(255) NULL,
                                        order_id int8 NOT NULL,
                                        CONSTRAINT payment_details_pkey PRIMARY KEY (payment_id),
                                        CONSTRAINT fk34yjcjptgtt05syk6x0t8s35b FOREIGN KEY (order_id) REFERENCES public.orders(order_id)
);


-- public.supplier definition

-- Drop table

-- DROP TABLE public.supplier;

CREATE TABLE public.supplier (
                                 supplier_id int8 NOT NULL,
                                 created_at timestamp NULL,
                                 created_by varchar(255) NULL,
                                 updated_at timestamp NULL,
                                 updated_by varchar(255) NULL,
                                 supplier_address varchar(100) NULL,
                                 supplier_description varchar(100) NULL,
                                 supplier_email varchar(50) NULL,
                                 supplier_image varchar(255) NULL,
                                 supplier_name varchar(100) NULL,
                                 supplier_phone varchar(12) NULL,
                                 supplier_rating varchar(255) NULL,
                                 company_id int8 NULL,
                                 CONSTRAINT supplier_pkey PRIMARY KEY (supplier_id),
                                 CONSTRAINT fk4xppoa5x686apqupj6f87a4ov FOREIGN KEY (company_id) REFERENCES public.supplier_company(company_id)
);


-- public.item definition

-- Drop table

-- DROP TABLE public.item;

CREATE TABLE public.item (
                             item_id int8 NOT NULL,
                             branch_id int8 NULL,
                             date_created timestamp NULL,
                             discounted_percentage float8 NULL,
                             discounted_price float8 NULL,
                             expire_date timestamp NULL,
                             is_discounted bool DEFAULT false NULL,
                             is_free_issued bool DEFAULT false NULL,
                             is_special_condition bool DEFAULT false NULL,
                             item_barcode varchar(20) NOT NULL,
                             item_description varchar(100) NULL,
                             item_image varchar(255) NULL,
                             item_manufacture varchar(100) NULL,
                             item_name varchar(100) NOT NULL,
                             item_quantity float8 NOT NULL,
                             last_updated_date timestamp NULL,
                             manufacture_date timestamp NULL,
                             measuring_unit_type varchar(20) NULL,
                             purchase_date timestamp NULL,
                             rack_number varchar(20) NULL,
                             selling_price float8 NOT NULL,
                             is_stock bool DEFAULT false NULL,
                             supplier_price float8 NOT NULL,
                             supply_date timestamp NULL,
                             warehouse_name varchar(100) NULL,
                             warranty_period varchar(255) NULL,
                             category_id int8 NOT NULL,
                             supplier_id int8 NOT NULL,
                             CONSTRAINT item_pkey PRIMARY KEY (item_id),
                             CONSTRAINT fk1rysna9so6qhvewuv59vest5f FOREIGN KEY (category_id) REFERENCES public.item_category(category_id),
                             CONSTRAINT fkcjes46ncuefgrkgt6ib0oo2bb FOREIGN KEY (supplier_id) REFERENCES public.supplier(supplier_id)
);


-- public.order_details definition

-- Drop table

-- DROP TABLE public.order_details;

CREATE TABLE public.order_details (
                                      order_details_id int4 NOT NULL,
                                      amount float8 NOT NULL,
                                      "name" varchar(100) NOT NULL,
                                      item_id int8 NOT NULL,
                                      order_id int8 NOT NULL,
                                      CONSTRAINT order_details_pkey PRIMARY KEY (order_details_id),
                                      CONSTRAINT fk7nax0e2omxncw5gjo2hu65rip FOREIGN KEY (item_id) REFERENCES public.item(item_id),
                                      CONSTRAINT fkjyu2qbqt8gnvno9oe9j2s2ldk FOREIGN KEY (order_id) REFERENCES public.orders(order_id)
);


-- public.branch_item definition

-- Drop table

-- DROP TABLE public.branch_item;

CREATE TABLE public.branch_item (
                                    branch_id int8 NOT NULL,
                                    item_id int8 NOT NULL,
                                    CONSTRAINT branch_item_pkey PRIMARY KEY (branch_id, item_id),
                                    CONSTRAINT fk2vcyplni0ccwk4b0xte9l0727 FOREIGN KEY (item_id) REFERENCES public.item(item_id),
                                    CONSTRAINT fk9ctfcags2nh1ka0cedlyu25nn FOREIGN KEY (branch_id) REFERENCES public.branch(branch_id)
);
