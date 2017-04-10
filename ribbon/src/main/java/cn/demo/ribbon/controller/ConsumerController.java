package cn.demo.ribbon.controller;

import cn.demo.ribbon.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ComputeService computeService;


    @GetMapping(value = "/add")
    public String add(){
        return restTemplate.getForEntity("http://SERVICE0/add/10/20", String.class).getBody();
    }

    @GetMapping(value = "/newAdd")
    public String newAdd(){
        return computeService.addService();
    }
}
