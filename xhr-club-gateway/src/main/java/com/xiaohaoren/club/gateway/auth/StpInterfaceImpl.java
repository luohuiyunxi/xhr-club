package com.xiaohaoren.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.nacos.common.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohaoren.club.gateway.entity.AuthPermission;
import com.xiaohaoren.club.gateway.entity.AuthRole;
import com.xiaohaoren.club.gateway.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private final String authPermissionPrefix = "auth.permission";
    private final String authRolePrefix = "auth.role";
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getAuth(loginId, authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId, authRolePrefix);
    }
    public List<String> getAuth(Object loginId, String prefix) {
        // 返回此 loginId 拥有的角色列表
        String authKey = redisUtil.buildKey(prefix, loginId.toString());
        String authKeyValue = redisUtil.get(authKey);
        if(StringUtils.isBlank(authKeyValue)){
            return Collections.emptyList();
        }
        List<String> authList = new LinkedList<>();
        if (authRolePrefix.equals(prefix)) {
            List<AuthRole> roleList = new Gson().fromJson(authKeyValue, new com.google.gson.reflect.TypeToken<List<AuthRole>>() {
            }.getType());
            authList = roleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (authPermissionPrefix.equals(prefix)) {
            List<AuthPermission> permissionList = new Gson().fromJson(authKeyValue, new TypeToken<List<AuthPermission>>() {
            }.getType());
            authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        }
        log.info("authList: {}", authList);
        return authList;
    }


}
