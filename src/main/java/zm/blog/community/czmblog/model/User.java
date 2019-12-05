package zm.blog.community.czmblog.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
