package zm.blog.community.czmblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zm.blog.community.czmblog.dto.QuestionDTO;
import zm.blog.community.czmblog.exception.CustomizeErrorCode;
import zm.blog.community.czmblog.exception.CustomizeException;
import zm.blog.community.czmblog.service.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id,
                           Model model){
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        //累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}