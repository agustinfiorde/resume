package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.myresume.web.app.models.ProjectModel;

public class ResumeRestController implements OwnRestController<ProjectModel>{

	@Override
	public ResponseEntity<List<ProjectModel>> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ProjectModel>> getAllAssets(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ProjectModel> getOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
