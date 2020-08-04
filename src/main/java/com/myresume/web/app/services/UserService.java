package com.myresume.web.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myresume.web.app.converters.UserConverter;
import com.myresume.web.app.enums.Role;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.UserModel;
import com.myresume.web.app.models.entities.User;
import com.myresume.web.app.repository.UserRepository;
import com.myresume.web.app.utils.UtilDate;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserRepository userRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public User save(UserModel model) throws WebException {
		User user = userConverter.modelToEntity(model);

		if (user.getName() == null || user.getName().isEmpty()) {
			throw new WebException("El nombre del agresor no puede estar vacio.");
		}
		return userRepository.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public User delete(String id) throws WebException {
		User financiamiento = userRepository.getOne(id);

		return financiamiento;
	}

	public UserModel search(String id) {
		User user = userRepository.getOne(id);
		return userConverter.entityToModel(user);
	}

	public User authentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.searchByEmail(auth.getName());
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.searchByEmail(email);
		if (user != null) {
			List<GrantedAuthority> permissions = new ArrayList<>();
			GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
			permissions.add(p);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("user", user);
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					permissions);
		} else {
			return null;
		}
	}

	public User authentication(String email) {
		return userRepository.searchByEmail(email);
	}

	public User lilith() {
		if (userRepository.searchByEmail("agulirio@yahoo.com.ar")==null) {
			User user = new User();
			user.setEmail("agulirio@yahoo.com.ar");
			user.setName("Agustin");
			user.setSecondName("Noe");
			user.setLastName("Fiordelisi");
			user.setDateBorn(UtilDate.parseFechaGuiones("1991-11-01"));
			user.setPassword(new BCryptPasswordEncoder().encode("asdasdasd"));
			user.setRole(Role.ADMIN);
			user.setRegistered(new Date());
			return userRepository.save(user);
		}else {
			return userRepository.searchByEmail("agulirio@yahoo.com.ar");
		}
	}

	public User adam() {
		if (userRepository.searchByEmail("gta_1942@hotmail.com")==null) {
			User user = new User();
			user.setEmail("gta_1942@yahoo.com.ar");
			user.setName("El");
			user.setLastName("Invitado");
			user.setPassword(new BCryptPasswordEncoder().encode("asdasdasd"));
			user.setRole(Role.GUEST);
			user.setRegistered(new Date());
			return userRepository.save(user);
		}else {
			return userRepository.searchByEmail("gta_1942@hotmail.com");
		}
	}

}