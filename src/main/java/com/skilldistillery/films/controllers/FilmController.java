package com.skilldistillery.films.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.entities.Film;
import com.skilldistillery.mvcfilmsiteproject.dao.MVCFilmSiteDAO;

@Controller
public class FilmController {
	
	private MVCFilmSiteDAO filmDAO;
	
	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/home.jsp");
		return mv;
	}
	
	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	public ModelAndView findFilmById(int filmId) {
		Film film = filmDAO.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/SingleFilmResult.jsp");
		return mv;
	}
	
	
}