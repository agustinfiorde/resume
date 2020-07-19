package com.myresume.web.app.enums;

public enum ProjectRole {
	DEVELOPER("Developer"), TECHNICAL_LEADER("Technical Leader"), COLABORATOR("Colaborator");

	private final String role;

	ProjectRole(String role) {
		this.role = role;

	}

	public String getRole() {
		return role;
	}

}