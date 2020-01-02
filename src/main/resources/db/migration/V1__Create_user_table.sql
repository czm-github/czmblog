create table user
(
    id bigint identity(1,1) primary key not null,
    account_id varchar(100),
    name varchar(50),
    token varchar(36),
    gmt_create bigint,
    gmt_modified bigint
);