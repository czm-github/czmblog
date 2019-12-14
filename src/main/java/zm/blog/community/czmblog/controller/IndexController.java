package zm.blog.community.czmblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zm.blog.community.czmblog.dto.PaginationDTO;
import zm.blog.community.czmblog.dto.QuestionDTO;
import zm.blog.community.czmblog.mapper.UserMapper;
import zm.blog.community.czmblog.model.User;
import zm.blog.community.czmblog.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    /*中间层*/
    @Autowired
    private QuestionService questionService;
    /*在服务器重启的时候只要访问首页才能获取到数据库的cookie*/
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDTO paginaction = questionService.list(page,size);
        model.addAttribute("paginaction",paginaction);
        return "index";
    }

}