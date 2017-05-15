/**
 * 
 */
package controller;

/**
 * @author Simon
 *
 */
public class Genre {
	public String genreTitle;

	public Genre(String genreTitle) {
		this.genreTitle = genreTitle;
	}

	public String getGenreTitle() {
		return genreTitle;
	}

	public void setGenreTitle(String genreTitle) {
		this.genreTitle = genreTitle;
	}

	@Override
	public String toString() {
		return "Genre [Title=" + genreTitle + "]";
	}

}
