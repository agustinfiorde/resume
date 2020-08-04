package com.myresume.web.app.controllers;

import static com.myresume.web.app.utils.Texts.ACTION_LABEL;
import static com.myresume.web.app.utils.Texts.ERROR;
import static com.myresume.web.app.utils.Texts.ERROR_INESPERADO;
import static com.myresume.web.app.utils.Texts.HOBBY_LABEL;
import static com.myresume.web.app.utils.Texts.PAGE_LABEL;
import static com.myresume.web.app.utils.Texts.SAVE_LABEL;
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

import com.myresume.web.app.converters.HobbyConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.HobbyModel;
import com.myresume.web.app.services.HobbyService;
import com.myresume.web.app.utils.Texts;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_GUEST')")
@RequestMapping("/hobby")
public class HobbyController extends OwnController {

	@Autowired
	private HobbyService hobbyService;

	@Autowired
	private HobbyConverter hobbyConverter;

	public HobbyController() {
		super("hobby-list", "hobby-form");
	}

	@GetMapping("/list")
	public ModelAndView toList(HttpSession session, Pageable paginable, @RequestParam(required = false) String q) {
		ModelAndView model = new ModelAndView(listView);

		Page<HobbyModel> page = null;

		if (q == null || q.isEmpty()) {
			page = hobbyService.listAll(paginable);
		} else {
			page = hobbyService.listAll(paginable, q);
			model.addObject(Texts.QUERY_LABEL, q);
		}

		model.addObject(PAGE_LABEL, page);

		log.info("METHOD: hobby.toList() -- PARAMS: " + paginable);

		model.addObject(URL_LABEL, "/hobby/list");
		model.addObject(HOBBY_LABEL, new HobbyModel());

		model.addObject("title", title("Hobbies", "Use this module to manage Hobbies"));
		model.addObject("subTitle", "List of Hobbies");

		session.setAttribute(USER_LABEL, userService.authentication(getUser()));

		return model;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public String save(HttpSession session, @Valid @ModelAttribute(HOBBY_LABEL) HobbyModel modelE,
			@RequestParam MultipartFile file, BindingResult result, ModelMap model) {
		log.info("METHOD: hobby.save -- PARAMETROS: " + modelE);
		try {
			if (result.hasErrors()) {
				error(model, result);
			} else {
				hobbyService.save(modelE, file);
				return "redirect:/hobby/list";
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
			hobbyService.recover(id);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return "redirect:/hobby/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String delete(@ModelAttribute(HOBBY_LABEL) HobbyModel modelE, ModelMap model) {
		log.info("METHOD: hobby.delete() -- PARAMETROS: " + modelE);
		model.addAttribute(ACTION_LABEL, "eliminar");
		try {
			hobbyService.delete(modelE.getId());
			return "redirect:/hobby/list";
		} catch (Exception e) {
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar eliminar la Tecnologia.");
			return formView;
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/form")
	public ModelAndView form(@RequestParam(required = false) String id, @RequestParam(required = false) String action) {
		
		ModelAndView model = new ModelAndView(formView);

		HobbyModel modelE = new HobbyModel();
		if (action == null || action.isEmpty()) {
			action = SAVE_LABEL;
		}

		if (id != null) {
			modelE = hobbyConverter.entityToModel(hobbyService.searchById(id));
		}

		loadModel(model.getModelMap(), modelE, action);

		model.addObject("title", title("Hobbies", "Use this module to load a Hobby"));
		model.addObject("subTitle", "Load Hobby");
		return model;
	}

	private void loadModel(ModelMap modelo, HobbyModel modelE, String action) {

		modelo.addAttribute(HOBBY_LABEL, modelE);
		modelo.addAttribute(ACTION_LABEL, action);

	}

}
