package com.myresume.web.app.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.myresume.web.app.models.entities.Company;

import lombok.Data;

@Data
public class ExperienceModel {

	private String id;
	
	private Company company;

	private String bossName;
	private String contactBoss;

	private Date dateFrom;

	private Date dateUntil;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;
	
}
