package com.skilldistillery.films.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.films.entities.Actor;
import com.skilldistillery.films.entities.Film;

@Component
public class MVCFilmSiteDAOObject implements MVCFilmSiteDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MVCFilmSiteDAOObject() {

	}

	@Override
	public Film findFilmById(int filmId) {

		Film tempFilm = new Film();
		String user = "student";
		String pw = "student";
		String sqltxt = "SELECT film.*, language.name, category.name FROM film JOIN language ON film.language_id = language.id JOIN film_category ON film.id = film_category.film_id JOIN category ON film_category.category_id = category.id WHERE film.id = ?";

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
				tempFilm.setCategory(filmResult.getString("category.name"));
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

	public Film addFilm(Film film) {

//		// Creating Connection
		Connection conn = null;
		String user = "student";
		String pw = "student";
		try {
//			//Connect to the Driver
			conn = DriverManager.getConnection(URL, user, pw);
			conn.setAutoCommit(false);
//			//SQL Statement
			String sql = "INSERT INTO film (title, language_id, description) VALUES (?,?,?)";
//			//Prepare Statement
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setInt(2, film.getLanguageId());
			stmt.setString(3, film.getDescription());
//			//Set count to the execute statement
			int updateCount = stmt.executeUpdate();
//			//Conditional Logic
			if (updateCount == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int newFilmId = rs.getInt(1);
					film.setId(newFilmId);
				}
				sql = "INSERT INTO film_category (film_id, category_id) VALUES (?,11)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());
				updateCount = stmt.executeUpdate();
			} else {
				film = null;
			}
			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error in rollback");
				}
			}
			throw new RuntimeException("Error inserting this film " + film);
		}
		return film;
	}

	@Override
	public boolean deleteFilm(Film film) {
		String user = "student";
		String pw = "student";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pw);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film_category WHERE film_id = ?"; // Added in case we decide to input Actors later
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			stmt.executeUpdate();
			sql = "DELETE FROM film WHERE film.id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public Film changeFilm(Film film) {

		String user = "student";
		String pw = "student";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pw);
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET film.title = ?, film.description =? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
				stmt.close();
				conn.close();
			} else {
				System.out.println(updateCount);
				conn.rollback();
				stmt.close();
				conn.close();
			}

		} catch (SQLException e) {
			System.err.println("Error during inserts.");
			System.err.println("SQL Error: " + e.getErrorCode() + ": " + e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
		}
		return film;
	}

	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();

		String user = "student";
		String pw = "student";

		// Establish Connection
		Connection conn = DriverManager.getConnection(URL, user, pw);

		// Establish SQL statement
		String sql = "SELECT film.*, language.name, category.name FROM film JOIN language ON film.language_id = language.id JOIN film_category ON film.id = film_category.film_id JOIN category ON film_category.category_id = category.id WHERE film.title LIKE ? OR film.description LIKE ?";

		// Prepare Statement
		PreparedStatement stmt = conn.prepareStatement(sql);

		// Execute
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet rs = stmt.executeQuery();

		// Process Data
		while (rs.next()) {
			Film film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguage(rs.getString("name"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setCategory(rs.getString("category.name"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setActors(findActorsByFilmId(rs.getInt("id")));

			films.add(film);

		}

		stmt.close();
		conn.close();
		return films;

	}

}
