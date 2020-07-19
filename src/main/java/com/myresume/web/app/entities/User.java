package com.myresume.web.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.myresume.web.app.enums.Role;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String name;
	private String secondName;
	private String lastName;
	private String password;
	
	@OneToOne
	private Photo profilePicture;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBorn;
	private String email;
		
	@ElementCollection
	List<String> phones;
	
	@ElementCollection
	List<String> citizenships;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registered;

	@Temporal(TemporalType.TIMESTAMP)
	private Date edited;

	@Temporal(TemporalType.TIMESTAMP)
	private Date removed;
	
}
