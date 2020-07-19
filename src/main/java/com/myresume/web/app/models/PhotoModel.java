package com.myresume.web.app.models;

import java.util.Date;

import lombok.Data;

@Data
public class PhotoModel {

	private String id;
	
	private String name;
	private String mime;

	private byte[] content;
	
	private Date registered;

	private Date edited;

	private Date removed;

}
