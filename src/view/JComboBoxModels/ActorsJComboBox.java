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
 * @author Simon
 *
 */
public class ActorsJComboBox extends DefaultComboBoxModel<String> {
	private List<Vip> vipList = new ArrayList<>();
	private DAOVip dao;

	public ActorsJComboBox(Movie movie) throws SQLException {
		this.dao = App.getDaoVip();
		vipList = dao.getActorsToAdd(movie);
	}

	@Override
	public String getElementAt(int i) {
		return (vipList.get(i).getName() + " " + vipList.get(i).getSurname()[0]);
	}

	@Override
	public int getSize() {
		return vipList.size();
	}

	@Override
	public void addElement(String string) {
	}

	public int getCurrentId(int i) {
		return vipList.get(i).getIdVip();
	}

}
