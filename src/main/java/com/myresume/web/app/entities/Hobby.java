package com.myresume.web.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Hobby implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String name;

	@Lob
	@Column(name = "description", length = 4000)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registered;

	@Temporal(TemporalType.TIMESTAMP)
	private Date edited;

	@Temporal(TemporalType.TIMESTAMP)
	private Date removed;
}
