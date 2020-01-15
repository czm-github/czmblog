create table user
(
    id bigint identity(1,1) primary key not null,
    account_id varchar(100), --github id
    name varchar(50), --github 名称
    token varchar(36), --github token
    gmt_create bigint, -- 创建时间
    gmt_modified bigint --修改时间
);