package com.hqyj.maiduoduo.config_shiro;

import com.hqyj.maiduoduo.module.user.entity.User;
import com.hqyj.maiduoduo.module.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author  Jayoung
 * createDate  2020/9/10 0010 9:44
 * version 1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByUsername(username);
        if (user == null){
            throw new UnknownAccountException();
        }
        //交由shiro判断密码

        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
    }
}
