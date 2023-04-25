package stu.cmq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stu.cmq.properties.SecurityProperties;
import stu.cmq.service.OnlineUserService;
import stu.cmq.service.dto.JwtUserDTO;
import stu.cmq.service.dto.OnlineUserDTO;
import stu.cmq.utils.PageUtil;
import stu.cmq.utils.RedisUtils;
import stu.cmq.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author kamifeng
 * @date 23:42
 */

@Service
@Slf4j
public class OnlineUserServiceImpl implements OnlineUserService {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;


    public OnlineUserServiceImpl(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    @Override
    public void save(JwtUserDTO jwtUserDTO, String token) {
        OnlineUserDTO onlineUserDTO = null;
        try {
            onlineUserDTO = new OnlineUserDTO(jwtUserDTO.getUsername(),jwtUserDTO.getUser().getNickname(), token, LocalDateTime.now());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDTO, properties.getTokenValidityInSeconds()/1000);
    }

    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key){
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     * @param token /
     */
    @Override
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    @Override
    public void checkLoginOnUser(String username, String igoreToken) {
        List<OnlineUserDTO> onlineUserDTOs = getAll(username);

        if(onlineUserDTOs ==null || onlineUserDTOs.isEmpty()){
            return;
        }
        for(OnlineUserDTO onlineUserDTO : onlineUserDTOs){
            if(onlineUserDTO.getUsername().equals(username)){
                try {
                    String token =onlineUserDTO.getToken();
                    if(StringUtils.isNotBlank(igoreToken)&&!igoreToken.equals(token)){
                        this.kickOut(token);
                    }else if(StringUtils.isBlank(igoreToken)){
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error",e);
                }
            }
        }
    }

    @Override
    public OnlineUserDTO getOne(String key) {
        return (OnlineUserDTO) redisUtils.get(key);
    }

    /**
     * 查询全部数据
     * @param filter /
     * @param pageable /
     * @return /
     */
    public Map<String,Object> getAll(String filter, Pageable pageable){
        List<OnlineUserDTO> onlineUserDtos = getAll(filter);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(),pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size()
        );
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<OnlineUserDTO> getAll(String filter){
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDTO> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDTO onlineUserDto = (OnlineUserDTO) redisUtils.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(onlineUserDto.toString().contains(filter)){
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }
}
