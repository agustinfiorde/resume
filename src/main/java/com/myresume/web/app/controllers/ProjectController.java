package com.myresume.web.app.controllers;


import static com.myresume.web.app.utils.Texts.PAGE_LABEL;
import static com.myresume.web.app.utils.Texts.PROJECT_LABEL;
import static com.myresume.web.app.utils.Texts.URL_LABEL;
import static com.myresume.web.app.utils.Texts.USER_LABEL;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myresume.web.app.entities.Project;
import com.myresume.web.app.models.ProjectModel;
import com.myresume.web.app.services.ProjectService;
import com.myresume.web.app.utils.Texts;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/project")
public class ProjectController extends OwnController {
	
	@Autowired
	private ProjectService projectService;
	
	public ProjectController() {
		super("project-list", "project-form");
	}

	@GetMapping("/list")
	public ModelAndView toList(HttpSession session, Pageable paginable, @RequestParam(required = false) String q) {
		ModelAndView model = new ModelAndView(listView);
		
		Page<Project> page = null;
		if (q == null || q.isEmpty()) {
			page = projectService.listAssets(paginable);
		} else {
			page = projectService.listAssets(paginable, q);
			model.addObject(Texts.QUERY_LABEL, q);
		}
		
		model.addObject(PAGE_LABEL, page);

		log.info("METODO: project.toList() -- PARAMS: " + paginable);

		model.addObject(URL_LABEL, "/project/list");
		model.addObject(PROJECT_LABEL, new ProjectModel());

		model.addObject("title", title("Proyectos","Utilice este modulo para gestionar los proyectos"));
		session.setAttribute(USER_LABEL, userService.authentication(getUser()));
		
		return model;
	}
}
