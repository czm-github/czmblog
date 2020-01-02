create table question
(
    id bigint identity(1,1) primary key,
    title varchar(50),
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator bigint,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
);