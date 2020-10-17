package com.skilldistillery.films.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.films.dao.MVCFilmSiteDAO;
import com.skilldistillery.films.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private MVCFilmSiteDAO filmDAO;

	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/home.jsp");
		return mv;
	}

	@RequestMapping(path = "searchByFilmId.do", method = RequestMethod.GET)
	public ModelAndView findFilmById(int filmId) {
		Film film = filmDAO.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/SingleFilmResult.jsp");
		return mv;
	}

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(String title, String language, RedirectAttributes redir) {
		Film tempFilm = new Film();
		tempFilm.setTitle(title);
		tempFilm.setLanguage(language);
		filmDAO.addFilm(tempFilm);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", tempFilm);
		mv.setViewName("redirect:filmAdded.do");
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", method = RequestMethod.GET)
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/SingleFilmResult.jsp");
		return mv;
	}
}