package com.myresume.web.app.converters;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.myresume.web.app.errors.WebException;

public abstract class Converter<M extends Object, E extends Object> {
	
	public abstract E modelToEntity(M m) throws WebException;

	public abstract M entityToModel(E e);
	
	public abstract List<E> modelsToEntities(List<M> m);
	
	public abstract List<M> entitiesToModels(List<E> e);
	
	public abstract JSONObject entityTOJSON(E e);
	
	public abstract JSONArray entitiesTOJSON(List<E> e);

	protected Log log;

	public Converter() {
		this.log = LogFactory.getLog(getClass());
	}

}
