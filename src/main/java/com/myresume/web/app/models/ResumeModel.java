package com.myresume.web.app.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "resumes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder
public class ResumeModel {

    @NotNull
    @Id
	private String id;
	
    @Field
	private UserModel user;

    @Field
	private String urlGit;

    @Field
	private List<SkillModel> skills;
	private List<String> idSkills;
	private String skillsSelected = "";

	@Field
	private List<HobbyModel> hobbies;
	private List<String> idHobbies;
	private String hobbiesSelected = "";

	@Field
	private List<ExperienceModel> experiences;
	private List<String> idExperiences;
	private String experiencesSelected = "";

	@Field
	private List<PhotoModel> photos;
	private List<String> idPhotos;
	private String photosSelected = "";

	@Field
	private String description;
	
	private String currentDay;
	
	@Field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registered;

	@Field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edited;

	@Field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date removed;

}
