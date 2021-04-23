package life.yangbo.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import life.yangbo.common.entity.system.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过用户名查询用户权限集合
     * @param username
     * @return
     */
    List<Menu> findUserPermissions(String username);
}
