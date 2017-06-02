package view.JComboBoxModels;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import controller.Vip;
import model.DAOVip;

/**
 * 
 * @author Mehdi
 *
 */
public class VipJComboBox extends DefaultComboBoxModel<Object> {
	private List<Vip> vipList;
	private DAOVip dao;

	public VipJComboBox(DAOVip dao) throws SQLException {
		vipList = new ArrayList<>();
		this.dao=dao;
		vipList = dao.getVip();
	}

	@Override
	public List<Object> getElementAt(int i) {
		List<Object> currentVip = new ArrayList<>();
		currentVip.add(vipList.get(i).getIdVip());
		String[] vipName = {vipList.get(i).getName(), vipList.get(i).getSurname()[0]};
		currentVip.add(vipName);
		return currentVip;
	}

}
