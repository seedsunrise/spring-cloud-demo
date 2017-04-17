package cn.demo.service0.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private Long id;

    private String userName;

    private String password;

    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public UserDetails createSecurityUser() {
        List<GrantedAuthority> authList = new ArrayList<>(this.roleList.size());
        for (Role role : roleList) {
            authList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(this.userName, this.password.toLowerCase(), true, true, true, true, authList);
    }
}
