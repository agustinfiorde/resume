package com.myresume.web.app.models;

import java.util.List;

import com.myresume.web.app.entities.Experience;
import com.myresume.web.app.entities.Hobby;
import com.myresume.web.app.entities.Photo;
import com.myresume.web.app.entities.Skill;
import com.myresume.web.app.entities.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResumeModel extends AuditModel {

	private User user;

	private String urlGit;

	private List<Skill> skills;
	private List<String> idSkills;
	private String skillsSelected = "";

	private List<Hobby> hobbies;
	private List<String> idHobbies;
	private String hobbiesSelected = "";

	private List<Experience> experiences;
	private List<String> idExperiences;
	private String experiencesSelected = "";

	private List<Photo> photos;
	private List<String> idPhotos;
	private String photosSelected = "";

	private String description;

}
