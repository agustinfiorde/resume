package com.myresume.web.app.models;

import java.util.Date;

import com.myresume.web.app.entities.Company;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExperienceModel extends AuditModel{

	private Company company;

	private String bossName;
	private String contactBoss;

	private Date dateFrom;

	private Date dateUntil;

	private String description;

}
