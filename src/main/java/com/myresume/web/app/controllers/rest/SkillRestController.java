package com.myresume.web.app.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myresume.web.app.models.ProjectModel;

@RestController
@RequestMapping("/api/skill")
public class SkillRestController implements OwnRestController<ProjectModel>{

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
