package com.myresume.web.app.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myresume.web.app.entities.Photo;
import com.myresume.web.app.enums.Roles;

import lombok.Data;
@Data
public class UserModel {

	private String id;
	
	private String name;
	private String secondName;
	private String lastName;
	private Roles role;
	private String password;
	
	private Photo profilePicture;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBorn;
	private String email;
	private List<String> phones;
	private List<String> citizenships;
	
}
