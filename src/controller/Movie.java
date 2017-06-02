/**
 * 
 */
package controller;

/**
 * @author Simon
 *
 */
public class Movie {
	public int movieVisa;
	public String movieTitle;
	public int releaseYear;
	
	public Movie(){
	}

	public Movie(int movieVisa, String movieTitle, int releaseYear) {
		this.movieVisa = movieVisa;
		this.movieTitle = movieTitle;
		this.releaseYear = releaseYear;
	}

	public int getMovieVisa() {
		return movieVisa;
	}

	public void setMovieVisa(int movieVisa) {
		this.movieVisa = movieVisa;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	@Override
	public String toString() {
		return "Movie [Visa=" + movieVisa + ", Title=" + movieTitle + ", Release year=" + releaseYear + "]";
	}

}
