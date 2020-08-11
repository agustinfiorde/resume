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

import com.myresume.web.app.converters.TechnologyConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.models.entities.Technology;
import com.myresume.web.app.repository.PhotoRepository;
import com.myresume.web.app.repository.TechnologyRepository;

@Service
public class TechnologyService implements OwnService<TechnologyModel, Technology>{

	@Autowired
	private TechnologyConverter technologyConverter;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private TechnologyRepository technologyRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology save(TechnologyModel model) throws WebException {

		Technology entity = technologyConverter.modelToEntity(model);

//		if (entity.getLogo() == null) {
//			entity.setLogo(photoService.save(entity.getLogo()));
//		} else {
//			photoRepository.delete(photoRepository.getOne(entity.getLogo().getId()));
//			entity.setLogo(photoService.save(entity.getLogo()));
//		}

		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new WebException("La Tecnologia debe tener nombre valido");
		}

		if (entity.getRegistered() == null) {
			entity.setRegistered(new Date());
		} else {
			entity.setEdited(new Date());
		}

		photoRepository.save(entity.getLogo());
		
		return technologyRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology delete(String id) throws WebException {
		Technology entity = technologyRepository.getOne(id);

		if (entity.getRemoved() == null) {
			entity.setRemoved(new Date());
		}

		return technologyRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology recover(String id) throws WebException {
		Technology entity = technologyRepository.getOne(id);

		if (entity.getRemoved() != null) {
			entity.setRemoved(null);
		}

		return technologyRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TechnologyModel> listAll(Pageable paginable, String q) {
		List<TechnologyModel> models;
		try {
			models = technologyConverter.entitiesToModels(
					technologyRepository.searchAllByPercent(paginable, Integer.parseInt(q)).getContent());
			return new PageImpl<>(models, paginable, models.size());
		} catch (NumberFormatException e) {
			models = technologyConverter
					.entitiesToModels(technologyRepository.searchAll(paginable, "%" + q + "%").getContent());
			return new PageImpl<>(models, paginable, models.size());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TechnologyModel> listAll(Pageable paginable) {
		List<TechnologyModel> models = technologyConverter
				.entitiesToModels(technologyRepository.searchAll(paginable).getContent());
		return new PageImpl<>(models, paginable, models.size());
	}

	@Override
	@Transactional(readOnly = true)
	public List<TechnologyModel> listAssets() {
		return technologyConverter.entitiesToModels(technologyRepository.searchAssets());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Technology> searchById(String id) {
		return technologyRepository.findById(id);
	}

}
