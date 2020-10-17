package com.skilldistillery.films.dao;

import java.util.List;

import com.skilldistillery.films.entities.Actor;
import com.skilldistillery.films.entities.Film;

public interface MVCFilmSiteDAO {

	public Film findFilmById(int filmId);

	public List<Actor> findActorsByFilmId(int filmId);

	public Film addFilm(Film film);

	public String findLanguageById(int languageId);
}
