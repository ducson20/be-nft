package com.asra.developer.models.payload.response;

import java.util.List;

public class UserInfo {

    private Long userID;
    private String username;
    private String email;
    private String fullName;
    private int age;
    private String gender;
    private List<String> roles;
    private String roleHighest;

    public UserInfo(Long userID, String username, String email, String fullName, int age, String gender, List<String> roles, String roleHighest) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.roles = roles;
        this.roleHighest = roleHighest;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRoleHighest() {
        return roleHighest;
    }

    public void setRoleHighest(String roleHighest) {
        this.roleHighest = roleHighest;
    }
}
