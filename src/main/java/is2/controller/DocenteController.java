package is2.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import is2.domain.Docente;
import is2.repository.CursoDictadoDao;
import is2.repository.DocenteDao;

@Controller
@RequestMapping("/docente")
public class DocenteController {

	@Inject
	CursoDictadoDao cursoDictadoDao;
	
	@Inject
	DocenteDao docenteDao;	

	@Inject
	Validator validator;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("docente/list", "docentes", docenteDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("docente", docenteDao.find(id));
		view.setViewName("docente/details");
		return view;
	}
	
	@RequestMapping("/{id}/cursos.html")
	public ModelAndView cursos(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("docente", docenteDao.find(id));
		view.setViewName("docente/cursos");
		return view;
	}
	
	@RequestMapping("/{id}/alumnos.html")
	public ModelAndView alumnos(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("curso", cursoDictadoDao.find(id));
		view.setViewName("docente/alumnos");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("docente", docenteDao.find(id));
		view.setViewName("docente/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("docente", new Docente());
		view.setViewName("docente/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("docente") @Valid Docente Docente, BindingResult result, SessionStatus status) {
		if (Docente.getId() == null) {
			docenteDao.persist(Docente);
			status.setComplete();
		}
		else {
			docenteDao.merge(Docente);
			status.setComplete();
		}
		return new ModelAndView(result.getErrorCount() > 0 ? "docente/edit" : "redirect:list.html");
//		return new ModelAndView("Docente/save");
	}
}