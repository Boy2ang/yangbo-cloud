package life.yangbo.auth.service;

import life.yangbo.auth.entity.FebsAuthUser;
import life.yangbo.auth.manager.UserManager;
import life.yangbo.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FebsUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*FebsAuthUser user = new FebsAuthUser();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));

        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));*/

        /**
         * 加入数据库后的写法，从数据库查询用户信息（SystemUser）塞到 Security 要求的 用户对象中（FebsAuthUser）
         * 把SystemUser和FebsAuthUser拆开本意是一个专门用来crud，一个专门用来做授权认证
         */
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus())) {
                notLocked = true;
            }
            // 这个构造方法里面的字段是父亲security的user里面的，把字段填好返回就好了，security会自动做认证处理。
            FebsAuthUser febsAuthUser = new FebsAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            // return transSystemUserToAuthUser(authUser, systemUser);
            // 使用工具拷贝
            BeanUtils.copyProperties(systemUser, febsAuthUser);
            return febsAuthUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

    /**
     * 将systemUser里面的值拷贝到authUser中
     * @param authUser
     * @param systemUser
     * @return
     */
    /*private FebsAuthUser transSystemUserToAuthUser(FebsAuthUser authUser, SystemUser systemUser) {
        authUser.setAvatar(systemUser.getAvatar());
        authUser.setDeptId(systemUser.getDeptId());
        authUser.setDeptName(systemUser.getDeptName());
        authUser.setEmail(systemUser.getEmail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setRoleId(systemUser.getRoleId());
        authUser.setRoleName(systemUser.getRoleName());
        authUser.setSex(systemUser.getSex());
        authUser.setUserId(systemUser.getUserId());
        authUser.setLastLoginTime(systemUser.getLastLoginTime());
        authUser.setDescription(systemUser.getDescription());
        authUser.setStatus(systemUser.getStatus());
        return authUser;
    }*/
}