package com.zn.quick.spring.boot.security.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  public static final String ROLE_USER = "USER_INFO";
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String PASSWORD_PREFIX = "{bcrypt}";

  private final static Collection<? extends GrantedAuthority> defaultAuthorities =
      AuthorityUtils.commaSeparatedStringToAuthorityList(ROLE_USER);


  private String id;

  private String username;

  private String nickname;

  private String password;

  private String email;

  private String name;

  private String sex;

  private String avatar;

  private Set<String> roles = new HashSet<>(16);

  private Boolean enabled = true;

  private Boolean accountNonExpired = true;

  private Boolean credentialsNonExpire = true;

  private Boolean accountNonLocked = true;


  /**
   * 返回一个Spring Security规范的用户信息.
   */
  public org.springframework.security.core.userdetails.User buildUserDetails() {

    Collection<? extends GrantedAuthority> authorities;

    if (this.roles != null && !this.roles.contains(User.ROLE_USER)) {
      // 每个用户都有一个USER_INFO权限
      this.roles.add(User.ROLE_USER);
    }
    authorities = this.buildAuthorities();

    return new org.springframework.security.core.userdetails.User(
        username,
        password,
        enabled,
        accountNonExpired,
        credentialsNonExpire,
        accountNonLocked,
        authorities
    );
  }

  public Collection<? extends GrantedAuthority> buildAuthorities() {
    if (roles == null) {
      return AuthorityUtils.commaSeparatedStringToAuthorityList(ROLE_USER);
    }
    return roles.stream()
        .map(o -> new SimpleGrantedAuthority("ROLE_" + o.toUpperCase()))
        .collect(Collectors.toList());
  }

}