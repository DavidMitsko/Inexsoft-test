create table bank
(
    uuid                      varchar(255) not null
        constraint bank_pk
            primary key,
    name                      varchar(255) unique,
    individuals_commission    integer,
    legal_entities_commission integer
);

create table client
(
    uuid        varchar(255) not null
        constraint client_pk
            primary key,
    name        varchar(255),
    surname     varchar(255),
    age         integer,
    client_type varchar(255)
);

create table account
(
    uuid        varchar(255) not null
        constraint account_pk
            primary key,
    client_uuid varchar(255),
    bank_uuid   varchar(255),
    currency    varchar(255),
    balance     numeric
);

create table transaction
(
    uuid                   varchar(255) not null
        constraint transaction_pk
            primary key,
    transaction_time       timestamp,
    sender_account_uuid    varchar(255),
    recipient_account_uuid varchar(255),
    amount                 numeric,
    commission             numeric
);

create table exchange_rate
(
    uuid            varchar(255) not null
        constraint exchange_rate_pk
            primary key,
    first_currency  varchar(255),
    second_currency varchar(255),
    rate            numeric
);

insert into exchange_rate (uuid, first_currency, second_currency, rate)
values ('24f5d9bc-7f65-4a94-8eed-03108353bd5c', 'USD', 'EUR', 0.8);

insert into exchange_rate (uuid, first_currency, second_currency, rate)
values ('ade03516-8136-48b7-801d-508099692b44', 'USD', 'BYN', 3.6);

insert into exchange_rate (uuid, first_currency, second_currency, rate)
values ('d085572e-ffdd-4618-b75c-c0eddf2e4d89', 'EUR', 'BYN', 3.8);