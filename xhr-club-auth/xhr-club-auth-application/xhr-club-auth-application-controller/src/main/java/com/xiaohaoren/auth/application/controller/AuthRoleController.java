package com.xiaohaoren.auth.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.auth.application.convert.AuthRoleDTOConverter;
import com.xiaohaoren.auth.application.convert.AuthUserDTOConverter;
import com.xiaohaoren.auth.application.dto.AuthRoleDTO;
import com.xiaohaoren.auth.application.dto.AuthUserDTO;
import com.xiaohaoren.auth.common.entity.Result;
import com.xiaohaoren.auth.domain.entity.AuthRoleBO;
import com.xiaohaoren.auth.domain.entity.AuthUserBO;
import com.xiaohaoren.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role/")
@Slf4j
public class AuthRoleController {
    @Resource
    private AuthRoleDomainService authRoleDomainService;
    @PostMapping("add")
    public Result<Boolean> register(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthRoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e){
            log.error("AuthRoleController.add.error:{}", e.getMessage());
            return Result.fail("添加角色失败");
        }
    }

    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthRoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.update(authRoleBO));
        }catch (Exception e){
            log.error("AuthRoleController.update.error:{}", e.getMessage());
            return Result.fail("更新角色失败");
        }
    }

    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("AuthRoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e){
            log.error("AuthRoleController.delete.error:{}", e.getMessage());
            return Result.fail("删除角色失败");
        }
    }
}
