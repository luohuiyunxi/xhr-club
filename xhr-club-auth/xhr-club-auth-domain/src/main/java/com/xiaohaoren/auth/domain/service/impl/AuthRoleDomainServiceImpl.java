package com.xiaohaoren.auth.domain.service.impl;


import com.xiaohaoren.auth.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.auth.domain.convert.AuthRoleBOConverter;
import com.xiaohaoren.auth.domain.entity.AuthRoleBO;
import com.xiaohaoren.auth.domain.service.AuthRoleDomainService;
import com.xiaohaoren.auth.infra.basic.entity.AuthRole;
import com.xiaohaoren.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {
    @Resource
    private AuthRoleService authRoleService;


    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        log.info("AuthRoleDomainServiceImpl.add.authRoleBO:{}", authRoleBO);
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        int insert = authRoleService.insert(authRole);
        return insert > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        int update = authRoleService.update(authRole);
        return update > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int delete = authRoleService.update(authRole);
        return delete > 0;
    }
}
