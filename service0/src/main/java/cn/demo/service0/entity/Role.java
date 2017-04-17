package cn.demo.service0.entity;

import java.util.List;

public class Role {
    private Long id;
    private String roleName;
    private List<Action> resourceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Action> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Action> resourceList) {
        this.resourceList = resourceList;
    }
}
