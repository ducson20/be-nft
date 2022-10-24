package com.asra.developer.models.payload.response;

import java.util.List;

public class AccountInfoResponse {

	private Long userID;

	private String username;

	private String fullName;

	private String email;

	private int age;

	private String gender;

	private List<String> roles;

	private String roleHighest;

	public AccountInfoResponse() {

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
