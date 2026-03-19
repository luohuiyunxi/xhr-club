package com.xiaohaoren.auth.domain.service.impl;


import com.xiaohaoren.auth.domain.entity.AuthRolePermissionBO;
import com.xiaohaoren.auth.domain.service.AuthRolePermissionDomainService;
import com.xiaohaoren.auth.infra.basic.entity.AuthRolePermission;
import com.xiaohaoren.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        log.info("AuthRolePermissionDomainServiceImpl.add.authRolePermissionBO:{}", authRolePermissionBO);
        LinkedList<AuthRolePermission> list = new LinkedList<>();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(authRolePermissionBO.getRoleId());
            authRolePermission.setPermissionId(permissionId);
            list.add(authRolePermission);
        });
        int insert = authRolePermissionService.batchInsert(list);
        return insert > 0;
    }
}
