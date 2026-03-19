package com.xiaohaoren.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.auth.application.convert.AuthPermissionDTOConverter;
import com.xiaohaoren.auth.application.convert.AuthRoleDTOConverter;
import com.xiaohaoren.auth.application.dto.AuthPermissionDTO;
import com.xiaohaoren.auth.application.dto.AuthRoleDTO;
import com.xiaohaoren.auth.common.entity.Result;
import com.xiaohaoren.auth.domain.entity.AuthPermissionBO;
import com.xiaohaoren.auth.domain.entity.AuthRoleBO;
import com.xiaohaoren.auth.domain.service.AuthPermissionDomainService;
import com.xiaohaoren.auth.domain.service.AuthRoleDomainService;
import com.xiaohaoren.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/permission/")
@Slf4j
public class AuthPermissionController {
    @Resource
    private AuthPermissionDomainService authPermissionDomainService;
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthPermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getPermissionKey()), "权限key不为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "父级权限id不为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e){
            log.error("AuthPermissionController.add.error:{}", e.getMessage());
            return Result.fail("添加权限失败");
        }
    }

    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthPermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e){
            log.error("AuthPermissionController.update.error:{}", e.getMessage());
            return Result.fail("更新权限失败");
        }
    }

    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthPermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e){
            log.error("AuthPermissionController.delete.error:{}", e.getMessage());
            return Result.fail("删除权限失败");
        }
    }
    /**
     * 查询用户权限
     */
    @RequestMapping("getPermission")
    public Result<List<String>> getPermission(String userName) {
        try {
            log.info("PermissionController.getPermission.userName:{}",userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户id不能为空");
            return Result.ok(authPermissionDomainService.getPermission(userName));
        } catch (Exception e) {
            log.error("PermissionController.getPermission.error:{}", e.getMessage(), e);
            return Result.fail("查询用户权限信息失败");
        }
    }
}
