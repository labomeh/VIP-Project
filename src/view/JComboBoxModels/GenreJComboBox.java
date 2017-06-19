package view.JComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import java.sql.SQLException;
import java.util.List;

import model.DAOGenre;

/**
 * 
 * @author Mehdi
 *
 */
public class GenreJComboBox extends DefaultComboBoxModel<String> {
	private final List<String> genreList;

	public GenreJComboBox() throws SQLException {
		this.genreList = DAOGenre.getGenres();
	}

	@Override
	public String getElementAt(int i) {
		return genreList.get(i);
	}

	@Override
	public int getSize() {
		return genreList.size();
	}

	@Override
	public void addElement(String item) {
		genreList.add(item);
	}

}
