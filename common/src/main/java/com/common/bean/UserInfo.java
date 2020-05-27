package com.common.bean;


import java.util.List;

public class UserInfo extends BaseBean {


    /**
     * id : 37
     * userName : 李寒磊
     * phoneNo : 13155300303
     * roles : [{"id":8,"roleName":"测试角色1","roleCode":"R15001"}]
     * departmentId : 157
     * departmentName : 针灸科
     * positionId : 66
     * positionName : 医生
     * statusName : 启用中
     * updateUser : 国民老中医
     * updateTime : 2020-01-03 10:42:02
     * email : 
     * headImg : null
     * adeptLabels : null
     * remark : 知名老中医，专治吹牛皮
     * isRemind : 2
     */

    /**
     * 账号唯一编号
     */
    private String accountNo;
    private String id;
    private String userName;
    private String phoneNo;
    private String departmentId;
    private String departmentName;
    private String positionId;
    private String positionName;
    private String statusName;
    private String updateUser;
    private String updateTime;
    private String email;
    private String headImg;
    private List<AdeptLabelBean> adeptLabels;
    private String remark;
    private String isRemind;
    private String tenantName;
    private String videoNum;
    private String serverNum;
    private List<RoleBean> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<AdeptLabelBean> getAdeptLabels() {
        return adeptLabels;
    }

    public void setAdeptLabels(List<AdeptLabelBean> adeptLabels) {
        this.adeptLabels = adeptLabels;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    public List<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBean> roles) {
        this.roles = roles;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(String videoNum) {
        this.videoNum = videoNum;
    }

    public String getServerNum() {
        return serverNum;
    }

    public void setServerNum(String serverNum) {
        this.serverNum = serverNum;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
