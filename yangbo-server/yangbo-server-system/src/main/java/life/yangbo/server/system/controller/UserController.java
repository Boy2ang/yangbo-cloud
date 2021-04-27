package life.yangbo.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import life.yangbo.common.entity.FebsResponse;
import life.yangbo.common.entity.QueryRequest;
import life.yangbo.common.entity.system.SystemUser;
import life.yangbo.common.exception.FebsException;
import life.yangbo.common.utils.FebsUtil;
import life.yangbo.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @Validated 对应普通参数的校验，作用是让@NotBlank注解生效。
 */
@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public FebsResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new FebsResponse().data(dataTable);
    }

    /**
     * @param user
     * @throws FebsException
     * @Valid 对应实体对象传参时的参数校验
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws FebsException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws FebsException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * @param userIds
     * @throws FebsException
     * @NotBlank(message = "{required}" 该注解表示请求参数不能为空，提示信息为{required}占位符里的内容。内容在ValidationMessages.properties（不能为空）
     */
    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
