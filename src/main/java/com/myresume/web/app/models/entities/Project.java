package com.myresume.web.app.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.myresume.web.app.enums.ProjectRole;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Project extends Audit implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	private String name;

	@ManyToMany
	private List<Technology> technologies;

	@Enumerated(EnumType.STRING)
	private ProjectRole rol;
	
	@Lob
	@Column(name = "description", length = 4000)
	private String description;

	@OneToOne
	private Photo photo;

	@ManyToOne
	private Company company;

}
