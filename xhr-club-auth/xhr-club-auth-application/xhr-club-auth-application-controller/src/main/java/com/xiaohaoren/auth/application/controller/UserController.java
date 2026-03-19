package com.xiaohaoren.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.auth.application.convert.AuthUserDTOConverter;
import com.xiaohaoren.auth.application.dto.AuthUserDTO;
import com.xiaohaoren.auth.common.entity.Result;
import com.xiaohaoren.auth.domain.entity.AuthUserBO;
import com.xiaohaoren.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;

    @PostMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        }catch (Exception e){
            log.error("UserController.register.error:{}", e.getMessage());
            return Result.fail("注册失败");
        }

    }
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("UserController.update.error:{}", e.getMessage());
            return Result.fail("更新用户信息失败");
        }

    }

    @PostMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        }catch (Exception e){
            log.error("UserController.delete.error:{}", e.getMessage());
            return Result.fail("删除用户失败");
        }

    }

    @PostMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("UserController.changeStatus.error:{}", e.getMessage());
            return Result.fail("更新用户状态");
        }

    }

    private static void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱不能为空");
    }



    @RequestMapping("/test")
    public String test(){
        return "hello world";
    }
    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.doLogin.validCode:{}", validCode);
            }
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            SaTokenInfo saTokenInfo = authUserDomainService.doLogin(validCode);
            if(saTokenInfo == null){
                return Result.fail("登录失败");
            }
            return Result.ok(saTokenInfo);
        }
        catch (Exception e){
            log.error("UserController.doLogin.error:{}", e.getMessage());
            return Result.fail("登录失败");
        }
    }

    @PostMapping("passwordLogin")
    public Result<SaTokenInfo> passwordLogin(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.passwordLogin.userName:{}", authUserDTO.getUserName());
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "userName can not be blank");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "password can not be blank");
            SaTokenInfo saTokenInfo = authUserDomainService.doLogin(authUserDTO.getUserName(), authUserDTO.getPassword());
            if (saTokenInfo == null) {
                return Result.fail("login failed");
            }
            return Result.ok(saTokenInfo);
        } catch (Exception e) {
            log.error("UserController.passwordLogin.error:{}", e.getMessage(), e);
            return Result.fail("login failed");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 批量获取用户信息
     */
    @RequestMapping("listByIds")
    public Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.listUserInfoByIds.dto:{}", JSON.toJSONString(userNameList));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(userNameList), "id集合不能为空");
            List<AuthUserBO> userInfos = authUserDomainService.listUserInfoByIds(userNameList);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfos));
        } catch (Exception e) {
            log.error("UserController.listUserInfoByIds.error:{}", e.getMessage(), e);
            return Result.fail("批量获取用户信息失败");
        }
    }


    /**
     * 用户退出
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam String userName) {
        try {
            log.info("UserController.logOut.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logOut.error:{}", e.getMessage(), e);
            return Result.fail("用户登出失败");
        }
    }

}
