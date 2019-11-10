create table message 
(
    id              serial PRIMARY KEY,
    content         varchar(250),
    created_at      timestamp with time zone not null default now()
);