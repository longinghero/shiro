package com.longing.demo.shiro.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.longing.demo.shiro.realm.UserRealm;



@Configuration
public class ShiroConfig {

	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher(){

		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
	
	@Bean("userRealm")
	public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher matcher){
		
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		
		bean.setSecurityManager(securityManager);
		bean.setSuccessUrl("/main");
		bean.setLoginUrl("/toLogin");
		bean.setUnauthorizedUrl("/error/unAuth");
		
		Map<String,String> filterMap = new LinkedHashMap<String, String>();
		
		filterMap.put("/login","anon");
        filterMap.put("/user/index","authc");
        filterMap.put("/vip/index","roles[vip]");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/static/**","anon");

        filterMap.put("/**","authc");
        filterMap.put("/logout", "logout");
        
        bean.setFilterChainDefinitionMap(filterMap);
		
		return bean;
	}
	
	/**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(HashedCredentialsMatcher hashedCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }
	
}
