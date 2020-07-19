package com.myresume.web.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Resume implements Serializable{

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@OneToOne
	private User user;
	
	private String urlGit;
	
	@OneToMany
	private List<Skill> skills;
	
	@OneToMany
	private List<Hobby> hobbies;
	
	@OneToMany
	private List<Experience> experiences;
	
	@ManyToMany
	private List<Photo> photos;
		
	@Lob
	@Column(name="description", length=4000)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registered;

	@Temporal(TemporalType.TIMESTAMP)
	private Date edited;

	@Temporal(TemporalType.TIMESTAMP)
	private Date removed;
}
