package com.sdk.mvp.entity;

public class UserInfo {


    /**
     * uid : 51737321
     * upgrade : 1
     * username : df585096063
     * token : 3da7e7243945642d1aaa8bd6352f992a
     * realName :
     * idCard :
     * realname_flag : 0
     * realname_adult :
     * pass_flag : 0
     * bind_mobile :
     * bind_zone :
     * is_register :
     * announcement :
     */

    private String uid;
    private String upgrade;
    private String username;
    private String token;
    private String realName;
    private String idCard;
    private String realname_flag;
    private String realname_adult;
    private String pass_flag;
    private String bind_mobile;
    private String bind_zone;
    private String is_register;
    private String announcement;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealname_flag() {
        return realname_flag;
    }

    public void setRealname_flag(String realname_flag) {
        this.realname_flag = realname_flag;
    }

    public String getRealname_adult() {
        return realname_adult;
    }

    public void setRealname_adult(String realname_adult) {
        this.realname_adult = realname_adult;
    }

    public String getPass_flag() {
        return pass_flag;
    }

    public void setPass_flag(String pass_flag) {
        this.pass_flag = pass_flag;
    }

    public String getBind_mobile() {
        return bind_mobile;
    }

    public void setBind_mobile(String bind_mobile) {
        this.bind_mobile = bind_mobile;
    }

    public String getBind_zone() {
        return bind_zone;
    }

    public void setBind_zone(String bind_zone) {
        this.bind_zone = bind_zone;
    }

    public String getIs_register() {
        return is_register;
    }

    public void setIs_register(String is_register) {
        this.is_register = is_register;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", upgrade='" + upgrade + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", realname_flag='" + realname_flag + '\'' +
                ", realname_adult='" + realname_adult + '\'' +
                ", pass_flag='" + pass_flag + '\'' +
                ", bind_mobile='" + bind_mobile + '\'' +
                ", bind_zone='" + bind_zone + '\'' +
                ", is_register='" + is_register + '\'' +
                ", announcement='" + announcement + '\'' +
                '}';
    }
}
