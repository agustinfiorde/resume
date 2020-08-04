package com.myresume.web.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myresume.web.app.errors.WebException;

public interface Service <M extends Object, E extends Object>{
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public abstract E save(M m) throws WebException;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public abstract E delete(String id) throws WebException;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public abstract E recover(String id) throws WebException;
	
	@Transactional(readOnly = true)
	public abstract List<M> listAssets();
	
	@Transactional(readOnly = true)
	public abstract Page<M> listAll(Pageable paginable, String q);

	@Transactional(readOnly = true)
	public abstract Page<M> listAll(Pageable paginable);
	
	@Transactional(readOnly = true)
	public abstract Optional<E> searchById(String id);
	
}
