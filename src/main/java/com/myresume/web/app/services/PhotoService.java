package com.myresume.web.app.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.myresume.web.app.entities.Photo;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.repository.PhotoRepository;

public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;

	@Transactional
	public Photo save(MultipartFile archivo) throws WebException {
		System.out.println(archivo);

		if (archivo != null) {
			try {
				Photo foto = new Photo();
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

	@Transactional
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
}
