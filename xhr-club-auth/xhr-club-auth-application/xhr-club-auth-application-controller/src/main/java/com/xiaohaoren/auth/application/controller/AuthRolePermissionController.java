package com.xiaohaoren.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.auth.application.convert.AuthPermissionDTOConverter;
import com.xiaohaoren.auth.application.convert.AuthRolePermissionDTOConverter;
import com.xiaohaoren.auth.application.dto.AuthPermissionDTO;
import com.xiaohaoren.auth.application.dto.AuthRolePermissionDTO;
import com.xiaohaoren.auth.common.entity.Result;
import com.xiaohaoren.auth.domain.entity.AuthPermissionBO;
import com.xiaohaoren.auth.domain.entity.AuthRolePermissionBO;
import com.xiaohaoren.auth.domain.service.AuthPermissionDomainService;
import com.xiaohaoren.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class AuthRolePermissionController {
    @Resource
    private AuthRolePermissionDomainService AuthRolePermissionDomainService;
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthRolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限id列表不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
            return Result.ok(AuthRolePermissionDomainService.add(authRolePermissionBO));
        }catch (Exception e){
            log.error("AuthRolePermissionController.add.error:{}", e.getMessage());
            return Result.fail("添加角色权限失败");
        }
    }
}
