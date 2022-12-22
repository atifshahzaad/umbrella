CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE thing
(
  created_at TIMESTAMP DEFAULT now() NOT NULL,
  updated_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE country
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  name VARCHAR(255) NOT NULL,
  iso3 VARCHAR(3) NOT null unique
)INHERITS (thing);

CREATE TABLE post
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  name VARCHAR(255) NOT NULL
)INHERITS (thing);


CREATE TABLE ou_user
(
  id UUID PRIMARY KEY NOT NULL,
  serial_number serial not null,
  supervisor UUID default null,
  phone VARCHAR(20) default NULL,
  phone2 VARCHAR(20) default NULL,
  geneder VARCHAR(10) CHECK (geneder IN ('Male', 'FeMale')) default null,
  marital_status VARCHAR(10) CHECK (marital_status IN ('Single', 'Married')) default null,
  status VARCHAR(10) CHECK (status IN ('Active', 'InActive')) default null,
  birth_date date default null,
  joining_date TIMESTAMP NOT null default now(),
  profile_picture VARCHAR(255) DEFAULT NULL
)INHERITS (thing);

create table address(
 id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
 ou_user UUID not null,
 address_line_1 varchar(255) not null,
 address_line_2 varchar(255) default null,
 city varchar(255) not null,
 zip varchar(10) not null,
 country UUID not null
)INHERITS (thing);

ALTER TABLE address
  ADD FOREIGN KEY (ou_user) REFERENCES ou_user (id);
 
 ALTER TABLE address
  ADD FOREIGN KEY (country) REFERENCES country (id);











