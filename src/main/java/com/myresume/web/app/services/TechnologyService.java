package com.myresume.web.app.services;

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
import com.myresume.web.app.repository.TechnologyRepository;

@Service
public class TechnologyService {

	@Autowired
	private TechnologyConverter technologyConverter;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private TechnologyRepository technologyRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology save(TechnologyModel model, MultipartFile file) throws WebException {
		Technology technology = technologyConverter.modelToEntity(model);

		technology.setLogo(photoService.save(file));

		if (technology.getLogo() == null) {
			throw new WebException("La Tecnologia debe tener al menos un logo generico");
		}

		if (technology.getName() == null) {
			throw new WebException("La Tecnologia debe tener nombre");
		}
		
		return technologyRepository.save(technology);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology delete(String id) throws WebException {
		Technology technology = technologyRepository.getOne(id);

		return technologyRepository.save(technology);
	}

	public Page<TechnologyModel> listAssets(Pageable paginable, String q) {
		List<TechnologyModel> technologyModels = technologyConverter.entitiesToModels(technologyRepository.searchAssets(paginable, "%" + q + "%").getContent());
		return new PageImpl<>(technologyModels, paginable, technologyModels.size());
	}

	public Page<TechnologyModel> listAssets(Pageable paginable) {
		List<TechnologyModel> technologyModels = technologyConverter.entitiesToModels(technologyRepository.searchAssets(paginable).getContent());
		return new PageImpl<>(technologyModels, paginable, technologyModels.size());
	}

	public List<TechnologyModel> listAssets() {
		return technologyConverter.entitiesToModels(technologyRepository.searchAssets());
	}

	public Technology searchById(String id) {
		return technologyRepository.getOne(id);
	}

}
