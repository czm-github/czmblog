create table question
(
    id bigint identity(1,1) primary key,
    title varchar(50), --标题
    description text, --内容
    gmt_create bigint, --创建时间
    gmt_modified bigint, --修改时间
    creator bigint, --发布人的id
    comment_count int default 0, --评论数
    view_count int default 0, --浏览数
    like_count int default 0, --点赞数
    tag varchar(256) --标签
);