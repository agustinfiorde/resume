package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.entities.Company;
import com.myresume.web.app.models.CompanyModel;
import com.myresume.web.app.repository.CompanyRepository;

@Component("CompanyConverter")
public class CompanyConverter extends Converter<CompanyModel, Company> {

	@Autowired
	private CompanyRepository userRepository;
	

	public CompanyModel entityToModel(Company entity) {
		CompanyModel model = new CompanyModel();
		try {
			BeanUtils.copyProperties(entity, model);

			
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de Comañia", e);
		}

		return model;
	}

	public Company modelToEntity(CompanyModel model) {
		Company entity = new Company();
				
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = userRepository.getOne(model.getId());
		}
		
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de Compañia en entity", e);
		}

		return entity;
	}

	public List<CompanyModel> entitiesToModels(List<Company> entities) {
		List<CompanyModel> models = new ArrayList<>();
		for (Company a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Company> modelsToEntities(List<CompanyModel> m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject entityTOJSON(Company e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray entitiesTOJSON(List<Company> e) {
		// TODO Auto-generated method stub
		return null;
	}

}
