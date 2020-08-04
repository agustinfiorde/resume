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
import org.springframework.web.multipart.MultipartFile;

import com.myresume.web.app.converters.HobbyConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.HobbyModel;
import com.myresume.web.app.models.entities.Hobby;
import com.myresume.web.app.repository.HobbyRepository;

@Service
public class HobbyService {

	@Autowired
	private HobbyConverter technologyConverter;

	@Autowired
	private HobbyRepository technologyRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Hobby save(HobbyModel model, MultipartFile file) throws WebException {

		Hobby entity = technologyConverter.modelToEntity(model);

		if (entity.getName() == null || entity.getName().isEmpty() || entity.getName().contains("  ")) {
			throw new WebException("La Tecnologia debe tener nombre valido");
		}
		
		if (entity.getDescription() == null || entity.getDescription().isEmpty() || entity.getDescription().contains("  ")) {
			throw new WebException("La Tecnologia debe tener una descripcion valida");
		}

		if (entity.getRegistered() == null) {
			entity.setRegistered(new Date());
		} else {
			entity.setEdited(new Date());
		}

		return technologyRepository.save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Hobby delete(String id) throws WebException {
		Hobby entity = technologyRepository.getOne(id);

		if (entity.getRemoved() == null) {
			entity.setRemoved(new Date());
		}

		return technologyRepository.save(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Hobby recover(String id) throws WebException {
		Hobby entity = technologyRepository.getOne(id);

		if (entity.getRemoved() != null) {
			entity.setRemoved(null);
		}

		return technologyRepository.save(entity);
	}

	public Page<HobbyModel> listAll(Pageable paginable, String q) {
		List<HobbyModel> models = technologyConverter
				.entitiesToModels(technologyRepository.searchAll(paginable, "%" + q + "%").getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	public Page<HobbyModel> listAll(Pageable paginable) {
		List<HobbyModel> models = technologyConverter
				.entitiesToModels(technologyRepository.searchAll(paginable).getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	public List<HobbyModel> listAssets() {
		return technologyConverter.entitiesToModels(technologyRepository.searchAssets());
	}

	public Hobby searchById(String id) {
		return technologyRepository.getOne(id);
	}

}
