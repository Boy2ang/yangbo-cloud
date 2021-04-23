package life.yangbo.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import life.yangbo.common.entity.system.SystemUser;


public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    SystemUser findByName(String username);
}
