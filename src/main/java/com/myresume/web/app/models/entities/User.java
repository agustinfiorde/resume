package com.myresume.web.app.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myresume.web.app.enums.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class User extends Audit implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;
	
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
	
}
