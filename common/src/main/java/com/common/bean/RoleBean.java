package com.common.bean;

public class RoleBean extends BaseBean {
    /**
     * id : 8
     * roleName : 测试角色1
     * roleCode : R15001
     */

    private int id;
    private String roleName;
    private String roleCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}