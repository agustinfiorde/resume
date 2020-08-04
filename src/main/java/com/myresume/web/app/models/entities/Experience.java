package com.myresume.web.app.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Experience extends Audit implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	@OneToOne
	private Company company;

	private String bossName;
	private String contactBoss;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUntil;
	
	@Lob
	@Column(name = "description", length = 4000)
	private String description;

}
