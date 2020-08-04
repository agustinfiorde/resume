package com.myresume.web.app.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.myresume.web.app.enums.ProjectRole;

import lombok.Data;

@Data
public class ProjectModel{

	private String id;
	
	private String name;

	private List<TechnologyModel> technologies;
	private List<String> idTechnologies;
	private String technologiesSelected = "";
	
	private ProjectRole rol;

	private String description;

	private PhotoModel photo;

	private CompanyModel company;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;
	
}
