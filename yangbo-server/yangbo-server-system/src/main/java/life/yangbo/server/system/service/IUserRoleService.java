package life.yangbo.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import life.yangbo.common.entity.system.UserRole;

public interface IUserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String[] roleIds);

	void deleteUserRolesByUserId(String[] userIds);
}
