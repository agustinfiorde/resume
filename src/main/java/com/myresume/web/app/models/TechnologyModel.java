package com.myresume.web.app.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TechnologyModel {

	private String id;
	
	private PhotoModel logo;

	private String name;
	private Integer percent;

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;
}
