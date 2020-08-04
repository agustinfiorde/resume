package com.myresume.web.app.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PhotoModel {

	private String id;
	
	private String name;
	private String mime;

	private byte[] content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;

}
