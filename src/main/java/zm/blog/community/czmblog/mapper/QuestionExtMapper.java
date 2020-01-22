package zm.blog.community.czmblog.mapper;

import zm.blog.community.czmblog.dto.QuestionQueryDTO;
import zm.blog.community.czmblog.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}