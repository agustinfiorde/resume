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

import com.myresume.web.app.converters.TechnologyConverter;
import com.myresume.web.app.entities.Technology;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.repository.PhotoRepository;
import com.myresume.web.app.repository.TechnologyRepository;

@Service
public class TechnologyService {

	@Autowired
	private TechnologyConverter technologyConverter;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private TechnologyRepository technologyRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology save(TechnologyModel model, MultipartFile file) throws WebException {

		Technology technology = technologyConverter.modelToEntity(model);

		if (file.getSize() == 0 && technology.getLogo() == null) {
			throw new WebException("La Tecnologia debe tener al menos un logo generico");
		}

		if (file.getSize() != 0 && technology.getLogo() == null) {
			technology.setLogo(photoService.save(photoService.convertMultipartFileToPhoto(file)));
		}

		if (file.getSize() != 0 && technology.getLogo() != null) {
			photoRepository.delete(photoRepository.getOne(technology.getLogo().getId()));
			technology.setLogo(photoService.save(photoService.convertMultipartFileToPhoto(file)));
		}

		if (technology.getName() == null || technology.getName().isEmpty() || technology.getName().contains("  ")) {
			throw new WebException("La Tecnologia debe tener nombre valido");
		}

		if (technology.getRegistered() == null) {
			technology.setRegistered(new Date());
		} else {
			technology.setEdited(new Date());
		}

		return technologyRepository.save(technology);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology delete(String id) throws WebException {
		Technology technology = technologyRepository.getOne(id);

		if (technology.getRemoved() == null) {
			technology.setRemoved(new Date());
		}

		return technologyRepository.save(technology);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology recover(String id) throws WebException {
		Technology technology = technologyRepository.getOne(id);

		if (technology.getRemoved() != null) {
			technology.setRemoved(null);
		}

		return technologyRepository.save(technology);
	}

	public Page<TechnologyModel> listAll(Pageable paginable, String q) {
		List<TechnologyModel> technologyModels = technologyConverter
				.entitiesToModels(technologyRepository.searchAll(paginable, "%" + q + "%").getContent());
		return new PageImpl<>(technologyModels, paginable, technologyModels.size());
	}

	public Page<TechnologyModel> listAll(Pageable paginable) {
		List<TechnologyModel> technologyModels = technologyConverter
				.entitiesToModels(technologyRepository.searchAll(paginable).getContent());
		return new PageImpl<>(technologyModels, paginable, technologyModels.size());
	}

	public List<TechnologyModel> listAssets() {
		return technologyConverter.entitiesToModels(technologyRepository.searchAssets());
	}

	public Technology searchById(String id) {
		return technologyRepository.getOne(id);
	}

}
