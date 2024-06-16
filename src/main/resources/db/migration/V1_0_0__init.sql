CREATE SCHEMA IF NOT EXISTS accounts;
CREATE TABLE accounts.t_coin
(
    id serial PRIMARY KEY,
    c_code varchar(5) NOT NULL UNIQUE CHECK(LENGTH(TRIM(c_code)) >= 1),
    c_name varchar(50) NOT NULL UNIQUE CHECK(LENGTH(TRIM(c_name)) >= 3),
    c_price numeric(12,4),
    c_change1h numeric(12,4),
    c_change24h numeric(12,4),
    c_change7d numeric(12,4),
    c_created_at timestamp NOT NULL,
    c_updated_at timestamp NOT NULL,
    c_status varchar(25) NOT NULL
);

CREATE TABLE accounts.t_client
(
    id serial PRIMARY KEY,
    c_name varchar(50) NOT NULL UNIQUE CHECK(LENGTH(TRIM(c_name)) >= 3),
    c_email varchar(50) NOT NULL UNIQUE CHECK(LENGTH(TRIM(c_email)) >= 3),
    c_created_at timestamp NOT NULL,
    c_updated_at timestamp NOT NULL,
    c_status varchar(25) NOT NULL
);

CREATE TABLE accounts.t_account
(
    id serial PRIMARY KEY,
    client_id bigint NOT NULL,
    coin_id bigint NOT NULL,
    c_number varchar(50) NOT NULL UNIQUE CHECK(LENGTH(TRIM(c_number)) >= 3),
    c_quantity numeric(12,4),
    c_price numeric(12,4),
    c_amount numeric(12,4),
    c_created_at timestamp NOT NULL,
    c_updated_at timestamp NOT NULL,
    c_status varchar(25) NOT NULL,

    FOREIGN KEY (client_id) REFERENCES accounts.t_client (id) ON DELETE RESTRICT,
    FOREIGN KEY (coin_id) REFERENCES accounts.t_coin (id) ON DELETE RESTRICT
);