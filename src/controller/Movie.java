/**
 * 
 */
package controller;

/**
 * @author Simon
 *
 */
public class Movie {
	public final int movieVisa;
	public String movieTitle;
	public int releaseYear;
	
	public Movie(int movieVisa, String movieTitle, int releaseYear) {
		this.movieVisa = movieVisa;
		this.movieTitle = movieTitle;
		this.releaseYear = releaseYear;
	}
	
	public int getMovieVisa() {
		return movieVisa;
	}
	public String getMovieTitle() {
		return movieTitle;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	@Override
	public String toString() {
		return "Movie [Visa=" + movieVisa + ", Title=" + movieTitle + ", Release year=" + releaseYear + "]";
	}

	
	
}
