package com.myresume.web.app.models;

import java.util.List;

import com.myresume.web.app.enums.ProjectRole;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectModel extends AuditModel{

	private String name;

	private List<TechnologyModel> technologies;
	private List<String> idTechnologies;
	private String technologiesSelected = "";
	
	private ProjectRole rol;

	private String description;

	private PhotoModel photo;

	private CompanyModel company;
	
}
