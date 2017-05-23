package view;

import java.sql.SQLException;
import java.util.Arrays;
/**
 * 
 */
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import controller.Country;
import model.DAOCountry;
/**
 * 
 * @author Mehdi
 *
 */
public class CountryJComboBox extends DefaultComboBoxModel<String> {
	private final List<String> countryList;
	
	public CountryJComboBox() throws SQLException{
		this.countryList=DAOCountry.getCountries();
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
	public void addElement(String item){
		countryList.add(item);
	}
	
}
