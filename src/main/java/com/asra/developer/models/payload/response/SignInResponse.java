package com.asra.developer.models.payload.response;

public class SignInResponse  {
	private String tokenType = "Bearer";
	private String token;
	private UserInfo userInfo;

	public SignInResponse(String token, UserInfo userInfo ) {
		this.token = token;
		this.userInfo = userInfo;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
