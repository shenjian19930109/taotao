package com.taotao.service.login.impl;

import com.taotao.bo.UserLoginBO;
import com.taotao.mapper.UserLoginMapper;
import com.taotao.po.UserLogin;
import com.taotao.po.UserLoginExample;
import com.taotao.service.login.LoginService;
import com.taotao.web.status.LoginStatusEnum;
import com.taotao.web.status.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by apple on 18/3/2.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    // 日志工具
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * 获取登录状态
     * @param userLoginBO 用户登录BO
     * @return 用户登录状态枚举
     * @throws IllegalArgumentException 当入参userLoginBO为null,或userLoginBO中属性username或password为空时.
     * */
    @Override
    public LoginStatusEnum getUserLoginInDB(UserLoginBO userLoginBO) {

        // 检查参数有效性
        try {
            checkUserLoginBO(userLoginBO);
        } catch (IllegalArgumentException e) {
            LOG.error("LoginServiceImpl类的getUserLoginInDB方法参数userLoginBO异常" + e);
            e.printStackTrace();
        }

        // 返回查询结果
        UserLoginExample userLoginExample = new UserLoginExample();
        UserLoginExample.Criteria criteria = userLoginExample.createCriteria();
        criteria.andUsernameEqualTo(userLoginBO.getUserName()).andPasswordEqualTo(userLoginBO.getPassWord())
                .andStatusEqualTo(new Integer(0).byteValue());

        List<UserLogin> userLogins = userLoginMapper.selectByExample(userLoginExample);

        if (userLogins.size() == 0) {
            return LoginStatusEnum.FAIL;
        }else {
            UserLogin userLoginInDB = userLoginMapper.selectByExample(userLoginExample).get(0);
            return transferUserLoginToLoginStatusEnum(userLoginInDB);
        }
    }

    /**
     * 校验用户登录参数
     * @param userLoginBO 用户登录BO
     * @throws IllegalArgumentException 当入参userLoginBO为null,或userLoginBO中属性username或password为空时.
     * */
    private void checkUserLoginBO(UserLoginBO userLoginBO) throws IllegalArgumentException{
        if (userLoginBO == null || userLoginBO.getUserName().trim().isEmpty() || userLoginBO.getPassWord().trim().isEmpty()) {
            throw new IllegalArgumentException("LoginServiceImpl类的getUserLoginInDB方法参数userLoginBO异常");
        }
    }

    /**
     * 将用户登录PO转化为登录状态枚举
     * @param userLoginInDB 用户登录PO
     * @return 用户登录状态枚举
     * */
    private LoginStatusEnum transferUserLoginToLoginStatusEnum(UserLogin userLoginInDB) {
        LoginStatusEnum loginStatusEnum = LoginStatusEnum.SUCCESS;
        loginStatusEnum.setUserName(userLoginInDB.getUsername());
        loginStatusEnum.setPassWord(userLoginInDB.getPassword());
        int roleTypeInt = userLoginInDB.getRoleType().intValue();
        if (roleTypeInt == 0) {
            loginStatusEnum.setRoleType(RoleType.BUYER);
        }else {
            loginStatusEnum.setRoleType(RoleType.SELLER);
        }
        return loginStatusEnum;
    }
}
