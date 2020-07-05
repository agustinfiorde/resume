package com.myresume.web.app.models;

import com.myresume.web.app.entities.Photo;

import lombok.Data;

@Data
public class TechnologyModel {

	private String id;
	
	private Photo logo;
	
	private String name;
	private Integer percent;
	
	private String description;
	
}
