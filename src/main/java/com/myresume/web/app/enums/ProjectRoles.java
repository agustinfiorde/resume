package com.myresume.web.app.enums;

public enum ProjectRoles {
	DEVELOPER("Developer"), TECHNICAL_LEADER("Technical Leader"), COLABORATOR("Colaborator");

	private final String role;

	ProjectRoles(String role) {
		this.role = role;

	}

	public String getRole() {
		return role;
	}

}