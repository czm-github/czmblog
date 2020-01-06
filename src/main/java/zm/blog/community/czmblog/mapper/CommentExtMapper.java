package zm.blog.community.czmblog.mapper;

import zm.blog.community.czmblog.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}