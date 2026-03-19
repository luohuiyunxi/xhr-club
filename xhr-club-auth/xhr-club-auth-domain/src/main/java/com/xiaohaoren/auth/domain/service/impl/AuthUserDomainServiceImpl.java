package com.xiaohaoren.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;

import com.xiaohaoren.auth.common.enums.AuthUserStatusEnum;
import com.xiaohaoren.auth.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.auth.domain.constants.AuthConstant;
import com.xiaohaoren.auth.domain.convert.AuthUserBOConverter;
import com.xiaohaoren.auth.domain.entity.AuthUserBO;
import com.xiaohaoren.auth.domain.redis.RedisUtil;
import com.xiaohaoren.auth.domain.service.AuthUserDomainService;
import com.xiaohaoren.auth.infra.basic.entity.*;
import com.xiaohaoren.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AuthRoleService authRoleService;
    private final String salt = "xiaohaoren";
    private static final String LOGIN_PREFIX = "loginCode";

    private final String authPermissionPrefix = "auth.permission";
    private final String authRolePrefix = "auth.role";
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        //校验用户是否存在
        List<AuthUser> authUsers = authUserService.queryByCondition(existAuthUser);
        if (!CollectionUtils.isEmpty(authUsers)){
            return true;
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        if(!StringUtils.isBlank(authUser.getPassword())){
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        authUser.setAvatar("http://localhost:9000/xhr/d10bedfb1ff89a95be21d834dc32c1f5.jpg");
        Integer count = authUserService.insert(authUser);

//        查询角色
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole authRole1 = authRoleService.queryByCondition(authRole);
        Long authRole1Id = authRole1.getId();
//        建立角色用户关系
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRole1Id);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        String  authRoleKey= redisUtil.buildKey(authRolePrefix, authUserBO.getUserName());
        LinkedList<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole1);
        redisUtil.set(authRoleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(authRole1Id);
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        List<Long> permissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> authPermissionList = authPermissionService.queryByRoleList(permissionIdList);
        String s = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(s, new Gson().toJson(authPermissionList));

        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer update = authUserService.updateByUserName(authUser);
        return update > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer update = authUserService.update(authUser);
        return update > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String buildKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String s = redisUtil.get(buildKey);
        if (StringUtils.isBlank(s)){
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(s);
        this.register(authUserBO);
        StpUtil.login(authUserBO.getUserName());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;
    }

    @Override
    public SaTokenInfo doLogin(String userName, String password) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(userName);
        authUser.setPassword(SaSecureUtil.md5BySalt(password, salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        List<AuthUser> authUserList = authUserService.queryByCondition(authUser);
        if (CollectionUtils.isEmpty(authUserList)) {
            return null;
        }
        StpUtil.login(authUserList.get(0).getUserName());
        return StpUtil.getTokenInfo();
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> userList = authUserService.queryByCondition(authUser);
        if (CollectionUtils.isEmpty(userList)) {
            return new AuthUserBO();
        }
        AuthUser user = userList.get(0);
        return AuthUserBOConverter.INSTANCE.convertEntityToBO(user);
    }

    @Override
    public List<AuthUserBO> listUserInfoByIds(List<String> userNameList) {
        List<AuthUser> userList = authUserService.listUserInfoByIds(userNameList);
        if (CollectionUtils.isEmpty(userList)) {
            return Collections.emptyList();
        }
        return AuthUserBOConverter.INSTANCE.convertEntityToBO(userList);
    }
}
