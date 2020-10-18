package com.skilldistillery.films.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.films.entities.Actor;
import com.skilldistillery.films.entities.Film;

public interface MVCFilmSiteDAO {

	public Film findFilmById(int filmId);

	public List<Actor> findActorsByFilmId(int filmId);

	public Film addFilm(Film film);

	public boolean deleteFilm(Film film);

	public Film changeFilm(Film film);

	public List<Film> findFilmsByKeyword(String keyword) throws SQLException;

}
