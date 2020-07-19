package com.myresume.web.app.controllers;

import static com.myresume.web.app.utils.Texts.ACCION_LABEL;
import static com.myresume.web.app.utils.Texts.ERROR;
import static com.myresume.web.app.utils.Texts.ERROR_INESPERADO;
import static com.myresume.web.app.utils.Texts.GUARDAR_LABEL;
import static com.myresume.web.app.utils.Texts.PAGE_LABEL;
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

import com.myresume.web.app.converters.TechnologyConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.services.TechnologyService;
import com.myresume.web.app.utils.Texts;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/technology")
public class TechnologyController extends OwnController{

	@Autowired
	private TechnologyService technologyService;
	
	@Autowired
	private TechnologyConverter technologyConverter;
	
	public TechnologyController() {
		super("technology-list", "technology-form");
	}

	@GetMapping("/list")
	public ModelAndView toList(HttpSession session, Pageable paginable, @RequestParam(required = false) String q) {
		ModelAndView model = new ModelAndView(listView);
		
		Page<TechnologyModel> page = null;
	
		if (q == null || q.isEmpty()) {
			page = technologyService.listAssets(paginable);
		} else {
			page = technologyService.listAssets(paginable, q);
			model.addObject(Texts.QUERY_LABEL, q);
		}
		
		model.addObject(PAGE_LABEL, page);

		log.info("METODO: technology.toList() -- PARAMS: " + paginable);

		model.addObject(URL_LABEL, "/technology/list");
//		model.addObject(TECHNOLOGY_LABEL, new TechnologyModel());

		model.addObject("title", title("Tecnologias","Utilice este modulo para gestionar las Tecnologias"));
		model.addObject("subTitle", "Listado de Tecnologias");
		
		session.setAttribute(USER_LABEL, userService.authentication(getUser()));
		
		return model;
	}
	
	@PostMapping("/save")
	public String save(HttpSession session, @Valid @ModelAttribute(TECHNOLOGY_LABEL) TechnologyModel modelE, @RequestParam MultipartFile file, BindingResult result, ModelMap model) {
		log.info("METODO: modelE.save -- PARAMETROS: " + modelE);
		try {
			if (result.hasErrors()) {
				error(model, result);
			} else {
				technologyService.save(modelE, file);
				return "redirect:/technology/list";
			}
		} catch (WebException e) {
			loadModel(model, modelE, "update");
			model.addAttribute(ERROR, "Ocurrió un error al intentar modificar la modelE. " + e.getMessage());
		} catch (Exception e) {
			loadModel(model, modelE, "update");
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar modificar la modelE.");
			log.error(ERROR_INESPERADO, e);
		}
		return formView;
	}

	@PostMapping("/reload")
	public String refrescar(HttpSession session, @Valid @ModelAttribute(TECHNOLOGY_LABEL) TechnologyModel modelE, BindingResult result, ModelMap model) {
		log.info("METODO: modelE.guardar -- PARAMETROS: " + modelE);
		loadModel(model, modelE, "actualizar");
		return formView;
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute(TECHNOLOGY_LABEL) TechnologyModel modelE, ModelMap model) {
		log.info("METODO: modelE.eliminar() -- PARAMETROS: " + modelE);
		model.addAttribute(ACCION_LABEL, "eliminar");
		try {
			technologyService.delete(modelE.getId());
			return "redirect:/technology/list";
		} catch (Exception e) {
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar eliminar la modelE.");
			return formView;
		}
	}

	@GetMapping("/form")
	public ModelAndView form(@RequestParam(required = false) String id, @RequestParam(required = false) String action) {
		ModelAndView model = new ModelAndView(formView);
		TechnologyModel modelE = new TechnologyModel();
		if (action == null || action.isEmpty()) {
			action = GUARDAR_LABEL;
		}

		if (id != null) {
			modelE = technologyConverter.entityToModel(technologyService.searchById(id));
		}

		loadModel(model.getModelMap(), modelE, action);

		model.addObject("title", title("Tecnologias","Utilice este modulo para cargar una Tecnología"));
		model.addObject("subTitle", "Cargar Tecnología");
		return model;
	}
	
	private void loadModel(ModelMap modelo, TechnologyModel modelE, String accion) {

		modelo.addAttribute(TECHNOLOGY_LABEL, modelE);
		modelo.addAttribute(ACCION_LABEL, accion);

	}

}
