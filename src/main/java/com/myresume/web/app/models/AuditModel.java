package com.myresume.web.app.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
public class AuditModel {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private Date registered;

	private Date edited;

	private Date removed;

}
