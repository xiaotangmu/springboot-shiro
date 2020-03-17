package com.tan.springbootshirotest.realm;

import com.tan.springbootshirotest.bean.User;
import com.tan.springbootshirotest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /*
	 * 执行授权逻辑
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User dbUser = userService.getUserById(user.getId());

        info.addStringPermission(dbUser.getPerms());

        return info;
    }

    /*
	 * 执行认证逻辑
	 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //数据库的用户名和密码
        User user = userService.getUserByName(token.getUsername());

        System.out.println(user);
        if( user == null ){
            //用户名不存在
            return null;//shiro 底层会抛出 UnKnowAccountException
        }

        //2. 判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");


//        //编写shiro 判断逻辑，判断用户名和密码
//        //1. 判断用户名
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        if(!token.getUsername().equals(name)){
//            //用户名不存在
//            return null;//shiro 底层会抛出 UnKnowAccountException
//        }
//
//        //2. 判断密码
//        return new SimpleAuthenticationInfo("", password, "");//中间参数为正确密码

    }
}
