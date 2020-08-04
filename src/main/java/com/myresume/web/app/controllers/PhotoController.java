package com.myresume.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myresume.web.app.models.entities.Photo;
import com.myresume.web.app.services.PhotoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_GUEST')")
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> photo(@PathVariable String id) {
		Photo photo = photoService.getOne(id);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(photo.getContent(), headers, HttpStatus.OK);
	}
	
}
