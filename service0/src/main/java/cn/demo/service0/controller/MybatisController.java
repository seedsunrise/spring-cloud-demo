package cn.demo.service0.controller;

import cn.demo.service0.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/m/count")
    public Integer count(){
        return userMapper.selectCount();
    }
}
