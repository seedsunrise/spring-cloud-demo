package cn.demo.service0.mybatis.mapper;

import cn.demo.service0.entity.UserInfo;

public interface UserMapper {

    int selectCount();

    UserInfo selectUser(String username);
}
