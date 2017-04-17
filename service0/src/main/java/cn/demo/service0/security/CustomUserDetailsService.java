package cn.demo.service0.security;

import cn.demo.service0.entity.UserInfo;
import cn.demo.service0.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserInfo dbUser = userMapper.selectUser(username);
            return dbUser.createSecurityUser();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }
    }
}
