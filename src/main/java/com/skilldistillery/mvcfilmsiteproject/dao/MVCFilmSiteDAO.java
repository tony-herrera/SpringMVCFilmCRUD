package com.skilldistillery.mvcfilmsiteproject.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface MVCFilmSiteDAO {

	public Film findFilmById(int filmId);
	public List<Actor> findActorsByFilmId(int filmId);
}
