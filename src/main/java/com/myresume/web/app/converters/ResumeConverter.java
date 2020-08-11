package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.ResumeModel;
import com.myresume.web.app.models.entities.Experience;
import com.myresume.web.app.models.entities.Hobby;
import com.myresume.web.app.models.entities.Photo;
import com.myresume.web.app.models.entities.Resume;
import com.myresume.web.app.models.entities.Skill;
import com.myresume.web.app.repository.ExperienceRepository;
import com.myresume.web.app.repository.HobbyRepository;
import com.myresume.web.app.repository.PhotoRepository;
import com.myresume.web.app.repository.ResumeRepository;
import com.myresume.web.app.repository.SkillRepository;
import com.myresume.web.app.repository.UserRepository;
import com.myresume.web.app.utils.UtilDate;

@Component("ResumeConverter")
public class ResumeConverter extends Converter<ResumeModel, Resume> {

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private SkillConverter skillConverter;

	@Autowired
	private HobbyConverter hobbyConverter;

	@Autowired
	private HobbyRepository hobbyRepository;
	
	@Autowired
	private ExperienceConverter experienceConverter;

	@Autowired
	private ExperienceRepository experienceRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhotoConverter photoConverter;

	@Autowired
	private PhotoRepository photoRepository;

	public ResumeModel entityToModel(Resume entity) {
		ResumeModel model = new ResumeModel();
		try {

			BeanUtils.copyProperties(entity, model);
			model.setCurrentDay(UtilDate.formatFechaGuiones(new Date()));
			
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Tecnologia", e);
		}
		
		if (entity.getUser()!=null) {
			model.setUser(userConverter.entityToModel(userRepository.getOne(entity.getUser().getId())));
		}
		
		StringBuilder selected;
		
		if (entity.getSkills() != null) {
			selected = new StringBuilder();
			for (Skill o : entity.getSkills()) {
				model.getSkills().add(skillConverter.entityToModel(o));
				model.getIdSkills().add(o.getId());
				selected.append(o.getId()).append(",");
			}
			model.setSkillsSelected(selected.toString());
		}
		
		if (entity.getHobbies() != null) {
			selected = new StringBuilder();
			for (Hobby o : entity.getHobbies()) {
				model.getHobbies().add(hobbyConverter.entityToModel(o));
				model.getIdHobbies().add(o.getId());
				selected.append(o.getId()).append(",");
			}
			model.setHobbiesSelected(selected.toString());
		}
		
		if (entity.getExperiences() != null) {
			selected = new StringBuilder();
			for (Experience o : entity.getExperiences()) {
				model.getExperiences().add(experienceConverter.entityToModel(o));
				model.getIdExperiences().add(o.getId());
				selected.append(o.getId()).append(",");
			}
			model.setExperiencesSelected(selected.toString());
		}
		
		if (entity.getPhotos() != null) {
			selected = new StringBuilder();
			for (Photo o : entity.getPhotos()) {
				model.getPhotos().add(photoConverter.entityToModel(o));
				model.getIdPhotos().add(o.getId());
				selected.append(o.getId()).append(",");
			}
			model.setPhotosSelected(selected.toString());
		}

		return model;
	}

	public Resume modelToEntity(ResumeModel model) {
		Resume entity;

		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = resumeRepository.getOne(model.getId());
		} else {
			entity = new Resume();
		}

		try {
			BeanUtils.copyProperties(model, entity);
			
			
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Tecnologia en entity", e);
		}

		return entity;
	}

	public List<ResumeModel> entitiesToModels(List<Resume> entities) {
		List<ResumeModel> models = new ArrayList<>();
		for (Resume a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Resume> modelsToEntities(List<ResumeModel> m) {
		List<Resume> entities = new ArrayList<>();
		for (ResumeModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
