package zm.blog.community.czmblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zm.blog.community.czmblog.dto.PaginationDTO;
import zm.blog.community.czmblog.service.QuestionService;

@Controller
public class IndexController {

    /*中间层*/
    @Autowired
    private QuestionService questionService;
    /*在服务器重启的时候只要访问首页才能获取到数据库的cookie*/
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search){

        PaginationDTO paginaction = questionService.list(search, page, size);
        model.addAttribute("paginaction",paginaction);
        model.addAttribute("search",search);
        return "index";
    }

}