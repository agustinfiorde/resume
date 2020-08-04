package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myresume.web.app.models.PhotoModel;

@RestController
@RequestMapping("/api/photo")
public class PhotoRestController implements OwnRestController<PhotoModel>{

	@Override
	public ResponseEntity<List<PhotoModel>> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<PhotoModel>> getAllAssets(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PhotoModel> getOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
