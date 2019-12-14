package zm.blog.community.czmblog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;
import zm.blog.community.czmblog.dto.PaginationDTO;
import zm.blog.community.czmblog.dto.QuestionDTO;
import zm.blog.community.czmblog.mapper.QuestionMapper;
import zm.blog.community.czmblog.mapper.UserMapper;
import zm.blog.community.czmblog.model.Question;
import zm.blog.community.czmblog.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Service : 中间层，用来同时传输user表和question表的一个层
 * 因为user表和question表的mapper都说用来针对一个特定的表的，所以出现了中间层
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();

        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else{
            totalPage = totalCount / size + 1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        //偏移量中的第一个参数，表示从第几条数据开始，第二个参数为size表示展示的数据为固定5
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        return paginationDTO;
    }
    public PaginationDTO list(Integer userId,Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else{
            totalPage = totalCount / size + 1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        //偏移量中的第一个参数，表示从第几条数据开始，第二个参数为size表示展示的数据为固定5
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
