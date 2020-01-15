/*通知*/
create table notification
(
    id bigint auto_increment primary key,
    notifier bigint not null,  --祖先的id
    receiver bigint not null,  --父亲的id
    outerid bigint not null, --父级问题的id
    type int not null, --通知的类型（回复的问题还是评论）
    gmt_create bigint not null, --创建时间
    status int default 0 not null --是否已读
);