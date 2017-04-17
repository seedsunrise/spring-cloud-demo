package cn.demo.service0.security;

import cn.demo.service0.entity.Action;
import cn.demo.service0.entity.Role;
import cn.demo.service0.mybatis.mapper.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        if(resourceMap == null){
            resourceMap = new HashMap<>();
        }
        List<Role> roleList = roleMapper.getAllRole();

        Collection<ConfigAttribute> configAttributeList;
        for (Role role : roleList) {
            ConfigAttribute attribute = new SecurityConfig(role.getRoleName());
            for (Action action : role.getResourceList()) {
                if (resourceMap.containsKey(action.getUrl())) {
                    configAttributeList = resourceMap.get(action.getUrl());
                    configAttributeList.add(attribute);
                    resourceMap.put(action.getUrl(), configAttributeList);
                } else {
                    configAttributeList = new ArrayList<>();
                    configAttributeList.add(attribute);
                    resourceMap.put(action.getUrl(), configAttributeList);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            String resURL = entry.getKey();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
