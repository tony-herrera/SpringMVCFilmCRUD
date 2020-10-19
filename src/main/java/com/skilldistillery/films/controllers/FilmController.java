package com.skilldistillery.films.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(path = "searchByKeyword.do", method = RequestMethod.GET)
	public ModelAndView findFilmByKeyword(String keyWord) {
		List<Film> filmList = new ArrayList<>();
		if (keyWord == null || keyWord.equals("")) {
			filmList = null;
		} else {
			try {
				filmList = filmDAO.findFilmsByKeyword(keyWord);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mv = new ModelAndView();

		mv.addObject("filmList", filmList);
		mv.setViewName("WEB-INF/MultiFilmResult.jsp");
		return mv;
	}

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(String title, String description, Integer language, RedirectAttributes redir) {
		Film tempFilm = new Film();
		tempFilm.setTitle(title);
		tempFilm.setDescription(description);
		tempFilm.setLanguageId(language);
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

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Integer id, RedirectAttributes redir) {
		Film tempFilm = new Film();
		ModelAndView mv = new ModelAndView();
		tempFilm.setId(id);
		if (id <= 1000) {
			mv.setViewName("WEB-INF/Undeletable.jsp");
		} else {
			filmDAO.deleteFilm(tempFilm);
			redir.addFlashAttribute("film", tempFilm);
			mv.setViewName("redirect: filmDeleted.do");
		}
		return mv;
	}

	@RequestMapping(path = "filmDeleted.do", method = RequestMethod.GET)
	public ModelAndView deleted() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/SingleFilmResult.jsp");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView changeFilm(Integer filmId, String title, String description) {
		ModelAndView mv = new ModelAndView();
		filmDAO.findFilmById(filmId);
		Film film = filmDAO.findFilmById(filmId);
		if (!title.equals("")) {
			film.setTitle(title);
		}
		if (!description.equals("")) {
			film.setDescription(description);
		}
		filmDAO.changeFilm(film);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/SingleFilmResult.jsp");
		return mv;
	}

	@RequestMapping(path = "redirToUpdate.do", method = RequestMethod.POST)
	public ModelAndView redirToUpdate(Integer filmId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", filmDAO.findFilmById(filmId));
		mv.setViewName("WEB-INF/UpdateFilm.jsp");
		return mv;
	}

}
