package com.myresume.web.app.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Resume extends Audit implements Serializable{

	private static final long serialVersionUID = 6522896498689132123L;

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
	
}
