package com.myresume.web.app.controllers;

import static com.myresume.web.app.utils.Texts.ACTION_LABEL;
import static com.myresume.web.app.utils.Texts.ERROR;
import static com.myresume.web.app.utils.Texts.ERROR_INESPERADO;
import static com.myresume.web.app.utils.Texts.PAGE_LABEL;
import static com.myresume.web.app.utils.Texts.SAVE_LABEL;
import static com.myresume.web.app.utils.Texts.SKILL_LABEL;
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
import org.springframework.web.servlet.ModelAndView;

import com.myresume.web.app.converters.SkillConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.SkillModel;
import com.myresume.web.app.services.SkillService;
import com.myresume.web.app.utils.Texts;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_GUEST')")
@RequestMapping("/skill")
public class SkillController extends OwnController {

	@Autowired
	private SkillService skillService;

	@Autowired
	private SkillConverter skillConverter;

	public SkillController() {
		super("skill-list", "skill-form");
	}

	@GetMapping("/list")
	public ModelAndView toList(HttpSession session, Pageable paginable, @RequestParam(required = false) String q) {
		ModelAndView model = new ModelAndView(listView);

		Page<SkillModel> page;

		if (q == null || q.isEmpty()) {
			page = skillService.listAll(paginable);
		} else {
			page = skillService.listAll(paginable, q);
			model.addObject(Texts.QUERY_LABEL, q);
		}

		model.addObject(PAGE_LABEL, page);

		log.info("METHOD: skill.toList() -- PARAMS: " + paginable);

		model.addObject(URL_LABEL, "/skill/list");
		model.addObject(SKILL_LABEL, new SkillModel());

		model.addObject("title", title("Skills", "Use this module to manage Skills"));
		model.addObject("subTitle", "List of Skills");

		session.setAttribute(USER_LABEL, userService.authentication(getUser()));

		return model;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public String save(HttpSession session, @Valid @ModelAttribute(SKILL_LABEL) SkillModel modelE,
			BindingResult result, ModelMap model) {
		log.info("METHOD: skill.save -- PARAMETROS: " + modelE);
		try {
			if (result.hasErrors()) {
				error(model, result);
			} else {
				skillService.save(modelE);
				return "redirect:/skill/list";
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
			skillService.recover(id);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return "redirect:/skill/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete")
	public String delete(@ModelAttribute(SKILL_LABEL) SkillModel modelE, ModelMap model) {
		log.info("METHOD: skill.delete() -- PARAMETROS: " + modelE);
		model.addAttribute(ACTION_LABEL, "delete");
		try {
			skillService.delete(modelE.getId());
			return "redirect:/skill/list";
		} catch (Exception e) {
			model.addAttribute(ERROR, "Ocurrió un error inesperado al intentar eliminar la Tecnologia.");
			return formView;
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/form")
	public ModelAndView form(@RequestParam(required = false) String id, @RequestParam(required = false) String action) {
		
		ModelAndView model = new ModelAndView(formView);

		SkillModel modelE = new SkillModel();
		if (action == null || action.isEmpty()) {
			action = SAVE_LABEL;
		}

		if (id != null) {
			modelE = skillConverter.entityToModel(skillService.searchById(id));
		}

		loadModel(model.getModelMap(), modelE, action);

		model.addObject("title", title("Skills", "Use this module to load a Skill"));
		model.addObject("subTitle", "Load Skill");
		return model;
	}

	private void loadModel(ModelMap modelo, SkillModel modelE, String action) {

		modelo.addAttribute(SKILL_LABEL, modelE);
		modelo.addAttribute(ACTION_LABEL, action);

	}

}
