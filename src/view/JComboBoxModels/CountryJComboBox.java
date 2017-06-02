package view.JComboBoxModels;

import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import app.App;
import model.DAOCountry;

/**
 * 
 * @author Mehdi
 *
 */
public class CountryJComboBox extends DefaultComboBoxModel<String> {
	private List<String> countryList;
	private DAOCountry dao;

	public CountryJComboBox() throws SQLException {
		this.dao = App.getDaoCountry();
		this.countryList = dao.getCountries();
	}

	@Override
	public String getElementAt(int i) {
		return countryList.get(i);
	}

	@Override
	public int getSize() {
		return countryList.size();
	}

	@Override
	public void addElement(String item) {
		countryList.add(item);
	}

}
