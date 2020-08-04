package com.myresume.web.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myresume.web.app.converters.SkillConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.SkillModel;
import com.myresume.web.app.models.entities.Skill;
import com.myresume.web.app.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillConverter skillConverter;

	@Autowired
	private SkillRepository skillRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Skill save(SkillModel model) throws WebException {

		Skill entity = skillConverter.modelToEntity(model);

		if (entity.getName() == null || entity.getName().isEmpty() || entity.getName().contains("  ")) {
			throw new WebException("La habilidad debe tener nombre valido");
		}
		
		if (skillRepository.findByName(entity.getName())!=null && entity.getRegistered()==null) {
			throw new WebException("El nombre de esa habilidad ya esta ocupado");
		}
		
		if (entity.getRegistered() == null) {
			entity.setRegistered(new Date());
		} else {
			entity.setEdited(new Date());
		}

		return skillRepository.save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Skill delete(String id) throws WebException {
		Skill entity = skillRepository.getOne(id);

		if (entity.getRemoved() == null) {
			entity.setRemoved(new Date());
		}

		return skillRepository.save(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Skill recover(String id) throws WebException {
		Skill entity = skillRepository.getOne(id);

		if (entity.getRemoved() != null) {
			entity.setRemoved(null);
		}

		return skillRepository.save(entity);
	}

	public Page<SkillModel> listAll(Pageable paginable, String q) {
		List<SkillModel> models = skillConverter
				.entitiesToModels(skillRepository.searchAll(paginable, "%" + q + "%").getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	public Page<SkillModel> listAll(Pageable paginable) {
		List<SkillModel> models = skillConverter
				.entitiesToModels(skillRepository.searchAll(paginable).getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	public List<SkillModel> listAssets() {
		return skillConverter.entitiesToModels(skillRepository.searchAssets());
	}

	public Skill searchById(String id) {
		return skillRepository.getOne(id);
	}

}
