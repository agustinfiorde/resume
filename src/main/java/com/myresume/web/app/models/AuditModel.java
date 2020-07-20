package com.myresume.web.app.models;

import java.util.Date;

import lombok.Data;

@Data
public class AuditModel {
	
	private String id;

	private Date registered;

	private Date edited;

	private Date removed;

}
