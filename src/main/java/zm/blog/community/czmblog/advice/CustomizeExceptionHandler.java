package zm.blog.community.czmblog.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import zm.blog.community.czmblog.dto.ResultDTO;
import zm.blog.community.czmblog.exception.CustomizeErrorCode;
import zm.blog.community.czmblog.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by codedrinker on 2019/5/28.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    /*
    * 这个方法返回的object和ModelAndView不能同时存在
    *
    * */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            if(e instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            }else{
                //处理其他异常的
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try{
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException ioexception){
            }
            return null;
        }else{
            //返回页面
            if(e instanceof CustomizeException){
                //这个是处理id异常的
                model.addAttribute("message",e.getMessage());
            }else{
                //处理其他异常的
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}