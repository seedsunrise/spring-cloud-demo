package cn.demo.service0.controller;

import cn.demo.service0.rabbitmq.SendService;
import cn.demo.service0.rabbitmq.entity.EventPublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {
    private Logger logger = LoggerFactory.getLogger(ComputeController.class);

    @Autowired
    private DiscoveryClient client;
    //    @Value("${from}")
    private String from;
    @Autowired
    private SendService sendService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/add/{a}/{b}")
    private Integer add(@PathVariable(value = "a") Integer a, @PathVariable(value = "b") Integer b) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("test", "123");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @GetMapping(value = "/config")
    private String getConfig() {
        return from;
    }

    @GetMapping(value = "/send/{msg}")
    private void send(@PathVariable(value = "msg") String msg) {
        EventPublish eventPublish = new EventPublish();
        eventPublish.setPayload(msg);
//        sendService.send(eventPublish);
    }
}
