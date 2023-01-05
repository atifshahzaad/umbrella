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

CREATE TABLE company
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT null unique,
  phone VARCHAR(20) NOT NULL,
  address_line_1 VARCHAR(255) NOT NULL,
  address_line_2 VARCHAR(255) default NULL,
  city VARCHAR(255) default null,
  country UUID not null,
  logo VARCHAR(255) DEFAULT NULL
)INHERITS (thing);

ALTER TABLE company
  ADD FOREIGN KEY (country) REFERENCES country (id);
 
 CREATE TABLE ou_user
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  middle_name VARCHAR(20) default NULL,
  last_name VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT null unique,
  password VARCHAR(255) NOT NULL,
  profile_picture VARCHAR(255) DEFAULT NULL
)INHERITS (thing);

CREATE TABLE user_detail
(
  id UUID PRIMARY KEY NOT null,
  company UUID not null
)INHERITS (thing);


ALTER TABLE user_detail
  ADD FOREIGN KEY (id) REFERENCES ou_user (id);
 
 ALTER TABLE user_detail
  ADD FOREIGN KEY (company) REFERENCES company (id);

 
 CREATE TABLE role
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  name VARCHAR(255) NOT null unique
)INHERITS (thing);

 CREATE TABLE user_role
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  ou_user UUID NOT null,
  role UUID NOT null
)INHERITS (thing);

ALTER TABLE user_role
  ADD FOREIGN KEY (ou_user) REFERENCES ou_user (id);
 
 ALTER TABLE user_role
  ADD FOREIGN KEY (role) REFERENCES role (id);
 
 
 alter table ou_user add column first_login boolean default true;





