package cn.demo.service1.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service0")
public interface ComputeService {

    @RequestMapping(method = RequestMethod.GET, value = "/add/{a}/{b}")
    Integer add(@PathVariable(value = "a") Integer a, @PathVariable(value = "b") Integer b);
}
