package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myresume.web.app.models.HobbyModel;

@RestController
@RequestMapping("/api/hobby")
public class HobbyRestController implements OwnRestController<HobbyModel>{

	@Override
	public ResponseEntity<List<HobbyModel>> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<HobbyModel>> getAllAssets(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<HobbyModel> getOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
