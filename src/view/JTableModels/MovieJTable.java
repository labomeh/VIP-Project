package view.JTableModels;

import javax.swing.table.AbstractTableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Movie;
import controller.Vip;
import model.DAOMovie;

/**
 * 
 * @author Mehdi
 *
 */
public class MovieJTable extends AbstractTableModel {
	private List<Movie> movieList;
	private final String[] columnHeads = { "Visa", "Title", "Release year" };
	private DAOMovie dao;

	public MovieJTable(DAOMovie dao) throws SQLException {
		movieList = new ArrayList<Movie>();
		this.dao = App.getDaoMovie();
		dao.getMovies(movieList);
	}
	
	public List<Movie> getMovieList() {
		return movieList;
	}

	@Override
	public String getColumnName(int col) {
		return columnHeads[col];
	}

	@Override
	public int getRowCount() {
		return movieList.size();
	}

	@Override
	public int getColumnCount() {
		return columnHeads.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return movieList.get(rowIndex).getMovieVisa();
		case 1:
			return movieList.get(rowIndex).getMovieTitle();
		case 2:
			return movieList.get(rowIndex).getReleaseYear();
		}
		return null;
	}
}
