package com.mall.shop.entity.gen;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class WxUser implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.nickname
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.gender
     *
     * @mbggenerated
     */
    private Integer gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.birthday
     *
     * @mbggenerated
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.register_time
     *
     * @mbggenerated
     */
    private Date registerTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.last_login_time
     *
     * @mbggenerated
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.last_login_ip
     *
     * @mbggenerated
     */
    private String lastLoginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.user_level_id
     *
     * @mbggenerated
     */
    private Integer userLevelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.register_ip
     *
     * @mbggenerated
     */
    private String registerIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.avatar
     *
     * @mbggenerated
     */
    private String avatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_user.weixin_openid
     *
     * @mbggenerated
     */
    private String weixinOpenid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wx_user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.id
     *
     * @return the value of wx_user.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.id
     *
     * @param id the value for wx_user.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.username
     *
     * @return the value of wx_user.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.username
     *
     * @param username the value for wx_user.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.nickname
     *
     * @return the value of wx_user.nickname
     *
     * @mbggenerated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.nickname
     *
     * @param nickname the value for wx_user.nickname
     *
     * @mbggenerated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.password
     *
     * @return the value of wx_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.password
     *
     * @param password the value for wx_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.gender
     *
     * @return the value of wx_user.gender
     *
     * @mbggenerated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.gender
     *
     * @param gender the value for wx_user.gender
     *
     * @mbggenerated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.birthday
     *
     * @return the value of wx_user.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.birthday
     *
     * @param birthday the value for wx_user.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.register_time
     *
     * @return the value of wx_user.register_time
     *
     * @mbggenerated
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.register_time
     *
     * @param registerTime the value for wx_user.register_time
     *
     * @mbggenerated
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.last_login_time
     *
     * @return the value of wx_user.last_login_time
     *
     * @mbggenerated
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.last_login_time
     *
     * @param lastLoginTime the value for wx_user.last_login_time
     *
     * @mbggenerated
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.last_login_ip
     *
     * @return the value of wx_user.last_login_ip
     *
     * @mbggenerated
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.last_login_ip
     *
     * @param lastLoginIp the value for wx_user.last_login_ip
     *
     * @mbggenerated
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.user_level_id
     *
     * @return the value of wx_user.user_level_id
     *
     * @mbggenerated
     */
    public Integer getUserLevelId() {
        return userLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.user_level_id
     *
     * @param userLevelId the value for wx_user.user_level_id
     *
     * @mbggenerated
     */
    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.mobile
     *
     * @return the value of wx_user.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.mobile
     *
     * @param mobile the value for wx_user.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.register_ip
     *
     * @return the value of wx_user.register_ip
     *
     * @mbggenerated
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.register_ip
     *
     * @param registerIp the value for wx_user.register_ip
     *
     * @mbggenerated
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.avatar
     *
     * @return the value of wx_user.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.avatar
     *
     * @param avatar the value for wx_user.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_user.weixin_openid
     *
     * @return the value of wx_user.weixin_openid
     *
     * @mbggenerated
     */
    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_user.weixin_openid
     *
     * @param weixinOpenid the value for wx_user.weixin_openid
     *
     * @mbggenerated
     */
    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid == null ? null : weixinOpenid.trim();
    }
}