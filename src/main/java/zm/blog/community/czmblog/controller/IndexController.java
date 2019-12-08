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

    @Autowired
    private UserMapper userMapper;
    /*中间层*/
    @Autowired
    private QuestionService questionService;
    /*在服务器重启的时候只要访问首页才能获取到数据库的cookie*/
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        /*因为这个cookies如果被删除掉了就会报null异常，所以需要添加一个判断让用户重新点击登录*/
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginaction = questionService.list(page,size);
        model.addAttribute("paginaction",paginaction);
        return "index";
    }

}