package com.skilldistillery.mvcfilmsiteproject.dao;

import com.skilldistillery.film.entities.Film;

public interface MVCFilmSiteDAO {

	public Film findFilmById(int filmId);
}
