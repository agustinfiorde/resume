package com.myresume.web.app.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.myresume.web.app.enums.Role;

import lombok.Data;

@Data
public class UserModel {

	private String id;
	
	private String name;
	private String secondName;
	private String lastName;
	
	private String password;
	private String email;

	private Role role;
	
	private PhotoModel profilePicture;

	private Date dateBorn;
	
	private List<String> phones;
	private List<String> citizenships;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;

}
