package controller;

/**
 * @author Simon
 *
 */
public class Movie {
	public int movieVisa;
	public String movieTitle;
	public int releaseYear;

	public Movie() {
	}

	public Movie(int movieVisa, String movieTitle, int releaseYear) {
		this.movieVisa = movieVisa;
		this.movieTitle = movieTitle;
		this.releaseYear = releaseYear;
	}

	// Getters
	public int getMovieVisa() {
		return movieVisa;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	// Setters
	public void setMovieVisa(int movieVisa) {
		this.movieVisa = movieVisa;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
}
