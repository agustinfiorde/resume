package com.myresume.web.app.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Company extends Audit implements Serializable{

	private static final long serialVersionUID = 6522896498689132123L;

	private String name;
	private String country;
	private String province;
	
	private Double latitude;
	private Double longitude;

	@Lob
	@Column(name = "description", length = 4000)
	private String description;

}
