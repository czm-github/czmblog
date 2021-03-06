package zm.blog.community.czmblog.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zm.blog.community.czmblog.cache.TagCache;
import zm.blog.community.czmblog.dto.QuestionDTO;
import zm.blog.community.czmblog.mapper.QuestionMapper;
import zm.blog.community.czmblog.mapper.UserMapper;
import zm.blog.community.czmblog.model.Question;
import zm.blog.community.czmblog.model.User;
import zm.blog.community.czmblog.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    public User user = null;

    /*因为request的作用域是整个请求链，所以作为参数可以访问得到cookie*/
    @GetMapping("/publish")
    public String publish(HttpServletRequest request,
                          Model model){
        SelectCookies(request);
        model.addAttribute("tags", TagCache.get());
        return "publish";}

    @PostMapping("/publish")
    public String doPostPublish(
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "id",required = false) Long id,
            Model model,
            HttpServletRequest request){

        model.addAttribute("tag",tag);
        model.addAttribute("description",description);
        model.addAttribute("title",title);
        if(StringUtils.isBlank(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(StringUtils.isBlank(description)){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(StringUtils.isBlank(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        SelectCookies(request);
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入非法标签：" + invalid + "，请选择给定的标签");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
    /*这个方法是判断get和post提交访问此也时user数据库中是否存在,如果查询失败mybatis会返回一个null值给user*/
    public void SelectCookies(HttpServletRequest request){
        user = (User) request.getSession().getAttribute("user");
    }

    /*根据id跳转到问题发布页面*/
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("tag",question.getTag());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("title",question.getTitle());
        model.addAttribute("id",id);
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
}
