package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myresume.web.app.models.TechnologyModel;

@RestController
@RequestMapping("/api/technology")
public class TechnologyRestController implements OwnRestController<TechnologyModel>{

	@Override
	public ResponseEntity<List<TechnologyModel>> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<TechnologyModel>> getAllAssets(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<TechnologyModel> getOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
