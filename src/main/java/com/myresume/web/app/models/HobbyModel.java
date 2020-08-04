package com.myresume.web.app.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HobbyModel{

	private String id;

	private String name;

	private PhotoModel photo;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;
	
}
