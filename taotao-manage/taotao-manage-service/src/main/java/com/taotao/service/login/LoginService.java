package com.taotao.service.login;

import com.taotao.bo.UserLoginBO;
import com.taotao.vo.UserLoginVO;
import com.taotao.web.status.LoginStatusEnum;

/**
 * Created by apple on 18/3/2.
 * 登录service层接口
 */
public interface LoginService {

    /**
     * 检查指定用户是否在数据库中
     * @param userLoginBO 指定用户
     * @return 登录状态,成功或失败
     * */
    LoginStatusEnum getUserLoginInDB(UserLoginBO userLoginBO);

    long getIdByUsername(String username);

    long getUserIdByUsername(String username);
}
