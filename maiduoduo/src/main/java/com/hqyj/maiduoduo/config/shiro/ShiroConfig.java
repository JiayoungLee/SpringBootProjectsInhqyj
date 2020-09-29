package com.hqyj.maiduoduo.config_shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author  Jayoung
 * createDate  2020/9/10 0010 9:08
 * version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        return factoryBean;
    }

    @Bean
    // @Qualifier 根据name进行注入，ioc容器中会管理多个Realm，如果使用类型注入，可能导致注入的不是自己想要的Realm
    public SecurityManager getSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();

        //指明加密的次数、名字等参数
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(10);
        matcher.setHashAlgorithmName("Md5");

        userRealm.setCredentialsMatcher(matcher);

        return userRealm;
    }
}
