package view.JComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Movie;
import controller.Vip;
import model.DAOVip;

/**
 *
 * @author Mehdi
 *
 */
public class NotDirectedMovieJComboBox extends DefaultComboBoxModel<String> {
	private List<Movie> notDirectedMovieList = new ArrayList<>();
	private DAOVip dao;

	public NotDirectedMovieJComboBox(Vip vip) throws SQLException {
		this.dao = App.getDaoVip();
		notDirectedMovieList = dao.getNotDirectedMovies(vip);
		System.out.println(notDirectedMovieList.toString());
	}

	@Override
	public String getElementAt(int i) {
		return (notDirectedMovieList.get(i).getMovieTitle());
	}

	@Override
	public int getSize() {
		return notDirectedMovieList.size();
	}

	@Override
	public void addElement(String string) {
	}

	public int getCurrentId(int i) {
		return notDirectedMovieList.get(i).getMovieVisa();
	}

}
