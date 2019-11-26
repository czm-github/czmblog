package zm.blog.community.czmblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublishCotroller {
    @GetMapping("/publish")
    public String publish(){
        return "publish";}
}
