package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface OwnRestController <M extends Object>{

	@GetMapping("/tolist")
	public ResponseEntity<List<M>> getAllAssets();
	
	@GetMapping("/tolist/{q}")
	public ResponseEntity<List<M>> getAllAssets(@PathVariable String q);

	@GetMapping("/{id}")
	public ResponseEntity<M> getOneById(@PathVariable String id);
	
	
}
