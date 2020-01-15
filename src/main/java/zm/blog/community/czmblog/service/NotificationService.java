package zm.blog.community.czmblog.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.blog.community.czmblog.dto.NotificationDTO;
import zm.blog.community.czmblog.dto.PaginationDTO;
import zm.blog.community.czmblog.enums.NotificationStatusEnum;
import zm.blog.community.czmblog.enums.NotificationTypeEnum;
import zm.blog.community.czmblog.exception.CustomizeErrorCode;
import zm.blog.community.czmblog.exception.CustomizeException;
import zm.blog.community.czmblog.mapper.NotificationMapper;
import zm.blog.community.czmblog.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId,Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        Integer totalPage;

        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else{
            totalPage = totalCount / size + 1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        //偏移量中的第一个参数，表示从第几条数据开始，第二个参数为size表示展示的数据为固定5
        Integer offset = size * (page - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));


        if (notifications.size() == 0){
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOs = new ArrayList<>();

        for(Notification notification : notifications){
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOs.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOs);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());;
//        查询评论表数据的条数count
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        /*点击时修改是否已读为已读*/
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        /*如果返回的结果为二级评论则查询出父级评论的id*/
        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()){
            return read(notificationDTO.getOuterid(),user);
        }
        return notificationDTO;
    }

}
