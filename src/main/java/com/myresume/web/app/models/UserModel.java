package com.myresume.web.app.models;

import java.util.Date;
import java.util.List;

import com.myresume.web.app.entities.Photo;
import com.myresume.web.app.enums.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserModel extends AuditModel {

	private String name;
	private String secondName;
	private String lastName;
	private Role role;
	private String password;

	private Photo profilePicture;

	private Date dateBorn;
	private String email;
	private List<String> phones;
	private List<String> citizenships;

}
