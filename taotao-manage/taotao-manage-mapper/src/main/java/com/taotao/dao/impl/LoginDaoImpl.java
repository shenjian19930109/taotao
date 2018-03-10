//package com.taotao.dao.impl;
//
//import com.taotao.dao.LoginDao;
//import com.taotao.mapper.UserLoginMapper;
//import com.taotao.po.UserLogin;
//import com.taotao.po.UserLoginExample;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
///**
// * Created by apple on 18/3/3.
// */
//@Repository("loginDao")
//public class LoginDaoImpl implements LoginDao {
//
//    @Autowired
//    UserLoginMapper userLoginMapper;
//
//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//    @Override
//    public UserLogin getUserLoginByUsernameAndPassword(UserLogin userLogin) {
//        UserLoginExample userLoginExample = new UserLoginExample();
//        UserLoginExample.Criteria criteria = userLoginExample.createCriteria();
//        criteria.andUsernameEqualTo(userLogin.getUsername()).andPasswordEqualTo(userLogin.getPassword()).andStatusEqualTo(new Integer(0).byteValue());
//        return userLoginMapper.selectByExample(userLoginExample).get(0);
//    }
//}
