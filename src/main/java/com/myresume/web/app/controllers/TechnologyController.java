package com.myresume.web.app.controllers;

import static com.myresume.web.app.utils.Texts.ACTION_LABEL;
import static com.myresume.web.app.utils.Texts.ERROR;
import static com.myresume.web.app.utils.Texts.ERROR_INESPERADO;
import static com.myresume.web.app.utils.Texts.PAGE_LABEL;
import static com.myresume.web.app.utils.Texts.SAVE_LABEL;
import static com.myresume.web.app.utils.Texts.TECHNOLOGY_LABEL;
import static com.myresume.web.app.utils.Texts.URL_LABEL;
import static com.myresume.web.app.utils.Texts.USER_LABEL;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myresume.web.app.converters.PhotoConverter;
import com.myresume.web.app.converters.TechnologyConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.services.PhotoService;
import com.myresume.web.app.services.TechnologyService;
import com.myresume.web.app.utils.Texts;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_GUEST')")
@RequestMapping("/technology")
public class TechnologyController extends OwnController {

	@Autowired
	private TechnologyService technologyService;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private TechnologyConverter technologyConverter;

	@Autowired
	private PhotoConverter photoConverter;

	public TechnologyController() {
		super("technology-list", "technology-form");
	}

	@GetMapping("/list")
	public ModelAndView toList(HttpSession session, Pageable paginable, @RequestParam(required = false) String q) {
		ModelAndView model = new ModelAndView(listView);

		Page<TechnologyModel> page = null;

		if (q == null || q.isEmpty()) {
			page = technologyService.listAll(paginable);
		} else {
			page = technologyService.listAll(paginable, q);
			model.addObject(Texts.QUERY_LABEL, q);
		}

		model.addObject(PAGE_LABEL, page);

		log.info("METHOD: technology.toList() -- PARAMS: " + paginable);

		model.addObject(URL_LABEL, "/technology/list");
		model.addObject(TECHNOLOGY_LABEL, new TechnologyModel());

		model.addObject("title", title("Technologies", "Use this module to manage Technologies"));
		model.addObject("subTitle", "List of Technologies");

		session.setAttribute(USER_LABEL, userService.authentication(getUser()));

		return model;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public String save(HttpSession session, @Valid @ModelAttribute(TECHNOLOGY_LABEL) TechnologyModel modelE,
			@RequestParam MultipartFile file, BindingResult result, ModelMap model) {
		log.info("METHOD: technology.save -- PARAMETROS: " + modelE);
		try {
			if (result.hasErrors()) {
				error(model, result);
			} else {

				modelE.setLogo(photoService.convertMultipartFileToPhotoModel(file));
				technologyService.save(modelE);
				return "redirect:/technology/list";
			}
		} catch (WebException e) {
			loadModel(model, modelE, "update");
			model.addAttribute(ERROR, "Ocurrió un error al intentar modificar la Tecnologia. " + e.getMessage());
		} catch (Exception e) {
			loadModel(model, modelE, "update");
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar modificar la Tecnologia.");
			log.error(ERROR_INESPERADO, e);
		}
		return formView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/recover")
	public String refrescar(@RequestParam(required = false) String id) {

		try {
			technologyService.recover(id);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return "redirect:/technology/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String delete(@ModelAttribute(TECHNOLOGY_LABEL) TechnologyModel modelE, ModelMap model) {
		log.info("METHOD: technology.delete() -- PARAMETROS: " + modelE);
		model.addAttribute(ACTION_LABEL, "eliminar");
		try {
			technologyService.delete(modelE.getId());
			return "redirect:/technology/list";
		} catch (Exception e) {
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar eliminar la Tecnologia.");
			return formView;
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/form")
	public ModelAndView form(@RequestParam(required = false) String id, @RequestParam(required = false) String action) {

		ModelAndView model = new ModelAndView(formView);

		TechnologyModel modelE = new TechnologyModel();
		if (action == null || action.isEmpty()) {
			action = SAVE_LABEL;
		}

		if (id != null) {
			modelE = technologyConverter.entityToModel(technologyService.searchById(id).get());
		}

		loadModel(model.getModelMap(), modelE, action);

		model.addObject("title", title("Technologies", "Use this module to load a Technology"));
		model.addObject("subTitle", "Load Technology");
		return model;
	}

	private void loadModel(ModelMap modelo, TechnologyModel modelE, String action) {

		modelo.addAttribute(TECHNOLOGY_LABEL, modelE);
		modelo.addAttribute(ACTION_LABEL, action);

	}

}
