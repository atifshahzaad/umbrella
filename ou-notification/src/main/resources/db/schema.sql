CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE thing
(
  created_at TIMESTAMP DEFAULT now() NOT NULL,
  updated_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE email
(
  id UUID PRIMARY KEY DEFAULT (uuid_generate_v1()) NOT NULL,
  type VARCHAR(20) NOT NULL,
  to_email VARCHAR(255) NOT null,
  to_name VARCHAR(255) NOT null,
  data text NOT NULL,
  completed boolean default false
)INHERITS (thing);