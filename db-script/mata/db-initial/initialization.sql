--create super user--
CREATE ROLE mata LOGIN ENCRYPTED PASSWORD 'md5c84432dc81d6b11fe6104b13acfd9e09'
SUPERUSER CREATEDB CREATEROLE REPLICATION
VALID UNTIL 'infinity';
GRANT pg_signal_backend TO mata;

--create DB----
CREATE DATABASE "mata-db"
WITH ENCODING='UTF8'
OWNER=mata
CONNECTION LIMIT=-1;

--create schema---
CREATE SCHEMA core
  AUTHORIZATION mata;

CREATE TABLE core.mata_corporate_user(
  corprate_user_id serial  PRIMARY KEY not null,
  corportae_user_name_fa varchar(50) not null,
  corportae_user_name_en varchar(50) not null,
  company_id varchar(50),
  status bigint not null,
  expire_date timestamp not null,
  city_fa varchar(100) not null,
  city_en varchar(100),
  address_fa varchar(500) not null,
  address_en varchar(500),
  phone_number VARCHAR(30) NOT NULL,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

  -- mata_user--
CREATE TABLE  core.mata_user(
  user_id serial PRIMARY KEY not null,
  first_name_fa varchar(50) not null,
  last_name_fa varchar(50) not null,
  first_name_en varchar(50) not null,
  last_name_en varchar(50) not null,
  national_code varchar(50) UNIQUE,
  phone_number varchar(50) not null,
  email varchar(150),
  expire_date timestamp not null,
  birth_date timestamp not null,
  status bigint not null,
  user_type bigint not null,
  corporate_user_id bigint REFERENCES core.mata_corporate_user,
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

-- mata_authentication--
CREATE TABLE  core.mata_authentication(
  authentication_id serial PRIMARY KEY not null,
  password varchar(200) not null,
  user_name varchar(50) not null UNIQUE ,
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null,
  user_id bigint references core.mata_user not null
);



-- mata_user_address--
CREATE TABLE  core.mata_user_address(
  address_id serial PRIMARY KEY not null,
  address_fa varchar (500) not null,
  address_en varchar(500),
  city_fa varchar(50) not null,
  city_en varchar(50),
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null,
  user_id bigint references core.mata_user not null
);

-- mata_role--
CREATE TABLE  core.mata_role(
  role_id BIGINT PRIMARY KEY not null,
  role_name varchar(50) not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null,
  cretae_date timestamp not null
);

-- mata_user_role--
CREATE TABLE  core.mata_user_role(
  user_role_id serial PRIMARY KEY not null,
  user_id bigint references core.mata_user not null,
  role_id bigint references core.mata_role not null,
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);


--mata_signup_request--
CREATE TABLE  core.mata_signup_request(
  signup_request_id serial PRIMARY KEY not null,
  first_name_fa varchar(50) not null,
  last_name_fa varchar(50) not null,
  phone_number varchar(50) not null ,
  email varchar(150),
  learn_method varchar(100),
  description varchar(300),
  create_date timestamp not null,
  signup_request_status bigint not null,
  investigation_user_id bigint
);

 --core.mata_order_category--
 CREATE TABLE  core.mata_order_category(
order_category_id bigint PRIMARY KEY not null,
title_fa varchar(50),
title_en varchar(50),
parent_id bigint,
create_date timestamp not null,
create_user_name varchar(50) not null,
update_date timestamp not null,
update_user_name varchar(50) not null
);

 --core.mata_order--
 CREATE TABLE  core.mata_order(
  order_id serial PRIMARY KEY not null,
  user_id bigint references core.mata_user not null,
  category_id bigint references core.mata_order_category not null,
  amount bigint ,
  order_status bigint,
  description varchar(1000),
  address_id  bigint references core.mata_user_address not null,
  order_number VARCHAR(20) not null UNIQUE,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

 --core.mata_message--
 CREATE TABLE  core.mata_message(
  message_id serial PRIMARY KEY not null,
  header varchar(100) not null,
  description varchar(1000) not null,
  sender_user_id bigint not null,
  receiver_user_id bigint not null,
  seen_status bigint not null,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

--core.mata_offer--
CREATE TABLE  core.mata_offer(
  offer_id serial PRIMARY KEY not null,
  header varchar(100) not null,
  description varchar(1000),
  offer_expire_date timestamp not null,
  offer_available_count bigint,
  offer_sender_user_id bigint not null,
  offer_status bigint not null,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

--core.mata_user_offer--
create TABLE core.mata_user_offer(
  user_offer_id serial PRIMARY KEY not null,
  offer_id BIGINT references core.mata_offer NOT NULL ,
  user_id BIGINT references core.mata_user not null,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

ALTER TABLE core.mata_user_offer
  ADD CONSTRAINT mata_user_offer_unique UNIQUE (offer_id,user_id);

--core.mata_admin_notification_type--
create TABLE core.mata_admin_notification_type(
  notification_type_id serial PRIMARY KEY not null,
  notification_type BIGINT not NULL UNIQUE,
  notification_template varchar(1000) not null,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);


--core.mata_admin_notification--
create TABLE core.mata_admin_notification(
  notification_id serial PRIMARY KEY not null,
  notification_type BIGINT not null,
  expire_date timestamp not null,
  notification_text varchar(1000),
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);


--core.mata_user_authorization_code--
create TABLE core.mata_user_authorization_code(
  id serial PRIMARY KEY not null,
  user_id BIGINT references core.mata_user not null,
  authorization_code VARCHAR(20) NOT NULL,
  expire_date timestamp not null,
  used BOOLEAN NOT NULL,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

--core.mata_notification_message_template--
create TABLE core.mata_notification_message_template(
  id BIGINT PRIMARY KEY,
  msg_template VARCHAR(1000) NOT NULL,
  create_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

INSERT INTO core.mata_notification_message_template (id, msg_template, create_date, create_user_name, update_date, update_user_name)
    VALUES (1,'کد ورود شما به پنل ماتا: authorizationCode','2017-12-11','system','2017-12-11','system');

CREATE TABLE core.mata_user_credit (
  user_id          BIGINT PRIMARY KEY references core.mata_user not null,
  balance          BIGINT      NOT NULL,
  create_date      TIMESTAMP   NOT NULL,
  create_user_name VARCHAR(50) NOT NULL,
  update_date      TIMESTAMP   NOT NULL,
  update_user_name VARCHAR(50) NOT NULL
);

CREATE TABLE core.mata_user_transaction (
  transaction_id SERIAL PRIMARY KEY NOT NULL,
  user_id BIGINT references core.mata_user not null,
  order_id BIGINT references core.mata_order,
  amount BIGINT NOT NULL,
  transaction_status BIGINT NOT NULL ,
  transaction_type BIGINT NOT NULL ,
  transaction_date TIMESTAMP NOT NULL,
  transaction_number VARCHAR(200) ,
  transaction_followup_number VARCHAR(200),
  last_balance     BIGINT NOT NULL,
  is_debit         BOOLEAN NOT NULL,
  create_date      TIMESTAMP   NOT NULL,
  create_user_name VARCHAR(50) NOT NULL,
  update_date      TIMESTAMP   NOT NULL,
  update_user_name VARCHAR(50) NOT NULL
);

--core.mata_order_log--
CREATE TABLE  core.mata_order_log(
  log_id serial PRIMARY KEY not null,
  order_id BIGINT references core.mata_order not null,
  user_id BIGINT references core.mata_user not null,
  log_title VARCHAR(50) ,
  log_content VARCHAR(1000),
  writer_first_name_fa VARCHAR (500) NOT NULL ,
  writer_last_name_fa VARCHAR (500) NOT NULL ,
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

--core.mata_order_file--
CREATE TABLE  core.mata_order_file(
  file_id serial PRIMARY KEY not null,
  file_order_id BIGINT references core.mata_order not null,
  file_name VARCHAR(50) NOT NULL ,
  cretae_date timestamp not null,
  create_user_name varchar(50) not null,
  update_date timestamp not null,
  update_user_name varchar(50) not null
);

INSERT INTO core.mata_admin_notification_type (notification_type_id, notification_type, notification_template, create_date, create_user_name, update_date, update_user_name)
VALUES (702, 702 , 'سفارش جدید توسط کاربر {param1} ثبت شده است.' ,'2017-12-11' ,'system','2017-12-11','system');

INSERT INTO core.mata_admin_notification_type (notification_type_id, notification_type, notification_template, create_date, create_user_name, update_date, update_user_name)
VALUES (701, 701 , 'کاربر {param1} با کد ملی {param2} درخواست تغییر اطلاعات شخصی خود را دارد.' ,'2017-12-11' ,'system','2017-12-11','system');

INSERT INTO core.mata_admin_notification_type (notification_type_id, notification_type, notification_template, create_date, create_user_name, update_date, update_user_name)
VALUES (700, 700 , 'درخواست ثبت نام جدید با نام {param1} و شماره تماس {param2} ثبت شده است' ,'2017-12-11' ,'system','2017-12-11','system');

ALTER TABLE core.mata_user_address
  ADD COLUMN address_status BIGINT NOT NULL;

INSERT INTO core.mata_notification_message_template(id, msg_template, create_date, create_user_name, update_date,update_user_name)
VALUES (2, 'سفارش شما با موفقیت ثبت شد.',current_timestamp,'1122334455', current_timestamp,'1122334455');

INSERT INTO core.mata_notification_message_template(id, msg_template, create_date, create_user_name, update_date,update_user_name)
VALUES (3, 'سفارش شما انجام شد.',current_timestamp,'1122334455', current_timestamp,'1122334455');

INSERT INTO core.mata_notification_message_template(id, msg_template, create_date, create_user_name, update_date,update_user_name)
VALUES (4, 'سفارش شما ارسال شد.',current_timestamp,'1122334455', current_timestamp,'1122334455');

INSERT INTO core.mata_notification_message_template(id, msg_template, create_date, create_user_name, update_date,update_user_name)
VALUES (5, 'سفارش شما تایید شد',current_timestamp,'1122334455', current_timestamp,'1122334455');

INSERT INTO core.mata_notification_message_template(id, msg_template, create_date, create_user_name, update_date,update_user_name)
VALUES (6, 'سفارش شما لغو شد',current_timestamp,'1122334455', current_timestamp,'1122334455');