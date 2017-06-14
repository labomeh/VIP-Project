package view.JComboBoxModels;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import app.App;
import controller.Movie;
import controller.Vip;
import model.DAOVip;

/**
 *
 * @author Mehdi
 *
 */
public class NotPlayedMovieJComboBox extends DefaultComboBoxModel<String> {
	private List<Movie> notPlayedMovieList = new ArrayList<>();
	private DAOVip dao;

	public NotPlayedMovieJComboBox(Vip vip) throws SQLException {
		this.dao=App.getDaoVip();
		notPlayedMovieList = dao.getVipNotPlayedMovies(vip);
		System.out.println(notPlayedMovieList.toString());
	}

	@Override
	public String getElementAt(int i) {
		return (notPlayedMovieList.get(i).getMovieTitle());
	}
	
	@Override
	public int getSize() {
		return notPlayedMovieList.size();
	}
	
	@Override
	public void addElement(String string) {
	}
	
	public int getCurrentId(int i) {
		return notPlayedMovieList.get(i).getMovieVisa();
	}

}
