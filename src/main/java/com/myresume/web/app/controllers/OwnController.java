package com.myresume.web.app.controllers;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.services.UserService;
import com.myresume.web.app.utils.Texts;



public abstract class OwnController {
	protected String listView;
	protected String formView;
	
	@Autowired
	protected UserService userService;

	protected Log log;

	public OwnController(String list, String form) {
		this.listView = list;
		this.formView = form;
		this.log = LogFactory.getLog(getClass());
	}

	public String getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	@SuppressWarnings("rawtypes")
	public String getRol() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Iterator it = auth.getAuthorities().iterator();
		while (it.hasNext()) {
			return it.next().toString();
		}
		return "";
	}

	public boolean isAdmin() {
		return getRol().equals("ROLE_ADMIN");
	}


	public void error(ModelAndView model, Exception e) {
		model.addObject(Texts.ERROR, "Ocurrio un error inesperado mientras se ejecutaba la acción.");
		log.error("Error inesperado", e);
	}

	public void error(ModelAndView model, WebException e) {
		model.addObject(Texts.ERROR, e.getMessage());
	}

	public void error(ModelMap modelo, BindingResult resultado) {
		StringBuilder msg = new StringBuilder();
		for (ObjectError o : resultado.getAllErrors()) {
			msg.append(o.getDefaultMessage() + System.getProperty("line.separator"));
		}
		log.info("Error: " + msg.toString());
		modelo.addAttribute(Texts.ERROR, msg.toString());
	}

	public void error(ModelAndView model, String e) {
		model.addObject(Texts.ERROR, e);
		model.setViewName(formView);
	}

	public void error(Model model, String e) {
		model.addAttribute(Texts.ERROR, e);
	}
	
	public String title(String title,String description) {
		return "<h1>"+title+"\r\n" + 
				"					<small>"+description+"</small>\r\n" + 
				"				</h1>\r\n" + 
				"				<ol class=\"breadcrumb\">\r\n" + 
				"					<li><a href=\"#\">\r\n" + 
				"							<i class=\"fa fa-dashboard\"></i>\r\n" + 
				"							Home\r\n" + 
				"						</a></li>\r\n" + 
				"					<li><a href=\"#\">MyResume</a></li>\r\n" + 
				"					<li class=\"active\">"+title+"</li>\r\n" + 
				"				</ol>";
	}
}
