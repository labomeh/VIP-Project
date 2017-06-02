package view.JComboBoxModels;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import app.App;
import controller.Vip;
import model.DAOVip;

/**
 *
 * @author Mehdi
 *
 */
public class VipJComboBox extends DefaultComboBoxModel<String> {
	private List<Vip> vipList = new ArrayList<>();
	private DAOVip dao;

	public VipJComboBox() throws SQLException {
		this.dao=App.getDaoVip();
		vipList = dao.getVip();
	}

	@Override
	public String getElementAt(int i) {
		return (vipList.get(i).getName()+" "+vipList.get(i).getSurname()[0]);
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
