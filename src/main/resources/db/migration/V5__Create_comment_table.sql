create table comment
(
    id bigint IDENTITY(1,1) PRIMARY KEY NOT NULL,
    parent_id bigint not null, --问题的id
    type int not null, --回复的类型（1:回复的问题，2:回复问题的评论）
    commentator bigint not null, --问题发布者的id
    gmt_create bigint not null, --创建时间
    gmt_modified bigint not null, --修改时间
    comment_count int default 0, --评论数
    like_count bigint default 0 --点赞数
);