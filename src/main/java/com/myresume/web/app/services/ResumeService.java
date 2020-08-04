package com.myresume.web.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myresume.web.app.converters.ResumeConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.ResumeModel;
import com.myresume.web.app.models.entities.Resume;
import com.myresume.web.app.repository.ResumeRepository;
import com.myresume.web.app.repository.nosql.ResumeModelRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeConverter resumeConverter;

	@Autowired
	private ResumeRepository resumeRepository;
	
	@Autowired
	private ResumeModelRepository resumeModelRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Resume save(ResumeModel model) throws WebException {

		Resume entity = resumeConverter.modelToEntity(model);

		if (entity.getRegistered() == null) {
			entity.setRegistered(new Date());
		} else {
			entity.setEdited(new Date());
		}

		return resumeRepository.save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Resume delete(String id) throws WebException {
		Resume entity = resumeRepository.getOne(id);

		if (entity.getRemoved() == null) {
			entity.setRemoved(new Date());
		}

		return resumeRepository.save(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Resume recover(String id) throws WebException {
		Resume entity = resumeRepository.getOne(id);

		if (entity.getRemoved() != null) {
			entity.setRemoved(null);
		}

		return resumeRepository.save(entity);
	}

	@Transactional(readOnly = true)
	public Page<ResumeModel> listAll(Pageable paginable, String q) {
		List<ResumeModel> models;
		
			models = resumeConverter.entitiesToModels(resumeRepository.searchAll(paginable, "%" + q + "%").getContent());
			return new PageImpl<>(models, paginable, models.size());
		
	}

	@Transactional(readOnly = true)
	public Page<ResumeModel> listAll(Pageable paginable) {
		List<ResumeModel> models = resumeConverter
				.entitiesToModels(resumeRepository.searchAll(paginable).getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	@Transactional(readOnly = true)
	public List<ResumeModel> listAssets() {
		return resumeConverter.entitiesToModels(resumeRepository.searchAssets());
	}

	@Transactional(readOnly = true)
	public Optional<Resume> searchById(String id) {
		return resumeRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<ResumeModel> searchByIdNoSQL(String id) {
		return resumeModelRepository.findById(id);
	}

}
