package zm.blog.community.czmblog.mapper;

import zm.blog.community.czmblog.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}