package com.zn.quick.spring.boot.security.service.impl;

import com.zn.quick.spring.boot.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security规范使用的获取用户认证信息.
 *
 * @author zhaona
 * @create 2018/10/15 5:12 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = User.builder().id("1").name("张三").password("123456").build();
    String password = user.getPassword();
    if (!password.startsWith(User.PASSWORD_PREFIX)) {
      password = User.PASSWORD_PREFIX + password;
    }
    user = user.toBuilder().password(password).build();
    return user.buildUserDetails();
  }
}
