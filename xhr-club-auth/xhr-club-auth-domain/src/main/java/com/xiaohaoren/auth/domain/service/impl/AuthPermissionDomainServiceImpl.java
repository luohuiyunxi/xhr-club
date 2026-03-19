package com.xiaohaoren.auth.domain.service.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohaoren.auth.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.auth.domain.convert.AuthPermissionBOConverter;
import com.xiaohaoren.auth.domain.entity.AuthPermissionBO;
import com.xiaohaoren.auth.domain.redis.RedisUtil;
import com.xiaohaoren.auth.domain.service.AuthPermissionDomainService;
import com.xiaohaoren.auth.infra.basic.entity.AuthPermission;
import com.xiaohaoren.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        log.info("AuthPermissionDomainServiceImpl.add.authPermissionBO:{}", authPermissionBO);
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        int insert = authPermissionService.insert(authPermission);
        return insert > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        log.info("AuthPermissionDomainServiceImpl.update.authPermissionBO:{}", authPermissionBO);
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        int update = authPermissionService.update(authPermission);
        return update > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        log.info("AuthPermissionDomainServiceImpl.delete.authPermissionBO:{}", authPermissionBO);
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int update = authPermissionService.update(authPermission);
        return update > 0;
    }

    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue,
                new TypeToken<List<AuthPermission>>() {
                }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }
}
