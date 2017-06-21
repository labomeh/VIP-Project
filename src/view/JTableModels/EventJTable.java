package view.JTableModels;

import javax.swing.table.AbstractTableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Event;
import controller.Vip;
import model.DAOEvent;

public class EventJTable extends AbstractTableModel {
	private List<Event> eventList;
	private final String[] columnHeads = { "Vip 1", "Vip 2", "Wedding date", "Wedding place", "Divorce date" };
	private DAOEvent dao;

	public EventJTable() throws SQLException {
		eventList = new ArrayList<Event>();
		this.dao = App.getDaoEvent();
		eventList = dao.getEvents();
	}

	@Override
	public String getColumnName(int col) {
		return columnHeads[col];
	}

	@Override
	public int getRowCount() {
		return eventList.size();
	}

	@Override
	public int getColumnCount() {
		return columnHeads.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			Vip vip1= App.getDaoVip().getVip(eventList.get(rowIndex).getIdVip1());
			Vip vip2= App.getDaoVip().getVip(eventList.get(rowIndex).getIdVip2());
		
		
		
		switch (columnIndex) {
		case 0:
			return vip1.getSurname()[0]+" "+vip1.getName();
		case 1:
			return vip2.getSurname()[0]+" "+vip2.getName();
		case 2:
			return eventList.get(rowIndex).getWeddingDate();
		case 3:
			return eventList.get(rowIndex).getWeddingPlace();
		case 4:
			return eventList.get(rowIndex).getDivorceDate();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
