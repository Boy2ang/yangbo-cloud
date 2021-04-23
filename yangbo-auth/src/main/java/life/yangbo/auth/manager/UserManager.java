package life.yangbo.auth.manager;

import life.yangbo.auth.mapper.MenuMapper;
import life.yangbo.auth.mapper.UserMapper;
import life.yangbo.common.entity.system.Menu;
import life.yangbo.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户相关的业务方法
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        /*List<String> perms = new ArrayList<>();
        for (Menu m : userPermissions) {
            perms.add(m.getPerms());
        }
        return StringUtils.join(perms, ",");*/
        // java8 Stream简化 结果示例：user:add,user:update,user:delete
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
