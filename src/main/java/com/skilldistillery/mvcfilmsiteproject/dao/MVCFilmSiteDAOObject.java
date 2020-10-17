package com.skilldistillery.mvcfilmsiteproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class MVCFilmSiteDAOObject implements MVCFilmSiteDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	
	public MVCFilmSiteDAOObject() {
		
	}
	
	@Override
	public Film findFilmById(int filmId) {
		
		Film tempFilm = new Film();
		String user = "student";
		String pw = "student";
		String sqltxt = "SELECT film.id, film.title, film.description, film.release_year, film.length, film.rating, film.special_features, language.name, category.name FROM film JOIN film_category ON film.id = film_category.film_id JOIN category ON category.id = film_category.category_id JOIN language ON film.language_id = language.id WHERE film.id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				tempFilm.setId(filmResult.getInt("film.id"));
				tempFilm.setTitle(filmResult.getString("film.title"));
				tempFilm.setDescription(filmResult.getString("film.description"));
				tempFilm.setReleaseYear(filmResult.getInt("film.release_year"));
				tempFilm.setLength(filmResult.getInt("film.length"));
				tempFilm.setRating(filmResult.getString("film.rating"));
				tempFilm.setLanguage(filmResult.getString("language.name"));
				tempFilm.setSpecialFeatures(filmResult.getString("film.special_features"));
				tempFilm.setActors(findActorsByFilmId(filmId));
			} else {
				return null;
			}
			stmt.close();
			conn.close();	
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return tempFilm;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		
		List<Actor> tempList = new ArrayList<>();
		Actor tempActor;
		String user = "student";
		String pw = "student";
		String sqltxt = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet resultList = stmt.executeQuery();
			if (resultList == null) {
				return null;
			} else {
				while (resultList.next()) {
					tempActor = new Actor();
					tempActor.setId(resultList.getInt("actor.id"));
					tempActor.setFirstName(resultList.getString("actor.first_name"));
					tempActor.setLastName(resultList.getString("actor.last_name"));
					tempList.add(tempActor);
				}
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tempList;
	}
}
