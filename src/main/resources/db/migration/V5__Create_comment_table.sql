create table comment
(
    id bigint IDENTITY(1,1) PRIMARY KEY NOT NULL,
    parent_id bigint not null,
    type int not null,
    commentator bigint not null,
    gmt_create bigint not null,
    gmt_modified bigint not null,
    comment_count int default 0,
    like_count bigint default 0
);