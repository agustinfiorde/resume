package com.myresume.web.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.entities.Photo;
import com.myresume.web.app.repository.PhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Photo save(Photo photo) throws WebException {
		
		if (photo != null ) {
			return photoRepository.save(photo);
		} 
		
		return null;
	}

	public Photo convertMultipartFileToPhoto(MultipartFile file) {
		try {
			Photo photo = new Photo();
			photo.setMime(file.getContentType());
			photo.setName(file.getName());
			photo.setContent(file.getBytes());

			return photo;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Photo update(String idPhoto, MultipartFile archivo) throws WebException {
		if (archivo != null) {
			try {
				Photo foto = new Photo();

				if (idPhoto != null) {
					Optional<Photo> respuesta = photoRepository.findById(idPhoto);
					if (respuesta.isPresent()) {
						foto = respuesta.get();
					}
				}

				foto.setMime(archivo.getContentType());
				foto.setName(archivo.getName());
				foto.setContent(archivo.getBytes());

				return photoRepository.save(foto);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		return null;
	}

	public Photo getOne(String id) {
		return photoRepository.getOne(id);
	}
}
