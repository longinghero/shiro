package com.longing.demo.shiro.realm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.longing.demo.shiro.model.Permission;
import com.longing.demo.shiro.model.Role;
import com.longing.demo.shiro.model.User;
import com.longing.demo.shiro.service.UserService;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	private Logger logger  = LoggerFactory.getLogger(getClass());

	/**
	 * 授权
	 **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		logger.info("授权开始....");
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		if(user != null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			Collection<String> rolesCollection = new HashSet<String>();
			Collection<String> permissionCollection = new HashSet<String>();
			
			Set<Role> roles = user.getRoles();
			for(Role role:roles){
				Set<Permission> permissions = role.getPermission();
				rolesCollection.add(role.getName());
				for(Permission permission:permissions){
					permissionCollection.add(permission.getUrl());
				}
				info.addStringPermissions(permissionCollection);
			}
			info.addRoles(rolesCollection);
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	
		logger.info("认证开始......");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		User user = userService.findByName(usernamePasswordToken.getUsername());
		
		if(user == null){
			throw new UnknownAccountException();
		}
		
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getName());
		return new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,getName());
	}
	
	public static void main(String[] args){
		
		 String hashAlgorithName = "MD5";
	        String password = "123456";
	        //加密次数
	        int hashIterations = 1024;
	        ByteSource credentialsSalt = ByteSource.Util.bytes("zhangsan");
	        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
	        System.out.println(obj);
	}

}
