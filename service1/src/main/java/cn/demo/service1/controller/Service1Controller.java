package cn.demo.service1.controller;

import cn.demo.service1.client.Service0Client;
import cn.demo.service1.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1Controller {

    @Autowired
    Service0Client service0Client;
    @Autowired
    private ComputeService computeService;

    @GetMapping("/test/{sleepSec}")
    public String test(@PathVariable int sleepSec) {
        try {
            return service0Client.test("test", sleepSec);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/add/{a}/{b}")
    public Integer add(@PathVariable(value = "a") Integer a, @PathVariable(value = "b") Integer b) {
        return computeService.add(a, b);
    }
}
