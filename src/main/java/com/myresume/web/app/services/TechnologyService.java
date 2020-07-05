package com.myresume.web.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	private TechnologyRepository technologyRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology save(TechnologyModel model) throws WebException {
		Technology technology = technologyConverter.modelToEntity(model);

//		if (technology.getEliminado() != null) {
//			throw new WebException("El Obejetivo de Desarrollo Sostenible que intenta modificar se encuentra dada de baja.");
//		}

		return technologyRepository.save(technology);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Technology delete(String id) throws WebException {
		Technology technology = technologyRepository.getOne(id);

		return technologyRepository.save(technology);
	}

	public Page<Technology> listAssets(Pageable paginable, String q) {
		return technologyRepository.searchAssets(paginable, "%" + q + "%");
	}

	public Page<Technology> listAssets(Pageable paginable) {
		return technologyRepository.searchAssets(paginable);
	}
	
	public List<Technology> listAssets() {
		return technologyRepository.searchAssets();
	}
	
	public Technology searchById(String id) {
		return technologyRepository.getOne(id);
	}
		
	
}
