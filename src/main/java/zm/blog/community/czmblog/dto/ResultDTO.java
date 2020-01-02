package zm.blog.community.czmblog.dto;

import lombok.Data;
import org.springframework.web.servlet.ModelAndView;
import zm.blog.community.czmblog.exception.CustomizeErrorCode;
import zm.blog.community.czmblog.exception.CustomizeException;

@Data
public class ResultDTO {
    private Integer code;
    private String message;


    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        return errorOf(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }


}
