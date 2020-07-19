package com.myresume.web.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HobbyModel extends AuditModel{

	private String name;

	private String description;
	
}
