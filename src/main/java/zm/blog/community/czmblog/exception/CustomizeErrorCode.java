package zm.blog.community.czmblog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不见了"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或答案进行回复"),
    NO_LOGIN(2003,"当前操作需要登录"),
    SYS_ERROR(2004,"服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你操作的评论不存在了"),
    COMMENT_IS_EMPTY(2007,"评论内容不能为空"),
    INVALID_INPUT(2011, "非法输入"),
    NOTIFICATION_NOT_FOUND(2012,"找不到该评论"),
    READ_NOTIFICATION_FAIL(2013,"读取评论失败"),
    FILE_UPLOAD_FAIL(2014,"图片上传失败"),
    ;
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
