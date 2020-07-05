package com.myresume.web.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.myresume.web.app.enums.ProjectRoles;

import lombok.Data;

@Data
@Entity
public class Project implements Serializable{

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String name;
	
	@ManyToMany
	private List<Technology> technologies;
	
	@Enumerated(EnumType.STRING)
	private ProjectRoles rol;
	
	@OneToOne
	private Photo photo;
	
	@ManyToOne
	private Company company;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date removed;
	
}
