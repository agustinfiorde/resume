package com.myresume.web.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TechnologyModel extends AuditModel {

	private PhotoModel logo;

	private String name;
	private Integer percent;

	private String description;
}
