package view;

/**
 * 
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import controller.Vip;
import model.DAOVip;

/**
 * 
 * @author Mehdi
 *
 */
public class VipJTable extends JTable{
	private List<Vip> vipList;
	private final String[] columnHeads = {"Name", "Surname(s)", "Birthdate", "Birthplace", "Role"};
	private DAOVip dao;
	
	public VipJTable(DAOVip dao) throws SQLException{
		vipList = new ArrayList<Vip>();
		this.dao=dao;
		dao.getVip(vipList);
	}
	
	@Override
	public String getColumnName(int col){
		return columnHeads[col];
	}
	
	@Override
	public int getRowCount(){
		return vipList.size();
	}
	
	@Override
	public int getColumnCount(){
		return columnHeads.length;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex){
		switch(columnIndex){
		case 0:
			return vipList.get(rowIndex).getName();
		case 1:
			return vipList.get(rowIndex).getSurname();
		case 2:
			return vipList.get(rowIndex).getBirthdate();
		case 3:
			return vipList.get(rowIndex).getBirthplace();
		case 4:
			return vipList.get(rowIndex).getRoleCode();
		}
		return null;
	}
}
